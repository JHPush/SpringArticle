package com.example.articleapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 적용
            .allowedOrigins("http://localhost:5173") // 프론트엔드 주소
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
            .allowedHeaders("*") // 모든 헤더 허용
            .allowCredentials(true); // 자격 증명(쿠키 등) 허용
    }
}