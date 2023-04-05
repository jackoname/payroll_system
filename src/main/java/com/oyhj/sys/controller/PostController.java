package com.oyhj.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.*;
import com.oyhj.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IPostDepUserService postDepUserService;

    @GetMapping("/list")
    public  Result<Map<String,Object>> getRoleList(@RequestParam(value = "post_name",required = false) String postName,
                                                   @RequestParam(value = "pageNo") Long pageNo, @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Post> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(StringUtils.hasLength(postName),Post::getPostName,postName);

        Page<Post> page =new Page<>(pageNo,pageSize);
        postService.page(page,objectLambdaQueryWrapper);
        Map<String,Object>data =new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }


    @PostMapping
    public Result<?> addPost(@RequestBody Post post){

        postService.addPost(post);
        return Result.success("新增角色成功！");
    }
    @PutMapping
    public Result<?> updatePost(@RequestBody  Post post){

        postService.updatePost(post);
        return Result.success("修改角色成功！");
    }

    @GetMapping("/{postId}")
    public Result<?> getPostById(@PathVariable("postId") Integer postId){
        Post byId = postService.getPostById(postId);
        System.out.println(byId.toString());
        return Result.success(byId);
    }

    @DeleteMapping("/{postId}")
    public Result<?> delPostById(@PathVariable("postId") Integer postId){

        LambdaQueryWrapper<PostDepUser> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(PostDepUser::getUserid,postService.getPostById(postId).getPostId());

        List<PostDepUser> list = postDepUserService.list(objectLambdaQueryWrapper);
        list.forEach(item->{
            if ((item.getPostid()==item.getPostid())) {
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
}
