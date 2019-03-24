package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public Converter<String, Date> dateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                source = source.replace("Z", " UTC");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
                Date d = null;
                if (!StringUtils.isEmpty(source)) {
                    try {
                        java.util.Date date = sdf.parse(source);
                        d = new Date(date.getTime());
                    } catch (ParseException e) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            java.util.Date date = sdf.parse(source);
                            d = new Date(date.getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                return d;
            }
        };
    }

    @Bean
    public Converter<String, Timestamp> timestampConverter() {
        return new Converter<String, Timestamp>() {
            @Override
            public Timestamp convert(String source) {
                source = source.replace("Z", " UTC");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
                Timestamp t = null;
                if (!StringUtils.isEmpty(source)) {
                    try {
                        java.util.Date date = sdf.parse(source);
                        t = new Timestamp(date.getTime());
                    } catch (ParseException e) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            java.util.Date date = sdf.parse(source);
                            t = new Timestamp(date.getTime());
                        } catch (ParseException ex) {
                            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            try {
                                java.util.Date date = sdf.parse(source);
                                t = new Timestamp(date.getTime());
                            } catch (ParseException exc) {
                                exc.printStackTrace();
                            }
                        }
                    }
                }
                return t;
            }
        };
    }

}
