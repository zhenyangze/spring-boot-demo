package com.example.model;

import java.util.Map;

public interface HBaseModel {

    /**
     * @return hbase主键对应字段名
     */
    String pk();

    /**
     * @return hbase列簇名称
     */
    String family();

    /**
     * @return hbase列与model字段对应关系
     */
    Map<String, String> rowMap();

}
