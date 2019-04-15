package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.po.Mail;
import com.example.params.Params;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MailMapper extends BaseMapper<Mail> {

    List<Mail> customSelectPage(Page<Mail> page, @Param("params") Params<Mail> params);

    Mail customSelectById(Integer id);

}
