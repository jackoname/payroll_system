package com.oyhj.sys.service.impl;

import com.oyhj.sys.entity.*;
import com.oyhj.sys.mapper.StaffWageMapper;
import com.oyhj.sys.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oyhj
 * @since 2023-03-18
 */
@Service

public class StaffWageServiceImpl extends ServiceImpl<StaffWageMapper, StaffWage> implements IStaffWageService {
    @Autowired
    IWageListService wageListService;
    @Autowired
    IUserBasewageService userBasewageService;
    @Autowired
    ITaxandsafeService taxandsafeService;
    @Autowired
    IStaffWageService staffWageService;
    @Autowired
    IUsersService usersService;

    @Override
    public List<StaffWage> greanWage(String gentime1) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date gentime= formatter.parse(gentime1);
        System.out.println(gentime+" -----" +gentime1);
        List<Users> userslist = usersService.list();//用户
        List<UserBasewage> ubwlists = userBasewageService.list();//基础工资
        List<WageList> wagelists1 = wageListService.list();//事项工资+绩效工资所有
        List<WageList> wagelists=new ArrayList<>();//当月
        wagelists1.forEach(item->{
            if(item.getEndtime().getYear()==gentime.getYear()&&item.getEndtime().getMonth()==gentime.getMonth()){
                wagelists.add(item);
            }
        });
        List<Taxandsafe> stlists = taxandsafeService.list();//税收社保
        @Data
        @AllArgsConstructor
        @NoArgsConstructor

        class personWage{
            Users user;
            UserBasewage userBasewage;
            List<WageList> wageLists;
        }
        List<personWage> personWages=new ArrayList<>();
        userslist.forEach(user ->{
            UserBasewage uBasewage=null;
            List<WageList> wageLists=new ArrayList<>();
            for (int i = 0; i < ubwlists.size(); i++) {
                if ((ubwlists.get(i).getUserid()==user.getUserId()&&ubwlists.get(i).getVersion()==1)) {
                    uBasewage=ubwlists.get(i);
                    break;
                }
            }
            wagelists.forEach(wageList -> {
                if (wageList.getUserId()==user.getUserId()){
                    wageLists.add(wageList);
                }
            });
            personWages.add(new personWage(user,uBasewage,wageLists));
        } );

