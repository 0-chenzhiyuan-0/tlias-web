package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.ClazzOption;
import org.example.pojo.JobOption;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JobOption empJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList,dataList);
    }
    @Override
    public List<Map> empGenderData() {
        return empMapper.countEmpGenderData();

    }
    @Override
    public ClazzOption studentCountData() {
        List<Map<String, Object>> list = studentMapper.studentCountData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazz_name")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new ClazzOption(clazzList,dataList);
    }
    @Override
    public List<Map> studentDegreeData() {
        return studentMapper.studentDegreeData();
    }
}
