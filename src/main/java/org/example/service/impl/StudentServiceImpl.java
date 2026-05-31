package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult<Student> page(StudentQueryParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        List<Student> list = studentMapper.page(param);
        Page<Student> studentPage = (Page<Student>) list;
        return new PageResult<Student>(studentPage.getTotal(), studentPage.getResult());
    }
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.save(student);
    }
    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
     }
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }
    @Override
    public void deleteById(Integer id){
        studentMapper.deleteById(id);
    }
    @Override
    public void updateviolationScore(Integer id, Integer violationScore) {
        studentMapper.updateviolationScore(id,violationScore,LocalDateTime.now());

    }

}

