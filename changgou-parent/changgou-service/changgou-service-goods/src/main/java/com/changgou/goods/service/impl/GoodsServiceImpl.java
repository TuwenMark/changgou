package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.IdWorker;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.GoodsService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @Program: changgou
 * @Description: 商品相关业务层操作
 * @Author: Mr.Ye
 * @Date: 2022-01-23 21:57
 **/
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 批量上架商品
     *
     * @param ids 待上架商品的id
     */
    @Override
    public void pushMany(Long[] ids) {
        // 修改的结果
        Spu spu = new Spu();
        spu.setIsMarketable("1");
        // 构建查询条件
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        // 未上架
        criteria.andEqualTo("isMarketable", "0");
        // 已审核
        criteria.andEqualTo("status", "1");
        // 未删除
        criteria.andEqualTo("isDelete", "0");
        spuMapper.updateByExampleSelective(spu, example);
    }

    /**
     * 上架商品
     *
     * @param spuId 商品分类id
     */
    @Override
    public void push(Long spuId) {
        // 查找商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        // 判断商品是否已删除或已上架
        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("商品上架失败，所选商品已删除！");
        } else if ("1".equals(spu.getIsMarketable())) {
            throw new RuntimeException("商品已上架，请不要重复上架！");
        } else if (!"1".equals(spu.getStatus())){
            throw new RuntimeException("未通过审核商品不能上架！");
        }
        // 上架商品
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 根据商品id下架商品
     *
     * @param spuId 商品分类id
     */
    @Override
    public void pull(Long spuId) {
        // 查找商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        // 判断商品是否已删除或已下架
        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("下架商品出错，商品已删除！");
        } else if ("0".equals(spu.getIsMarketable())) {
            throw new RuntimeException("商品已下架，请不要重复操作！");
        }
        // 下架商品
        spu.setIsMarketable("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 审核商品
     *
     * @param spuId 商品分类id
     */
    @Override
    public void audit(Long spuId) {
        // 查找商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        // 判断商品是否已上架或删除
        if ("1".equals(spu.getIsMarketable())) {
            throw new RuntimeException("审核商品出错，商品已上架！");
        } else if ("0".equals(spu.getIsDelete())) {
            throw new RuntimeException("审核商品出错，商品已删除！");
        }
        // 审核通过
        spu.setStatus("1");
        // 上架
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 修改商品
     *
     * @param goods 修改后的商品
     */
    @Override
    public void update(Goods goods) {
        Spu spu = goods.getSpu();
        // 更新商品
        spuMapper.updateByPrimaryKeySelective(spu);
        // 删除原先的sku
        Sku skuOriginal = new Sku();
        skuOriginal.setSpuId(spu.getId());
        skuMapper.delete(skuOriginal);
        // 新增sku
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        Date date = new Date();
        // 获取Sku
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            sku.setId(idWorker.nextId());
            sku.setSn(generateSn());
            // 获取spu名称
            String name = spu.getName();
            // 获取规格列表
            String spec = sku.getSpec();
            if (StringUtil.isNullOrEmpty(spec)) {
                spec = "{}";
            }
            Map<String, String> specMap = JSON.parseObject(spec, Map.class);
            for (Map.Entry<String, String> entry : specMap.entrySet()) {
                name += entry.getValue();
            }
            sku.setName(name);
            sku.setCreateTime(date);
            sku.setUpdateTime(date);
            sku.setSpuId(spu.getId());
            sku.setBrandName(brand.getName());
            sku.setCategoryName(category.getName());
            skuMapper.insertSelective(sku);
        }
    }

    /**
     * 添加商品
     *
     * @param goods 商品信息
     */
    @Override
    public void add(Goods goods) {
        // 1、获取Spu
        Spu spu = goods.getSpu();
        // 新增商品，设置id
        spu.setId(idWorker.nextId());
        // 设置货号
        spu.setSn(generateSn());
        spuMapper.insertSelective(spu);
        // 新增sku
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        Date date = new Date();
        // 获取Sku
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            sku.setId(idWorker.nextId());
            sku.setSn(generateSn());
            // 获取spu名称
            String name = spu.getName();
            // 获取规格列表
            String spec = sku.getSpec();
            if (StringUtil.isNullOrEmpty(spec)) {
                spec = "{}";
            }
            Map<String, String> specMap = JSON.parseObject(spec, Map.class);
            for (Map.Entry<String, String> entry : specMap.entrySet()) {
                name += entry.getValue();
            }
            sku.setName(name);
            sku.setCreateTime(date);
            sku.setUpdateTime(date);
            sku.setSpuId(spu.getId());
            sku.setBrandName(brand.getName());
            sku.setCategoryName(category.getName());
            skuMapper.insertSelective(sku);
        }
    }

    /**
     * 通过spuId查询Goods
     *
     * @param spuId spu主键
     * @return Goods对象
     */
    @Override
    public Goods findById(Long spuId) {
        // 根据spuId查询Spu
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        // 根据spuId查询Sku列表
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        return new Goods(spu, skuList);
    }

    /**
     * 随机生成一串序列，作为货号sn
     *
     * @return 随机字符串
     */
    private static String generateSn() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
