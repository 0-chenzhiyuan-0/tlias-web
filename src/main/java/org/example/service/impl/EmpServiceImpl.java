package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;


    //    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize){
//        long total = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        return new PageResult<>(total, rows);
//    }
    @Override
    public PageResult<Emp> page(EmpQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Emp> list = empMapper.list(param);
        Page<Emp> empPage = (Page<Emp>) list;
        return new PageResult<Emp>(empPage.getTotal(), empPage.getResult());
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }


    }

}
