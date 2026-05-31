package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.ClazzOption;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result empJobData(){
        log.info("生成员工数据报表");
        JobOption jobOption = reportService.empJobData();
        return Result.success(jobOption);
    }
    @GetMapping("/empGenderData")
    public Result empGenderData(){
        log.info("生成员工性别数据报表");
        List<Map> list = reportService.empGenderData();
        return Result.success(list);
    }
    @GetMapping("/studentCountData")
    public Result studentCountData(){
        log.info("生成班级数据报表");
        ClazzOption list = reportService.studentCountData();
        return Result.success(list);
    }
    @GetMapping("/studentDegreeData")
    public Result studentDereeData(){
        log.info("生成班级数据报表");
        List<Map> list = reportService.studentDegreeData();
        return Result.success(list);
    }


}
