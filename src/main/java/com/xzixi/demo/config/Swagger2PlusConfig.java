package com.xzixi.demo.config;

import com.google.common.collect.Lists;
import com.xzixi.demo.filter.TokenFilter;
import com.xzixi.swagger2.plus.annotation.EnableSwagger2Plus;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConditionalOnExpression("${swagger2.enable}==true")
@EnableSwagger2Plus
public class Swagger2PlusConfig {

    @Bean
    public Docket api() {
        Parameter token = new ParameterBuilder()
                .parameterType("header")
                .parameterAccess("access")
                .name(TokenFilter.TOKEN_KEY)
                .description("认证参数")
                .required(false)
                .modelRef(new ModelRef("string"))
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // 只显示添加@Api注解的类
                .build()
                .globalOperationParameters(Lists.newArrayList(token))
                .apiInfo(new ApiInfoBuilder()
                        .title("spring boot项目模板api")
                        .version("1.0")
                        .build());
    }

}
