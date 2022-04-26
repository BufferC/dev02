package com.fc.config;

import com.fc.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class Config {
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
