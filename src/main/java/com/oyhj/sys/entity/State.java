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
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "state_id", type = IdType.AUTO)
    private Integer stateId;

    private String stateName;

    private String stateDec;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    public String getStateDec() {
        return stateDec;
    }

    public void setStateDec(String stateDec) {
        this.stateDec = stateDec;
    }

    @Override
    public String toString() {
        return "State{" +
            "stateId=" + stateId +
            ", stateName=" + stateName +
            ", stateDec=" + stateDec +
        "}";
    }
}
