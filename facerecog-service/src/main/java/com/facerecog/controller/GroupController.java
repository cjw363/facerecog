package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname GroupController
 * @Description
 * @Date 2018/10/19 11:17
 * @Created by cjw
 */
@Controller
@RequestMapping(value = "/group", method = {RequestMethod.POST, RequestMethod.GET})
@SuppressWarnings({"rawtypes", "unchecked"})
public class GroupController extends WebBaseController {

    @Autowired
    private GroupService mGroupService;

    @ResponseBody
    @RequestMapping("/list")
    public ResultData<PageData<ParamData>> list() {
        try {
            return mGroupService.getGroupList(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/detail")
    public ResultData<ParamData> detail() {
        try {
            ParamData data = mGroupService.queryGroup(paramDataInit());
            return new ResultData<>(HandleEnum.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultData<ParamData> add() {
        try {
            return mGroupService.addGroup(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/add_person")
    public ResultData<ParamData> addPerson() {
        try {
            return mGroupService.addGroupPerson(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/add_device")
    public ResultData<ParamData> addDevice() {
        try {
            return mGroupService.addGroupDevice(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/update_info")
    public ResultData<ParamData> updateInfo() {
        try {
            return mGroupService.updateGroupInfo(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultData<ParamData> delete() {
        try {
            return mGroupService.deleteGroup(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/delete_person")
    public ResultData<ParamData> deletePerson() {
        try {
            return mGroupService.deleteGroupPerson(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/delete_device")
    public ResultData<ParamData> deleteDevice() {
        try {
            return mGroupService.deleteGroupDevice(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }
}
