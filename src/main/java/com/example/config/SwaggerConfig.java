package com.example.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        TypeResolver resolver = new TypeResolver();
        AlternateTypeRule timestampRule = new AlternateTypeRule(resolver.resolve(Timestamp.class), resolver.resolve(String.class));
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) //只显示添加@Api注解的类
                .build()
                .alternateTypeRules(timestampRule)
                .apiInfo(new ApiInfoBuilder()
                        .title("spring boot项目模板api")
                        .version("1.0")
                        .build());
//        removeDefaultPlugin();
        return docket;
    }

}
