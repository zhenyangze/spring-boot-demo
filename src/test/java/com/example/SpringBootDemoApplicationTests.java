package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class SpringBootDemoApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationContext context;

    @Test
    public void testPassword() {
        System.out.println(passwordEncoder.encode("123"));
    }

    @Test
    public void testContext() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = context.getBean(name);
            System.out.println(bean==null? null: bean.getClass());
        }
    }

}
