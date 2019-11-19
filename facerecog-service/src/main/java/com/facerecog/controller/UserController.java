package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname UserLoginController
 * @Description
 * @Date 2018/10/16 10:55
 * @Created by cjw
 */
@Controller
@RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
@SuppressWarnings({"rawtypes", "unchecked"})
public class UserController extends WebBaseController {
    @Autowired
    private UserService mUserService;

    //    @ResponseBody
    //    @RequestMapping("/login")
    //    public ResultData<ParamData> login(HttpServletRequest request, HttpServletResponse response) {
    //        try {
    //            return mUserService.login(paramDataInit(), request, response);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
    //        }
    //    }

    @ResponseBody
    @RequestMapping("/register")
    public ResultData<ParamData> register(HttpServletRequest request) {
        return mUserService.register(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/logout")
    public ResultData<ParamData> logout(HttpServletRequest request, HttpServletResponse response) {
        mUserService.logout(request, response, paramDataInit());
        return new ResultData<>(ResultEnum.SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/nologin")
    public ResultData<ParamData> noLogin(HttpServletRequest request) {
        return new ResultData<>(ResultEnum.SESSION_ERROR_102);
    }

    @ResponseBody
    @RequestMapping("/change_password")
    public ResultData<ParamData> changePassword(HttpServletRequest request) {
        return mUserService.changePassword(paramDataInit());
    }
}
