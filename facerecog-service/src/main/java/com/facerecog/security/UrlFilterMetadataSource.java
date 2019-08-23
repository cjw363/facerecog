package com.facerecog.security;

import com.facerecog.dao.UserDao;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

/**
 * 该类的主要功能就是通过当前的请求地址，获取该地址需要的用户角色
 */
@Component
public class UrlFilterMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource
    private UserDao mUserDao;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * @param o
     * @return 返回null的话，意味着当前这个请求不需要任何角色就能访问，甚至不需要登录。
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<String> roles = mUserDao.selectRoleNameListByUrl(requestUrl.split("\\?")[0]);
        if (roles != null && roles.size() > 0)
            return SecurityConfig.createList(roles.toArray(new String[0]));

        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_NO_CONFIG");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}