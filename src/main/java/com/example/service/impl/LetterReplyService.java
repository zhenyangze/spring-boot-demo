package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.LetterReplyMapper;
import com.example.model.po.LetterReply;
import com.example.params.Params;
import com.example.service.ILetterReplyService;
import org.springframework.stereotype.Service;

@Service
public class LetterReplyService extends BaseService<LetterReplyMapper, LetterReply> implements ILetterReplyService {

    @Override
    public IPage<LetterReply> customPage(Page<LetterReply> page, Params<LetterReply> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

}
