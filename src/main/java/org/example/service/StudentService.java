package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;


public interface StudentService {

    PageResult<Student> page(StudentQueryParam param);

    void save(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void deleteById(Integer id);

    void updateviolationScore(Integer id, Integer violationScore);
}
