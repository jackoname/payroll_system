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

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-02
 */
@TableName("wage_list")
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @TableField(exist = false)
    private String post_name;
    @TableField(exist = false)
    private  String depart;

    private  String name;

}
