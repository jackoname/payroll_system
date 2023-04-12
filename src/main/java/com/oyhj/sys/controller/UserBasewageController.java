package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.UserBasewage;
import com.oyhj.sys.entity.UserRole;
import com.oyhj.sys.entity.Users;
import com.oyhj.sys.mapper.RoleMapper;
import com.oyhj.sys.mapper.UserBasewageMapper;
import com.oyhj.sys.mapper.UsersMapper;
import com.oyhj.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-10
 */
@RestController
@RequestMapping("/userBasewage")
public class UserBasewageController {
    @Resource
    UsersMapper usersMapper;
    @Autowired
    IUsersService usersService;
    @Autowired
    IUserBasewageService userBasewageService;
    @Resource
    UserBasewageMapper userBasewageMapper;
   @Autowired
   IPostService postService;
   @Autowired
    IDepartmentService departmentService;
    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "userId",required = false) Integer userId,
                                                  @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<UserBasewage> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<UserBasewage> page =new Page<>(pageNo,pageSize);
        if (Objects.nonNull(userId)) {
            objectLambdaQueryWrapper.eq(UserBasewage::getUserid,userId);
            List<UserBasewage> list = userBasewageService.list(objectLambdaQueryWrapper);
            List<UserBasewage> Re=new ArrayList<>();
            list.forEach(item->{
                if ((item.getVersion()==1)) {
                    Re.add(item);
                }
            });
            page.setRecords(Re);
            page.setSize(pageSize);
            page.setTotal(Re.size());
            page.setPages(pageNo);
        }
        else {
            objectLambdaQueryWrapper.eq(UserBasewage::getVersion,1);
            page.setRecords(userBasewageService.list(objectLambdaQueryWrapper));
        page.setTotal(userBasewageService.list().size());
        }
        if(page.getRecords().size()>0) {
            page.getRecords().forEach(item -> {
                item.setName(usersService.getById(item.getUserid()).getName());
                item.setUsername(usersService.getById(item.getUserid()).getUsername());
                item.setPost_name(postService.getById(usersService.getById(item.getUserid()).getPostid()).getPostName());
                item.setDepart(departmentService.getById(usersService.getById(item.getUserid()).getDepid()).getDepart());
            });
        }
        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addUserwage(@RequestBody UserBasewage userBasewage){

        LambdaQueryWrapper<UserBasewage>userBasewageLambdaQueryWrapper =new LambdaQueryWrapper<>();
        userBasewageLambdaQueryWrapper.eq(UserBasewage::getUserid,userBasewage.getUserid());
        List<UserBasewage> list = userBasewageService.list(userBasewageLambdaQueryWrapper);
        if (list.size()>0){
            list.forEach(one->{
                one.setVersion(0);
                one.setDsc("工资更新替换");
                userBasewageService.updateById(one);
            });
        }
        userBasewage.setVersion(1);
        userBasewageService.save(userBasewage);
       // usersService.addUser(user);

        return Result.success("为"+userBasewage.getUsername()+" 添加基础工资成功!");
    }
    @DeleteMapping("/{userId}")
    public Result<?> DeleteUserById(@PathVariable("userId") Integer userId){
        LambdaQueryWrapper<UserBasewage>userBasewageLambdaQueryWrapper =new LambdaQueryWrapper<>();
        userBasewageLambdaQueryWrapper.eq(UserBasewage::getUserid,userId);
        List<UserBasewage> list = userBasewageService.list(userBasewageLambdaQueryWrapper);
        list.forEach(item->{
            if ((item.getVersion()==1)) {
                item.setDsc("工资删除");
                item.setVersion(-1);
                userBasewageService.updateById(item);
            }
        });
        return Result.success("删除成功");
    }

    @GetMapping("/{userId}")
    public Result<?> getUserWageById(@PathVariable("userId") Integer userId){
        LambdaQueryWrapper<UserBasewage>userBasewageLambdaQueryWrapper =new LambdaQueryWrapper<>();
        userBasewageLambdaQueryWrapper.eq(UserBasewage::getUserid,userId);
        List<UserBasewage> list = userBasewageService.list(userBasewageLambdaQueryWrapper);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVersion()==1){
                return Result.success(list.get(i));
            }
        }

        return Result.fail("error");
    }
    @PutMapping
    public Result<?> updateUserwage(@RequestBody UserBasewage userwage){
        userBasewageService.updateById(userwage);
        //usersService.updateUser(user);
        return Result.success("修改职工工资成功！");
    }
}
