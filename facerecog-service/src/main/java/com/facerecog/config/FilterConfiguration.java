package com.facerecog.config;

import com.facerecog.config.properties.FilterProperties;
import com.facerecog.interceptor.WebInterceptor;
import com.facerecog.interceptor.AppInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(FilterProperties.class)
public class FilterConfiguration implements WebMvcConfigurer {
    @Autowired
    private WebInterceptor mWebInterceptor;

    @Autowired
    private AppInterceptor mAppInterceptor;

    @Autowired
    private FilterProperties mFilterProperties;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mWebInterceptor).addPathPatterns("/**").excludePathPatterns(mFilterProperties.getWebExcludePaths());
        registry.addInterceptor(mAppInterceptor).addPathPatterns("/app/**").excludePathPatterns(mFilterProperties.getAppExcludePaths());
    }
}