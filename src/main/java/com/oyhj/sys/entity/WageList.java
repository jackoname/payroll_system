package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-02
 */
@TableName("wage_list")
public class WageList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer listId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String wageName;

    private Double wage;

    private Integer roleId;

    private Integer userId;

    private LocalDateTime creattime;

    private LocalDateTime endtime;

    private String state;

    private String des;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getWageName() {
        return wageName;
    }

    public void setWageName(String wageName) {
        this.wageName = wageName;
    }
    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public LocalDateTime getCreattime() {
        return creattime;
    }

    public void setCreattime(LocalDateTime creattime) {
        this.creattime = creattime;
    }
    public LocalDateTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "WageList{" +
            "listId=" + listId +
            ", id=" + id +
            ", wageName=" + wageName +
            ", wage=" + wage +
            ", roleId=" + roleId +
            ", userId=" + userId +
            ", creattime=" + creattime +
            ", endtime=" + endtime +
            ", state=" + state +
            ", des=" + des +
        "}";
    }
}
