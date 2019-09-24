package com.xzixi.demo.service;

import com.xzixi.demo.model.po.Resource;

import java.util.List;

public interface IResourceService extends IBaseService<Resource> {

    /** 根据资源类型查询资源 */
    List<Resource> getByType(String type);

    List<Resource> all();

    List<String> categorys();

}
