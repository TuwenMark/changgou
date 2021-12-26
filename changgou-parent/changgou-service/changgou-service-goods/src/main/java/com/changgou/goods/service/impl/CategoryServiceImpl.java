package com.changgou.goods.service.impl;

import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
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
 * @Description: 商品分类业务
 * @Author: Mr.Ye
 * @Date: 2021-12-22 07:14
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点id查询商品分类
     * @param pid 父节点id
     * @return 商品分类列表
     */
    @Override
    public List<Category> findByParentId(Integer pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    /**
     * 查询所有的商品分类
     *
     * @return 所有商品分类列表
     */
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    /**
     * 根据主键id查询商品分类
     *
     * @param id 商品id
     * @return 商品分类
     */
    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增商品分类
     *
     * @param category 商品分类
     */
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    /**
     * 根据主键更新商品分类
     *
     * @param category 更新后的商品分类
     */
    @Override
    public void updateById(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 根据id删除商品分类
     *
     * @param id 商品分类的主键
     */
    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 分页查询
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 商品分类列表
     */
    @Override
    public PageInfo<Category> findPage(String pageNum, String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Category> categoryList = categoryMapper.selectAll();
        return new PageInfo<>(categoryList);
    }

    /**
     * 根据条件查询商品分类
     *
     * @param category 查询条件
     * @return 符合条件的商品分类列表
     */
    @Override
    public List<Category> findList(Map category) {
        Example example = getExample(category);
        return categoryMapper.selectByExample(example);
    }

    /**
     * 根据条件分页查询
     *
     * @param category 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 符合条件的商品分类列表
     */
    @Override
    public PageInfo<Category> findPageList(Map category, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = getExample(category);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return new PageInfo<>(categoryList);
    }

    /**
     * 获取条件实例
     *
     * @param category 查询条件
     * @return 条件实例
     */
    private Example getExample(Map category) {
        Example example = new Example(Category.class);
        Set<String> keys = category.keySet();
        for (String key : keys) {
            Optional.ofNullable(category.get(key)).map(value -> example.createCriteria().andLike(key, "%" + value + "%"));
        }
        return example;
    }
}
