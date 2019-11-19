package com.facerecog.security;

import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.ResultData;
import com.facerecog.utils.JsonUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname AuthenticationFailureHandler
 * @Description
 * @Date 2019/8/22 21:53
 * @Created by cjw
 */
@Component
public class SecFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        StringBuilder sb = new StringBuilder();
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            sb.append("用户名或密码输入错误，登录失败!");
        } else if (e instanceof DisabledException) {
            sb.append("账户被禁用，登录失败，请联系管理员!");
        } else {
            sb.append("登录失败!");
        }
        ResultData resultData = new ResultData<>(ResultEnum.FAIL, sb.toString());
        String jsonData = JsonUtils.serialize(resultData);
        if(!StringUtils.isEmpty(jsonData))
            out.write(jsonData);
        out.flush();
        out.close();
    }
}
