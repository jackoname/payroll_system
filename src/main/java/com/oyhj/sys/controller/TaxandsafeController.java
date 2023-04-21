package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.ListType;
import com.oyhj.sys.entity.Taxandsafe;
import com.oyhj.sys.entity.WageList;
import com.oyhj.sys.service.ITaxandsafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
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
@RequestMapping("/taxandsafe")
public class TaxandsafeController {
    @Autowired
    ITaxandsafeService taxandsafeService;
    @GetMapping("/list")
    public Result<Map<String,Object>> getSafeList(@RequestParam(value = "userId",required = false) Integer userId,
                                                  @RequestParam(value = "endtime",required = false) String endtime1,
                                                  @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize) throws ParseException {
        Page<Taxandsafe> page =new Page<>(pageNo,pageSize);
        List<Taxandsafe> taxandsafes =new ArrayList<>();
        taxandsafeService.list().forEach(item->{
            if (item.getFlag()==1) {
                taxandsafes.add(item);
            }
        });
        page.setRecords(taxandsafes);
        page.setTotal(taxandsafes.size());
        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }
    @GetMapping("/list1")
    public Result<Map<String,Object>> getSafeList1(@RequestParam(value = "userId",required = false) Integer userId,
                                                  @RequestParam(value = "endtime",required = false) String endtime1,
                                                  @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize) throws ParseException {
        Page<Taxandsafe> page =new Page<>(pageNo,pageSize);
        List<Taxandsafe> taxandsafes =new ArrayList<>();
        taxandsafeService.list().forEach(item->{
            if (item.getFlag()>=2) {
                taxandsafes.add(item);
            }
        });
        page.setRecords(taxandsafes);
        page.setTotal(taxandsafes.size());
        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }
    @PostMapping
    public Result<?> addListType(@RequestBody Taxandsafe safeList){
        safeList.setFlag(1);
        taxandsafeService.save(safeList);
        return Result.success("添加成功!");
    }
    @PostMapping("/add")
    public Result<?> addListType1(@RequestBody Taxandsafe safeList){
        safeList.setFlag(2);
        taxandsafeService.save(safeList);
        return Result.success("添加成功!");
    }

    @DeleteMapping("/{id}")
    public Result<?> DeleteUserById(@PathVariable("id") Integer id){
        taxandsafeService.removeById(id);
        return Result.success("删除成功");
    }

    @GetMapping("/{id}")
    public Result<?> getUserWageById(@PathVariable("id") Integer id){

        return Result.success(taxandsafeService.getById(id));

    }
    @PutMapping
    public Result<?> updateUserwage(@RequestBody Taxandsafe safeList){
       taxandsafeService.updateById(safeList);
        return Result.success("成功！");
    }
    }
