package com.xiaoxin.datinghubback.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaoxin.datinghubback.controller.domain.UserRquest;
import com.xiaoxin.datinghubback.entity.User;
import com.xiaoxin.datinghubback.exception.ServiceException;
import com.xiaoxin.datinghubback.mapper.UserMapper;
import com.xiaoxin.datinghubback.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.datinghubback.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaoxin
 * @since 2023-08-02
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Map<String, Long> CODE_MAP = new ConcurrentHashMap<>();
    private static final long TIME_MIN5 = 5 * 60 * 1000;
    @Resource
    EmailUtils emailUtils;
    @Resource
    UserMapper userMapper;

    @Override
    public User login(UserRquest user) {
        User dbUser = null;
        try {
            dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()));
        } catch (Exception e) {
            throw new RuntimeException("系统异常",e);
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
    public User register(UserRquest user) {
        String emailCode = user.getEmailCode();
        Long timestamp = CODE_MAP.get(emailCode);
        if (timestamp == null) {
            throw new ServiceException("请先验证邮箱");
        }
//            处理五分钟有效
        if (timestamp + TIME_MIN5 < System.currentTimeMillis()) {
            throw new ServiceException("验证码过期");
        }
        //把emailCode从缓存中去掉
        CODE_MAP.remove(emailCode);
        try {
//            校验邮箱
            User saveUser = new User();
//            使用beanUtil直接把saveUser中的属性复制到user中
            BeanUtils.copyProperties(user, saveUser);
            return saveUser(saveUser);
        } catch (Exception e) {
            throw new RuntimeException("数据库异常", e);
        }
    }

    @Override
    public void sendEmail(String email, String type) {
        String code = RandomUtil.randomNumbers(6);
        log.info("本次生成的验证码为：{}", code);
        String content = "<b>尊敬的XIAOXIN校园交友网站的用户你好：</b><br/>@nbsp;@nbsp;@nbsp;@nbsp;@nbsp;@nbsp;您的验证码为：{}," + "有效期为五分钟。<br><br><br><br><br><br><b>XIAOXIN交友网</b>";
        String html = StrUtil.format(content, code);
        if ("REGISTER".equals(type)) {
//            查询邮箱是否已经被注册
            User user = getOne(new QueryWrapper<User>().eq("email", email));
            if (user != null) {
                throw new ServiceException("邮箱已经被注册");
            }
//            这一行代码可能存在问题，可以做成异步
            ThreadUtil.execAsync(() -> {//多线程异步请求，无论执行成功还是失败，都会向下一步执行，可以防止网咯阻塞
                emailUtils.sendHtml("XIAOXIN交友邮箱注册验证", html, email);
            });
            CODE_MAP.put(code, System.currentTimeMillis());
        }
    }

    public User saveUser(User user) {
        User dbUser = getOne(new UpdateWrapper<User>().eq("username", user.getUsername()));
        if (dbUser != null) {
            throw new ServiceException("用户名已经注册，请更换用户名");
        }
        //        设置用户昵称
        if (StrUtil.isBlank(user.getName())) {
            user.setName("小新交友用户" + new Random().nextInt(9000) + 1000);
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

    @Override
    public ArrayList<String> getAllUserList() {
        ArrayList<String> allUserList = userMapper.getAllUserList();
        return allUserList;
    }

    @Override
    public User updatepassword(User user) {
        User updateUser = getOne(new QueryWrapper<User>().eq("id", user.getId()));
        if (!updateUser.getIdcard().equals(user.getIdcard())) {
            throw new ServiceException("校验错误，您输入的身份证号和数据库中不匹配");
        }
        saveOrUpdate(user);
        return user;
    }
}
