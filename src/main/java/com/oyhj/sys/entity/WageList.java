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
    private Integer flag;
    private Date creattime;

    private Date endtime;

    private String state;

    private String des;

    @TableField(exist = false)
    private String post_name;
    @TableField(exist = false)
    private  String depart;
    @TableField(exist = false)
    private  String cname;
    private  String name;
    @TableField(exist = false)
    private Integer option[];
    @TableField(exist = false)
    private Integer tag;
}
// {"option":[7,6],"endtime":"2023-04-09T16:00:00.000Z","userId":31,"wageName":"大苏打撒","wage":"87897","des":"撒娇客户端"}
