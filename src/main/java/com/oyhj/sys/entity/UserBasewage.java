package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-04-10
 */
@TableName("user_basewage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasewage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "wuid", type = IdType.AUTO)
    private Integer wuid;

    private Double basewage;

    private String dsc;

    private Integer userid;

    private Integer version;

    @TableField(exist = false)
    private String post_name;
    @TableField(exist = false)
    private  String depart;
    @TableField(exist = false)
    private  String name;
    @TableField (exist= false)
    private String username;


}
