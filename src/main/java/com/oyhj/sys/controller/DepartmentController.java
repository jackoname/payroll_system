package com.oyhj.sys.controller;

import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.Department;
import com.oyhj.sys.entity.Role;
import com.oyhj.sys.service.IDepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oyhj
 * @since 2023-04-02
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    IDepartmentService departmentService;
    @GetMapping("/all")
    public Result<List<Department>> getAllRole(){
        List<Department>departmentList = departmentService.list();
        return Result.success(departmentList);
    }
}
