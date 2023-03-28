package com.oyhj.sys.service;

import com.oyhj.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-25
 */
public interface IRoleService extends IService<Role> {

    void addRole(Role role);
    void updateRole(Role role);
     Role getRoleById(Integer id);
}
