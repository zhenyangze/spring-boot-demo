package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.BaseModel;

import java.util.Collection;

public interface IBaseService<T extends BaseModel> extends IService<T> {

    /**
     * 类似oracle的merge语法
     * @param collection 目标集合
     * @param queryWrapper 查询条件，用来查询将要覆盖的集合
     */
    void merge(Collection<T> collection, QueryWrapper<T> queryWrapper);

    /**
     * 类似oracle的merge语法
     * @param collection 目标集合
     * @param queryWrapper 查询条件，用来查询将要覆盖的集合
     * @param batchSize 插入批次数量
     */
    void merge(Collection<T> collection, QueryWrapper<T> queryWrapper, int batchSize);

}
