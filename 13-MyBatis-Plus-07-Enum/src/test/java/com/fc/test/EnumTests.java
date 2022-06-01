package com.fc.test;

import com.fc.dao.PersonDao;
import com.fc.entity.Person;
import com.fc.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumTests {
    @Autowired
    private PersonDao personDao;

    @Test
    void test() {
        Person person = new Person();
        person.setName("玛卡巴卡");
        // 使用枚举之后就不能随意得去使用其他不匹配的类型值了
        person.setGender(Gender.MALE);

        int affectedRows = personDao.insert(person);

        System.out.println(affectedRows > 0 ? "插入成功" : "插入失败");
    }
}
