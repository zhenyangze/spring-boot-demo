package com.example.config.swagger.extension;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;

// 自定义接口
@Component
public class ApiListingScannerPluginImpl implements ApiListingScannerPlugin {

    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        Operation login = new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .summary("登录")
                .tags(Sets.newHashSet("认证"))
                .parameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .description("用户名")
                                        .type(new TypeResolver().resolve(String.class))
                                        .name("username")
                                        .parameterType("query")
                                        .parameterAccess("access")
                                        .required(true)
                                        .modelRef(new ModelRef("string"))
                                        .build(),
                                new ParameterBuilder()
                                        .description("密码")
                                        .type(new TypeResolver().resolve(String.class))
                                        .name("password")
                                        .parameterType("query")
                                        .parameterAccess("access")
                                        .required(true)
                                        .modelRef(new ModelRef("string"))
                                        .build()
                        ))
                .responseModel(new ModelRef("com.example.model.vo.ResultVO"))
                .build();
        Operation logout = new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.GET)
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .summary("登出")
                .tags(Sets.newHashSet("认证"))
                .responseModel(new ModelRef("com.example.model.vo.ResultVO"))
                .responseMessages(Sets.newHashSet())
                .build();
        return Arrays.asList(
                new ApiDescription("/login", "login", Collections.singletonList(login), false),
                new ApiDescription("/logout", "logout", Collections.singletonList(logout), false)
        );
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }

}
