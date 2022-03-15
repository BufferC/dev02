package com.fc.test;

import com.fc.entity.Car;
import com.fc.entity.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DITest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Car car = applicationContext.getBean(Car.class);

        System.out.println(car);

        Person person = applicationContext.getBean(Person.class);

        System.out.println(person);
    }
}
