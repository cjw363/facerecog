package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.GrantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname GrantController
 * @Description
 * @Date 2018/10/19 11:17
 * @Created by cjw
 */
@Controller
@RequestMapping(value = "/grant", method = {RequestMethod.POST, RequestMethod.GET})
@SuppressWarnings({"rawtypes", "unchecked"})
public class GrantController extends WebBaseController {

    @Autowired
    private GrantService mGrantService;

    @ResponseBody
    @RequestMapping("/add")
    public ResultData<ParamData> add(HttpServletRequest request) throws Exception {
        return mGrantService.addGrant(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/ban")
    public ResultData<ParamData> ban(HttpServletRequest request) throws Exception {
        return mGrantService.banGrant(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/list_device_person")
    public ResultData<ParamData> listDevicePerson(HttpServletRequest request) {
        return mGrantService.getListDevicePerson(paramDataInit());
    }
}
