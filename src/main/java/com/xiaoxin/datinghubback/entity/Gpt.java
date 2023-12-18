package com.xiaoxin.datinghubback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
* ai问答记录表
* </p>
*
* @author xiaoxin
* @since 2023-12-18
*/
@Getter
@Setter
@ApiModel(value = "Gpt对象", description = "ai问答记录表")
public class Gpt implements Serializable {

private static final long serialVersionUID = 1L;

    // 主键
    @ApiModelProperty("主键")
    @Alias("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 发送的内容
    @ApiModelProperty("发送的内容")
    @Alias("发送的内容")
    private String req;

    // 使用者uid
    @ApiModelProperty("使用者uid")
    @Alias("使用者uid")
    private String uid;

    // gpt的回复
    @ApiModelProperty("gpt的回复")
    @Alias("gpt的回复")
    private String resp;
}