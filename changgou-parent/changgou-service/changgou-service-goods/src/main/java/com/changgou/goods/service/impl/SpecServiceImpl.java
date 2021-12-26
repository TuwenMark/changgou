package com.changgou.goods.service.impl;

import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @Program: changgou
 * @Description: 规格相关服务
 * @Author: Mr.Ye
 * @Date: 2021-12-19 16:49
 **/
@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    private SpecMapper specMapper;

    /**
     * 查询所有规格参数
     *
     * @return 规格参数列表
     */
    @Override
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }

    /**
     * 根据主键查询对应的规格参数
     *
     * @param id 主键id
     * @return 规格参数对象
     */
    @Override
    public Spec findById(Integer id) {
        return specMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加规格参数
     * @param spec 规格参数对象
     */
    @Override
    public void add(Spec spec) {
        specMapper.insertSelective(spec);
    }

    /**
     * 根据主键更新规格参数
     *
     * @param spec 更新后的规格参数对象
     */
    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKey(spec);
    }

    /**
     * 根据主键删除规格参数
     *
     * @param id 规格参数主键
     */
    @Override
    public void delete(Integer id) {
        specMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据条件查询规格参数列表
     *
     * @param spec 规格参数查询条件
     * @return 规格参数列表
     */
    @Override
    public List<Spec> findList(Map spec) {
        Example example = getExample(spec);
        return specMapper.selectByExample(example);
    }

    /**
     * 分页查询规格参数
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 规格参数列表
     */
    @Override
    public List<Spec> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return specMapper.selectAll();
    }

    /**
     * 根据条件分页查询规格参数
     *
     * @param spec 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 规格参数列表
     */
    @Override
    public List<Spec> findPageList(Map spec, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = getExample(spec);
        return specMapper.selectByExample(example);
    }

    /**
     * 获取实例，封装查询条件
     *
     * @param spec 查询条件
     * @return 封装了查询条件的实例
     */
    private Example getExample(Map spec) {
        // 创建实例
        Example example = new Example(Spec.class);
        // 创建实例的条件
        Example.Criteria criteria = example.createCriteria();
        // 封装条件数据
        Set<String> keys = spec.keySet();
        for (String key : keys) {
            Optional.ofNullable(spec.get(key)).map(value -> criteria.andLike(key, "%" + value + "%"));
        }
        return example;
    }
}
