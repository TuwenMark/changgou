package com.changgou.goods.service.impl;

import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
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
 * @Description: 参数服务
 * @Author: Mr.Ye
 * @Date: 2021-12-19 18:31
 **/
@Service
public class ParaServiceImpl implements ParaService {
    @Autowired
    private ParaMapper paraMapper;

    /**
     * 查询所有参数
     *
     * @return 参数列表
     */
    @Override
    public List<Para> findAll() {
        return paraMapper.selectAll();
    }

    /**
     * 根据主键查询参数数据
     *
     * @param id 主键id
     * @return 参数数据
     */
    @Override
    public Para findById(Integer id) {
        return paraMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增参数数据
     *
     * @param para 参数数据
     */
    @Override
    public void add(Para para) {
        paraMapper.insertSelective(para);
    }

    /**
     * 更新参数数据
     *
     * @param para 更新后的参数数据
     */
    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKey(para);
    }

    /**
     * 根据主键删除参数数据
     *
     * @param id 主键
     */
    @Override
    public void delete(Integer id) {
        paraMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据条件查询参数数据
     *
     * @param para 查询条件
     * @return 符合条件的参数数据
     */
    @Override
    public List<Para> findList(Map para) {
        Example example = getExample(para);
        return paraMapper.selectByExample(example);
    }

    /**
     * 分页查询所有参数数据
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 所有参数数据
     */
    @Override
    public List<Para> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return paraMapper.selectAll();
    }

    /**
     * 根据条件分页查询参数数据
     *
     * @param para 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 符合条件的所有参数数据
     */
    @Override
    public List<Para> findPageList(Map para, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = getExample(para);
        return paraMapper.selectByExample(example);
    }

    /**
     * 获取实例，封装查询条件
     *
     * @param para 查询条件
     * @return 封装了条件的实例
     */
    private Example getExample(Map para) {
        Example example = new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        Set<String> keys = para.keySet();
        for (String key : keys) {
            Optional.ofNullable(para.get(key)).map(value -> criteria.andLike(key, "%" + value + "%"));
        }
        return example;
    }
}
