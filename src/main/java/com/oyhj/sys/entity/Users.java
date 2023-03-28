package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-18
 */
@Data
@TableName("users")

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    @TableId(value = "userId",type = IdType.AUTO)
    private Integer userId;
    @TableField("username")
    private String username;

    private String password;
    private  Integer isuse;
    @TableField("roletag")
    private  Integer roletag;
    private String phone;

    private String email;
@TableField(exist = false)
   private List<Integer> roleIdList;
}
