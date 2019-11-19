package com.facerecog.security;

import com.facerecog.bo.UserInfo;
import com.facerecog.dao.UserDao;
import com.facerecog.ehcache.WebCache;
import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.utils.CommConst;
import com.facerecog.utils.JsonUtils;
import com.facerecog.utils.SystemConfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname SecSuccessHandler
 * @Description
 * @Date 2019/8/22 21:59
 * @Created by cjw
 */
@Component
public class SecSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private WebCache memory;
    @Resource
    private UserDao mUserDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        memory.putCache(userInfo);

        //存入cookie中
        Cookie cookie = new Cookie(CommConst.ACCESS_CPFR_TOKEN, userInfo.getToken());
        cookie.setMaxAge(SystemConfig.COOKIE_LIVE_TIME);
        cookie.setPath(request.getContextPath() + "/");
        //写回浏览器
        response.addCookie(cookie);

        ParamData paramData = new ParamData();
        paramData.put(CommConst.USER_ID, userInfo.getUserId());
        paramData.put(CommConst.ACCESS_CPFR_TOKEN, userInfo.getToken());

        mUserDao.updateUserLoginTime(paramData);

        PrintWriter out = response.getWriter();
        ResultData resultData = new ResultData<>(ResultEnum.SUCCESS,userInfo);
        String jsonData = JsonUtils.serialize(resultData);
        if(!StringUtils.isEmpty(jsonData))
            out.write(jsonData);
        out.flush();
        out.close();
    }
}
