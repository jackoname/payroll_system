package com.oyhj.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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

    public String getWageId() {
        return wageId;
    }

    public void setWageId(String wageId) {
        this.wageId = wageId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Double getBasicWage() {
        return basicWage;
    }

    public void setBasicWage(Double basicWage) {
        this.basicWage = basicWage;
    }
    public Double getPerInTax() {
        return perInTax;
    }

    public void setPerInTax(Double perInTax) {
        this.perInTax = perInTax;
    }
    public Double getEndowmentIn() {
        return endowmentIn;
    }

    public void setEndowmentIn(Double endowmentIn) {
        this.endowmentIn = endowmentIn;
    }
    public Double getMedicalIn() {
        return medicalIn;
    }

    public void setMedicalIn(Double medicalIn) {
        this.medicalIn = medicalIn;
    }
    public Double getUnemploymentIn() {
        return unemploymentIn;
    }

    public void setUnemploymentIn(Double unemploymentIn) {
        this.unemploymentIn = unemploymentIn;
    }
    public Double getEmploymentInjuryIn() {
        return employmentInjuryIn;
    }

    public void setEmploymentInjuryIn(Double employmentInjuryIn) {
        this.employmentInjuryIn = employmentInjuryIn;
    }
    public Double getMaternityIn() {
        return maternityIn;
    }

    public void setMaternityIn(Double maternityIn) {
        this.maternityIn = maternityIn;
    }
    public Double getHousingAccFund() {
        return housingAccFund;
    }

    public void setHousingAccFund(Double housingAccFund) {
        this.housingAccFund = housingAccFund;
    }
    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }
    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }
    public Integer getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }
    public Double getOtherDeduct() {
        return otherDeduct;
    }

    public void setOtherDeduct(Double otherDeduct) {
        this.otherDeduct = otherDeduct;
    }
    public Double getComprehensiveSalary() {
        return comprehensiveSalary;
    }

    public void setComprehensiveSalary(Double comprehensiveSalary) {
        this.comprehensiveSalary = comprehensiveSalary;
    }
    public Integer getHandOut() {
        return handOut;
    }

    public void setHandOut(Integer handOut) {
        this.handOut = handOut;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "StaffWage{" +
            "wageId=" + wageId +
            ", name=" + name +
            ", basicWage=" + basicWage +
            ", perInTax=" + perInTax +
            ", endowmentIn=" + endowmentIn +
            ", medicalIn=" + medicalIn +
            ", unemploymentIn=" + unemploymentIn +
            ", employmentInjuryIn=" + employmentInjuryIn +
            ", maternityIn=" + maternityIn +
            ", housingAccFund=" + housingAccFund +
            ", performance=" + performance +
            ", bonus=" + bonus +
            ", fine=" + fine +
            ", leaveDays=" + leaveDays +
            ", otherDeduct=" + otherDeduct +
            ", comprehensiveSalary=" + comprehensiveSalary +
            ", handOut=" + handOut +
            ", state=" + state +
        "}";
    }
}
