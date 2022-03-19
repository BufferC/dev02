package com.fc.test;

import com.fc.config.TxConfig;
import com.fc.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PropagateTest {
    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);

        AccountService accountService = applicationContext.getBean(AccountService.class);

        accountService.addAccount("海绵宝宝", "太平洋比基尼海滩比奇堡镇贝壳街124号波萝屋");
    }
}
