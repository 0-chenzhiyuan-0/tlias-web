package org.example.service;

import org.example.pojo.ClazzOption;
import org.example.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption empJobData();

    List<Map> empGenderData();

    ClazzOption studentCountData();

    List<Map> studentDegreeData();
}
