package com.fc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
// swagger2.10.5要用这个注解
// swagger2.9.x之前用的都是@EnableSwagger2
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean
    public Docket buffer_Docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 添加api的基本信息
                .apiInfo(apiInfo_Buffer())
                .groupName("Buffer")
                // 对基本信息进行查询
                .select()
                // 扫描指定包下的所有Swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.fc"))
                // 支持所有以user开头的请求url路径
                .paths(PathSelectors.ant("/user/**"))
                // 构建
                .build();
    }

    // 一个Docket就对应一个文档
    @Bean
    public Docket yyqx_Docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 添加api的基本信息
                .apiInfo(apiInfo_yyqx())
                .groupName("易烊千玺")
                // 对基本信息进行查询
                .select()
                // 扫描指定包下的所有Swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.fc"))
                // 支持任意路径
                .paths(PathSelectors.ant("/login/**"))
                // 构建
                .build();
    }

    private ApiInfo apiInfo_yyqx() {
        return new ApiInfoBuilder()
                // api标题
                .title("易烊千玺负责的模块")
                // 描述
                .description("易烊千玺写的代码~~~")
                // 联系人(作者的联系方式)
                .contact(new Contact("易烊千玺", "https://github.com/21-User", "yyqx@qq.com"))
                // 版本号
                .version("2.1")
                // 许可证
                .license("Apache 2.0")
                // 服务条款
                .termsOfServiceUrl("https://swagger.io")
                .build();
    }

    private ApiInfo apiInfo_Buffer() {
        return new ApiInfoBuilder()
                // api标题
                .title("Buffer的模块")
                // 描述
                .description("Buffer写的代码")
                // 联系人(作者的联系方式)
                .contact(new Contact("BufferC", "https://github.com/BufferC", "2937753364@qq.com"))
                // 版本号
                .version("3.0")
                // 许可证
                .license("Apache 2.0")
                // 服务条款
                .termsOfServiceUrl("https://swagger.io")
                .build();
    }
}
