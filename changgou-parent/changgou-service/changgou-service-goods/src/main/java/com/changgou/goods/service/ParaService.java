package com.changgou.goods.service;

import com.changgou.goods.pojo.Para;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 参数服务接口
 * @Author: Mr.Ye
 * @Date: 2021-12-19 18:30
 **/
public interface ParaService {
    List<Para> findAll();

    Para findById(Integer id);

    void add(Para para);

    void update(Para para);

    void delete(Integer id);

    List<Para> findList(Map para);

    List<Para> findPage(Integer pageNum, Integer pageSize);

    List<Para> findPageList(Map para, Integer pageNum, Integer pageSize);
}
