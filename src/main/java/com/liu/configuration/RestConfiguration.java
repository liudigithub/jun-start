/*
 * Copyright © 2013-2016 BLT, Co., Ltd. SELF Rights Reserved.
 */
package com.liu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 处理跨域
 * 
 * @author liudi
 */
@Configuration
public class RestConfiguration {

    /**
     * 处理跨域
     * @return 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // you USUALLY want this
        config.addAllowedOrigin("http://118.184.32.71");
        config.addAllowedOrigin("http://118.184.32.71:*");
        config.addAllowedOrigin("http://localhost:*");
        config.addAllowedOrigin("http://127.0.0.1:*");
        config.addAllowedOrigin("http://localhost");
        config.addAllowedOrigin("http://127.0.0.1");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("OPTIONS");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
