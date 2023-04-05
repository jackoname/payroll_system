package com.oyhj.sys.service;

import com.oyhj.sys.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oyhj
 * @since 2023-03-09
 */
public interface IUsersService extends IService<Users> {

    Map<String, Object> login(Users user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    void addUser(Users user);

    Users getUserById(Integer userId);

    void updateUser(Users user);

    void deleteUserById(Integer userId);
}
