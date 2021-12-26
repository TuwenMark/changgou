package com.changgou.controller;

import com.changgou.entity.FastDFSFile;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.util.FastDFSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Program: changgou
 * @Description: 文件相关接口
 * @Author: Mr.Ye
 * @Date: 2021-12-05 21:05
 **/
@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file) throws Exception {
        // 封装FastDFS文件对象
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        );
        // 上传文件
        String[] fileMessage = FastDFSClient.upload(fastDFSFile);
        // 拼接文件的访问地址
        String url = FastDFSClient.getTrackerUrl() + "/" + fileMessage[0] + "/" + fileMessage[1];
        return new Result(true, StatusCode.OK, "文件上传成功！", url);
    }
}
