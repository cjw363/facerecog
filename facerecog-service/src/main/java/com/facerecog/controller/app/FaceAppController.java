package com.facerecog.controller.app;

import com.facerecog.controller.base.AppBaseController;
import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.FaceAppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname AppDeviceController
 * @Description
 * @Date 2018/10/25 10:38
 * @Created by cjw
 */
@Controller
@RequestMapping(value = "/app",method = {RequestMethod.POST, RequestMethod.GET})
@SuppressWarnings({"rawtypes", "unchecked"})
public class FaceAppController extends AppBaseController {
    @Autowired
    FaceAppService mFaceAppService;

    @ResponseBody
    @RequestMapping("/device_register")
    public ResultData<ParamData> deviceRegister(HttpServletRequest request, HttpServletResponse response) {
        try {
            return mFaceAppService.register(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/device_info")
    public ResultData<ParamData> deviceInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            return mFaceAppService.getDeviceInfo(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/person_list")
    public ResultData<List<ParamData>> personList(HttpServletRequest request, HttpServletResponse response) {
        try {
            return mFaceAppService.getPersonBase64List(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/grant_list")
    public ResultData<List<ParamData>> grantList(HttpServletRequest request, HttpServletResponse response) {
        try {
            return mFaceAppService.getGrantList(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/upload_record")
    public ResultData<ParamData> uploadRecord(HttpServletRequest request) {
        try {
            return mFaceAppService.addRecord(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/current_date")
    public ResultData<ParamData> currentDate(HttpServletRequest request) {
        try {
            return mFaceAppService.getCurrentDate();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/disconnect")
    public ResultData<ParamData> disconnect(HttpServletRequest request) {
        return new ResultData<>(ResultEnum.FAIL, "未与服务器建立连接!");
    }

    @ResponseBody
    @RequestMapping("/add_person")
    public ResultData<ParamData> addPerson(HttpServletRequest request) {
        try {
            return mFaceAppService.addPersonWithGrant(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/sync_person")
    public ResultData<List<ParamData>> syncPerson(HttpServletRequest request) {
        try {
            return mFaceAppService.syncPerson(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/last_version_info")
    public ResultData<ParamData> lastVersionInfo(HttpServletRequest request) {
        try {
            return mFaceAppService.getLastVersionInfo(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/downl_apk")
    public ResultData<ParamData> downloadApk(HttpServletRequest request, HttpServletResponse response) {
        try {
            mFaceAppService.downloadApk(paramDataInit(), request, response);
            return new ResultData<>(ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/person_list2")
    public ResultData<List<ParamData>> personList2(HttpServletRequest request, HttpServletResponse response) {
        try {
            return mFaceAppService.getPersonList(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/downl_image")
    public ResultData<ParamData> downloadImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            mFaceAppService.downloadImage(paramDataInit(), response);
            return new ResultData<>(ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/upload_device_info")
    public ResultData<ParamData> uploadDeviceInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            return mFaceAppService.uploadDeviceInfo(paramDataInit());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData<>(ResultEnum.FAIL, e.getMessage());
        }
    }
}
