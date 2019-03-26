package com.example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

@EnableCaching
@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jackson2JsonRedisSerializer);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(300))
                .serializeValuesWith(pair);

        return redisCacheConfiguration;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration());
    }

    @Bean
    public KeyGenerator defaultKeyGenerator() {
        return new KeyGenerator() {
            private static final String SEPARATOR = ":";
            private static final String PREFIX = "demo";
            private final static int NO_PARAM_KEY = 0;

            private boolean isSimpleValueType(Class<?> clazz) {
                return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz)
                        || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || URI.class==clazz
                        || URL.class==clazz || Locale.class==clazz || Class.class==clazz);
            }

            private String toJson(Object obj) {
                return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
            }

            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder builder = new StringBuilder(PREFIX);
                builder.append(SEPARATOR);
                builder.append(target.getClass().getName());
                builder.append(SEPARATOR);
                builder.append(method.getName());
                builder.append(SEPARATOR);
                if (params.length>0) {
                    // 参数值
                    for (Object object : params) {
                        if (isSimpleValueType(object.getClass())) {
                            builder.append(object);
                        } else {
                            builder.append(toJson(object).hashCode());
                        }
                    }
                } else {
                    builder.append(NO_PARAM_KEY);
                }
                return builder.toString();
            }
        };
    }

}
