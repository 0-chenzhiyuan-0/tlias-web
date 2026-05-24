package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping
    public Result list(EmpQueryParam  param){
        log.info("分页查询员工信息，参数：param={}",param);
        PageResult<Emp> pageResult = empService.page(param);
        return Result.success(pageResult);
    }

}
