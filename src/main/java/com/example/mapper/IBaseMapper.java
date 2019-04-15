package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exception.ProjectException;
import com.example.model.BaseModel;
import com.example.params.Params;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBaseMapper<T extends BaseModel> extends BaseMapper<T> {

    default List<T> customSelectPage(Page<T> page, @Param("params") Params<T> params) {
        throw new ProjectException("方法未实现！");
    }

    default T customSelectById(Integer id) {
        throw new ProjectException("方法未实现！");
    }

    default T customSelectOne(@Param("params") Params<T> params) {
        throw new ProjectException("方法未实现！");
    }

}
