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
 * @since 2023-04-10
 */
public class Taxandsafe implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Double taxMe;

    private Double submoney;

    private String dsc;

    private String name;

    private String flag;

    private Double taxCom;

    private Double lowmoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Double getTaxMe() {
        return taxMe;
    }

    public void setTaxMe(Double taxMe) {
        this.taxMe = taxMe;
    }
    public Double getSubmoney() {
        return submoney;
    }

    public void setSubmoney(Double submoney) {
        this.submoney = submoney;
    }
    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    public Double getTaxCom() {
        return taxCom;
    }

    public void setTaxCom(Double taxCom) {
        this.taxCom = taxCom;
    }
    public Double getLowmoney() {
        return lowmoney;
    }

    public void setLowmoney(Double lowmoney) {
        this.lowmoney = lowmoney;
    }

    @Override
    public String toString() {
        return "Taxandsafe{" +
            "id=" + id +
            ", taxMe=" + taxMe +
            ", submoney=" + submoney +
            ", dsc=" + dsc +
            ", name=" + name +
            ", flag=" + flag +
            ", taxCom=" + taxCom +
            ", lowmoney=" + lowmoney +
        "}";
    }
}
