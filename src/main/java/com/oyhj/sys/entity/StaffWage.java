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
 * @since 2023-04-19
 */
@TableName("staff_wage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffWage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工id和信息id一致
     */
    @TableId(value = "wage_id", type = IdType.AUTO)
    private Integer wageId;

    /**
     * 基本工资
     */
    private Double basicWage;

    /**
     * 个人所得税
     */
    private Double perInTax;

    /**
     * 基本养老保险
     */
    private Double endowmentIn;

    /**
     * 基本医疗保险
     */
    private Double medicalIn;

    /**
     * 失业保险
     */
    private Double unemploymentIn;

    /**
     * 工商保险
     */
    private Double employmentInjuryIn;

    /**
     * 生育保险
     */
    private Double maternityIn;

    /**
     * 住房公积金
     */
    private Double housingAccFund;

    /**
     * 当月绩效
     */
    private Double performance;

    /**
     * 奖金
     */
    private Double bonus;

    /**
     * 罚款金额
     */
    private Double fine;

    /**
     * 缺勤天数
     */
    private Integer leaveDays;

    /**
     * 其他扣除
     */
    private Double otherDeduct;

    /**
     * 综合工资
     */
    private Double comprehensiveSalary;

    /**
     * 综合工资
     */@TableField(exist = false)
    private Double sum;
    /**
     * 工资发放状态
     */
    private Integer handOut;

    /**
     * 状态
     */
    private Integer state;

    private Integer userId;

    private Date begin;

    private Date endtime;
    @TableField(value = "creatTime")
    private Date creatTime;
    @TableField(value = "allInsure ")
    private Double allInsure;

    private String name;

    private String statename;
    private  Date updatetime;
}
