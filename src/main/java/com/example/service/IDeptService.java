package com.example.service;

import com.example.model.po.Dept;

import java.util.List;

public interface IDeptService extends IBaseService<Dept> {

    List<Dept> tree();

    List<Dept> all();

}
