package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 品牌相关接口
 * @Author: Mr.Ye
 * @Date: 2021-12-04 17:34
 **/
@RestController
@RequestMapping("/goods/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 根据分类id查询对应的品牌列表
     *
     * @param categoryId 分类id
     * @return 品牌列表
     */
    @GetMapping("/category/{categoryId}")
    public Result<List<Brand>> findByCategoryId(@PathVariable Integer categoryId) {
        List<Brand> brands = brandService.findByCategoryId(categoryId);
        return new Result<>(true, StatusCode.OK, "根据分类id查询品牌信息成功！", brands);
    }

    /**
     * 根据条件分页查询品牌信息
     *
     * @param brand 品牌查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 分页查询品牌列表
     */
    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Map brand, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageInfo<Brand> brands = brandService.findPage(brand, pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "分页条件查询成功！", brands);
    }

    /**
     * 分页查询品牌列表
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 品牌列表
     */
    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Brand>> findPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageInfo<Brand> brands = brandService.findPage(pageNum, pageSize);
    return new Result<>(true, StatusCode.OK, "分页查询品牌列表成功！", brands);
    }

    /**
     * 根据条件查询品牌列表
     *
     * @param brand 查询条件集合
     * @return 查询结果
     */
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Map brand) {
        List<Brand> brands = brandService.findList(brand);
        return new Result<>(true, StatusCode.OK, "条件查询品牌列表成功！", brands);
    }

    /**
     * 根据品牌id删除品牌
     *
     * @param id 品牌id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        brandService.deleteById(id);
        return new Result(true, StatusCode.OK, "品牌删除成功！");
    }

    /**
     * 根据品牌id更新品牌信息
     *
     * @param id 品牌id
     * @param brand 品牌更新信息
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Result updateById(@PathVariable Integer id , @RequestBody Brand brand) {
        // 根据id修改，需要将id放进brand对象传递过去
        brand.setId(id);
        brandService.updateById(brand);
        return new Result(true, StatusCode.OK, "品牌更新成功！");
    }
    /**
     * 添加品牌
     *
     * @param brand 品牌
     * @return 添加结果
     */
    @PostMapping
    public Result<Brand> add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result<>(true, StatusCode.OK, "添加成功！");
    }

    /**
     * 根据主键id查询品牌
     *
     * @param id 品牌id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Brand brand = brandService.findById(id);
        return new Result(true, StatusCode.OK, "查询品牌成功！", brand);
    }
    /**
     * 查询所有品牌
     *
     * @return 品牌列表
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brands = brandService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有品牌成功！", brands);
    }
}
