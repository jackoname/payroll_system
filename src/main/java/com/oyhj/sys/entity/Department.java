package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-02
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private String depart;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String departBoss;

    private String dec;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getDepartBoss() {
        return departBoss;
    }

    public void setDepartBoss(String departBoss) {
        this.departBoss = departBoss;
    }
    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    @Override
    public String toString() {
        return "Department{" +
            "depart=" + depart +
            ", id=" + id +
            ", departBoss=" + departBoss +
            ", dec=" + dec +
        "}";
    }
}
