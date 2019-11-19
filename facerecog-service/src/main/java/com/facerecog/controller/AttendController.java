package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.AttendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date 2019/3/14
 * @Created by xwr
 */
@Controller
@RequestMapping(value = "/attend", method = {RequestMethod.POST, RequestMethod.GET})
public class AttendController extends WebBaseController {
    @Autowired
    private AttendService mAttendService;

    @ResponseBody
    @RequestMapping("/attend_list")
    public ResultData<PageData<ParamData>> attendList(HttpServletRequest request) {
        return mAttendService.getAttendList(paramDataInit());
    }

    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        mAttendService.export(paramDataInit(), response);
    }
}
