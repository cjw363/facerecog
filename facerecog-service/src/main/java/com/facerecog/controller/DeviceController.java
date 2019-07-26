package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/device")
@SuppressWarnings({"rawtypes", "unchecked"})
public class DeviceController extends WebBaseController {

    @Autowired
    private DeviceService mDeviceService;

    @ResponseBody
    @RequestMapping("/list")
    public ResultData<PageData<ParamData>> list() {
        try {
            return mDeviceService.getDeviceList(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/inact_list")
    public ResultData<PageData<ParamData>> inactList() {
        try {
            return mDeviceService.getInActDeviceList(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/all_list")
    public ResultData<List<PageData<ParamData>>> allList() {
        try {
            ResultData<PageData<ParamData>> device = mDeviceService.getDeviceList(paramDataInit());
            ResultData<PageData<ParamData>> inActDevice = mDeviceService.getInActDeviceList(paramDataInit());

            List<PageData<ParamData>> list = new ArrayList();
            list.add(inActDevice.getData());
            list.add(device.getData());
            return new ResultData<>(HandleEnum.SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/activate")
    public ResultData<ParamData> activate() {
        try {
            return mDeviceService.activateDevice(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/inact_detail")
    public ResultData<ParamData> inactDetail() {
        try {
            ParamData data = mDeviceService.queryInActDevice(paramDataInit());
            return new ResultData<>(HandleEnum.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/detail")
    public ResultData<ParamData> detail() {
        try {
            ParamData data = mDeviceService.queryDevice(paramDataInit());
            return new ResultData<>(HandleEnum.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/grant_person_list")
    public ResultData<PageData<ParamData>> personList() {
        try {
            return mDeviceService.getGrantPersonList(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/change_info")
    public ResultData<ParamData> changeInfo() {
        try {
            return mDeviceService.changeDeviceInfo(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultData<ParamData> delete() {
        try {
            return mDeviceService.deleteDevice(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/check_update")
    public ResultData<ParamData> checkUpdate() {
        try {
            return mDeviceService.checkAppVersionUpdate(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/group_device_list")
    public ResultData<ParamData> groupDeviceList() {
        try {
            return mDeviceService.getGroupDeviceList(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/list_by_group")
    public ResultData<PageData<ParamData>> listByGroup() {
        try {
            return mDeviceService.getDeviceListByGroup(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(HandleEnum.FAIL, e.getMessage());
        }
    }
}
