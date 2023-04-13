package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-02
 */
@TableName("list_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "list_id", type = IdType.AUTO)
    private Integer listId;

    private String name;

    private String dsc;

    private Date createtime;
    private Integer tag;
    @TableField(exist = false)
    private String cname;
    private Integer flag;
    private Double money;
}
