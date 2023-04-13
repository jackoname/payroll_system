package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.UserBasewage;
import com.oyhj.sys.entity.Users;
import com.oyhj.sys.entity.WageList;
import com.oyhj.sys.mapper.UserBasewageMapper;
import com.oyhj.sys.mapper.UsersMapper;
import com.oyhj.sys.service.*;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    @Autowired
    IListTypeService listTypeService;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "userId",required = false) Integer userId,
                                                  @RequestParam(value = "endtime",required = false) String endtime1,
                                                  @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize) throws ParseException {


        List<WageList> list1 = new ArrayList<>();
        LambdaQueryWrapper<WageList> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<WageList> page =new Page<>(pageNo,pageSize);
        if (Objects.nonNull(userId)) {
            objectLambdaQueryWrapper.eq(WageList::getUserId,userId);
            List<WageList> list = wageListService.list(objectLambdaQueryWrapper);

          list.forEach(item->{
              if ((item.getFlag()==1)) {
                  list1.add(item);
              }

          });
            page.setRecords(list1);
            page.setSize(pageSize);
            page.setTotal(list1.size());
            page.setPages(pageNo);
        }
        else  if(Objects.nonNull(endtime1)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date endtime= formatter.parse(endtime1);;
            wageListService.list().forEach(item->{
                if (Objects.nonNull(userId)){
                    if ((item.getUserId()==userId&&item.getEndtime().getYear()==endtime.getYear()
                            &&item.getEndtime().getMonth()==endtime.getMonth())&&item.getFlag()==1) {
                        list1.add(item);
                    }
                }
                else {
                    if (item.getEndtime().getYear()==endtime.getYear() &&item.getFlag()==1&&item.getEndtime().getMonth()==endtime.getMonth()){
                        list1.add(item);
                    }
                }
            });
                    page.setRecords(list1);
                    page.setTotal(list1.size());
        }
        else {
            wageListService.list().forEach(item->{
                if ((item.getFlag()==1)) {
                    list1.add(item);
                }
            });
            page.setRecords(list1);
            page.setTotal(list1.size());
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
    }@DateTimeFormat
    @JsonFormat
    @GetMapping("/list1")
    public Result<Map<String,Object>> getUserList1(@RequestParam(value = "option",required = false) Integer option,
                                                  @RequestParam(value = "userId",required = false) Integer userId,
                                                  @RequestParam(value = "endtime",required = false) String endtime1,
                                                  @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize) throws ParseException {

        LambdaQueryWrapper<WageList> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();

        Page<WageList> page =new Page<>(pageNo,pageSize);
        List<WageList> lists= new ArrayList<>();
        if (Objects.nonNull(option)){
            objectLambdaQueryWrapper.eq(WageList::getListId,option);
            wageListService.list(objectLambdaQueryWrapper).forEach(item->{
                if ((item.getFlag()!=1)) {
                    lists.add(item);
                }
        });
        }

   else   if (Objects.nonNull(userId)) {
            objectLambdaQueryWrapper.eq(WageList::getUserId,userId);
            wageListService.list(objectLambdaQueryWrapper).forEach(item->{
                if ((item.getFlag()!=1)) {
                    lists.add(item);
                }
            });
        }

        else  if(Objects.nonNull(endtime1)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endtime= formatter.parse(endtime1);;
            wageListService.list().forEach(item->{
                if (Objects.nonNull(userId)) {
                    if (Objects.nonNull(option)) {
                        if ((item.getUserId() == userId && item.getEndtime().getYear() == endtime.getYear()
                                && item.getEndtime().getMonth() == endtime.getMonth()) && item.getFlag() != 1 && item.getListId() == option) {
                            lists.add(item);
                        }
                    }
                }
                    else {
                        if (item.getEndtime().getYear()==endtime.getYear() &&item.getFlag()!=1&&item.getEndtime().getMonth()==endtime.getMonth()) {
                            lists.add(item);
                        }
                    }

            });
            }
        else {
            wageListService.list().forEach(item->{
                if ((item.getFlag()!=1)) {
                    lists.add(item);
                }
            });
        }
        page.setRecords(lists);
        page.setPages(lists.size());
        if(page.getRecords().size()>0) {
            page.getRecords().forEach(item -> {
                item.setName(usersService.getById(item.getUserId()).getName());
                item.setPost_name(postService.getById(usersService.getById(item.getUserId()).getPostid()).getPostName());
                item.setDepart(departmentService.getById(usersService.getById(item.getUserId()).getDepid()).getDepart());
            });
        }
        page.getRecords().forEach(item->{
            try {
                item.setCname(listTypeService.getById(listTypeService.getById((wageListService.getById(item.getId()))).getTag()).getName());
            }catch (Exception e){}

        });
        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }
    @PostMapping
    public Result<?> addUserprewage(@RequestBody WageList wageList){
        System.out.println(wageList);
        Users byId = usersService.getById(wageList.getUserId());
        wageList.setName(byId.getName());
        wageList.setCreattime(new Date());
        LambdaQueryWrapper<WageList>wageListLambdaQueryWrapper=new LambdaQueryWrapper<>();
        System.out.println((!Objects.isNull(wageList.getTag())));
        if(!Objects.isNull(wageList.getTag())){
       wageListLambdaQueryWrapper.eq(WageList::getUserId,wageList.getUserId());
        List<WageList> list = wageListService.list(wageListLambdaQueryWrapper);
        wageList.setFlag(1);
        wageList.setWageName("绩效工资");
        for (int i = 0; i < list.size(); i++) {
            if(wageList.getEndtime().getYear()==list.get(i).getEndtime().getYear()&&wageList.getEndtime().getMonth()==list.get(i).getEndtime().getMonth()&&list.get(i).getFlag()==1){
                return Result.fail(wageList.getName()+" 本月绩效工资已存在!");
            }
        }
       }
       else {
           if (Objects.nonNull(wageList.getWageName())){
               wageList.setFlag(2);
               wageList.setListId(9999);
           }
           else {

               wageList.setWageName(listTypeService.getById(wageList.getOption()[1]).getName());
            //   wageList.setWageName(listTypeService.getById(wageList.getOption()[0]).getCname());
               wageList.setListId(wageList.getOption()[1]);
               wageList.setFlag(0);
           }
       }
        wageListService.save(wageList);
        return Result.success("为"+wageList.getName()+" 添加工资成功!");
    }
    @DeleteMapping("/{id}")
    public Result<?> DeleteUserById(@PathVariable("id") Integer id){
        wageListService.removeById(id);
        return Result.success("删除成功");
    }

    @GetMapping("/{id}")
    public Result<?> getUserWageById(@PathVariable("id") Integer id){

                return Result.success(wageListService.getById(id));

    }
    @PutMapping
    public Result<?> updateUserwage(@RequestBody WageList wageList){
        wageListService.updateById(wageList);
        return Result.success("修改"+wageList.getName()+"的绩效工资成功！");
    }
}
