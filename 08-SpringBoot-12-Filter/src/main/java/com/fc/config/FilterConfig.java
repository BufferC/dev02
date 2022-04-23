package com.fc.config;

import com.fc.filter.MoneyFilter;
import com.fc.filter.SexFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Collections;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> sexRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        // 设置过滤器到spring容器中，相当于加上了@Component注解
        bean.setFilter(new SexFilter());
        // 设置当前过滤器的优先级
        bean.setOrder(-1);
        //Collection<String> set = new ArrayList<>();
        //set.add("/test");
        //set.add("/login.html");
        bean.setUrlPatterns(Collections.singletonList("/*"));
        //bean.setUrlPatterns(set);

        return bean;
    }

    @Bean
    public FilterRegistrationBean<Filter> moneyRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        // 设置过滤器到spring容器中，相当于加上了@Component注解
        bean.setFilter(new MoneyFilter());
        // 设置当前过滤器的优先级
        bean.setOrder(1);
        bean.setUrlPatterns(Collections.singletonList("/login"));

        return bean;
    }
}
