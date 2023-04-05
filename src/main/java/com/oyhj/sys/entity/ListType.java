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
@TableName("list_type")
public class ListType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "list_id", type = IdType.AUTO)
    private Integer listId;

    private String name;

    private String dsc;

    private LocalDateTime createtime;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "ListType{" +
            "listId=" + listId +
            ", name=" + name +
            ", dsc=" + dsc +
            ", createtime=" + createtime +
        "}";
    }
}
