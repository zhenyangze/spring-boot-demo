package com.xzixi.demo.config;

import com.xzixi.demo.ftp.SftpFactory;
import com.xzixi.demo.ftp.SftpHelper;
import com.xzixi.demo.ftp.SftpPool;
import com.xzixi.demo.ftp.SftpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ftp配置
@Configuration
@EnableConfigurationProperties(SftpProperties.class)
public class SftpConfig {

    // 工厂
    @Bean
    public SftpFactory sftpFactory(SftpProperties properties) {
        return new SftpFactory(properties);
    }

    // 连接池
    @Bean
    public SftpPool sftpPool(SftpFactory sftpFactory) {
        return new SftpPool(sftpFactory);
    }

    // 辅助类
    @Bean
    public SftpHelper sftpHelper(SftpPool sftpPool) {
        return new SftpHelper(sftpPool);
    }

}
