package com.xiaoxin.datinghubback.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaoxin.datinghubback.common.LDTConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
* <p>
* 聊天记录表
* </p>
*
* @author xiaoxin
* @since 2023-12-17
*/
@Getter
@Setter
@Builder
@ApiModel(value = "Im对象", description = "聊天记录表")
public class Im implements Serializable {

private static final long serialVersionUID = 1L;

    // 主键
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
}