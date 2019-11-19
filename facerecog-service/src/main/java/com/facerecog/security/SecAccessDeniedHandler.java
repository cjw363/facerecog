package com.facerecog.security;

import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.ResultData;
import com.facerecog.utils.JsonUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        ResultData resultData = new ResultData<>(ResultEnum.FAIL, e.getMessage());
        String jsonData = JsonUtils.serialize(resultData);
        if(!StringUtils.isEmpty(jsonData))
        out.write(jsonData);
        out.flush();
        out.close();
    }
}