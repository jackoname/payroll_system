package com.oyhj.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oyhj.sys.entity.Role;
import com.oyhj.sys.entity.RoleMenu;
import com.oyhj.sys.mapper.RoleMapper;
import com.oyhj.sys.mapper.RoleMenuMapper;
import com.oyhj.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional
    public void addRole(Role role) {
        this.baseMapper.insert(role);

        if(null!=role.getMenuidList()){
            for (Integer menuId : role.getMenuidList()) {
                roleMenuMapper.insert(new RoleMenu(null,role.getId(),menuId));
            }
        }
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        this.baseMapper.updateById(role);
       LambdaQueryWrapper<RoleMenu>wrapper=new LambdaQueryWrapper<>();
       wrapper.eq(RoleMenu::getRoleId,role.getId());
       roleMenuMapper.delete(wrapper);
        if(null!=role.getMenuidList()){
            for (Integer menuId : role.getMenuidList()) {
                roleMenuMapper.insert(new RoleMenu(null,role.getId(),menuId));
            }
        }
    }

    @Override
    public Role getRoleById(Integer id) {
        Role role =this.baseMapper.selectById(id);
        List<Integer> menuIdListByRoleId = roleMenuMapper.getMenuIdListByRoleId(role.getId());
        role.setMenuidList(menuIdListByRoleId);
        return role;
    }

}
