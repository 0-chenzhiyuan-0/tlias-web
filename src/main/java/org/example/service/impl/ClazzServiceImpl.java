package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam param){
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Clazz> list = clazzMapper.list(param);
        Page<Clazz> clazzPage = (Page<Clazz>) list;
        LocalDate now = LocalDate.now();
        for (Clazz clazz : list) {
            if (now.isAfter(clazz.getEndDate())) {
                    clazz.setStatus("已结课");
            } else if (now.isBefore(clazz.getBeginDate())){
                    clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }

        return new PageResult<Clazz>(clazzPage.getTotal(), clazzPage.getResult());
    }
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteByClazzId(id);
    }

}
