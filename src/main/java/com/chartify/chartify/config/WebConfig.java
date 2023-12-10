package com.chartify.chartify.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/Users/**")
                .allowedOrigins("*") // 允许的域名
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 允许的请求方法
                .maxAge(3600); // 预检请求的有效期
    }
}
