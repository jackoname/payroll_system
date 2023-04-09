package com.oyhj.sys.mapper;

import com.oyhj.sys.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-18
 */
public interface UsersMapper extends BaseMapper<Users> {
    public List<String> getRoleNameByUserId(Integer userId);
    public List<Users> getuserByRoleId(Integer roleid);
}
