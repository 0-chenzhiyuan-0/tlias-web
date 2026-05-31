package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    @Select("select count(*) from student where clazz_id=#{clazzId}")
    Integer countById(Integer clazzId);

    List<Student> page(StudentQueryParam param);

    @Insert("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time)" +
            "values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void save(Student student);

    @Select("select * from student where id= #{id}")
    Student getById(Integer id);

    @Update("update student set name=#{name},no=#{no},gender=#{gender},phone=#{phone},id_card=#{idCard},is_college=#{isCollege},violation_count=#{violationCount}," +
            "address=#{address},degree=#{degree},violation_score=#{violationScore},graduation_date=#{graduationDate},clazz_id=#{clazzId},update_time=#{updateTime} where id=#{id}")
    void update(Student student);

    @Delete("delete from student where id= #{id}")
    void deleteById(Integer id);

    @Update("update student set violation_score=violation_score+#{violationScore},violation_count=violation_count+1,update_time=#{updateTime} where id=#{id}")
    void updateviolationScore(Integer id, Integer violationScore, LocalDateTime updateTime);


    @MapKey("clazz_name")
    List<Map<String, Object>> studentCountData();

    @MapKey("name")
    List<Map> studentDegreeData();
}
