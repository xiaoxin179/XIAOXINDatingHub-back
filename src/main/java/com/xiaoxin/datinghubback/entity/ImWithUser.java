package com.xiaoxin.datinghubback.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaoxin.datinghubback.common.LDTConfig;
import lombok.Getter;
import lombok.Setter;

/**
* <p>
* VIEW
* </p>
*
* @author xiaoxin
* @since 2023-12-18
*/
@Getter
@Setter
@TableName("im_with_user")
@ApiModel(value = "ImWithUser对象", description = "VIEW")
public class ImWithUser implements Serializable {

private static final long serialVersionUID = 1L;

    // 主键
    @ApiModelProperty("主键")
    @Alias("主键")
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

    // 用户头像
    @ApiModelProperty("用户头像")
    @Alias("用户头像")
    private String avatar;

    // 用户名
    @ApiModelProperty("用户名")
    @Alias("用户名")
    private String username;
}