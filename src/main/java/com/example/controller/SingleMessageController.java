package com.example.controller;

import com.example.service.ISingleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SingleMessageController {

    @Autowired
    private ISingleMessageService singleMessageService;

}
