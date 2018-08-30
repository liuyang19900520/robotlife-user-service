package com.liuyang19900520.robotlife.user.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuya
 */
@Configuration
public class HikariDatabaseConfig {


    @Bean
    public HikariDataSource HikariDataSourceRobotLifeUser() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/robotlife?useUnicode=true&characterEncoding=utf-8");
        config.setUsername("root");
        config.setPassword("1234");

//        config.setJdbcUrl("jdbc:mysql://liuyang19900520db.chuuiho8uwp7.ap-northeast-1.rds.amazonaws.com/robotlife?useUnicode=true&characterEncoding=utf-8");
//        config.setUsername("liuyang19900520");
//        config.setPassword("LY-052077");

        config.setPoolName("HikariDataSourceRobotLifeUser");
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setMaximumPoolSize(20);

        HikariDataSource ds = new HikariDataSource(config);

        return ds;
    }




}
