package com.oyhj.sys.service;

import com.oyhj.common.vo.Result;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Delete;
import com.oyhj.sys.entity.StaffWage;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author oyhj
 * @since 2023-03-18
 */
public interface IStaffWageService extends IService<StaffWage> {



    List<StaffWage> greanWage(String gentime1) throws ParseException;

}
