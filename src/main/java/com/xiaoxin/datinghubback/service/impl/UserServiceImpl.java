package com.xiaoxin.datinghubback.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaoxin.datinghubback.entity.User;
import com.xiaoxin.datinghubback.exception.ServiceException;
import com.xiaoxin.datinghubback.mapper.UserMapper;
import com.xiaoxin.datinghubback.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoxin
 * @since 2023-08-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User login(User user) {
        User dbUser = null;
        try {
            dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()));
        } catch (Exception e) {
            throw new RuntimeException("系统异常");
        }
        if (dbUser == null) {
            throw new ServiceException("未找到用户");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或者密码错误");
        }
        return dbUser;
    }

    @Override
    public User register(User user) {
        try {
            saveUser(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("数据库异常");
        }
    }

    private User saveUser(User user) {
        User dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()));
        if (dbUser != null) {
            throw new ServiceException("用户已经注册");
        }
        //        设置用户昵称
        if (StrUtil.isBlank(user.getName())) {
            user.setName("小新交友用户"+new Random().nextInt(9000) + 1000);
        }
        user.setId(null);
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword("123");//设置默认密码
        }
//        设置用户的唯一标识
        user.setUid(IdUtil.fastSimpleUUID());
        boolean saveUser = save(user);
        if (!saveUser) {
            throw new RuntimeException("注册失败");
        }
        return user;
    }
}
