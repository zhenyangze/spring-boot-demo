package com.xzixi.demo.security;

import com.xzixi.demo.model.po.Dept;
import com.xzixi.demo.model.po.User;
import com.xzixi.demo.model.vo.*;
import com.xzixi.demo.service.ITokenService;
import com.xzixi.demo.service.IUserService;
import com.xzixi.demo.util.ModelUtil;
import com.xzixi.demo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.xzixi.demo.model.vo.ResultVO.SUCCESS;

// 认证成功
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private ITokenService tokenService;
    @Autowired
    private IUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        TokenVO tokenVO = tokenService.saveToken(userDetails);
        tokenVO = (TokenVO) ModelUtil.copy(tokenVO,
                new ModelUtil.Mapping(User.class, UserVO.class, "password", "roles", "logintime", "deptId"),
                new ModelUtil.Mapping(Dept.class, DeptVO.class, "level", "seq"));
        ResultVO<TokenVO> resultVO = new ResultVO<>(SUCCESS, "登录成功！", tokenVO);
        ResponseUtil.println(response, resultVO);
        // 设置登录时间
        User user = userDetails.getUser();
        user.setLogintime(System.currentTimeMillis());
        userService.updateById(user);
    }

}
