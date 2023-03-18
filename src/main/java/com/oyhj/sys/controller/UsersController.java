package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;


import com.oyhj.sys.entity.Users;
import com.oyhj.sys.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-09
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@ResponseBody
public class UsersController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/all")
    public Result<List<Users>> getAllUser(){
        List<Users> list =usersService.list();
        return Result.success(list,"查询成功");
    }
    @PostMapping("/login")
    private Result<Map<String, Object>> login(@RequestBody Users user){

        Map<String,Object>data=usersService.login(user);
        System.out.println(user.toString());
        if(data!=null){

            return  Result.success(data);
        }
       else return Result.fail(20002,"用户名或密码错误");
    }

    @GetMapping("/info")
    public  Result<Map<String, Object>> getUserInfo( @RequestParam("token") String token){
        //根据token获取用户信息
        Map<String,Object> data =usersService.getUserInfo(token);

        if(data!=null){
            return  Result.success(data);
        }
        else return Result.fail(20003,"登录信息无效，请重新登录！");

    }
    @PostMapping("/logout")
    public  Result<?> logOut(@RequestHeader("X-Token") String token){
        usersService.logout(token);
        return Result.success();
    }

    @GetMapping("/list")
    public  Result<Map<String,Object>> getUserList(@RequestParam(value = "username",required = false) String username,
                                          @RequestParam(value = "phone",required = false) String phone,
    @RequestParam(value = "pageNo") Long pageNo,  @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Users> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(StringUtils.hasLength(username),Users::getUsername,username);
        objectLambdaQueryWrapper.eq(StringUtils.hasLength(phone),Users::getUserId,username);

        Page<Users> page =new Page<>(pageNo,pageSize);
        usersService.page(page,objectLambdaQueryWrapper);
        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addUser(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersService.save(user);
        return Result.success("新增用户成功！");
    }
    @PutMapping
    public Result<?> updateUser(@RequestBody Users user){
        user.setPassword(null);
        usersService.updateById(user);
        return Result.success("修改用户成功！");
    }
   // @RequestMapping("/{userId}")
    @GetMapping("/{userId}")
    public Result<?> getUserById(@PathVariable("userId") Integer userId){
        System.out.println(userId);
        Users user = usersService.getById(userId);
        System.out.println(user.toString());
        return Result.success(user);
    }
}
