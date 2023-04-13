package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.ListType;
import com.oyhj.sys.entity.Users;
import com.oyhj.sys.entity.WageList;
import com.oyhj.sys.service.IListTypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oyhj
 * @since 2023-04-02
 */

@RestController
@RequestMapping("/listType")
public class ListTypeController {
    @Autowired
    IListTypeService listTypeService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "listId",required = false) Integer listId,
                                                  @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize){
        Page<ListType> page =new Page<>(pageNo,pageSize);
        List<ListType> list = listTypeService.list();
        List<ListType> list1= new ArrayList<>();
        List<ListType> types= new ArrayList<>();
        list.forEach(item->{
            if (item.getTag()>0) {
                if(!Objects.nonNull(listId))
                list1.add(item);
            }
            else  {
                types.add(item);
            }
        });
        if(Objects.nonNull(listId)){
            list.forEach(item->{
                if(item.getTag()==listId){
                    list1.add(item);
                }
            });
        }
        list1.forEach(item->{
            types.forEach(item1->{
                if(item.getTag()==item1.getListId()){
                    item.setCname(item1.getName());
                }
            });
        });
        page.setRecords(list1);
        page.setPages(list1.size());
        Map<String,Object>data =new HashMap<>();
        @Data
        @AllArgsConstructor
        class children{
            Integer value;
            String label;
        }
        @Data
        @AllArgsConstructor
        class value{
            Integer value;
            String label;
            List<children> children;
        }

       List<value>values=new ArrayList<>();

        types.forEach(item->{
            List<children>children=new ArrayList<>();
           list.forEach(item1->{
               if(item1.getTag()>0&&item.getListId()==item1.getTag()){
                   children.add(new children(item1.getListId(),item1.getName()));
               }
            });
            values.add(new value(item.getListId(),item.getName(),children));
        });

        data.put("total",page.getTotal());
        data.put("types",types);
        data.put("rows",page.getRecords());
        data.put("values",values);
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addListType(@RequestBody ListType listType){
        listType.setCreatetime(new Date());
        if (Objects.nonNull(listType.getFlag())){
            listType.setTag(0);
            listTypeService.save(listType);
        }
        else{
            listType.setFlag(0);
            listTypeService.save(listType);
        }
        return Result.success("为"+" 添加绩效工资成功!");
    }

    @DeleteMapping("/{listId}")
    public Result<?> DeleteUserById(@PathVariable("listId") Integer listId){

        LambdaQueryWrapper<ListType> listTypeLambdaQueryWrapper =new LambdaQueryWrapper<>();

        if ((listTypeService.getById(listId).getTag()==0)) {
            listTypeLambdaQueryWrapper.eq(ListType::getTag,listId);
            List<ListType> list = listTypeService.list(listTypeLambdaQueryWrapper);
            list.forEach(item->{
                listTypeService.removeById(item.getListId());
            });
        }
      listTypeService.removeById(listId);
        return Result.success("删除成功");
    }

    @GetMapping("/{listId}")
    public Result<?> getUserlistType(@PathVariable("listId") Integer id){

        return Result.success(listTypeService.getById(id));

    }
    @PutMapping
    public Result<?> updatelistType(@RequestBody ListType listType){
       listTypeService.updateById(listType);
        //usersService.updateUser(user);
        return Result.success("修改成功");
    }
}
