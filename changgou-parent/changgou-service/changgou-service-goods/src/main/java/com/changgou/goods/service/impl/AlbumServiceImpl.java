package com.changgou.goods.service.impl;

import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
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
 * @Description: 相册相关业务层操作
 * @Author: Mr.Ye
 * @Date: 2021-12-06 23:19
 **/
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 根据id删除指定相册
     *
     * @param id 相册id
     */
    @Override
    public void deleteById(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id修改相册
     *
     * @param album 相册对象
     */
    @Override
    public void updateById(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    /**
     * 添加相册
     *
     * @param album 相册对象
     */
    @Override
    public void add(Album album) {
        albumMapper.insertSelective(album);
    }

    /**
     * 根据条件分页查询相册
     *
     * @param album 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 分页的相册列表
     */
    @Override
    public PageInfo<Album> findPage(Map album, Integer pageNum, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        // 创建查询对象
        Example example = new Example(Album.class);
        // 创建条件对象
        Example.Criteria criteria = example.createCriteria();
        // 添加条件
        Set<String> keys = album.keySet();
        for (String key : keys) {
            Optional.ofNullable(album.get(key)).map(value -> criteria.andLike(key, "%" + value + "%"));
        }
        // 执行查询
        List<Album> albums = albumMapper.selectByExample(example);
        // 封装分页结果
        PageInfo<Album> pageInfo = new PageInfo<>(albums);
        return pageInfo;
    }

    /**
     * 分页查询相册
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 相册分页列表
     */
    @Override
    public PageInfo<Album> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Album> albums = albumMapper.selectAll();
        PageInfo<Album> pageInfo = new PageInfo<>(albums);
        return pageInfo;
    }

    /**
     * 通过条件查询相册
     *
     * @param album 相册查询条件
     * @return 符合条件的相册列表
     */
    @Override
    public List<Album> findAlbumList(Map album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        Set<String> keys = album.keySet();
        for (String key : keys) {
            Optional.ofNullable(album.get(key)).map(value -> criteria.andLike(key, "%" + value + "%"));
        }
        List<Album> albums = albumMapper.selectByExample(example);
        return albums;
    }

    /**
     * 根据id查找相册
     *
     * @return
     */
    @Override
    public Album findById(Integer id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有相册
     *
     * @return 所有相册的列表
     */
    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }
}
