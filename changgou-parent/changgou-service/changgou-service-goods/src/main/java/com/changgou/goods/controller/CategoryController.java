package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 商品分类服务接口
 * @Author: Mr.Ye
 * @Date: 2021-12-22 07:15
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父Id查找商品分类
     *
     * @param pid 父级id
     * @return 商品分类列表
     */
    @GetMapping("/list/{pid}")
    public Result<List<Category>> findByParentId(@PathVariable Integer pid) {
        List<Category> categoryList = categoryService.findByParentId(pid);
        return new Result<>(true, StatusCode.OK, "根据父级id查找商品分类成功！", categoryList);
    }

    /**
     * 查询所有商品分类
     *
     * @return 所有商品分类列表
     */
    @GetMapping
    public Result<List<Category>> findAll() {
        List<Category> categoryList = categoryService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有商品分类成功！", categoryList);
    }

    /**
     * 根据主键id查询商品分类
     *
     * @param id 商品分类主键id
     * @return 想要查询的商品分类
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return new Result<>(true, StatusCode.OK, "根据主键查询商品分类成功！", category);
    }

    /**
     * 新增商品分类
     *
     * @param category 商品分类
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return new Result(true, StatusCode.OK, "新增商品分类成功！");
    }

    /**
     * 根据id更新商品分类
     *
     * @param id 商品主键id
     * @param category 更新后的商品分类
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Result updateById(@PathVariable Integer id, @RequestBody Category category){
        category.setId(id);
        categoryService.updateById(category);
        return new Result(true, StatusCode.OK, "更新商品分类成功！");
    }

    /**
     * 根据主键id删除指定商品分类
     *
     * @param id 商品分类主键id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除指定商品分类成功！");
    }

    /**
     * 分页查询全部商品分类列表
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 全部商品分类列表
     */
    @GetMapping("/search")
    public Result<PageInfo<Category>> findPage(@RequestParam String pageNum, @RequestParam String pageSize) {
        PageInfo<Category> categoryList = categoryService.findPage(pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "分页查询全部商品分类成功！", categoryList);
    }

//    /**
//     * 根据条件查询商品分类列表
//     *
//     * @param category 查询条件
//     * @return 符合条件的商品分类列表
//     */
//    @PostMapping("/search")
//    public Result<List<Category>> findList(@RequestBody Map category){
//        List<Category> categoryList = categoryService.findList(category);
//        return new Result<>(true, StatusCode.OK, "根据条件查询商品分类列表成功！", categoryList);
//    }

    /**
     * 根据条件分页查询商品分类列表
     *
     * @param category 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 符合条件的商品分类列表
     */
    @PostMapping("/search")
    public Result<PageInfo<Category>> findPageList(@RequestBody Map category, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<Category> categoryList = categoryService.findPageList(category, pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "根据条件分页查询成功！", categoryList);
    }

}
