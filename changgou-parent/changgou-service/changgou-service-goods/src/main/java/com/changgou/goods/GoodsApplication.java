package com.changgou.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Program: changgou
 * @Description: 商品微服务的启动引导类
 * @Author: Mr.Ye
 * @Date: 2021-12-04 12:16
 **/
@SpringBootApplication(scanBasePackages = {"com.changgou"})
@EnableEurekaClient  // 开启Eureka客户端
@MapperScan(basePackages = {"com.changgou.goods.dao"})  // 通用Mapper扫描
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
