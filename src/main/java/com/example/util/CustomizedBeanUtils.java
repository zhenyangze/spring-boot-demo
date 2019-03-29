package com.example.util;

import com.example.model.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Collection;

@Slf4j
public class CustomizedBeanUtils {

    // 复制对象属性
    public static <S, T> T copyObject(S source, Class<T> targetClass, String... ignoreProperties) {
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (InstantiationException|IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // 复制对象属性
    public static <S, T> T copyObject(S source, Class<T> targetClass, Class<?> editable) {
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target, editable);
            return target;
        } catch (InstantiationException|IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // 复制集合元素属性
    @SuppressWarnings("unchecked")
    public static <S extends BaseModel, T extends BaseModel> Collection<T> copyCollection(Collection<S> sources, Class<T> targetClass, String... ignoreProperties) {
        try {
            Collection<T> targets = sources.getClass().newInstance();
            sources.forEach(source -> {
                T target = copyObject(source.clone(), targetClass, ignoreProperties);
                targets.add(target);
            });
            return targets;
        } catch (InstantiationException|IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // 复制集合元素属性
    @SuppressWarnings("unchecked")
    public static <S extends BaseModel, T extends BaseModel> Collection<T> copyCollection(Collection<S> sources, Class<T> targetClass, Class<?> editable) {
        try {
            Collection<T> targets = sources.getClass().newInstance();
            sources.forEach(source -> {
                T target = copyObject(source.clone(), targetClass, editable);
                targets.add(target);
            });
            return targets;
        } catch (InstantiationException|IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
