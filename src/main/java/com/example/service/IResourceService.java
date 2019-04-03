package com.example.service;

import com.example.model.po.Resource;

import java.util.List;

public interface IResourceService extends IBaseService<Resource> {

    /** 查询所有资源 */
    List<Resource> getAll();

}
