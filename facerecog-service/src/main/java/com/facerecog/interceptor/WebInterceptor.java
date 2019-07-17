package com.facerecog.interceptor;

import com.facerecog.ehcache.WebCache;
import com.facerecog.utils.CommUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/12/27.
 */
@Component
public class WebInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private WebCache memory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查请求的token值是否为空
        String token = CommUtil.getTokenFromRequest(request);
        if (StringUtils.isEmpty(token) || !memory.checkCache(token)) {
            request.getRequestDispatcher("/user/nologin").forward(request, response);
            return false;
        }
        return true;
    }
}
