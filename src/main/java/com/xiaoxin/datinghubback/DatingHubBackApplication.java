package com.xiaoxin.datinghubback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoxin.datinghubback.mapper")
public class DatingHubBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatingHubBackApplication.class, args);
    }

}
