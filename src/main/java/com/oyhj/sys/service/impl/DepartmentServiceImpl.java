package com.oyhj.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oyhj.sys.entity.Department;
import com.oyhj.sys.entity.Role;
import com.oyhj.sys.entity.RoleMenu;
import com.oyhj.sys.mapper.DepartmentMapper;
import com.oyhj.sys.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Override
    public void addDep(Department dep) {
        this.baseMapper.insert(dep);
    }

    @Override
    public Department getDepById(Integer depId) {
       Department department =this.baseMapper.selectById(depId);
        return department;
    }
    @Transactional
    @Override
    public void updateDep(Department dep) {
        this.baseMapper.updateById(dep);
    }
}
