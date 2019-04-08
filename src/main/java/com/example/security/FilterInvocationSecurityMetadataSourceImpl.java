package com.example.security;

import com.example.model.po.Resource;
import com.example.model.po.Role;
import com.example.service.IResourceService;
import com.example.service.IRoleService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

// 根据访问url获取所需角色
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Value("${resource.type.http}")
    private String http;
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IRoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        // 查询所有http类型的资源
        List<Resource> resources = resourceService.getByType(http);

        Resource resource = null;
        // 按照url匹配
        if (!Collections.isEmpty(resources)) {
            for (Resource r: resources) {
                RequestMatcher requestMatcher = new AntPathRequestMatcher(r.getResourcePattern(), r.getResourceMethod());
                if (requestMatcher.matches(request)) {
                    resource = r;
                }
            }
        }

        // 如果没有匹配的资源则说明大家都可以访问
        if (resource == null) {
            return null;
        }

        // 按照资源id获取角色
        List<Role> roles = roleService.getByResourceId(resource.getId());
        if (!Collections.isEmpty(roles)) {
            int size = roles.size();
            String[] values = new String[size];
            for (int i = 0; i < size; i++) {
                values[i] = roles.get(i).getRoleName();
            }
            return SecurityConfig.createList(values);
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

}
