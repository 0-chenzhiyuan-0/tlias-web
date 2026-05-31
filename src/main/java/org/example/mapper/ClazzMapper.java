package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {

    List<Clazz> list(ClazzQueryParam param);

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})   ")
    void insert(Clazz clazz);

    @Select("select * from clazz where id=#{id} ")
    Clazz getById(Integer id);

    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId}," +
            "subject=#{subject},update_time=#{updateTime} where id=#{id}")
    void updateById(Clazz clazz);

    @Delete("delete from clazz where id=#{id}")
    void deleteByClazzId(Integer id);

    @Select("select * from clazz")
    List<Clazz> findAll();


}
