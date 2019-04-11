package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.Insert;
import com.example.model.po.BroadcastMessage;
import com.example.model.po.User;
import com.example.model.vo.BroadcastMessageVO;
import com.example.model.vo.ResultVO;
import com.example.model.vo.UserVO;
import com.example.service.IBroadcastMessageService;
import com.example.util.GenericMessageBuilder;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/broadcast", produces = "application/json; charset=UTF-8")
@Api(tags = "广播")
@Validated
public class BroadcastMessageController {

    @Value("${websocket.destination.broadcast}")
    private String broadcast;
    @Autowired
    private IBroadcastMessageService broadcastMessageService;
    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询广播列表")
    public ResultVO list(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "当前页", defaultValue = "10", required = true) long size,
                         BroadcastMessageVO broadcastMessageVO) {
        Page<BroadcastMessage> page = new Page<>(current, size);
        Wrapper<BroadcastMessage> wrapper = new QueryWrapper<>(broadcastMessageVO);
        IPage<BroadcastMessage> iPage = broadcastMessageService.page(page, wrapper);
        IPage messages = (IPage) ModelUtil.copy(iPage,
                new ModelUtil.Mapping(BroadcastMessage.class, BroadcastMessageVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", messages);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询广播")
    public ResultVO<BroadcastMessageVO> findById(@PathVariable @NotNull(message = "广播id不能为空") @ApiParam(value = "广播id", required = true) Integer id) {
        BroadcastMessage broadcastMessage = broadcastMessageService.getById(id);
        BroadcastMessageVO broadcastMessageVO = (BroadcastMessageVO) ModelUtil.copy(broadcastMessage,
                new ModelUtil.Mapping(BroadcastMessage.class, BroadcastMessageVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", broadcastMessageVO);
    }

    @DeleteMapping
    @ApiOperation(value = "删除广播")
    public ResultVO delete(@PathVariable @NotNull(message = "广播id不能为空") @ApiParam(value = "广播id", required = true) Integer id) {
        broadcastMessageService.removeById(id);
        return new ResultVO<>(SUCCESS, "删除广播成功！", null);
    }


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

        GenericMessage message = new GenericMessageBuilder()
                .payload(ModelUtil.copy(broadcastMessage,
                        new ModelUtil.Mapping(BroadcastMessage.class, BroadcastMessageVO.class),
                        new ModelUtil.Mapping(User.class, UserVO.class, "password", "roles")))
                .destination(broadcast)
                .leaveMutable(true)
                .build();
        template.send(message);
        return new ResultVO<>(SUCCESS, "广播成功！", null);
    }

}
