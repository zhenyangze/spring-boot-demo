package com.xzixi.demo.service;

import com.xzixi.demo.model.po.Letter;
import com.xzixi.demo.model.po.Mail;

public interface ILetterService extends IBaseService<Letter> {

    Mail notifyMail(Letter letter);

}
