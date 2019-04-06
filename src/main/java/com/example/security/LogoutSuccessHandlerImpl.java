package com.example.security;

import com.example.filter.TokenFilter;
import com.example.service.ITokenService;
import com.example.model.vo.ResultVO;
import com.example.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.vo.ResultVO.SUCCESS;

// 退出登录成功
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private ITokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = TokenFilter.getToken(request);
        tokenService.deleteToken(token);
        ResultVO resultVO = new ResultVO<>(SUCCESS, "退出成功！", null);
        ResponseUtil.println(response, resultVO);
    }

}
