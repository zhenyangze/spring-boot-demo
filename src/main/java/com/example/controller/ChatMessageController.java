package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.po.ChatMessage;
import com.example.model.po.User;
import com.example.model.vo.ChatMessageVO;
import com.example.model.vo.ResultVO;
import com.example.model.vo.UserVO;
import com.example.service.IChatMessageService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/chat", produces = "application/json; charset=UTF-8")
@Api(tags = "点对点消息")
@Validated
public class ChatMessageController {

    @Value("${websocket.destination.chat}")
    private String chat;
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
        Wrapper<ChatMessage> wrapper = new QueryWrapper<>(chatMessageVO);
        IPage<ChatMessage> iPage = chatMessageService.page(page, wrapper);
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
        Wrapper<ChatMessage> wrapper = new QueryWrapper<>(chatMessageVO);
        IPage<ChatMessage> iPage = chatMessageService.page(page, wrapper);
        IPage messages = (IPage) ModelUtil.copy(iPage,
                new ModelUtil.Mapping(ChatMessage.class, ChatMessageVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", messages);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询消息")
    public ResultVO<ChatMessageVO> findById(@PathVariable @NotNull(message = "消息id不能为空") @ApiParam(value = "消息id", required = true) Integer id) {
        ChatMessage chatMessage = chatMessageService.getById(id);
        ChatMessageVO chatMessageVO = (ChatMessageVO) ModelUtil.copy(chatMessage,
                new ModelUtil.Mapping(ChatMessage.class, ChatMessageVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", chatMessageVO);
    }

}
