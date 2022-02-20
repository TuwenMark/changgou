package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 规格相关接口
 * @Author: Mr.Ye
 * @Date: 2021-12-19 16:51
 **/
@RestController
@RequestMapping("/goods/spec")
public class SpecController {
    @Autowired
    private SpecService specService;

    /**
     * 根据分类id查询规格列表
     *
     * @param categoryId 分类id
     * @return 规格列表
     */
    @GetMapping("/category/{categoryId}")
    public Result<List<Spec>> findByCategoryId(@PathVariable Integer categoryId) {
        List<Spec> specs = specService.findByCategoryId(categoryId);
        return new Result<>(true, StatusCode.OK, "根据分类id查询规格列表成功！", specs);
    }

    /**
     * 查询所有规格参数
     *
     * @return 规格参数列表
     */
    @GetMapping
    public Result<List<Spec>> findAll() {
        List<Spec> specs = specService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有规格参数成功！", specs);
    }

    /**
     * 根据主键查询对应的规格参数
     *
     * @param id 主键id
     * @return 规格参数对象
     */
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable Integer id) {
        Spec spec = specService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询规格参数成功！", spec);
    }

    /**
     * 新增规格参数
     *
     * @param spec 规格参数对象
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Spec spec) {
        specService.add(spec);
        return new Result(true, StatusCode.OK, "新增规格参数成功！");
    }

    /**
     * 根据主键更新规格参数
     *
     * @param spec 更新后的规格参数数据
     * @param id 主键id
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Spec spec, @PathVariable Integer id) {
        spec.setId(id);
        specService.update(spec);
        return new Result(true, StatusCode.OK, "新增规格参数成功！");
    }

    /**
     * 根据主键删除规格参数
     *
     * @param id 主键id
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id) {
        specService.delete(id);
        return new Result(true, StatusCode.OK, "删除规格参数成功！");
    }

    /**
     * 根据条件查询规格参数
     *
     * @param spec 查询条件
     * @return 规格参数列表
     */
    @PostMapping("/search")
    public Result<List<Spec>> findList(@RequestBody Map spec) {
        List<Spec> specs = specService.findList(spec);
        return new Result<>(true, StatusCode.OK, "根据条件查询规格参数成功！", specs);
    }

    /**
     * 分页查询规格参数
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 规格参数结果列表
     */
    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<List<Spec>> findPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Spec> specs = specService.findPage(pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "分页查询所有规格参数成功！", specs);
    }

    /**
     * 根据条件分页查询规格参数
     *
     * @param spec 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 查询的规格参数列表
     */
    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<List<Spec>> findPageList(@RequestBody Map spec, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Spec> specs = specService.findPageList(spec, pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "根据条件分页查询规格参数成功！", specs);
    }
 }
