package com.changgou.handler;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Program: changgou
 * @Description: 公共异常处理类
 * @Author: Mr.Ye
 * @Date: 2021-12-05 00:07
 **/
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }

}
