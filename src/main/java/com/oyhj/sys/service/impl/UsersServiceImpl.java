package com.oyhj.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oyhj.common.jwtUnit.JwtUtil;
import com.oyhj.sys.entity.*;

import com.oyhj.sys.mapper.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oyhj.sys.service.IMenuService;
import com.oyhj.sys.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oyhj
 * @since 2023-03-09
 */
 @Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private DepUserMapper  depUserMapper;
    @Resource
    private PostDepUserMapper postDepUserMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private PostMapper postMapper;
    @Autowired
    private IMenuService menuService;
    @Override
    public Map<String, Object> login(Users user) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        Map<String,Object> data = null;
        wrapper.eq(Users::getUsername,user.getUsername());
        Users loginUser = this.baseMapper.selectOne(wrapper);
        System.out.println("*************/"+(loginUser.getIsuse() == 0));
        if (+loginUser.getIsuse()==0) {
            data =new HashMap<>();
            data.put("ban",user);
            return data;
        }
        if (loginUser!=null&& passwordEncoder.matches(user.getPassword(),loginUser.getPassword())) {
            //生成token，存入redis
       //  String key = "user:"+UUID.randomUUID();
           loginUser.setPassword(null);
         //  redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
            String token = jwtUtil.createToken(loginUser);
            //返回数据
          data =new HashMap<>();
          data.put("token",token);
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
        //Object obj= redisTemplate.opsForValue().get(token);
       Users loginuser=null;
       try {
          loginuser = jwtUtil.parseToken(token, Users.class);
       } catch (Exception e) {
           e.printStackTrace();
       }
       if (loginuser!=null) {
          //  Users user = JSON.parseObject(JSON.toJSONString(obj), Users.class);
            Map<String,Object>data =new HashMap<>();
            data.put("name", loginuser.getUsername());
            data.put("userId", loginuser.getUserId());

           // 角色
          List<String> roleList = this.baseMapper.getRoleNameByUserId(loginuser.getUserId());
       //   List<Integer> depIdList=this.baseMapper.g(loginuser.getUserId());
         // List<Integer> postIdList;
           // 权限列表
           List<Menu> menuList = menuService.getMenuListByUserId(loginuser.getUserId());
           data.put("menuList",menuList);

           return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {

        //redisTemplate.delete(token);
    }

    @Override
    @Transactional
    public void addUser(Users user) {
        baseMapper.insert(user);
        List<Integer> roleList = user.getRoleIdList();
        List<Integer> depList = user.getDepIdList();
        List<Integer>postList = user.getPostIdList();
        if (roleList!=null) {

                userRoleMapper.insert(new UserRole(null,user.getUserId(),roleMapper.selectById(roleList.get(0)).getId()));
            }
        if (depList!=null) {

            userRoleMapper.insert(new UserRole(null,user.getUserId(),departmentMapper.selectById(depList.get(0)).getId()));
        }

        if (postList!=null) {
            userRoleMapper.insert(new UserRole(null,user.getUserId(),postMapper.selectById(postList.get(0)).getPostId()));
        }


    }

    @Override
    public Users getUserById(Integer userId) {
        Users users = this.baseMapper.selectById(userId);
        LambdaQueryWrapper<UserRole> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(UserRole::getUserid,userId);
        List<UserRole> userRoles = userRoleMapper.selectList(objectLambdaQueryWrapper);
        List<Integer> roleIdList =userRoles.stream().map(ur->{
            return ur.getRoleid();})
                .collect(Collectors.toList());
        users.setRoleIdList(roleIdList);

        LambdaQueryWrapper<DepUser> objectLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper1.eq(DepUser::getUserid,userId);
        List<DepUser> depUsers = depUserMapper.selectList(objectLambdaQueryWrapper1);
        List<Integer> depIdList =depUsers.stream().map(ur->{
            return ur.getDepid();})
                .collect(Collectors.toList());
        users.setDepIdList(depIdList);

        LambdaQueryWrapper<PostDepUser> objectLambdaQueryWrapper2= new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper2.eq(PostDepUser::getUserid,userId);

        List<PostDepUser>postDepUsers = postDepUserMapper.selectList(objectLambdaQueryWrapper2);
        List<Integer> postIdList =postDepUsers.stream().map(ur->{
            return ur.getPostid();})
                .collect(Collectors.toList());
        users.setPostIdList(postIdList);

        return users;
    }

    @Override
    @Transactional
    public void updateUser(Users user) {
        // 更新用户表
        this.baseMapper.updateById(user);
        // 清除原有角色
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserid,user.getUserId());
        userRoleMapper.delete(wrapper);

        LambdaQueryWrapper<DepUser> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(DepUser::getUserid,user.getUserId());
        depUserMapper.delete(wrapper1);

        LambdaQueryWrapper<PostDepUser> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(PostDepUser::getUserid,user.getUserId());
        postDepUserMapper.delete(wrapper2);

        // 设置新的角色
        List<Integer> roleIdList = user.getRoleIdList();
        List<Integer> depIdList = user.getDepIdList();
        List<Integer> postIdList = user.getPostIdList();
        if(roleIdList != null){
            for (Integer roleId : roleIdList) {
                userRoleMapper.insert(new UserRole(null,user.getUserId(),roleId));
            }
        }
        if(depIdList != null){
            for (Integer depId : depIdList) {
                depUserMapper.insert(new DepUser(null,depId,user.getUserId()));
            }
        }
        if(roleIdList != null){
            for (Integer postId : postIdList) {
                postDepUserMapper.insert(new PostDepUser(null,user.getUserId(),postId,null));
            }
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        this.baseMapper.deleteById(id);
        // 清除原有角色
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserid,id);
        userRoleMapper.delete(wrapper);
        LambdaQueryWrapper<DepUser> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(DepUser::getUserid,id);
        depUserMapper.delete(wrapper1);
        LambdaQueryWrapper<PostDepUser> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(PostDepUser::getUserid,id);
    }

}
