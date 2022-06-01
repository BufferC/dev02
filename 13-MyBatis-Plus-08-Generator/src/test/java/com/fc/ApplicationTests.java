package com.fc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class ApplicationTests {
    @Test
    void generator() {
        // 数据库连接
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root",
                "root")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("BufferC") // 设置作者【会生成到注释中】
                            .enableSwagger() // 开启 swagger 模式【自动添加swagger相关的注解】
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() // 禁止打开输出目录【如果不写会自动跳转出生成的路径目录】
                            .outputDir("src\\main\\java"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.fc") // 设置父包名
                            //.moduleName("") // 设置父包模块名
                            .mapper("dao")   // 设置mapper接口的包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src\\main\\resources\\com\\fc\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .enableSkipView() // 跳过视图【前后端分离就不需要使用视图】
                            .addInclude("t_account") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_", "tb_", "sys_")  // 设置过滤表前缀
                            .addFieldPrefix("t_", "c_", "tb_", "sys_")  // 设置过滤字段前缀
                            .entityBuilder()  // Entity 策略配置【实体类的策略配置】
                            .enableLombok()   // 开启 lombok
                            .enableRemoveIsPrefix() // Boolean 类型字段移除 is 前缀
                            .logicDeleteColumnName("availability") // 逻辑删除字段名
                            .logicDeletePropertyName("availability") // 逻辑删除属性名
                            .versionColumnName("version") // 乐观锁字段名
                            .versionPropertyName("version") // 乐观锁属性名
                            .idType(IdType.AUTO) // 使用主键生成策略
                            .enableTableFieldAnnotation() // 开启@TableField 注解
                            .controllerBuilder() // Controller 配置
                            .enableRestStyle() // 开启生成@RestController【自动加上RestController注解】
                            .serviceBuilder() // Service 配置
                            .formatServiceFileName("%sService")
                            .mapperBuilder() // mapper 配置
                            .enableMapperAnnotation(); // 使用@Mapper注解
                })
                .templateEngine(new FreemarkerTemplateEngine())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
