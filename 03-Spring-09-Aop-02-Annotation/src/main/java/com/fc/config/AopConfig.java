package com.fc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.fc")
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class AopConfig {
}
