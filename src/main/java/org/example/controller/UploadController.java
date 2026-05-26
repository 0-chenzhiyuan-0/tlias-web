package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(String name,Integer age, MultipartFile file) throws IOException {
        log.info("文件上传开始");
        log.info("文件名：{}",file.getOriginalFilename());
        log.info("文件大小：{}",file.getSize());
        log.info("文件类型：{}",file.getContentType());
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + suffix;
        file.transferTo(new java.io.File("C:/Users/34615/Desktop/" + newFileName));
        return Result.success();
    }
}
