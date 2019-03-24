package com.example.interceptor;

import com.example.model.User;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 登录拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null) {
            JSONObject result = new JSONObject();
            result.put("status", -1);
            result.put("message", "请登录！");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(result.toString());
            return false;
        }
        return true;
    }
}
