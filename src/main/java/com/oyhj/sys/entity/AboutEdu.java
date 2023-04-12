package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-10
 */
@TableName("about_edu")
public class AboutEdu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "edu_id", type = IdType.AUTO)
    private Integer eduId;

    private String mean;

    private String dsc;

    private Integer flag;

    public Integer getEduId() {
        return eduId;
    }

    public void setEduId(Integer eduId) {
        this.eduId = eduId;
    }
    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "AboutEdu{" +
            "eduId=" + eduId +
            ", mean=" + mean +
            ", dsc=" + dsc +
            ", flag=" + flag +
        "}";
    }
}
