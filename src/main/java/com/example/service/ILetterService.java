package com.example.service;

import com.example.model.po.Letter;
import com.example.model.po.Mail;

public interface ILetterService extends IBaseService<Letter> {

    Mail notifyMail(Letter letter);

}
