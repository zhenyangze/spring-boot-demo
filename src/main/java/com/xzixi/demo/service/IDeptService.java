package com.xzixi.demo.service;

import com.xzixi.demo.model.po.Dept;

import java.util.List;

public interface IDeptService extends IBaseService<Dept> {

    int DEFAULT = 1;

    int NOT_DEFAULT = 0;

    List<Dept> tree();

    void resetDefault();

}
