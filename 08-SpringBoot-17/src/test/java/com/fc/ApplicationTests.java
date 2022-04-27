package com.fc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// 之前我们用@ContextConfiguration来指定Spring容器的创建方式
@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("123");
	}

}
