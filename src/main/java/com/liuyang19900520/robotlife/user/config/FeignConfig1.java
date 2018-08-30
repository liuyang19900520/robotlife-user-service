package com.liuyang19900520.robotlife.user.config;


import com.liuyang19900520.robotlife.user.annotation.ExcludeFromComponentScan;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;

@Configuration
@ExcludeFromComponentScan
public class FeignConfig1 {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
