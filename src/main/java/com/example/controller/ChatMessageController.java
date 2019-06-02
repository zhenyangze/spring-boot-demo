package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exception.LogicException;
import com.example.group.Insert;
import com.example.model.po.ChatMessage;
import com.example.model.po.User;
import com.example.model.vo.ChatMessageVO;
import com.example.model.vo.ResultVO;
import com.example.model.vo.UserVO;
import com.example.params.Params;
import com.example.service.IChatMessageService;
import com.example.util.MessageHeadersBuilder;
import com.example.util.ModelUtil;
import com.example.websocket.SessionIdRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

import static com.example.config.KafkaConfig.CHAT_TOPIC;
import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/chat", produces = "application/json; charset=UTF-8")
@Api(tags = "点对点消息")
@Validated
public class ChatMessageController {

    @Value("${websocket.destination.chat}")
    private String chat;
    @Autowired
    private SessionIdRegistry sessionIdRegistry;
    @Autowired
    private IChatMessageService chatMessageService;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ListenableFutureCallback<SendResult<String, Object>> defaultFutureCallback;


    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询消息列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                         ChatMessageVO chatMessageVO) {
        Page<ChatMessage> page = new Page<>(current, size);
        Params<ChatMessage> params = new Params<>(chatMessageVO);
        IPage<ChatMessage> iPage = chatMessageService.customPage(page, params);
        IPage messages = (IPage) ModelUtil.copy(iPage,
                new ModelUtil.Mapping(ChatMessage.class, ChatMessageVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", messages);
    }

    @GetMapping("/{userId}/{anotherUserId}/{current}/{size}")
    @ApiOperation(value = "查询往来消息")
    public ResultVO contacts(@PathVariable @NotNull(message = "用户id不能为空") @ApiParam(value = "用户id", required = true) Integer userId,
                             @PathVariable @NotNull(message = "另一个用户id不能为空") @ApiParam(value = "另一个用户id", required = true) Integer anotherUserId,
                             @PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                             @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                             ChatMessageVO chatMessageVO) {
        Page<ChatMessage> page = new Page<>(current, size);
        Params<ChatMessage> params = new Params<>(chatMessageVO);
        params.put("userId", userId);
        params.put("anotherUserId", anotherUserId);
        IPage<ChatMessage> iPage = chatMessageService.customPage(page, params);
        IPage messages = (IPage) ModelUtil.copy(iPage,
                new ModelUtil.Mapping(ChatMessage.class, ChatMessageVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", messages);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询消息")
    public ResultVO<ChatMessageVO> findById(@PathVariable @NotNull(message = "消息id不能为空") @ApiParam(value = "消息id", required = true) Integer id) {
        ChatMessage chatMessage = chatMessageService.customGetById(id);
        ChatMessageVO chatMessageVO = (ChatMessageVO) ModelUtil.copy(chatMessage,
                new ModelUtil.Mapping(ChatMessage.class, ChatMessageVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", chatMessageVO);
    }

    @PostMapping
    @ApiOperation(value = "发送消息")
    public ResultVO save(@Validated({Insert.class}) ChatMessageVO chatMessageVO) {
        ChatMessage chatMessage = (ChatMessage) ModelUtil.copy(chatMessageVO, new ModelUtil.Mapping(ChatMessageVO.class, ChatMessage.class));
        long now = System.currentTimeMillis();
        chatMessage.setSendTime(now);
        User currentUser = chatMessageService.currentUser();
        chatMessage.setSendUser(currentUser);
        chatMessage.setSendUserId(currentUser.getId());
        chatMessage.setReadStatus(IChatMessageService.UNREAD);
        chatMessageService.save(chatMessage);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(CHAT_TOPIC, chatMessage);
        future.addCallback(defaultFutureCallback);

        Set<String> sessionIds = sessionIdRegistry.getSessionIds(chatMessage.getToUserId());
        sessionIds.forEach(sessionId -> template.convertAndSendToUser(
                sessionId,
                chat,
                ModelUtil.copy(chatMessage,
                        new ModelUtil.Mapping(ChatMessage.class, ChatMessageVO.class),
                        new ModelUtil.Mapping(User.class, UserVO.class, "password", "roles")),
                new MessageHeadersBuilder()
                        .sessionId(sessionId)
                        .leaveMutable(true)
                        .build()));
        return new ResultVO<>(SUCCESS, "发送消息成功！", null);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新消息状态为已读")
    public ResultVO update(@PathVariable @NotNull(message = "消息id不能为空") Integer id) {
        ChatMessage chatMessage = chatMessageService.getById(id);
        User currentUser = chatMessageService.currentUser();
        if (!chatMessage.getToUserId().equals(currentUser.getId())) {
            throw new LogicException("无法更新！");
        }
        chatMessage.setReadStatus(IChatMessageService.READ);
        chatMessageService.updateById(chatMessage);
        return new ResultVO<>(SUCCESS, "更新消息状态成功！", null);
    }

}
