package com.fj.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ComponentScan(basePackages={"com.fj.*"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan(basePackages = "com.fj.redis.mapper.*")
public class RedisTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisTestApplication.class, args);
    }

}

