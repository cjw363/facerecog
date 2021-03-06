package com.facerecog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class FrCorsConfiguration {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://127.0.0.1:8080");
        config.addAllowedOrigin("http://127.0.0.1:9091");
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://localhost:9091");
        config.addAllowedOrigin("http://152.136.134.235");
        config.addAllowedOrigin("http://152.136.134.235:9090");
        config.addAllowedOrigin("http://152.136.134.235:9091");
        config.addAllowedOrigin("http://132.232.108.138");
        config.addAllowedOrigin("http://132.232.108.138:8080");
        config.addAllowedOrigin("http://111.230.151.37");
        config.addAllowedOrigin("http://111.230.151.37:8080");
        config.addAllowedOrigin("http://www.cjw363.cn");
        config.addAllowedOrigin("http://www.cjw363.cn:9090");
        config.addAllowedOrigin("http://www.cjw363.cn:9091");
        //2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求方式 GET POST DELETE...
        config.addAllowedMethod("*");
        // 4）允许的头信息
        config.addAllowedHeader("*");

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}
