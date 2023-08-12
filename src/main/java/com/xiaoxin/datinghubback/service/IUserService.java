package com.xiaoxin.datinghubback.service;

import com.xiaoxin.datinghubback.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxin
 * @since 2023-08-02
 */
public interface IUserService extends IService<User> {

    User login(User user);

    User register(User user);

}
