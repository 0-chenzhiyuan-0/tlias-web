package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;

import java.time.LocalDate;


public interface EmpService {
    PageResult<Emp> page(EmpQueryParam param);

    void add(Emp emp);
}
