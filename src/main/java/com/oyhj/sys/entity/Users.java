package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.io.Serializable;


import java.sql.Date;
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
    private  Integer depid;
    private  Integer  postid;
    private  Integer age;
    private String phone;
    private  Integer sex;
    private String email;
    private Date birthday;
    private String   name;
    @TableField("bankno")
    private String bankno;
    private String idcardno;
    private String photo;
    private String remarks;
    private String address;
    @TableField(exist = false)
   private List<Integer> roleIdList;
    @TableField(exist = false)
    private List<Integer> depIdList;
    @TableField(exist = false)
    private List<Integer> postIdList;
    @TableField(exist = false)
    private String postName;
    @TableField(exist = false)
    private  String depName;
    @TableField(exist = false)
    private  String roleName;

}
