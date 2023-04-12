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
public class Usestate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "isusid", type = IdType.AUTO)
    private Integer isusid;

    private String usestate;

    private String enable;

    public Integer getIsusid() {
        return isusid;
    }

    public void setIsusid(Integer isusid) {
        this.isusid = isusid;
    }
    public String getUsestate() {
        return usestate;
    }

    public void setUsestate(String usestate) {
        this.usestate = usestate;
    }
    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Usestate{" +
            "isusid=" + isusid +
            ", usestate=" + usestate +
            ", enable=" + enable +
        "}";
    }
}
