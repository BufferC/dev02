package com.fc.filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("/*")
//@Component
//@Order(1)
public class MoneyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤条件前：只要有钱的");
        chain.doFilter(request, response);
        System.out.println("过滤条件后：很有钱");
    }
}
