package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.LetterMapper;
import com.example.model.po.Letter;
import com.example.params.Params;
import com.example.service.ILetterService;
import org.springframework.stereotype.Service;

@Service
public class LetterService extends BaseService<LetterMapper, Letter> implements ILetterService {

    @Override
    public IPage<Letter> customPage(Page<Letter> page, Params<Letter> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    public Letter customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

}
