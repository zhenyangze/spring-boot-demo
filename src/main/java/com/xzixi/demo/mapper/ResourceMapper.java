package com.xzixi.demo.mapper;

import com.xzixi.demo.model.po.Resource;

import java.util.List;

public interface ResourceMapper extends IBaseMapper<Resource> {

    List<String> selectAllCategorys();

}
