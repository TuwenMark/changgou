package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 品牌相关业务层操作
 * @Author: Mr.Ye
 * @Date: 2021-12-04 17:32
 **/
public interface BrandService {
    List<Brand> findByCategoryId(Integer categoryId);

    PageInfo<Brand> findPage(Map brand, Integer pageNum, Integer pageSize);

    PageInfo<Brand> findPage(Integer pageNum, Integer PageSize);

    List<Brand> findList(Map brand);

    void deleteById(Integer id);

    void updateById(Brand brand);

    void add(Brand brand);

    Brand findById(Integer id);

    List<Brand> findAll();
}
