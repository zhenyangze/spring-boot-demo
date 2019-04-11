package com.example.mapper.params;

import com.example.model.BaseModel;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class Params<T extends BaseModel> implements Serializable {

    private static final long serialVersionUID = 1;
    private T entity;
    private Map<String, Object> map = new HashMap<>();

    public Params(T entity) {
        this.entity = entity;
    }
    public T getEntity() {
        return entity;
    }
    public void setEntity(T entity) {
        this.entity = entity;
    }
    public Map<String, Object> getMap() {
        return map;
    }
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
    public void put(String key, Object value) {
        map.put(key, value);
    }
    public Object get(String key) {
        return map.get(key);
    }

}
