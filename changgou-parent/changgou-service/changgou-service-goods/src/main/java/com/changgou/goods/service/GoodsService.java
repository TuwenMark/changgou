package com.changgou.goods.service;

import com.changgou.goods.pojo.Goods;

/**
 * @Program: changgou
 * @Description: 商品相关业务层操作
 * @Author: Mr.Ye
 * @Date: 2022-01-23 21:54
 **/
public interface GoodsService {
    /**
     * 批量上架商品
     *
     * @param ids 待上架商品的id
     */
    void pushMany(Long[] ids);

    /**
     * 上架商品
     *
     * @param spuId 商品分类id
     */
    void push(Long spuId);

    /**
     * 下架商品
     *
     * @param spuId 商品分类id
     */
    void pull(Long spuId);

    /**
     * 审核商品
     *
     * @param spuId 商品分类id
     */
    void audit(Long spuId);

    /**
     * 更新商品
     *
     * @param goods 更新后的商品
     */
    void update(Goods goods);

    /**
     * 添加商品
     *
     * @param goods 待添加的商品
     */
    void add(Goods goods);

    /**
     * 根据分类id查找商品
     *
     * @param spuId 分类id
     * @return 商品
     */
    Goods findById(Long spuId);
}
