package com.example.config.security;

import com.example.model.po.Resource;
import com.example.model.po.Role;
import com.example.service.IResourceService;
import com.example.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IRoleService roleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        // 得到用户的请求地址,控制台输出一下
        String requestUrl = request.getMethod() + " " + ((FilterInvocation) object).getRequestUrl();
        System.out.println("用户请求的地址是：" + requestUrl);

        // 查询所有资源
        List<Resource> resources = resourceService.getAll();

        Resource resource = null;
        // 按照url匹配
        for (Resource r: resources) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(r.getResourcePattern(), r.getResourceMethod());
            if (requestMatcher.matches(request)) {
                resource = r;
            }
        }

        // 如果没有匹配的资源则说明大家都可以访问
        if (resource == null) {
            return null;
        }

        // 按照资源id获取角色
        List<Role> roles = roleService.getByResourceId(resource.getId());
        int size = roles.size();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = roles.get(i).getRoleName();
        }
        return SecurityConfig.createList(values);
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
