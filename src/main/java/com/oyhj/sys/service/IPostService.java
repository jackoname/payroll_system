package com.oyhj.sys.service;

import com.oyhj.sys.entity.Menu;
import com.oyhj.sys.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oyhj.sys.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oyhj
 * @since 2023-04-02
 */
public interface IPostService extends IService<Post> {
    List<Post> getAllPost();
    void addPost(Post post);
    void updatePost(Post post);
   Post getPostById(Integer id);

}
