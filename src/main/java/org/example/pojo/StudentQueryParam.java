package org.example.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    private String name;
    private Integer clazzId;
    private Integer page=1;
    private Integer pageSize=10;
    private Integer degree;
}
