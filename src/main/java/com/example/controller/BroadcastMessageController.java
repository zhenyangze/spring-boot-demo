package com.example.controller;

import com.example.group.Insert;
import com.example.model.po.BroadcastMessage;
import com.example.model.po.User;
import com.example.model.vo.BroadcastMessageVO;
import com.example.model.vo.ResultVO;
import com.example.model.vo.UserVO;
import com.example.service.IBroadcastMessageService;
import com.example.util.MessageUtil;
import com.example.util.ModelUtil;
import com.example.websocket.SessionIdRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/broadcast", produces = "application/json; charset=UTF-8")
@Api(tags = "广播")
public class BroadcastMessageController {

    @Autowired
    private IBroadcastMessageService broadcastMessageService;
    @Autowired
    private SessionIdRegistry sessionIdRegistry;
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping
    @ApiOperation(value = "发送广播")
    public ResultVO broadcast(@Validated({Insert.class}) BroadcastMessageVO broadcastMessageVO) {
        BroadcastMessage broadcastMessage = (BroadcastMessage) ModelUtil.copy(broadcastMessageVO,
                new ModelUtil.Mapping(BroadcastMessageVO.class, BroadcastMessage.class));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        broadcastMessage.setSendTime(now);
        User currentUser = broadcastMessageService.currentUser();
        broadcastMessage.setSendUser(currentUser);
        broadcastMessage.setSendUserId(currentUser.getId());

        broadcastMessageService.save(broadcastMessage);

        BroadcastMessageVO ret = (BroadcastMessageVO) ModelUtil.copy(broadcastMessage,
                new ModelUtil.Mapping(BroadcastMessage.class, BroadcastMessageVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password", "roles"));

        Map<Integer, Set<String>> sessionIds = sessionIdRegistry.getAllSessionIds();
        for (Set<String> set: sessionIds.values()) {
            for (String sessionId: set) {
                template.convertAndSendToUser(sessionId, "/topic/broadcast",
                        ret, MessageUtil.createHeaders(sessionId));
            }
        }
        return new ResultVO<>(SUCCESS, "广播成功！", null);
    }

}
