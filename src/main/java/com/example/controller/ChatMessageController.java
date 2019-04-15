package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.Insert;
import com.example.group.Update;
import com.example.params.Params;
import com.example.model.po.ChatMessage;
import com.example.model.po.User;
import com.example.model.vo.ChatMessageVO;
import com.example.model.vo.ResultVO;
import com.example.model.vo.UserVO;
import com.example.service.IChatMessageService;
import com.example.util.MessageHeadersBuilder;
import com.example.util.ModelUtil;
import com.example.websocket.SessionIdRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/chat", produces = "application/json; charset=UTF-8")
@Api(tags = "点对点消息")
@Validated
public class ChatMessageController implements IBaseController<ChatMessageVO> {

    @Value("${websocket.destination.chat}")
    private String chat;
    @Autowired
    private SessionIdRegistry sessionIdRegistry;
    @Autowired
    private IChatMessageService chatMessageService;
    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询消息列表")
    public ResultVO list(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
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
        Timestamp now = new Timestamp(System.currentTimeMillis());
        chatMessage.setSendTime(now);
        User currentUser = chatMessageService.currentUser();
        chatMessage.setSendUser(currentUser);
        chatMessage.setSendUserId(currentUser.getId());
        chatMessage.setReadStatus(0);
        chatMessageService.save(chatMessage);

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

    @PutMapping
    @ApiOperation(value = "更新消息")
    public ResultVO update(@Validated({Update.class}) ChatMessageVO chatMessageVO) {
        ChatMessage chatMessage = (ChatMessage) ModelUtil.copy(chatMessageVO, new ModelUtil.Mapping(ChatMessageVO.class, ChatMessage.class));
        chatMessageService.updateById(chatMessage);
        return new ResultVO<>(SUCCESS, "更新消息成功！", null);
    }

}
