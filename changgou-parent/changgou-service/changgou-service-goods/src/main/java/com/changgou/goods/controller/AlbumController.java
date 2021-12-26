package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Program: changgou
 * @Description: 相册相关接口
 * @Author: Mr.Ye
 * @Date: 2021-12-06 23:21
 **/
@RestController
@RequestMapping("/goods/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    /**
     * 根据id删除相册
     *
     * @param id 相册id
      * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id){
        albumService.deleteById(id);
        return new Result(true, StatusCode.OK, "相册删除成功！");
    }

    /**
     * 根据相册id修改相册
     *
     * @param album 修改后的相册
     * @param id 相册的id
     * @return 修改结果
     */
    @PutMapping("/update/{id}")
    public Result updateById(@RequestBody Album album, @PathVariable Long id) {
        // 页面修改的时候，id是不能修改的，会隐藏传入后端，需要将其添加到对象中
        album.setId(id);
        albumService.updateById(album);
        return new Result(true, StatusCode.OK, "相册修改成功！");
    }

    /**
     * 添加相册
     *
     * @param album 相册对象
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return new Result(true, StatusCode.OK, "相册添加成功！");
    }

    /**
     * 根据条件分页查询
     *
     * @param album 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 符合条件的分页列表
     */
    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Album>> findPage(@RequestBody Map album, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageInfo<Album> albumsPage = albumService.findPage(album, pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "分页条件查询成功！", albumsPage);
    }

    /**
     * 分页查询相册
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示条数
     * @return 相册的分页列表
     */
    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Album>> findPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageInfo<Album> albumsPage = albumService.findPage(pageNum, pageSize);
        return new Result<>(true, StatusCode.OK, "分页查询成功！", albumsPage);
    }

    /**
     * 根据条件查询相册
     *
     * @param album 查询条件
     * @return 符合条件的相册列表
     */
    @PostMapping("/search")
    public Result<List<Album>> findAlbumList(@RequestBody Map album) {
        List<Album> albums = albumService.findAlbumList(album);
        return new Result<>(true, StatusCode.OK, "条件查询成功！", albums);
    }

    /**
     * 根据id查询相册
     *
     * @param id 相册的id
     * @return 相册对象
     */
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable Integer id) {
        Album album = albumService.findById(id);
        return new Result<>(true, StatusCode.OK, "查找相册成功！", album);
    }

    /**
     * 查询所有相册
     *
     * @return 所有相册列表
     */
    @GetMapping
    public Result<List<Album>> findAll() {
        List<Album> albums = albumService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有相册成功！", albums);
    }
}
