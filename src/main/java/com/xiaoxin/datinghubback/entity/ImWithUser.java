package com.xiaoxin.datinghubback.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaoxin.datinghubback.common.LDTConfig;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * @author:XIAOXIN
 * @date:2023/12/18
 **/
public class ImWithUser {
    @ApiModelProperty("主键")
    @Alias("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 发送消息的内容
    @ApiModelProperty("发送消息的内容")
    @Alias("发送消息的内容")
    private String text;

    // 发送消息的时间
    @ApiModelProperty("发送消息的时间")
    @Alias("发送消息的时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonDeserialize(using = LDTConfig.CmzLdtDeSerializer.class)
    @JsonSerialize(using = LDTConfig.CmzLdtSerializer.class)
    private LocalDateTime createTime;

    // 发送者
    @ApiModelProperty("发送者")
    @Alias("发送者")
    private String uid;
    private String username;
    private String avatar;
}
