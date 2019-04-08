package com.example.websocket;

import com.example.model.po.Resource;
import com.example.model.vo.UserDetailsImpl;
import com.example.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageChannel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

import java.util.Collection;
import java.util.List;

// websocket消息授权决策
public class AccessDecisionFromClientInterceptor implements FromClientInterceptor {

    @Value("${resource.type.websocket}")
    private String websocket;
    @Autowired
    private IResourceService resourceService;

    @Override
    public boolean preHandle(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel, StompSubProtocolHandler handler) {
        System.out.println("-----------------websocket消息授权决策-----------------");
        System.out.println("session_id: "+session.getId());
        Authentication authentication = (Authentication) session.getPrincipal();
        if (authentication!=null) {
            // 用户信息
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            System.out.println("userDetails: "+userDetails);
            // 资源信息
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            System.out.println("authorities: "+authorities);
        } else {
            System.out.println("authentication is null");
        }
        System.out.println("message:\n"+webSocketMessage.getPayload());
        // 查询所有websocket类型的资源
        List<Resource> resources = resourceService.getByType(websocket);
        // TODO 拆分成两个方法
        /*
         * TODO
         *  遍历资源，根据message的类型和destination匹配资源，
         *  获取到当前资源resource，
         *  如果resource==null，禁止访问（安全）
         *  注：这里和http请求授权策略不一样，http请求如果访问controller中没有定义的url会返回404，
         *  所以在http请求授权策略中当resource==null时默认为不需要权限
         *  而websocket如果不做限制可以发送任意主题的消息，所以为了安全，禁止用户发送资源定义以外的主题
         */

        /*
         * TODO
         *  如果authorities（当前用户资源）不为空 !Collections.isEmpty(authorities)
         *  resource和authorities进行匹配，
         *  匹配上则放行 return true，
         *  匹配不上则调用session.sendMessage()，将错误信息发送给客户端 return false
         */

        return true;
    }

}
