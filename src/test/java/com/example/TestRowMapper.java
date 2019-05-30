package com.example;

import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class TestRowMapper implements RowMapper<Test> {

    private static byte[] COLUMN_FAMILY = "f".getBytes();
    private static byte[] ROWID = "rowId".getBytes();

    @Override
    public Test mapRow(Result result, int rowNum) throws Exception {
//        result.getRow()
        String rowId = Bytes.toString(result.getRow());
        Test test = new Test();
        test.setRowId(rowId);
        return test;
    }

}
