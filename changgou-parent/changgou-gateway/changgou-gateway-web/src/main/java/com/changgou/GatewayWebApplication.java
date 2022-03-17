package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * @program: changgou-parent
 * @description: 普通web请求网关的启动引导类
 * @author: Mr.Ye
 * @create: 2022-03-10 21:18
 **/
@SpringBootApplication
@EnableEurekaClient
public class GatewayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class, args);
    }

    /**
     * 限流
     *
     * @return 限流的key，此处为IP
     */
    @Bean(name = "ipKeyResolver")
    public KeyResolver userKeyResolver() {
        return (exchange -> {
            String hostName = exchange.getRequest().getRemoteAddress().getHostString();
            System.out.println(hostName);
            return Mono.just(hostName);
        });
    }
}
