package com.example.model;

import com.example.exception.ProjectException;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Map;

public class HBaseRowMapper<T extends BaseModel & HBaseModel> implements RowMapper<T> {

    private Class<T> type;

    // 泛型参数的类型无法获取，所以要在构造方法中传入
    public HBaseRowMapper(Class<T> type) {
        this.type = type;
    }

    @Override
    public T mapRow(Result result, int rowNum) throws ProjectException {
        try {
            T t = type.newInstance();
            String pk = Bytes.toString(result.getRow());
            t.value(t.pk(), pk);
            String familyStr = t.family();
            if (StringUtils.isNotEmpty(familyStr)) {
                byte[] family = familyStr.getBytes();
                Map<String, String> rowMap = t.rowMap();
                if (!Collections.isEmpty(rowMap)) {
                    rowMap.forEach((row, field) -> t.value(field, Bytes.toString(result.getValue(family, row.getBytes()))));
                }
            }
            return t;
        } catch (Exception e) {
            throw new ProjectException("hbase映射出错", e);
        }
    }

}
