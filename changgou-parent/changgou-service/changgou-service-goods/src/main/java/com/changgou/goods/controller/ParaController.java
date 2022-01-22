package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 操作参数相关接口
 * @Author: Mr.Ye
 * @Date: 2021-12-19 18:31
 **/
@RestController
@RequestMapping("/goods/para")
public class ParaController {
    @Autowired
    private ParaService paraService;

    /**
     * 查询所有的参数数据
     *
     * @return 所有的参数数据
     */
    @GetMapping
    public Result<List<Para>> findAll() {
        List<Para> paras = paraService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有参数数据成功！", paras);
    }

    /**
     * 根据主键查询参数数据
     *
     * @param id 主键
     * @return 参数数据
     */
    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable Integer id) {
        Para para = paraService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询参数数据成功！", para);
    }

    /**
     * 新增参数数据
     *
     * @param para 新增的参数数据
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Para para) {
        paraService.add(para);
        return new Result(true, StatusCode.OK, "新增参数数据成功！");
    }

    /**
     * 更新参数数据
     *
     * @param para 更新后的参数数据
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Para para, @PathVariable Integer id) {
        para.setId(id);
        paraService.update(para);
        return new Result(true, StatusCode.OK, "更新参数数据成功！");
    }

    /**
     * 根据主键删除参数数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        paraService.delete(id);
        return new Result(true, StatusCode.OK, "删除参数数据成功！");
    }

    /**
     * 根据条件查询参数数据
     *
     * @param para 查询条件
     * @return 符合条件的参数数据列表
     */
    @PostMapping("/search")
    public Result<List<Para>> findList(@RequestBody Map para) {
        List<Para> paras = paraService.findList(para);
        return new Result<>(true, StatusCode.OK, "根据条件查询参数数据成功！", paras);
    }

    /**
     * 分页查询所有参数数据
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 参数数据列表
     */
    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<List<Para>> findPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Para> paras = paraService.findPage(pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "分页查询成功！", paras);
    }

    /**
     * 根据条件分页查询数据
     *
     * @param para 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 参数数据列表
     */
    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<List<Para>> findPageList(@RequestBody Map para, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Para> paras = paraService.findPageList(para, pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "根据条件分页查询成功！", paras);
    }
}
