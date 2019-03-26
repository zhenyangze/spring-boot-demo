package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.AttachmentDao;
import com.example.model.Attachment;
import com.example.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class AttachmentImpl implements AttachmentService {

    @Autowired
    private AttachmentDao attachmentDao;

    @Override
    public IPage<Attachment> list(long current, long size) {
        IPage<Attachment> page = attachmentDao.selectPage(new Page<>(current, size), null);
        return page;
    }

    @Override
    public Attachment findById(Integer id) {
        return attachmentDao.selectById(id);
    }

    @Override
    @Transactional
    public Attachment save(Attachment attachment) {
        attachmentDao.insert(attachment);
        return attachment;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Attachment attachment = attachmentDao.selectById(id);
        File file = new File(attachment.getAttachmentPath());
        file.delete();
        attachmentDao.deleteById(id);
    }

}
