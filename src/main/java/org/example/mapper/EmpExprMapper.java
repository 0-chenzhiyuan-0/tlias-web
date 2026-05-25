package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;

import java.util.List;

public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> empExprList);
}
