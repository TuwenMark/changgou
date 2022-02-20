package com.changgou.goods.dao;


import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Program: changgou
 * @Description: 品牌相关持久层操作
 * @Author: Mr.Ye
 * @Date: 2021-12-04 17:30
 **/
public interface BrandMapper extends Mapper<Brand> {
    @Select("select * from tb_brand tb where tb.id in (select brand_id from tb_category_brand where category_id = #{categoryId})")
    List<Brand> findByCategoryId(Integer categoryId);
}
