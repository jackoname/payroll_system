package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaocai
 * @since 2023-04-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taxandsafe implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Double taxMe;

    private Double submoney;

    private String dsc;

    private String name;

    private Integer flag;

    private Double taxCom;

    private Double lowmoney;


}
