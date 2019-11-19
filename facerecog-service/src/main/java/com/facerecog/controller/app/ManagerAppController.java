package com.facerecog.controller.app;


import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.ManagerAppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ConfigController
 * @Description
 * @Date 2018/11/6 10:38
 * @Created by cjw
 */
@Controller
@RequestMapping(value = "/manager",method = {RequestMethod.POST, RequestMethod.GET})
public class ManagerAppController extends WebBaseController {

    @Autowired
    ManagerAppService mManagerAppService;

    @ResponseBody
    @RequestMapping("/add_person")
    public ResultData<ParamData> addPerson(HttpServletRequest request) {
        try {
            return mManagerAppService.addPersonWithGrant(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }
}
