package com.example.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter implements Converter<String, Timestamp> {

    @Override
    public Timestamp convert(String source) {
        source = source.replace("Z", " UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Timestamp t = null;
        if (!StringUtils.isEmpty(source)) {
            try {
                Date date = sdf.parse(source);
                t = new Timestamp(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

}
