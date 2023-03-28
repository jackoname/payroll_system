package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaocai
 * @since 2023-03-25
 */
@TableName("role")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("roledec")
    private String roledec;
    @TableField("rolename")
    private String rolename;

    @TableField(exist = false)
    private List<Integer> menuidList;

}
