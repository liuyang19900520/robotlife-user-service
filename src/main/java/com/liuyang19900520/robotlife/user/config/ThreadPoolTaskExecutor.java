package com.liuyang19900520.robotlife.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: robotlife-user-service
 * @description:
 * @author: LiuYang
 * @create: 2018-08-23 17:02
 **/
@Configuration
public class ThreadPoolTaskExecutor {
    @Bean
    public org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor createThreadPoolTaskExecutor() {
        org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor threadPoolTaskExecutor = new org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor();
        //最小数量
        threadPoolTaskExecutor.setCorePoolSize(10);
        //最大数量
        threadPoolTaskExecutor.setMaxPoolSize(20);
        //线程池维护线程所允许的空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(30000);
        //线程池所使用的缓冲队列
        threadPoolTaskExecutor.setQueueCapacity(100);
        return threadPoolTaskExecutor;
    }
}
