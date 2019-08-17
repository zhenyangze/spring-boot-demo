package com.example.service;

import com.example.model.po.LetterReply;
import com.example.model.po.Mail;

public interface ILetterReplyService extends IBaseService<LetterReply> {

    Mail notifyMail(LetterReply letterReply);

}
