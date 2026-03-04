
package com.example.dingding.config;

import feign.Logger.Level;
import feign.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * Feign 基础配置（超时、日志级别、日志实现）。
 */
public class FeignConfiguration {
    private static final Logger log = LogManager.getLogger(FeignConfiguration.class);

    public FeignConfiguration() {
    }

    @Bean
    public Request.Options options() {
        // 连接超时、读超时均设置为 5 分钟。
        return new Request.Options(5L, TimeUnit.MINUTES, 5L, TimeUnit.MINUTES, true);
    }

    @Bean
    Level feignLoggerLevel() {
        return Level.BASIC;
    }

    @Bean
    public feign.Logger logger() {
        return new FeignLogger();
    }
}
