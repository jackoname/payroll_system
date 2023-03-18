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
@TableName("staff_data")
public class StaffData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataId;

    private String name;

    private Integer age;

    private String idCardNumber;

    private String telephoneNumber;

    private String department;

    private String post;

    private String date1;

    private String sex;

    private String remarks;

    private String address;

    private Integer state;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "StaffData{" +
            "dataId=" + dataId +
            ", name=" + name +
            ", age=" + age +
            ", idCardNumber=" + idCardNumber +
            ", telephoneNumber=" + telephoneNumber +
            ", department=" + department +
            ", post=" + post +
            ", date1=" + date1 +
            ", sex=" + sex +
            ", remarks=" + remarks +
            ", address=" + address +
            ", state=" + state +
        "}";
    }
}
