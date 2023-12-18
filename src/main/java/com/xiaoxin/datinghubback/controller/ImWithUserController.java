package com.xiaoxin.datinghubback.controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.xiaoxin.datinghubback.common.Result;
import com.xiaoxin.datinghubback.service.IImWithUserService;

import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* VIEW 前端控制器
* </p>
*
* @author xiaoxin
* @since 2023-12-18
*/
@RestController
@RequestMapping("/im-with-user")
public class ImWithUserController {

    @Resource
    private IImWithUserService imWithUserService;


    @GetMapping
    public Result findAll() {
        return Result.success(imWithUserService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(imWithUserService.getById(id));
    }

}
