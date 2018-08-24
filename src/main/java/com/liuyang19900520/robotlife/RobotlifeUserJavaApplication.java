package com.liuyang19900520.robotlife;

import com.liuyang19900520.robotlife.annotation.ExcludeFromComponentScan;
import com.liuyang19900520.robotlife.config.RibbonConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.liuyang19900520.robotlife.dao")
@EnableEurekaClient
@EnableFeignClients
@EnableHystrixDashboard
@RibbonClient(name = "robotlife-blog", configuration = RibbonConfig.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class)})
public class RobotlifeUserJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(RobotlifeUserJavaApplication.class, args);
    }
}
