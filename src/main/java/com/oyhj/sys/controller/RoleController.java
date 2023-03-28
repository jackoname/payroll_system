package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.Role;
import com.oyhj.sys.entity.RoleMenu;
import com.oyhj.sys.entity.Users;
import com.oyhj.sys.service.IRoleMenuService;
import com.oyhj.sys.service.IRoleService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleMenuService roleMenuServicer;
    @GetMapping("/list")
    public  Result<Map<String,Object>> getRoleList(@RequestParam(value = "rolename",required = false) String roleName,
                                                   @RequestParam(value = "pageNo") Long pageNo,  @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Role> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(StringUtils.hasLength(roleName),Role::getRolename,roleName);

        Page<Role> page =new Page<>(pageNo,pageSize);
        roleService.page(page,objectLambdaQueryWrapper);
        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }


    @PostMapping
    public Result<?> addRole(@RequestBody Role role){

         roleService.addRole(role);
        return Result.success("新增角色成功！");
    }
    @PutMapping
    public Result<?> updateRole(@RequestBody Role role){

        roleService.updateRole(role);
        return Result.success("修改角色成功！");
    }

    @GetMapping("/{roleId}")
    public Result<?> getRoleById(@PathVariable("roleId") Integer roleId){
        Role byId = roleService.getRoleById(roleId);
        System.out.println(byId.toString());
        return Result.success(byId);
    }

    @DeleteMapping("/{roleId}")
    public Result<?> delRoleById(@PathVariable("roleId") Integer roleId){

        LambdaQueryWrapper<RoleMenu> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(RoleMenu::getRoleId,roleService.getById(roleId).getId());

        List<RoleMenu> list = roleMenuServicer.list(objectLambdaQueryWrapper);
        list.forEach(item->{
            if ((item.getRoleId()==item.getRoleId())) {
                roleMenuServicer.removeById(item.getId());
            }
        });

        roleService.removeById(roleId);
        return Result.success("删除角色成功");
    }
    @GetMapping("/all")
    public Result<List<Role>> getAllRole(){
        List<Role> roleList = roleService.list();
        return Result.success(roleList);
    }
}
