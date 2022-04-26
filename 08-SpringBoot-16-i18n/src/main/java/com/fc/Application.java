package com.fc;

import com.fc.component.MyLocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        MyLocaleResolver resolver = context.getBean(MyLocaleResolver.class);

        System.out.println(resolver);
    }

}
