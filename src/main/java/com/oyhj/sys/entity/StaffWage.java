package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-03-18
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
    private String wageId;

    /**
     * 姓名和staff_data中一致
     */
    private String name;

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
     * 工资发放状态
     */
    private Integer handOut;

    /**
     * 状态
     */
    private Integer state;

    private Double allInsure;


}
