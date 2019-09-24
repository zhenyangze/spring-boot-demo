package com.xzixi.demo.service;

import com.xzixi.demo.model.po.LetterReply;
import com.xzixi.demo.model.po.Mail;

public interface ILetterReplyService extends IBaseService<LetterReply> {

    Mail notifyMail(LetterReply letterReply);

}
