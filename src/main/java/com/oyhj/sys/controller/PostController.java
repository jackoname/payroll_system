package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.*;
import com.oyhj.sys.mapper.UsersMapper;
import com.oyhj.sys.service.*;
import freemarker.template.ObjectWrapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oyhj
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IPostDepUserService postDepUserService;


    @GetMapping("/list")
    public  Result<Map<String,Object>> getRoleList(@RequestParam(value = "post_name",required = false) String postName, @RequestParam(value = "id",required = false) Integer id,
                                                   @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize){
        System.out.println(Objects.nonNull(id)+"-----------"+id);
        LambdaQueryWrapper<Post> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(Objects.nonNull(id)) {
            objectLambdaQueryWrapper.eq(Post::getDepid, departmentService.getById(id).getId());
        }
        else
        objectLambdaQueryWrapper.eq(StringUtils.hasLength(postName),Post::getPostName,postName);

        Page<Post> page =new Page<>(pageNo,pageSize);
        postService.page(page,objectLambdaQueryWrapper);
        try {
            page.getRecords().forEach(item->{
                item.setDepName(departmentService.getById(item.getDepid()).getDepart());
            });
        }catch (Exception e){}

        Map<String,Object>data =new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }


    @PostMapping
    public Result<?> addPost(@RequestBody Post post){

        postService.addPost(post);
        return Result.success("新增职位成功！");
    }
    @PutMapping
    public Result<?> updatePost(@RequestBody  Post post){

        postService.updatePost(post);
        return Result.success("修改职位成功！");
    }

    @GetMapping("/{postId}")
    public Result<?> getPostById(@PathVariable("postId") Integer postId){
        Post byId = postService.getPostById(postId);

        return Result.success(byId);
    }

    @DeleteMapping("/{postId}")
    public Result<?> delPostById(@PathVariable("postId") Integer postId){

        LambdaQueryWrapper<PostDepUser> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(PostDepUser::getPostid,postService.getById(postId).getPostId());

        List<PostDepUser> list = postDepUserService.list(objectLambdaQueryWrapper);
        list.forEach(item->{
            if ((item.getPostid()==postId)) {
                postDepUserService.removeById(item.getPduid());
            }
        });

        postService.removeById(postId);
        return Result.success("删除职位成功");
    }
    @GetMapping("/all")
    public Result<List<Post>> getAllPost(){
        List<Post> postList = postService.list();
        return Result.success(postList);
    }

    @GetMapping("/alllbyDid")
    public Result<List<Post>> getAllPostById(@RequestParam(value = "depIdList",required = false) Integer depId){
       LambdaQueryWrapper<Post> wrapper =new LambdaQueryWrapper<>();
       wrapper.eq(Post::getDepid,depId);
        List<Post> postList = postService.list(wrapper);
        return Result.success(postList);
    }
}
