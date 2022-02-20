package com.changgou.config;

import com.changgou.entity.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: changgou
 * @Description: 工具类的配置类
 * @Author: Mr.Ye
 * @Date: 2022-01-23 22:15
 **/
@Configuration
public class UtilConfiguration {
    @Bean
    public IdWorker generateIdWorker() {
        return new IdWorker(0, 0);
    }
}
