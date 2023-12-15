package com.xiaoxin.datinghubback.mapper;

import com.xiaoxin.datinghubback.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoxin
 * @since 2023-08-02
 */
public interface UserMapper extends BaseMapper<User> {

    ArrayList<String> getAllUserList();
}
