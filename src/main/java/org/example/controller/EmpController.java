package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result list(EmpQueryParam  param){
        log.info("分页查询员工信息，参数：param={}",param);
        PageResult<Emp> pageResult = empService.page(param);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("添加员工信息，参数：emp={}",emp);
        empService.save(emp);
        return  Result.success();
    }
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}",ids);
        empService.deleteById(ids);
        return Result.success();
    }
    @GetMapping ("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("更新员工信息，id：{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息，参数：emp={}",emp);
        empService.update(emp);
        return Result.success();
    }
    @GetMapping("/list")
    public Result list(){
        log.info("查询全部员工信息");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }


}
