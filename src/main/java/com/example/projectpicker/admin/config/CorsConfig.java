package com.example.projectpicker.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // api cors 정책 설정
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000", "http://todo-buket.s3-website.ap-northeast-2.amazonaws.com") // api 요청 허용 URL
                .allowedMethods("GET", "POST", "PATCH", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}


