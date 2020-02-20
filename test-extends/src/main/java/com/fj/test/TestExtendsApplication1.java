package com.fj.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 因为将启动文件忽略了，所以就新建了一个
@MapperScan("com.fj.test.gmall.mapper")
@SpringBootApplication
public class TestExtendsApplication1 {

    public static void main(String[] args) {
        SpringApplication.run(TestExtendsApplication1.class, args);
    }

}
