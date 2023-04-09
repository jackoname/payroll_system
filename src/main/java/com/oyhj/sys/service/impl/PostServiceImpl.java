package com.oyhj.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oyhj.sys.entity.Post;
import com.oyhj.sys.entity.UserRole;
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
        this.baseMapper.insert(post);
    }

    @Override
    public void updatePost(Post post) {
        this.baseMapper.updateById(post);
    }

    @Override
    public Post getPostById(Integer id) {
        return  this.baseMapper.selectById(id);
    }
}