        List<StaffWage> staffWages=new ArrayList<>();
        personWages.forEach(item->{
            StaffWage staffWage =new StaffWage();
            staffWage.setName(item.user.getName());
            staffWage.setHandOut(-1);//订单状态
            staffWage.setUserId(item.user.getUserId());
            staffWage.setState(0);//状态
            staffWage.setBasicWage(item.userBasewage.getBasewage());
            /**************绩效**************/
            double jx=0.0;
            for (int i = 0; i < item.wageLists.size(); i++) {
                if(item.wageLists.get(i).getFlag()==1) {
                    jx = item.wageLists.get(i).getWage();
                    break;
                }
            }
            staffWage.setPerformance(jx);//当月绩效
            /****************罚款************/
            double fk=0.0;
            for (int i = 0; i < item.wageLists.size(); i++) {
                if(item.wageLists.get(i).getFlag()!=1&&item.wageLists.get(i).getWage()<0) {
                    fk+= item.wageLists.get(i).getWage();
                }
            }
            staffWage.setFine(fk);//罚款金额
            /****************奖励金额************/
            double jl=0.0;
            for (int i = 0; i < item.wageLists.size(); i++) {
                if(item.wageLists.get(i).getFlag()!=1&&item.wageLists.get(i).getWage()>0) {
                    jl+= item.wageLists.get(i).getWage();
                }
            }
            staffWage.setBonus(jl);//奖励金额
            double sum =fk+jl+item.userBasewage.getBasewage()+jx;
            staffWage.setSum(sum);
            /****************计算五险一金************/
            double sumwxyj=0.0;
            //养老保险2
            double personoldbx=0.0;  double comoldbx=0.0;
            for (int i = 0; i < stlists.size(); i++) {
                if ((stlists.get(i).getFlag()==2)) {
                    personoldbx=sum*stlists.get(i).getTaxMe();
                    comoldbx=sum*stlists.get(i).getTaxCom();
                    break;
                }
            }
            sumwxyj-=personoldbx;
            staffWage.setEndowmentIn(0-personoldbx);//个人保险
            //医疗保险3
            double personmedbx=0.0;  double commedbx=0.0;
            for (int i = 0; i < stlists.size(); i++) {
                if ((stlists.get(i).getFlag()==3)) {
                    personmedbx=sum*stlists.get(i).getTaxMe();
                    commedbx=sum*stlists.get(i).getTaxCom();
                    break;
                }
            }
            sumwxyj-=personmedbx;
            staffWage.setMedicalIn(0-personmedbx);//医疗保险

            //失业保险4
            double personnojobbx=0.0;  double comnojobbx=0.0;
            for (int i = 0; i < stlists.size(); i++) {
                if ((stlists.get(i).getFlag()==4)) {
                    personnojobbx=sum*stlists.get(i).getTaxMe();
                    comnojobbx=sum*stlists.get(i).getTaxCom();
                    break;
                }
            }
            sumwxyj-=personnojobbx;
            staffWage.setUnemploymentIn(0-personnojobbx);//失业保险4

            //工伤保险5
            double personhurtbx=0.0;  double comhurtbx=0.0;
            for (int i = 0; i < stlists.size(); i++) {
                if ((stlists.get(i).getFlag()==5)){
                    personhurtbx=sum*stlists.get(i).getTaxMe();
                    comhurtbx=sum*stlists.get(i).getTaxCom();
                    break;
                }
            }
            sumwxyj-=personhurtbx;
            staffWage.setEmploymentInjuryIn(0-personhurtbx);//工伤保险5

            //生育保险6
            double personbornbx=0.0;  double combornbx=0.0;
            for (int i = 0; i < stlists.size(); i++) {
                if ((stlists.get(i).getFlag()==6)){
                    personbornbx=sum*stlists.get(i).getTaxMe();
                    combornbx=sum*stlists.get(i).getTaxCom();
                    break;
                }
            }
            sumwxyj-=personbornbx;
            staffWage.setMaternityIn(0-personbornbx);//生育保险6
            //公积金7
            double personlivebx=0.0;  double comlivebx=0.0;
            for (int i = 0; i < stlists.size(); i++) {
                if ((stlists.get(i).getFlag()==7)){
                    personlivebx=sum*stlists.get(i).getTaxMe();
                    comlivebx=sum*stlists.get(i).getTaxCom();
                    break;
                }
            }
            sumwxyj-=personlivebx;
            staffWage.setHousingAccFund(0-personlivebx);//公积金7
            staffWage.setAllInsure(sumwxyj);//社保
/***********************************税前工资****************************/
            double pretax =sum+sumwxyj;//税前工资

            double low=5000.00; double taxme=0.0;
/***********************************个税****************************/
            if(pretax<=5000&&pretax>1){
                taxme=0.0;
            }
            else if(pretax>5000&&pretax<=8000){
                taxme=0.03;
            }
            else if(pretax>5000&&pretax<=8000){
                taxme=0.03;
            }
            else if(pretax>8000&&pretax<=17000){
                taxme=0.03;
            }
            else if(pretax>8000&&pretax<=17000){
                taxme=0.1;
            }
            else if(pretax>17000&&pretax<=30000){
                taxme=0.2;
            }
            else if(pretax>30000&&pretax<=40000){
                taxme=0.25;
            }
            else if(pretax>40000&&pretax<=60000){
                taxme=0.3;
            }
            else if(pretax>60000&&pretax<=85000){
                taxme=0.35;
            }
            else if(pretax>85000){
                taxme=0.45;
            }
            double pretaxmoney =-(pretax-low)*taxme;//个税
            staffWage.setPerInTax(pretaxmoney);//个税
            double comwage=pretax+pretaxmoney;
            staffWage.setComprehensiveSalary(comwage);//综合工资
            //日期
            staffWage.setEndtime(gentime);
            staffWage.setCreatTime(new Date());
            staffWage.setStatename("待确认");
            staffWages.add(staffWage);
        });
        return staffWages;
    }

}
