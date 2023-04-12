package com.oyhj.sys;

import com.oyhj.sys.controller.UserBasewageController;
import com.oyhj.sys.entity.UserBasewage;
import com.oyhj.sys.mapper.UserBasewageMapper;
import com.oyhj.sys.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Component
public class test {
    @Resource
    UserBasewageMapper userBasewageMapper;
    @Resource
    UsersMapper usersMapper;
    @Test
    void test(){
        System.out.println(usersMapper.getRoleNameByUserId(3));
      //  System.out.println("-----"+userBasewageMapper.getUserWageList());
    }
}
