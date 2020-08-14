package com.ssafy.nnd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] EXCLUDE_PATHS = {
        "/member/login",
        "/error/**",
        "/login/**",
        "/contest/**",
        "/swagger-ui.html/**"
    };
 
    @Autowired
    private JwtInterceptor jwtInterceptor;
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                        .addPathPatterns("/**")
                        .excludePathPatterns(EXCLUDE_PATHS);
    }
    
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
    			.allowedOrigins("*")
    			.allowedMethods("*")
    			.allowedHeaders("*")
    			.exposedHeaders("Authorization");
    }
}