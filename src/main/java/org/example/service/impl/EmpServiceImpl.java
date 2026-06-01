package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpService;
import org.example.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Transactional
    @Override
    public void deleteById(List<Integer> ids) {
        empMapper.deleteByEmpId(ids);
        empExprMapper.deleteByEmpExprId(ids);
    }
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }
    @Transactional
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //将获取的Intger封装到数组里，是为了对齐deleteByEmpExprId的参数
        empExprMapper.deleteByEmpExprId(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }
    @Override
    public List<Emp> list(){
        return empMapper.findAll();
    };

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            Map<String, Object> dataMap = new HashMap<>() ;
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());
            String jwt = JwtUtils.generateJwt(dataMap);
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }

}
