package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @Program: changgou
 * @Description: 品牌相关业务层操作
 * @Author: Mr.Ye
 * @Date: 2021-12-04 17:33
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据条件分页查询品牌信息
     *
     * @param brand 品牌查询信息
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 分页条件查询结果
     */
    @Override
    public PageInfo<Brand> findPage(Map brand, Integer pageNum, Integer pageSize) {
        // 利用PageHelper插件实现分页
        PageHelper.startPage(pageNum, pageSize);
        // 根据条件分页查询数据
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        Set<String> keys = brand.keySet();
        for (String key : keys) {
            Optional.ofNullable(brand.get(key)).map(value -> criteria.andLike(key, "%" + value + "%"));
        }
        List<Brand> brands = brandMapper.selectByExample(example);
        return new PageInfo<>(brands);
    }

    /**
     * 利用PageHelper插件实现分页
     *
     * @param pageNum 当前页
     * @param PageSize 每页显示条数
     * @return 数据的分页列表
     */
    @Override
    public PageInfo<Brand> findPage(Integer pageNum, Integer PageSize) {
        // PageHelper插件实现分页功能
        PageHelper.startPage(pageNum, PageSize);
        // 查询数据：注意这里必须要查询集合，不能查询单条记录
        List<Brand> brands = brandMapper.selectAll();
        return new PageInfo<>(brands);
    }

    /**
     * 根据条件查询品牌列表
     *
     * @param brand 查询条件集合
     * @return 符合条件的品牌集合
     */
    @Override
    public List<Brand> findList(Map brand) {
        // 创建条件构造器，指定想要查询的表(Brand类对应的表)
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        // 构造条件
        Set<String> keys = brand.keySet();
        for (String key : keys) {
            Optional.ofNullable(brand.get(key)).map(value -> criteria.andLike(key, "%" + value + "%"));
        }
        return brandMapper.selectByExample(example);
    }

    /**
     * 根据品牌id删除品牌
     *
     * @param id 品牌id
     */
    @Override
    public void deleteById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据品牌id更新品牌信息
     *
     * @param brand 更新的品牌信息
     */
    @Override
    public void updateById(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 添加品牌
     *
     * @param brand 品牌
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 通过主键id查询品牌
     *
     * @param id 主键id
     * @return 某个品牌
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有品牌
     *
     * @return 品牌列表
     */
    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }
}
