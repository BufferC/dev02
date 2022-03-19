package com.fc.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.fc.dao.AccountDao;
import com.fc.dao.impl.AccountDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource("classpath:jdbc.properties")
// 指定包扫描路径，会扫描指定包下的所有注解
@ComponentScan("com.fc")
// 当前类就是一个配置类
@Configuration
// 开启注解支持
@EnableTransactionManagement
public class TxConfig {
    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource() {

        Properties properties = new Properties();

        properties.setProperty("driverClassName", driverClassName);
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);

        DataSource dataSource = null;

        try {
            // 通过数据源工厂获取数据源并且加载Properties中的配置
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public AccountDao accountDao(DataSource dataSource) {
        AccountDaoImpl accountDao = new AccountDaoImpl();

        accountDao.setDataSource(dataSource);

        return accountDao;
    }
}
