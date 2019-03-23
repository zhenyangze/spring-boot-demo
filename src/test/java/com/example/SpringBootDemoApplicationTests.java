package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootDemoApplicationTests {


    @Autowired
    private RedisTemplate redisTemplate;

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
        redisTemplate.getConnectionFactory().getConnection().set("abc".getBytes(), "123".getBytes());
    }

}
