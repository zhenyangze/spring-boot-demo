package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.po.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectByResourceId(Integer resourceId);

}
