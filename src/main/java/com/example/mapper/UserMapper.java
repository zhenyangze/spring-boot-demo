package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.params.Params;
import com.example.model.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    User customSelectById(Integer id);

    User customSelectOne(@Param("params") Params<User> params);

}
