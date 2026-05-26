package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;

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
}
