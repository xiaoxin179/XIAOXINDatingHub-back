package com.xiaoxin.datinghubback.controller.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author:XIAOXIN
 * @date:2023/12/13
 **/
@Data
@NoArgsConstructor
@Getter
@Setter
public class UserRquest {
    private String username;
    private String password;
    private String email;
    private String name;
    private String emailCode;
}
