package com.oyhj.sys.service.impl;

import com.oyhj.sys.entity.Post;
import com.oyhj.sys.mapper.PostMapper;
import com.oyhj.sys.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oyhj
 * @since 2023-04-02
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public void addPost(Post post) {

    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public Post getPostById(Integer id) {
        return null;
    }
}
