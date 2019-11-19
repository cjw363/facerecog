package com.facerecog.controller;

import com.facerecog.controller.base.WebBaseController;
import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname PersonController
 * @Description
 * @Date 2018/11/6 10:38
 * @Created by cjw
 */
@Controller
@RequestMapping(value = "/person", method = {RequestMethod.POST, RequestMethod.GET})
public class PersonController extends WebBaseController {

    @Autowired
    private PersonService mPersonService;

    @ResponseBody
    @RequestMapping("/list")
    public ResultData<PageData<ParamData>> list(HttpServletRequest request) {
        return mPersonService.getPersonList(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/list_base64")
    public ResultData<PageData<ParamData>> listBase64(HttpServletRequest request) {
        return mPersonService.getPersonBase64List(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultData<ParamData> add(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        ParamData pd = paramDataInit();
        pd.put("person_name", request.getParameter("person_name"));
        pd.put("emp_number", request.getParameter("emp_number"));
        return mPersonService.addPerson(file, pd);
    }

    @ResponseBody
    @RequestMapping("/detail")
    public ResultData<ParamData> detail() {
        ParamData paramData = mPersonService.queryPerson(paramDataInit());
        return new ResultData<>(ResultEnum.SUCCESS, paramData);
    }

    /**
     * 修改人员信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/update_img_info")
    public ResultData<ParamData> updatePersonInfoWithFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        ParamData pd = paramDataInit();
        pd.put("person_name", request.getParameter("person_name"));
        pd.put("emp_number", request.getParameter("emp_number"));
        pd.put("person_id", request.getParameter("person_id"));
        return mPersonService.updatePerson(file, pd);
    }

    /**
     * 修改人员信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/update_info")
    public ResultData<ParamData> updatePersonInfo(HttpServletRequest request) throws Exception {
        return mPersonService.updatePerson(paramDataInit());
    }

    /**
     * 删除人员
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public ResultData<ParamData> deletePerson(HttpServletRequest request) throws Exception {
        return mPersonService.deletePerson(paramDataInit());
    }

    /**
     * 可通行设备
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/access_device_list")
    public ResultData<PageData<ParamData>> accessDeviceList(HttpServletRequest request) {
        return mPersonService.getAccessDeviceList(paramDataInit());
    }


    @ResponseBody
    @RequestMapping("/image")
    public ResultData<ParamData> image(HttpServletRequest request, HttpServletResponse response) throws Exception {
        mPersonService.loadImageFile(paramDataInit(), response);
        return new ResultData<>(ResultEnum.SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/batch_upload")
    public ResultData<ParamData> batchUpload(@RequestParam("file[]") MultipartFile[] files, HttpServletRequest request) {
        mPersonService.batchUpload(files, paramDataInit());
        return new ResultData<>(ResultEnum.SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/group_person_list")
    public ResultData<ParamData> groupPersonList(HttpServletRequest request) {
        return mPersonService.getGroupPersonList(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/list_by_group")
    public ResultData<PageData<ParamData>> listByGroup(HttpServletRequest request) {
        return mPersonService.getPersonListByGroup(paramDataInit());
    }

    @ResponseBody
    @RequestMapping("/list_group_unselected")
    public ResultData<List<ParamData>> listNoSelected() {
        return mPersonService.getListGroupUnSelected(paramDataInit());
    }
}
