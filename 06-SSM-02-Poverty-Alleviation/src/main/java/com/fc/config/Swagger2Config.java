package com.fc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {
    @Bean
    public Docket docket_yyqx() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo_yyqx())
                .groupName("易烊千玺")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fc.controller"))
                .paths(PathSelectors.ant("/user/login"))
                .build();
    }

    @Bean
    public Docket docket_BufferC() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo_BufferC())
                .groupName("BufferC")
                // 全局的操作参数
                .globalOperationParameters(jwtToken())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fc.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // 自定义请求头携带的规则
    private List<Parameter> jwtToken() {
        // 用于存储所有的参数
        List<Parameter> parameters = new ArrayList<>();

        // 参数构造器
        ParameterBuilder builder = new ParameterBuilder();

        // 请求头的参数名，必须和我们后续拦截器中保持一致
        Parameter tokenParameter = builder.name("Authorization")
                // 描述
                .description("token令牌")
                // 数据类型
                .modelRef(new ModelRef("string"))
                // 请求参数的类型，用于区分请求头或者是普通的请求参数
                .parameterType("header")
                // 默认值
                .defaultValue("请给我一个token")
                // 非主体示例值
                .scalarExample("header.payload.signature")
                // 是否必须，因为登录是不需要加token
                .required(false)
                .build();

        parameters.add(tokenParameter);

        return parameters;
    }

    private ApiInfo apiInfo_BufferC() {
        return new ApiInfoBuilder()
                .title("BufferC的模块")
                .description("BufferC的代码实现~")
                .version("2.0")
                .contact(new Contact("BufferC", "https://github.com/BufferC", "BufferC@qq.com"))
                .build();
    }

    private ApiInfo apiInfo_yyqx() {
        return new ApiInfoBuilder()
                .title("易烊千玺的模块")
                .description("易烊千玺的代码实现~")
                .version("2.0")
                .contact(new Contact("易烊千玺", "https://github.com/BufferC", "BufferC@qq.com"))
                .build();
    }
}
