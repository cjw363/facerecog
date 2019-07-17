package com.facerecog.service.interf;


import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;

/**
 * @Classname RecordService
 * @Description
 * @Date 2018/12/5 10:39
 * @Created by cjw
 */
public interface RecordService {
    ResultData<PageData<ParamData>> getRecordBase64List(ParamData pd);
    ResultData<ParamData> deleteRecord(ParamData pd);
    ResultData<ParamData> deleteRecordLists(ParamData pd);
    ResultData<ParamData> clearRecord(ParamData pd);
}
