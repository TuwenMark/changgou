package com.changgou.goods.service;

import com.changgou.goods.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 相册相关的业务层操作
 * @Author: Mr.Ye
 * @Date: 2021-12-06 23:18
 **/
public interface AlbumService {
    void deleteById(Long id);

    void updateById(Album album);

    void add(Album album);

    PageInfo<Album> findPage(Map album, Integer pageNum, Integer pageSize);

    PageInfo<Album> findPage(Integer pageNum, Integer pageSize);

    List<Album> findAlbumList(Map album);

    Album findById(Integer id);

    List<Album> findAll();
}
