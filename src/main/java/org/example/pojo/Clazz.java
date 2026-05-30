package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private Integer id;
    private LocalDate beginDate;
    private LocalDateTime createTime;
    private Integer masterId;
    private LocalDate endDate;
    private String name;
    private String room;
    private Integer subject;
    private LocalDateTime updateTime;
    private String status;
    private String masterName;
}
