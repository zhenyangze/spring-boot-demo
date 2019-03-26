package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.model.Attachment;

public interface AttachmentService {

    IPage<Attachment> list(long current, long size);

    Attachment findById(Integer id);

    Attachment save(Attachment attachment);
    
    void delete(Integer id);

}
