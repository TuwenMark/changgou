package com.changgou.goods.service.impl;

import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
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
 * @Description: 模板相关业务处理操作
 * @Author: Mr.Ye
 * @Date: 2021-12-09 23:24
 **/
@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 根据主键删除模板
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键更新模板
     *
     * @param template 模板对象
     */
    @Override
    public void updateById(Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }

    /**
     * 新增模板
     *
     * @param template 模板对象
     */
    @Override
    public void add(Template template) {
        templateMapper.insert(template);
    }

    /**
     * 分页条件查询
     *
     * @param template 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 符合条件的分页列表
     */
    @Override
    public PageInfo<Template> findPageList(Map template, Integer pageNum, Integer pageSize) {
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        // 创建条件对象
        Example example = getExample(template);
        List<Template> templates = templateMapper.selectByExample(example);
        return new PageInfo<>(templates);
    }

    /**
     * 利用PageHelper插件分页查询
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 分页列表
     */
    @Override
    public PageInfo<Template> findPageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Template> templates = templateMapper.selectAll();
        return new PageInfo<>(templates);
    }

    /**
     * 根据条件查询
     *
     * @param template 模板条件
     * @return 模板列表
     */
    @Override
    public List<Template> findList(Map template) {
        Example example = getExample(template);
        return templateMapper.selectByExample(example);
    }

    /**
     * 获取条件对象
     *
     * @param template 查询条件
     * @return 条件对象
     */
    private Example getExample(Map template) {
        // 创建条件对象
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        // 添加条件
        Set<String> keys = template.keySet();
        for (String key : keys) {
            Optional.ofNullable(template.get(key)).map(value -> criteria.andLike(key, "%" + value + "%"));
        }
        return example;
    }

    /**
     * 根据主键查询模板
     *
     * @param id 主键id
     * @return 模板对象
     */
    @Override
    public Template findById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有模板
     *
     * @return 所有模板列表
     */
    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }
}

