package com.xzixi.demo;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int size;
    private static final float loadFactor = 0.75f;
    public LRUCache(int size) {
        super((int) Math.ceil(size/loadFactor) + 1, loadFactor, true);
        this.size = size;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>size;
    }
}
