package com.xiaoxin.datinghubback.service;

import com.xiaoxin.datinghubback.controller.domain.UserRquest;
import com.xiaoxin.datinghubback.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxin
 * @since 2023-08-02
 */
public interface IUserService extends IService<User> {

    User login(UserRquest user);

    User register(UserRquest user);

    void  sendEmail(String email, String type);

    User saveUser(User user);

    ArrayList<String> getAllUserList();
}
