package com.example.model;

import com.example.util.SerializeUtil;

import java.io.Serializable;

public class BaseModel implements Cloneable, Serializable {

    @Override
    public BaseModel clone() {
        return SerializeUtil.deseria(SerializeUtil.seria(this), BaseModel.class);
    }
}
