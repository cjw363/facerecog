package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.RecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname RecordController
 * @Description
 * @Date 2018/11/6 10:38
 * @Created by cjw
 */
@Controller
@RequestMapping(value = "/record", method = {RequestMethod.POST, RequestMethod.GET})
public class RecordController extends WebBaseController {

    @Autowired
    private RecordService mRecordService;

    @ResponseBody
    @RequestMapping("/list_base64")
    public ResultData<PageData<ParamData>> listBase64(HttpServletRequest request) {
        return mRecordService.getRecordBase64List(paramDataInit());
    }

    /**
     * 删除记录
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public ResultData<ParamData> delete(HttpServletRequest request) {
        return mRecordService.deleteRecord(paramDataInit());
    }

    /**
     * 批量删除
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteLists")
    public ResultData<ParamData> deleteLists(HttpServletRequest request) {
        return mRecordService.deleteRecordLists(paramDataInit());
    }


    /**
     * 清空
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/clear")
    public ResultData<ParamData> clear(HttpServletRequest request) {
        return mRecordService.clearRecord(paramDataInit());
    }
}
