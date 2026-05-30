package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface EmpMapper {
//    老款分页查询
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public long count();
//
//    @Select("select e.*,d.name empName from emp e left join dept d on e.dept_id = d.id order by update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);
//     @Select("select e.*,d.name empName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
     List<Emp> list(EmpQueryParam param);
     @Options(useGeneratedKeys = true,keyProperty = "id")
     @Insert("insert into emp(username,name,gender,phone,job,salary,image,dept_id,entry_date,create_time,update_time) " +
             "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{deptId},#{entryDate},#{createTime},#{updateTime})")
     void insert(Emp emp);

     void deleteByEmpId(List<Integer> ids);

     Emp getById(Integer id);

     void updateById(Emp emp);

     @MapKey("pos")
     List<Map<String, Object>> countEmpJobData();
     @MapKey("name")
     List<Map> countEmpGenderData();

     @Select("select id,username,password,name,gender,image,job,salary,entry_date,dept_id,create_time,update_time from emp ")
     List<Emp> findAll();
}
