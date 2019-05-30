package com.example;

import com.example.model.HBaseRowMapper;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class SpringBootDemoApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Test
    public void testPassword() {
        System.out.println(passwordEncoder.encode("123"));
    }

    @Test
    public void testContext() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = context.getBean(name);
            System.out.println(bean.getClass());
        }
    }

    @Test
    public void testHbase() {
        System.setProperty("hadoop.home.dir", "E:\\hadoop-2.6.0");
        String startRow = "1";
        String stopRow = "4";
        Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(stopRow));
        scan.setCaching(5000);
        List<com.example.Test> dtos = this.hbaseTemplate.find("SYSTEM.TEST", scan, new HBaseRowMapper<com.example.Test>(com.example.Test.class));
        System.out.println(dtos);
    }

}
