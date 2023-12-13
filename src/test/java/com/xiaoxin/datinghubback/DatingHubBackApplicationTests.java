package com.xiaoxin.datinghubback;

import com.xiaoxin.datinghubback.entity.User;
import com.xiaoxin.datinghubback.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DatingHubBackApplicationTests {
    @Resource
    IUserService userService;
    @Test
    void contextLoads() {
        boolean b = userService.removeById(14);
        if (b) {
            System.out.println("删除id为6的用户成功");
        }
        User byId = userService.getById(14);
        if (byId == null) {
            System.out.println("用户id为14的已经不存在");

        }

    }

    @Test
    void getUserByName() {

    }

}
