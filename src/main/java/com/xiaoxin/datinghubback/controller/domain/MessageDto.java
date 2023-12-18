package com.xiaoxin.datinghubback.controller.domain;

import cn.hutool.core.date.DateTime;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author:XIAOXIN
 * @date:2023/12/17
 **/
@Data
@Builder
public class MessageDto {
    private String uid;
    private String username;
    private String avatar;
    private String text;
    private Date create_time;

}
