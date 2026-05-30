package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;


public interface ClazzService {

    PageResult<Clazz> page(ClazzQueryParam param);

    void save(Clazz clazz);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);
}
