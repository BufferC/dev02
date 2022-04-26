package com.fc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrossFilterConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
