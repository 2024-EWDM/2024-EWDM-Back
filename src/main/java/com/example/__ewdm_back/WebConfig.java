package com.example.__ewdm_back;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 허용 설정
                .allowedOrigins("https://2024-ewdm.vercel.app/", "http://192.168.0.6:3000/", "https://2024-ewdm-6y20wzpa7-naeuns-projects-66f9d841.vercel.app/"); // 허용할 origin 목록
    }
}