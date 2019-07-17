package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.AttendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date 2019/3/14
 * @Created by xwr
 */
@Controller
@RequestMapping("/attend")
public class AttendController extends WebBaseController {
    @Autowired
    private AttendService mAttendService;

    @ResponseBody
    @RequestMapping("/attend_list")
    public ResultData<PageData<ParamData>> attendList(HttpServletRequest request) {
        try {
            return mAttendService.getAttendList(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }
}
