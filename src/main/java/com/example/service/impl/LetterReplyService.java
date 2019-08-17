package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exception.LogicException;
import com.example.mapper.LetterReplyMapper;
import com.example.model.po.LetterReply;
import com.example.model.po.User;
import com.example.params.Params;
import com.example.service.ILetterReplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterReplyService extends BaseService<LetterReplyMapper, LetterReply> implements ILetterReplyService {

    @Override
    public IPage<LetterReply> customPage(Page<LetterReply> page, Params<LetterReply> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    public void customRemoveByIds(List<Integer> ids) {
        User currentUser = currentUser();
        Integer currentUserId = currentUser.getId();
        List<LetterReply> list = baseMapper.selectBatchIds(ids);
        for (LetterReply reply: list) {
            if (!currentUserId.equals(reply.getReplyUserId())) {
                throw new LogicException("无法删除其他用户的回复！");
            }
        }
        super.removeByIds(ids);
    }

}
