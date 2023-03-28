package com.oyhj.sys.service;

import com.oyhj.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getAllMenu();
}
