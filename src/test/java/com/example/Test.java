package com.example;

import com.example.model.BaseModel;
import com.example.model.HBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Test extends BaseModel implements HBaseModel {

    private static final long serialVersionUID = 1L;
    private String rowId;

    @Override
    public String pk() {
        return "rowId";
    }

    @Override
    public String family() {
        return null;
    }

    @Override
    public Map<String, String> rowMap() {
        return null;
    }
}
