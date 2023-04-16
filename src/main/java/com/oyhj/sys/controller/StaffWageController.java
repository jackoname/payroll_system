package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.*;
import com.oyhj.sys.service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-18
 */
@RestController
@RequestMapping("/staffWage")
public class StaffWageController {
    @Autowired
    IWageListService wageListService;
    @Autowired
    IUserBasewageService userBasewageService;
    @Autowired
    ITaxandsafeService taxandsafeService;
    @Autowired
    IStaffWageService staffWageService;
    @Autowired
    IUsersService usersService;

    @PostMapping("/greanwage")
    public Result<?> greanWage(@RequestParam(value = "gentime",required = false) String gentime1 ,
                               @RequestParam(value = "pageNo") Long pageNo,
                               @RequestParam(value = "pageSize") Long pageSize
    )  throws ParseException {
        Page<StaffWage> page=new Page<>(pageNo,pageSize);
        List<StaffWage> staffWages = staffWageService.greanWage(gentime1);
        page.setRecords(staffWages);
        page.setTotal(staffWages.size());
        Map<String,Object> data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);

    }


}
