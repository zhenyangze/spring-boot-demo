package com.example.aspect;

import com.example.model.po.User;
import com.example.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.model.vo.ResultVO.FORBIDDEN;

// 登录验证切面
@Component
@Aspect
@Order(2)
@Slf4j
public class AccessAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @Value("${access.ignore-methods}")
    private String[] ignoreMethods;
    @Value("${access.ignore-uris}")
    private String[] ignoreUris;

    @Around("execution(* com..controller.*.*(..))")
    public Object accessHandle(ProceedingJoinPoint pjp) throws Throwable {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        for (String method_: ignoreMethods) {
            if (method_.equalsIgnoreCase(method)) {
                return pjp.proceed();
            }
        }
        for (String uri_: ignoreUris) {
            if (uri.contains(uri_)) {
                return pjp.proceed();
            }
        }
        User user = (User) session.getAttribute("user");
        if (user==null) {
            return new ResultVO<>(FORBIDDEN, "请登录！", null);
        }
        return pjp.proceed();
    }

}
