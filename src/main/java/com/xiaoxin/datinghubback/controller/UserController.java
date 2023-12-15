package com.xiaoxin.datinghubback.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.datinghubback.common.Result;
import com.xiaoxin.datinghubback.service.IUserService;
import com.xiaoxin.datinghubback.entity.User;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
*
* @author xiaoxin
* @since 2023-08-02
*/
@RestController
@Api(tags="user表的接口")
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    @ApiOperation("保存用户")
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("更新用户")
    public Result update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("逻辑删除")
    public Result delete(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    @ApiOperation("多个删除")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    @ApiOperation("获取全部用户")
    public Result findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return Result.success(userService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
    * 导出接口
    */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("User信息表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    @GetMapping("/getAllUserList")
    @ApiOperation("获取所有的用户列表")
    public Result getAllUserList() throws Exception {
        return Result.success(userService.getAllUserList());
    }
}
