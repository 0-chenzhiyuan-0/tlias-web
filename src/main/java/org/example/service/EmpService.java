package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {
    PageResult<Emp> page(EmpQueryParam param);

    void save(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);
}
