package com.xzixi.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    static {
        // 解决netty冲突导致初始化client报错的问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

}
