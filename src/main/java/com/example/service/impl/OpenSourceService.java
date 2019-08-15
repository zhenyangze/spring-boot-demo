package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.OpenSourceMapper;
import com.example.model.po.OpenSource;
import com.example.model.po.OpenSourceIntro;
import com.example.params.Params;
import com.example.service.IOpenSourceIntroService;
import com.example.service.IOpenSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenSourceService extends BaseService<OpenSourceMapper, OpenSource> implements IOpenSourceService {

    @Autowired
    private IOpenSourceIntroService openSourceIntroService;

    @Override
    public IPage<OpenSource> customPage(Page<OpenSource> page, Params<OpenSource> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    public OpenSource customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

    @Override
    @Transactional
    public void customSave(OpenSource openSource) {
        baseMapper.insert(openSource);
        Integer openSourceId = openSource.getId();
        OpenSourceIntro intro = openSource.getIntro();
        intro.setOpenSourceId(openSourceId);
        openSourceIntroService.save(intro);
    }

    @Override
    @Transactional
    public void customUpdateById(OpenSource openSource) {
        baseMapper.updateById(openSource);
        Integer openSourceId = openSource.getId();
        OpenSourceIntro intro = openSource.getIntro();
        intro.setOpenSourceId(openSourceId);
        openSourceIntroService.updateById(intro);
    }

}
