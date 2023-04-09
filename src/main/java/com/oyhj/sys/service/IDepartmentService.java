package com.oyhj.sys.service;

import com.oyhj.sys.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oyhj.sys.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oyhj
 * @since 2023-04-02
 */
public interface IDepartmentService extends IService<Department> {

    void addDep(Department dep);

    Department getDepById(Integer depId);

    void updateDep(Department dep);
}
