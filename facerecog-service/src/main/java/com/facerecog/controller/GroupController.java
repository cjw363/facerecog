package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.ResultEnum;
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
        return mGroupService.getGroupList(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/detail")
    public ResultData<ParamData> detail() {
        ParamData data = mGroupService.queryGroup(paramDataInit());
        return new ResultData<>(ResultEnum.SUCCESS, data);
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultData<ParamData> add() {
        return mGroupService.addGroup(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/add_person")
    public ResultData<ParamData> addPerson() {
        return mGroupService.addGroupPerson(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/add_device")
    public ResultData<ParamData> addDevice() {
        return mGroupService.addGroupDevice(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/update_info")
    public ResultData<ParamData> updateInfo() {
        return mGroupService.updateGroupInfo(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultData<ParamData> delete() {
        return mGroupService.deleteGroup(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/delete_person")
    public ResultData<ParamData> deletePerson() {
        return mGroupService.deleteGroupPerson(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/delete_device")
    public ResultData<ParamData> deleteDevice() {
        return mGroupService.deleteGroupDevice(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/list_device_person_by_group")
    public ResultData<ParamData> listDevicePersonByGroup() {
        return mGroupService.getListDevicePersonByGroup(paramDataInit());
    }
}
