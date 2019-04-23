package com.example.security;

import com.example.model.vo.ResultVO;
import com.example.model.vo.TokenVO;
import com.example.model.vo.UserDetailsImpl;
import com.example.service.ITokenService;
import com.example.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.model.vo.ResultVO.SUCCESS;

// 认证成功
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private ITokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        TokenVO tokenVO = tokenService.saveToken(userDetails);
        ResultVO resultVO = new ResultVO<>(SUCCESS, "登录成功！", tokenVO);
        ResponseUtil.println(response, resultVO);
    }

}
