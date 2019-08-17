package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exception.LogicException;
import com.example.mapper.LetterMapper;
import com.example.model.po.Letter;
import com.example.model.po.User;
import com.example.params.Params;
import com.example.service.ILetterService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void customRemoveByIds(List<Integer> ids) {
        User currentUser = currentUser();
        Integer currentUserId = currentUser.getId();
        List<Letter> list = baseMapper.selectBatchIds(ids);
        for (Letter letter: list) {
            if (!currentUserId.equals(letter.getLetterUserId())) {
                throw new LogicException("无法删除其他用户的留言！");
            }
        }
        super.removeByIds(ids);
    }

}
