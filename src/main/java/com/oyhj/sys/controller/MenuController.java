package com.oyhj.sys.controller;

import com.oyhj.common.vo.Result;
import com.oyhj.sys.entity.Menu;
import com.oyhj.sys.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @GetMapping
    public Result<?> getAllMenu(){
        List<Menu> menuList =  menuService.getAllMenu();
        return Result.success(menuList);
    }

}
