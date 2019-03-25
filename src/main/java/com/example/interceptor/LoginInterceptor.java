package com.example.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

// 登录拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", -1);
            map.put("message", "请登录！");
            String result = JSONObject.toJSONString(map);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(result);
            return false;
        }
        return true;
    }
}
