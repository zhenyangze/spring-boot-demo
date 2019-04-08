package com.example.security;

import io.jsonwebtoken.lang.Collections;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

// 自定义授权决策
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 必须登录
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BadCredentialsException("未登录！");
        }
        // 当前用户角色
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!Collections.isEmpty(configAttributes) && !Collections.isEmpty(authorities)) {
            // 遍历所需角色
            for (ConfigAttribute configAttribute : configAttributes) {
                String roleName = configAttribute.getAttribute();
                // 遍历当前用户角色
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals(roleName)) {
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException("权限不足！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
