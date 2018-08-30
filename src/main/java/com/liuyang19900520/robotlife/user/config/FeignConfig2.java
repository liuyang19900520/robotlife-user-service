package com.liuyang19900520.robotlife.user.config;

import com.liuyang19900520.robotlife.user.annotation.ExcludeFromComponentScan;
import feign.Contract;
import feign.Logger;
import feign.MethodMetadata;
import feign.hystrix.HystrixDelegatingContract;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Configuration
@ExcludeFromComponentScan
public class FeignConfig2 {
    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }
}
