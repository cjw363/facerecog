package com.facerecog.service.interf;

import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @Classname PersonService
 * @Description
 * @Date 2018/11/6 10:44
 * @Created by cjw
 */
public interface PersonService {
    ResultData<PageData<ParamData>> getPersonList(ParamData pd);

    ResultData<PageData<ParamData>> getPersonBase64List(ParamData pd);

    ResultData<ParamData> addPerson(MultipartFile file, ParamData pd) throws IOException;

    ResultData<ParamData> updatePerson(MultipartFile file, ParamData pd) throws IOException;

    ResultData<ParamData> updatePerson(ParamData pd) throws IOException;

    ResultData<ParamData> deletePerson(ParamData pd) throws IOException;

    ParamData queryPerson(ParamData pd);

    boolean uploadImageFile(MultipartFile file, ParamData pd) throws Exception;

    void loadImageFile(ParamData pd, HttpServletResponse response) throws Exception;

    void file2base64(List<ParamData> list) throws Exception;

    void file2base64(ParamData pd) throws Exception;

    void blob2base64(List<ParamData> list) throws Exception;

    void blob2base64(ParamData pd) throws Exception;

    ResultData<PageData<ParamData>> getAccessDeviceList(ParamData pd);

    ResultData<ParamData> batchUpload(MultipartFile[] files, ParamData pd);

    ResultData<ParamData> getGroupPersonList(ParamData pd);

    ResultData<PageData<ParamData>> getPersonListByGroup(ParamData pd);

    ResultData<List<ParamData>> getListGroupUnSelected(ParamData pd);
}
