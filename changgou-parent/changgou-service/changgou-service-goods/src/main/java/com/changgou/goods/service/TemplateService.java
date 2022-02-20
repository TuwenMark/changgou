package com.changgou.goods.service;

import com.changgou.goods.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 模板相关业务处理操作
 * @Author: Mr.Ye
 * @Date: 2021-12-09 23:23
 **/
public interface TemplateService {
    Template findByCategoryId(Integer categoryId);

    void deleteById(Integer id);

    void updateById(Template template);

    void add(Template template);

    PageInfo<Template> findPageList(Map template, Integer pageNum, Integer pageSize);

    PageInfo<Template> findPageList(Integer pageNum, Integer pageSize);

    List<Template> findList(Map template);

    Template findById(Integer id);

    List<Template> findAll();
}
