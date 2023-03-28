package com.oyhj.sys.mapper;

import com.oyhj.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
  public List<Integer> getMenuIdListByRoleId(Integer roleId);
}
