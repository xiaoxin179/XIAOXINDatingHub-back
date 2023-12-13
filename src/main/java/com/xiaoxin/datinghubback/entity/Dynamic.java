package com.xiaoxin.datinghubback.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import cn.hutool.core.annotation.Alias;
import com.xiaoxin.datinghubback.common.LDTConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

/**
* <p>
* 动态表
* </p>
*
* @author xiaoxin
* @since 2023-12-13
*/
@Getter
@Setter
@ApiModel(value = "Dynamic对象", description = "动态表")
public class Dynamic implements Serializable {

private static final long serialVersionUID = 1L;

    private Integer id;

    // 标题
    @ApiModelProperty("标题")
    @Alias("标题")
    private String name;

    // 内容
    @ApiModelProperty("内容")
    @Alias("内容")
    private String content;

    // 头像
    @ApiModelProperty("头像")
    @Alias("头像")
    private String imgs;

    // 简介
    @ApiModelProperty("简介")
    @Alias("简介")
    private String description;

    // 创建时间
    @ApiModelProperty("创建时间")
    @Alias("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonDeserialize(using = LDTConfig.CmzLdtDeSerializer.class)
    @JsonSerialize(using = LDTConfig.CmzLdtSerializer.class)
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty("更新时间")
    @Alias("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonDeserialize(using = LDTConfig.CmzLdtDeSerializer.class)
    @JsonSerialize(using = LDTConfig.CmzLdtSerializer.class)
    private LocalDateTime updateTime;

    // 发布用户
    @ApiModelProperty("发布用户")
    @Alias("发布用户")
    private String userId;
}