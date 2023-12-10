package com.chartify.chartify;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.chartify.chartify"})
@MapperScan("com.chartify.chartify.mapper")
public class ChartifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChartifyApplication.class, args);
    }
}
