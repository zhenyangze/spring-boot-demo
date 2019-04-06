package com.example.swagger;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerFilterConfig {

    @Bean
    public SwaggerFilter swaggerFilter() {
        return new SwaggerFilter();
    }

    @Bean
    public FilterRegistrationBean<SwaggerFilter> swaggerFilterFilterRegistrationBean(SwaggerFilter swaggerFilter) {
        FilterRegistrationBean<SwaggerFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(swaggerFilter);
        registration.addUrlPatterns("/v2/api-docs");
        registration.setName("swaggerFilter");
        return registration;
    }

}
