package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.io.Serializable;

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
    @TableId("userId")  
    private Integer userId;
    @TableField("username")
    private String username;

    private String password;
    @TableField("petName")
    private String petName;

    private String phone;

    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" +
            "userId=" + userId +
            ", username=" + username +
            ", password=" + password +
            ", petName=" + petName +
            ", phone=" + phone +
            ", email=" + email +
        "}";
    }
}
