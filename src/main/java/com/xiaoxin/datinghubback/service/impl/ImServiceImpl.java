package com.xiaoxin.datinghubback.service.impl;

import com.xiaoxin.datinghubback.entity.Im;
import com.xiaoxin.datinghubback.mapper.ImMapper;
import com.xiaoxin.datinghubback.service.IImService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天记录表 服务实现类
 * </p>
 *
 * @author xiaoxin
 * @since 2023-12-17
 */
@Service
public class ImServiceImpl extends ServiceImpl<ImMapper, Im> implements IImService {

}
