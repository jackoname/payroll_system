package com.oyhj.sys.mapper;

import com.oyhj.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-26
 */
public interface MenuMapper extends BaseMapper<Menu> {
public List<Menu> getMunuListByUserId(@Param("UserId") Integer userId, @Param("pid") Integer pid);
}
