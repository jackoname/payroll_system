package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;


import com.oyhj.sys.entity.Role;
import com.oyhj.sys.entity.UserRole;
import com.oyhj.sys.entity.Users;
import com.oyhj.sys.mapper.UsersMapper;
import com.oyhj.sys.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oyhj
 * @since 2023-03-09
 */
@Api(tags={"用户接口列表"})
@RestController
@RequestMapping("/user")
@CrossOrigin
@ResponseBody
public class UsersController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private IPostService postService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;
    @Resource
    private UsersMapper usersMapper;
    @GetMapping("/all")
    public Result<List<Users>> getAllUser(){
        List<Users> list =usersService.list();
        return Result.success(list,"查询成功");
    }
    @ApiOperation("用户登录")
    @PostMapping("/login")
    private Result<Map<String, Object>> login(@RequestBody Users user){
        Map<String,Object>data=usersService.login(user);
        try {
            if(!Objects.equals(data.get("ban"),null)){
                return Result.fail(20003,"用户被禁用,请联系管理员！");
            }

        }catch (Exception e){
            return Result.fail(20003,"用户被禁用,请联系管理员！");
        }

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
                                                   @RequestParam(value = "roleid",required = false) Integer roleid,
                                          @RequestParam(value = "phone",required = false) String phone,
    @RequestParam(value = "pageNo") Long pageNo,  @RequestParam(value = "pageSize") Long pageSize){
        Page<Users> page =new Page<>(pageNo,pageSize);
        LambdaQueryWrapper<Users> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(roleid)) {
            page.setRecords(usersMapper.getuserByRoleId(roleid));
        }
        else {
            objectLambdaQueryWrapper.eq(StringUtils.hasLength(username), Users::getUsername, username);
            usersService.page(page, objectLambdaQueryWrapper);
        }
        page.getRecords().forEach(item->{
            LambdaQueryWrapper<UserRole> Wrapper = new LambdaQueryWrapper<>();
            Wrapper.eq(UserRole::getUserid, item.getUserId());
            try {
                    item.setPostName(postService.getById(item.getPostid()).getPostName());
                    item.setDepName(departmentService.getById(item.getDepid()).getDepart());

                try {

                    item.setRoleName(roleService.getById(userRoleService.list(Wrapper).get(0).getRoleid()).getRolename());
                }catch (Exception e) {
                    System.out.println(e.fillInStackTrace());
                }
            }catch (Exception e){

                System.out.println(e.fillInStackTrace());
            }


            item.setPassword("");

        });

        Map<String,Object>data =new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addUser(@RequestBody Users user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersService.addUser(user);

        return Result.success("添加用户成功!");
    }
    @PutMapping
    public Result<?> updateUser(@RequestBody Users user){
       // user.setPassword(null);
        usersService.updateUser(user);
        return Result.success("修改用户成功！");
    }

    @GetMapping("/{userId}")
    public Result<?> getUserById(@PathVariable("userId") Integer userId){
        Users user = usersService.getUserById(userId);
        return Result.success(user);
    }

    @DeleteMapping("/{userId}")
    public Result<?> DeleteUserById(@PathVariable("userId") Integer userId){
        usersService.deleteUserById(userId);
        return Result.success("删除成功");
    }
    @PostMapping("/photo")
    public String uploadTx(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return null;
    }
}
