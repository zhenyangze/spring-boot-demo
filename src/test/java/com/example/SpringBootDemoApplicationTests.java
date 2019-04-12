package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.UserMapper;
import com.example.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class SpringBootDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
        log.info("info");
        log.debug("debug");
        log.trace("trace");
        log.warn("warn");
        log.error("error");
    }

    @Test
    public void testRedis() {
        User user = userMapper.selectById(1);
        redisTemplate.opsForValue().set("user", user);
        Object obj = redisTemplate.opsForValue().get("user");
        System.out.println(obj);
        System.out.println(obj.getClass());
    }

    @Test
    public void testArray() throws IllegalAccessException, InstantiationException {
        User[] users = new User[7];
        Object[] objects = (Object[]) users;
        System.out.println(objects.length);
        System.out.println(objects.getClass());
    }

    @Test
    public void testQueryWrapper() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", 1);
        wrapper.eq("role_id", 1);
        wrapper.or();
        wrapper.eq("user_id", 2);
        wrapper.eq("role_id", 3);
        wrapper.or();
        System.out.println(wrapper.getCustomSqlSegment());
    }

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
