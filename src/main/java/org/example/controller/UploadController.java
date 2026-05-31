package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.example.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(String name,Integer age, MultipartFile file) throws Exception {
        log.info("文件上传开始");
        log.info("文件名：{}",file.getOriginalFilename());
        log.info("文件大小：{}",file.getSize());
        log.info("文件类型：{}",file.getContentType());
        if (!file.isEmpty()){
            String originalFileName = file.getOriginalFilename();
            String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString() + extName;
            String url=aliyunOSSOperator.upload(file.getBytes(), uniqueFileName);

            return Result.success(url);
        }
        return Result.error("上传失败");
    }
}
