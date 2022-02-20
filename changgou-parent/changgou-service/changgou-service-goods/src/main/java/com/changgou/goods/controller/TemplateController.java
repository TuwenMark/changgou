package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 模板相关控制层操作
 * @Author: Mr.Ye
 * @Date: 2021-12-09 23:29
 **/
@RestController
@RequestMapping("/goods/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    /**
     * 根据分类的id查询模板信息
     *
     * @param categoryId 分类id
     * @return 模板信息
     */
    @GetMapping("/category/{categoryId}")
    public Result<Template> findByCategoryId(@PathVariable Integer categoryId) {
        Template template = templateService.findByCategoryId(categoryId);
        return new Result<>(true, StatusCode.OK, "根据分类id查询模板信息成功！", template);
    }

    /**
     * 根据主键删除模板
     *
     * @param id 主键
     * @return 删除结果
     *
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Integer id) {
        templateService.deleteById(id);
        return new Result(true, StatusCode.OK, "模板删除成功！");
    }

    /**
     * 根据主键更新模板
     *
     * @param template 更新后的模板数据
     * @param id 模板主键
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Result updateById(@RequestBody Template template, @PathVariable Integer id) {
        template.setId(id);
        templateService.updateById(template);
        return new Result(true, StatusCode.OK, "模板更新成功！");
    }

    /**
     * 新增模板
     *
     * @param template 模板对象
     * @return 添加结果
     */
    @PostMapping
    public Result add(@RequestBody Template template) {
        templateService.add(template);
        return new Result(true, StatusCode.OK, "模板新增成功！");
    }

    /**
     * 根据条件分页查询
     *
     * @param template 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 符合条件的分页列表
     */
    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Template>> findPageList(@RequestBody Map template, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageInfo<Template> templates = templateService.findPageList(template, pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "根据条件分页查询成功！", templates);
    }

    /**
     * 利用PageHelper插件分页查询
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 分页模板列表
     */
    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Template>> findPageList(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageInfo<Template> templates = templateService.findPageList(pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "分页查询成功！", templates);
    }

    /**
     * 根据条件查询模板
     *
     * @param template 查询条件
     * @return 符合条件的模板列表
     */
    @PostMapping("/search")
    public Result<List<Template>> findList(@RequestBody Map template) {
        List<Template> templates = templateService.findList(template);
    return new Result<>(true, StatusCode.OK, "根据条件查询成功！", templates);
    }

    /**
     * 根据主键查询模板
     *
     * @param id 主键id
     * @return 模板对象
     */
    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable Integer id) {
        Template template = templateService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询模板成功！", template);
    }

    /**
     * 查询所有模板
     *
     * @return 模板对象
     */
    @GetMapping
    public Result<List<Template>> findAll() {
        List<Template> templates = templateService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有模板成功！", templates);
    }
}
