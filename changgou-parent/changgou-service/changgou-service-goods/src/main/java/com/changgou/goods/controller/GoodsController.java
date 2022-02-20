package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Program: changgou
 * @Description: 商品的控制层相关操作
 * @Author: Mr.Ye
 * @Date: 2022-01-23 23:21
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 根据商品id数组批量上架商品
     *
     * @param ids 待上架商品id数组
     * @return 上架结果
     */
    @PutMapping("/push/many")
    public Result pushMany(@RequestBody Long[] ids) {
        goodsService.pushMany(ids);
        return new Result(true, StatusCode.OK, "商品批量上架成功！");
    }

    /**
     * 根据商品id上架商品
     *
     * @param spuId 商品id
     * @return 上架结果
     */
    @PutMapping("/push/{id}")
    public Result push(@PathVariable("id") Long spuId) {
        goodsService.push(spuId);
        return new Result(true, StatusCode.OK, "商品上架成功！");
    }

    /**
     * 根据商品id下架商品
     *
     * @param spuId 商品id
     * @return 下架结果
     */
    @PutMapping("/pull/{id}")
    public Result pull(@PathVariable("id") Long spuId) {
        goodsService.pull(spuId);
        return new Result(true, StatusCode.OK, "商品下架成功！");
    }

    /**
     * 审核商品
     *
     * @param spuId 商品分类id
     * @return 审核结果
     */
    @PutMapping("/audit/{id}")
    public Result audit(@PathVariable("id") Long spuId) {
        goodsService.audit(spuId);
        return new Result(true, StatusCode.OK, "审核商品成功！");
    }

    /**
     * 修改商品
     *
     * @param goods 修改后的商品
     * @return 修改结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody Goods goods) {
        goodsService.update(goods);
        return new Result(true, StatusCode.OK, "修改商品成功！");
    }

    /**
     * 添加/修改商品
     *
     * @param goods 商品信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody Goods goods) {
        goodsService.add(goods);
        return new Result(true, StatusCode.OK, "添加商品成功！");
    }

    /**
     * 根据spuId查询Goods
     *
     * @param spuId spu主键
     * @return Goods对象
     */
    @GetMapping("/{id}")
    public Result<Goods> findById(@PathVariable("id") Long spuId) {
        Goods goods = goodsService.findById(spuId);
        return new Result<>(true, StatusCode.OK, "根据spuId查询Goods成功！", goods);
    }
}
