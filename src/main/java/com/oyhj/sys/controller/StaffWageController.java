package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.*;
import com.oyhj.sys.mapper.StaffWageMapper;
import com.oyhj.sys.mapper.WageListMapper;
import com.oyhj.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    @Autowired
    IStateService stateService;
    @Resource
    StaffWageMapper staffWageMapper;
    @PostMapping("/greanwage")

    public Result<?> greanWage(@RequestParam(value = "gentime",required = false) String gentime1 ,
                               @RequestParam(value = "pageNo") Long pageNo,
                               @RequestParam(value = "pageSize") Long pageSize
    )  throws ParseException {
        Page<StaffWage> page=new Page<>(pageNo,pageSize);
        List<StaffWage> staffWages1 = staffWageService.greanWage(gentime1);
        List<StaffWage> staffWages=new ArrayList<>();

        List<StaffWage> lists = staffWageService.list();
        for (int i = 0; i < staffWages1.size(); i++) {
            int flag=0;
            for (int i1 = 0; i1 < lists.size(); i1++) {
                if(lists.get(i1).getEndtime().getYear()==staffWages1.get(i).getEndtime().getYear()
                        &&lists.get(i1).getEndtime().getMonth()==staffWages1.get(i).getEndtime().getMonth()
                        &&lists.get(i1).getUserId()==staffWages1.get(i).getUserId()){
                    flag=1;
                    break;
                }
            }
            if (flag==0){
                staffWages.add(staffWages1.get(i));
            }
        }
        page.setRecords(staffWages);
        page.setTotal(staffWages.size());
        Map<String,Object> data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }
    @PostMapping("/saveOne")
    public Result<?> saveOne(@RequestBody StaffWage staff){
        List<StaffWage> list = staffWageService.list();
        try {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getEndtime().getYear()==staff.getEndtime().getYear()&&list.get(i).getEndtime().getMonth()==staff.getEndtime().getMonth()&&
                        staff.getUserId()==list.get(i).getUserId()){
                    return  Result.fail(staff.getName()+"的工资计算"+staff.getEndtime().toLocaleString()+"已确认!");
                }
            }
        }catch (Exception e){}
        staff.setStatename("待审核");
        staff.setState(1);
        staffWageService.save(staff);
        return Result.success("添加用户成功!");
    }
    @GetMapping("/list")
    public  Result<Map<String,Object>> getUserList(@RequestParam(value = "endtime",required = false) String endtime,
                                                   @RequestParam(value = "userId",required = false) String userId,
                                                   @RequestParam(value = "pageNo") Long pageNo,  @RequestParam(value = "pageSize") Long pageSize){
        Page<StaffWage> page =new Page<>(pageNo,pageSize);
        LambdaQueryWrapper<StaffWage> staffWageLambdaQueryWrapper =new LambdaQueryWrapper<>();
        staffWageLambdaQueryWrapper.eq(StaffWage::getState,1);
        List<StaffWage> list = staffWageService.list(staffWageLambdaQueryWrapper);

        page.setRecords(list);
        page.setTotal(list.size());
        Map<String,Object> data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        data.put("dff",page.getRecords());
        return Result.success(data);
    }
    @GetMapping("/list1")
    public  Result<Map<String,Object>> getUserList1(@RequestParam(value = "endtime",required = false) String endtime,
                                                   @RequestParam(value = "userId",required = false) String userId,
                                                   @RequestParam(value = "pageNo") Long pageNo,  @RequestParam(value = "pageSize") Long pageSize){
        Page<StaffWage> page =new Page<>(pageNo,pageSize);
        LambdaQueryWrapper<StaffWage> staffWageLambdaQueryWrapper =new LambdaQueryWrapper<>();
        staffWageLambdaQueryWrapper.eq(StaffWage::getState,2);
        List<StaffWage> list = staffWageService.list(staffWageLambdaQueryWrapper);

        page.setRecords(list);
        page.setTotal(list.size());
        Map<String,Object> data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        data.put("dff",page.getRecords());
        return Result.success(data);
    }
    @DeleteMapping("/{wageId}")
    public Result<?> DeleteWageById(@PathVariable("wageId") Integer wageId) throws ParseException {
        staffWageService.removeById(wageId);
        return Result.success("已驳回");
    }
    @PutMapping("/{wageId}")
    public Result<?>editUserwage(@PathVariable("wageId") Integer wageId) throws ParseException {
        StaffWage staff = staffWageService.getById(wageId);
        staff.setState(2);
        staff.setStatename("待发放");
        staff.setUpdatetime(new Date());
        staffWageService.updateById(staff);
        return Result.success("已通过");
    }
}
