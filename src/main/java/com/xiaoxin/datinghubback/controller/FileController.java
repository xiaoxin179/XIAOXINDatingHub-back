package com.xiaoxin.datinghubback.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.xiaoxin.datinghubback.common.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author:XIAOXIN
 * @date:2023/08/31 文件处理接口
 **/
@RestController
@Api(tags = "文件处理接口下")
@RequestMapping("/file")
@Slf4j
public class FileController {
    private static final String FILE_DIR = "/files/";
    private String titleCom;
    @Autowired
    private HttpServletRequest request;
    @Value("${file.upload.path:}")
    private String uploadPath;
    @Value("${server.port:9090}")
    private String serverPort;
    @Value("${file.download.ip:localhost}")
    private String downloadIp;

    /*
     *文件上传接口
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        if (StrUtil.isBlank(uploadPath)) {
            uploadPath = System.getProperty("user.dir");
        }
        String mainName = FileUtil.mainName(file.getOriginalFilename());
        String endName = FileUtil.extName(file.getOriginalFilename());
        String fileUnique = IdUtil.fastSimpleUUID();
        String fileFullName = fileUnique + StrUtil.DOT + endName;

        // 构建完整的文件路径
        String fullFilePath = uploadPath + FILE_DIR + fileFullName;

        String originalFileName = file.getOriginalFilename();
        long size = file.getSize();
        String name = file.getName();
        HttpSession session = request.getSession();
        titleCom=originalFileName;
        log.info("存储在文件上传接口中的titleCom："+titleCom);
        log.info("{},{},{}", originalFileName, size, name);

        File updateFile = new File(fullFilePath);
        File parentFile = updateFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        file.transferTo(updateFile);
        return Result.success("http://" + downloadIp + ":" + serverPort + "/file/" + fileFullName);
    }

    @GetMapping("/{fileFullName}")
    public void downloadFile(@PathVariable String fileFullName, HttpServletResponse response) throws IOException {
        String fullUploadPath = getFillUploadPath(fileFullName);
        byte[] bytes = FileUtil.readBytes(fullUploadPath);
        ServletOutputStream os = response.getOutputStream();
        os.write(bytes);
        os.flush();
        os.close();
    }
    /*
     *获取完整路径的函数
     */

    private String getFillUploadPath(String fileFullName) {
        if (StrUtil.isBlank(uploadPath)) {
            uploadPath = System.getProperty("user.dir");
        }
        return uploadPath + FILE_DIR + fileFullName;
    }
//   为了共享同一个session
}