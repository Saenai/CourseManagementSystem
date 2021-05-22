package com.shu.cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
*   Configuration for Backend that allows different port to access
*   @author Saenai
*/

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").maxAge(3600);
    }

}
