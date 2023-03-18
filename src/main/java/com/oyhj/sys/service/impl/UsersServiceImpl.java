package com.oyhj.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oyhj.sys.entity.Users;

import com.oyhj.sys.mapper.UsersMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oyhj.sys.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-09
 */
 @Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, Object> login(Users user) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,user.getUsername());
        Users loginUser = this.baseMapper.selectOne(wrapper);
        if (loginUser!=null&& passwordEncoder.matches(user.getPassword(),loginUser.getPassword())) {
            //生成token，存入redis
         String key = "user:"+UUID.randomUUID();
           loginUser.setPassword(null);
           redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
          //返回数据
          Map<String,Object> data =new HashMap<>();
          data.put("token",key);
            return data;
        }
        return null;
   }


//    @Override
//    public Map<String, Object> login(User user) {
//      //更具用户名密码查询
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getUsername,user.getUsername());
//        wrapper.eq(User::getPassword,user.getPassword());
//        User loginUser = this.baseMapper.selectOne(wrapper);
//        if (loginUser!=null) {
//            //生成token，存入redis
//            String key = "user:"+UUID.randomUUID();
//            loginUser.setPassword(null);
//            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
//
//            //返回数据
//            Map<String,Object> data =new HashMap<>();
//            data.put("token",key);
//            return data;
//        }
//        return null;
//    }

   @Override
    public Map<String, Object> getUserInfo(String token) {
        //
        Object obj= redisTemplate.opsForValue().get(token);
        if (obj!=null) {
            Users user = JSON.parseObject(JSON.toJSONString(obj), Users.class);
            Map<String,Object>data =new HashMap<>();
            data.put("name", user.getUsername());
            data.put("userId", user.getUserId());
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

}
