package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.*;
import com.oyhj.sys.service.IDepartmentService;
import com.oyhj.sys.service.IPostDepUserService;
import com.oyhj.sys.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oyhj
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
   private IDepartmentService departmentService;
    @Autowired
    private IPostService postService ;
    @Autowired
    private IPostDepUserService postDepUserService;
    @GetMapping("/all")
    public Result<List<Department>> getAllDep(){
        List<Department>departmentList = departmentService.list();
        return Result.success(departmentList);
    }

    @GetMapping("/list")
    public  Result<Map<String,Object>> getDepList(@RequestParam(value = "depart",required = false) String depart,
                                                   @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Department> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(StringUtils.hasLength(depart),Department::getDepart,depart);
        Page<Department> page =new Page<>(pageNo,pageSize);
        departmentService.page(page,objectLambdaQueryWrapper);
        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }

    @DeleteMapping("/{depId}")
    public Result<?> delDepById(@PathVariable("depId") Integer depId){

        LambdaQueryWrapper<PostDepUser> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(PostDepUser::getDepid,departmentService.getById(depId).getId());



        LambdaQueryWrapper<Post> objectLambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper2.eq(Post::getDepid,depId);

        List<PostDepUser> list = postDepUserService.list(objectLambdaQueryWrapper);
        List<Post>list1=postService.list(objectLambdaQueryWrapper2);
        list.forEach(item->{
            if ((item.getDepid()==depId)) {
                postDepUserService.removeById(item.getPduid());
            }
        });
        list1.forEach(item->{
            if ((item.getDepid()==depId)) {
                postService.removeById(item.getPostId());
            }
        });
        departmentService.removeById(depId);
        return Result.success("删除部门成功");
    }
    @PostMapping
    public Result<?> addDep(@RequestBody Department dep){

        departmentService.addDep(dep);
        return Result.success("新增角色成功！");
    }

    @GetMapping("/{depId}")
    public Result<?> getRoleById(@PathVariable("depId") Integer depId){
        Department byId =departmentService.getDepById(depId);
        return Result.success(byId);
    }

    @PutMapping
    public Result<?> updateRole(@RequestBody Department dep){

        departmentService.updateDep(dep);
        return Result.success("修改角色成功！");
    }
}
