package com.fc;

import com.fc.entity.Car;
import com.fc.entity.GirlFriend;
import com.fc.entity.Son;
import com.fc.entity.Wife;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        if (context.containsBean("girlFriend")) {
            GirlFriend girlFriend = context.getBean(GirlFriend.class);
            System.out.println("有对象：" + girlFriend);
        } else {
            System.out.println("路见不平一声吼，你还没有女朋友");
        }

        if (context.containsBean("son")) {
            Son son = context.getBean(Son.class);
            System.out.println("有孩子：" + son);
        } else {
            System.out.println("没孩子");
        }

        if (context.containsBean("wife")) {
            Wife wife = context.getBean(Wife.class);
            System.out.println("有老婆：" + wife);
        } else {
            System.out.println("没老婆");
        }

        Car car = context.getBean("car2", Car.class);

        System.out.println(car);

    }

}
