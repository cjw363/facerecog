package com.facerecog.controller;

import com.facerecog.api.DeviceApi;
import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DeviceController
 * @Description
 * @Date 2018/10/19 11:17
 * @Created by cjw
 */
@Controller
@RequestMapping(value = "/device", method = {RequestMethod.POST, RequestMethod.GET})
@SuppressWarnings({"rawtypes", "unchecked"})
public class DeviceController extends WebBaseController implements DeviceApi {

    @Autowired
    private DeviceService mDeviceService;

    @ResponseBody
    @RequestMapping("/list")
    public ResultData<PageData<ParamData>> list() {
        return mDeviceService.getDeviceList(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/inact_list")
    public ResultData<PageData<ParamData>> inactList() {
        return mDeviceService.getInActDeviceList(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/all_list")
    public ResultData<List<PageData<ParamData>>> allList() {
        ResultData<PageData<ParamData>> device = mDeviceService.getDeviceList(paramDataInit());
        ResultData<PageData<ParamData>> inActDevice = mDeviceService.getInActDeviceList(paramDataInit());

        List<PageData<ParamData>> list = new ArrayList();
        list.add(inActDevice.getData());
        list.add(device.getData());
        return new ResultData<>(ResultEnum.SUCCESS, list);
    }

    @ResponseBody
    @RequestMapping("/activate")
    public ResultData<ParamData> activate() throws Exception {
        return mDeviceService.activateDevice(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/inact_detail")
    public ResultData<ParamData> inactDetail() {
        ParamData data = mDeviceService.queryInActDevice(paramDataInit());
        return new ResultData<>(ResultEnum.SUCCESS, data);
    }

    @ResponseBody
    @RequestMapping("/detail")
    public ResultData<ParamData> detail() {
        ParamData data = mDeviceService.queryDevice(paramDataInit());
        return new ResultData<>(ResultEnum.SUCCESS, data);
    }

    @ResponseBody
    @RequestMapping("/grant_person_list")
    public ResultData<PageData<ParamData>> personList() {
        return mDeviceService.getGrantPersonList(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/change_info")
    public ResultData<ParamData> changeInfo() throws Exception {
        return mDeviceService.changeDeviceInfo(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultData<ParamData> delete() throws Exception {
        return mDeviceService.deleteDevice(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/check_update")
    public ResultData<ParamData> checkUpdate() throws Exception {
        return mDeviceService.checkAppVersionUpdate(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/group_device_list")
    public ResultData<ParamData> groupDeviceList() {
        return mDeviceService.getGroupDeviceList(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/list_by_group")
    public ResultData<PageData<ParamData>> listByGroup() {
        return mDeviceService.getDeviceListByGroup(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/list_group_unselected")
    public ResultData<List<ParamData>> listNoSelected() {
        return mDeviceService.getListGroupUnSelected(paramDataInit());
    }
}
