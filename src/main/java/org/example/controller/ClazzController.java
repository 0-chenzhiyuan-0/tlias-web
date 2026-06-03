package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.LogOperation;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @GetMapping
    public Result list(ClazzQueryParam param){
        log.info("分页查询班级信息，参数：param={}",param);
        PageResult<Clazz> pageResult = clazzService.page(param);
        return Result.success(pageResult);
    }
    @LogOperation
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级信息，参数：clazz={}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询班级信息，id：{}",id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("更新班级信息，参数：clazz={}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级信息，id：{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级信息");
        List<Clazz> list = clazzService.list();
        return Result.success(list);
    }
}
