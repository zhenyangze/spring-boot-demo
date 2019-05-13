package com.example.util;

import com.example.model.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {

    public static <T extends Tree<T>> List<T> toTree(List<T> list) {
        Map<Integer, T> map = new HashMap<>();
        for (T t: list) {
            map.put(t.getId(), t);
        }
        Integer resultLevel = null;
        for (T t: list) {
            if (resultLevel==null || t.getLevel()<resultLevel) {
                resultLevel = t.getLevel();
            }
            Integer pid = t.getPid();
            if (pid!=null) {
                T pt = map.get(pid);
                if (pt!=null) {
                    List<T> subs = pt.getSubs();
                    if (subs==null) {
                        subs = new ArrayList<>();
                        pt.setSubs(subs);
                    }
                    subs.add(t);
                }
            }
        }
        if (resultLevel!=null) {
            List<T> result = new ArrayList<>();
            final Integer _level = resultLevel;
            list.forEach(t -> {
                if (_level.equals(t.getLevel())) {
                    result.add(t);
                }
            });
            return result;
        }
        return null;
    }

}
