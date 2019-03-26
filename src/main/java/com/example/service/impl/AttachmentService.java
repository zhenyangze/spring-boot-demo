package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.AttachmentMapper;
import com.example.model.Attachment;
import com.example.service.IAttachmentService;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

}
