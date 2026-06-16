package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result list(StudentQueryParam param){
        log.info("查询所有学生信息");
        PageResult<Student> list = studentService.page(param);
        return Result.success(list);
    }
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加学生信息，参数：student={}",student);
        studentService.save(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询学生信息，id：{}",id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新学生信息，参数：student={}",student);
        studentService.update(student);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除学生信息，id：{}",id);
        studentService.deleteById(id);
        return Result.success();
    }
    @PutMapping("violation/{id}/{Score}")
    public Result updateDegree(@PathVariable Integer id,@PathVariable Integer Score){
        log.info("更新学生信息，参数：id={},degree={}",id,Score);
        studentService.updateviolationScore(id,Score);
        return Result.success();
    }


}
