package com.example.config.security;

import com.alibaba.fastjson.JSON;
import com.example.model.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.model.vo.ResultVO.SUCCESS;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResultVO resultVO = new ResultVO<>(SUCCESS, "登陆成功！", null);
        String result = JSON.toJSONString(resultVO);
        out.println(result);
    }

}
