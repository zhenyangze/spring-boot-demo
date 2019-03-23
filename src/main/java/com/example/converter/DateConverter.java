package com.example.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter implements Converter<String, Date> {

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
                e.printStackTrace();
            }
        }
        return d;
    }

}
