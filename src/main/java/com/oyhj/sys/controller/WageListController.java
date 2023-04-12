package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.UserBasewage;
import com.oyhj.sys.entity.Users;
import com.oyhj.sys.entity.WageList;
import com.oyhj.sys.mapper.UserBasewageMapper;
import com.oyhj.sys.mapper.UsersMapper;
import com.oyhj.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/wageList")
public class WageListController {
    @Resource
    UsersMapper usersMapper;
    @Autowired
    IUsersService usersService;
    @Autowired
    IWageListService wageListService;
    @Resource
    UserBasewageMapper userBasewageMapper;
    @Autowired
    IPostService postService;
    @Autowired
    IDepartmentService departmentService;
    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "userId",required = false) Integer userId,
                                                  @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<WageList> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<WageList> page =new Page<>(pageNo,pageSize);
        if (Objects.nonNull(userId)) {
            objectLambdaQueryWrapper.eq(WageList::getUserId,userId);
            List<WageList> list = wageListService.list(objectLambdaQueryWrapper);
            page.setRecords(list);
            page.setSize(pageSize);
            page.setTotal(list.size());
            page.setPages(pageNo);
        }
        else {

            page.setRecords(wageListService.list());
            page.setTotal(wageListService.list().size());
        }
        if(page.getRecords().size()>0) {
            page.getRecords().forEach(item -> {
                item.setName(usersService.getById(item.getUserId()).getName());
                item.setPost_name(postService.getById(usersService.getById(item.getUserId()).getPostid()).getPostName());
                item.setDepart(departmentService.getById(usersService.getById(item.getUserId()).getDepid()).getDepart());
            });
        }
        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addUserprewage(@RequestBody WageList wageList){
       Users byId = usersService.getById(wageList.getUserId());
//        List<WageList> list = wageListService.list(userBasewageLambdaQueryWrapper);
        wageList.setName(byId.getName());
        wageListService.save(wageList);
        //userBasewageService.save(userBasewage);
        // usersService.addUser(user);

        return Result.success("为"+wageList.getName()+" 添加绩效工资成功!");
    }

}
