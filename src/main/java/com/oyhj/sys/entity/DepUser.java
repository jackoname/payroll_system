package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-03
 */
@TableName("dep_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "udid", type = IdType.AUTO)
    private Integer udid;

    private Integer depid;

    private Integer userid;

}
