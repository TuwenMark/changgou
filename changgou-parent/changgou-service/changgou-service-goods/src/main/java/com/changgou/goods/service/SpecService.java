package com.changgou.goods.service;

import com.changgou.goods.pojo.Spec;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 规格相关服务接口
 * @Author: Mr.Ye
 * @Date: 2021-12-19 16:48
 **/
public interface SpecService {
    List<Spec> findAll();

    Spec findById(Integer id);

    void add(Spec spec);

    void update(Spec spec);

    void delete(Integer id);

    List<Spec> findList(Map spec);

    List<Spec> findPage(Integer pageNum, Integer pageSize);

    List<Spec> findPageList(Map spec, Integer pageNum, Integer pageSize);
}
