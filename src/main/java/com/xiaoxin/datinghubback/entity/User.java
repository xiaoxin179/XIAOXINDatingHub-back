package com.xiaoxin.datinghubback.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* <p>
* 
* </p>
*
* @author xiaoxin
* @since 2023-08-02
*/
@Getter
@Setter
@Data
@NoArgsConstructor
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String name;
    private String password;
    private String uid;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
    //    逻辑删除字段
    @TableLogic(value = "0", delval = "id")
    private Integer deleted;
    private String email;
}