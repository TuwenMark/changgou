package com.changgou.goods.service;

import com.changgou.goods.pojo.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 商品分类业务层接口
 * @Author: Mr.Ye
 * @Date: 2021-12-22 07:12
 **/
public interface CategoryService {
    List<Category> findByParentId(Integer pid);

    List<Category> findAll();

    Category findById(Integer id);

    void add(Category category);

    void updateById(Category category);

    void deleteById(Integer id);

    PageInfo<Category> findPage(String pageNum, String pageSize);

    List<Category> findList(Map category);

    PageInfo<Category> findPageList(Map category, Integer pageNum, Integer pageSize);

}
