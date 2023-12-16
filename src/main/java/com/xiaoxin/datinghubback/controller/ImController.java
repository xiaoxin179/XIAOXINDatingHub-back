package com.xiaoxin.datinghubback.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.datinghubback.common.Result;
import org.springframework.web.multipart.MultipartFile;
import com.xiaoxin.datinghubback.service.IImService;
import com.xiaoxin.datinghubback.entity.Im;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 聊天记录表 前端控制器
* </p>
*
* @author xiaoxin
* @since 2023-12-16
*/
@RestController
@RequestMapping("/im")
public class ImController {

    @Resource
    private IImService imService;

    @PostMapping
    public Result save(@RequestBody Im im) {
        imService.save(im);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Im im) {
        imService.updateById(im);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        imService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        imService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(imService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(imService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Im> queryWrapper = new QueryWrapper<Im>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return Result.success(imService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}
