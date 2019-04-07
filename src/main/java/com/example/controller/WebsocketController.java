package com.example.controller;

import com.example.model.vo.MessageVO;
import com.example.model.vo.ResponseVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @MessageMapping("/welcome")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @SendTo("/topic/getResponse")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public ResponseVO say(MessageVO messageVO) throws Exception {
        Thread.sleep(1000);
        return new ResponseVO("Welcome, " + messageVO.getName() + "!");
    }

}
