package com.xiaoxin.datinghubback.controller;
import com.xiaoxin.datinghubback.common.Result;
import com.xiaoxin.datinghubback.utils.AliyunUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author:XIAOXIN
 * @date:2023/11/23
 **/
@RestController
@Slf4j
@RequestMapping("/ai")
public class AiRespController {
    @GetMapping
    public Result ai(@RequestParam String pro) {
        log.info("传递进来的参数："+pro);

        String resp = AliyunUtils.ai(pro);
        log.info("response: " + resp);
        return Result.success(resp);
    }


}