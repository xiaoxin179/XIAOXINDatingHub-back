package com.xiaoxin.datinghubback.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.datinghubback.common.Result;
import org.springframework.web.multipart.MultipartFile;
import com.xiaoxin.datinghubback.service.IGptService;
import com.xiaoxin.datinghubback.entity.Gpt;

import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* ai问答记录表 前端控制器
* </p>
*
* @author xiaoxin
* @since 2023-12-18
*/
@RestController
@RequestMapping("/gpt")
public class GptController {

    @Resource
    private IGptService gptService;

    @PostMapping
    public Result save(@RequestBody Gpt gpt) {
        gptService.save(gpt);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Gpt gpt) {
        gptService.updateById(gpt);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        gptService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        gptService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(gptService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(gptService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Gpt> queryWrapper = new QueryWrapper<Gpt>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return Result.success(gptService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}
