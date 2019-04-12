package com.example.config;

import com.example.filter.TokenFilter;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger2.enable}")
    private boolean enable;
    @Autowired
    private ApplicationContext context;

    @Bean
    public Docket api() {
        TypeResolver resolver = new TypeResolver();
        AlternateTypeRule timestampRule = new AlternateTypeRule(resolver.resolve(Timestamp.class), resolver.resolve(String.class));
        Parameter token = new ParameterBuilder()
                .parameterType("header")
                .parameterAccess("access")
                .name(TokenFilter.TOKEN_KEY)
                .description("认证参数")
                .required(false)
                .modelRef(new ModelRef("string"))
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // 只显示添加@Api注解的类
                .build()
                .globalOperationParameters(Lists.newArrayList(token))
                .alternateTypeRules(timestampRule)
                .apiInfo(new ApiInfoBuilder()
                        .title("spring boot项目模板api")
                        .version("1.0")
                        .build());
    }

}
