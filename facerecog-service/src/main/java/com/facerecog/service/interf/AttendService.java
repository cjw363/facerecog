package com.facerecog.service.interf;

import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;

/**
 * @Date 2019/3/14
 * @Created by xwr
 */
public interface AttendService {
    ResultData<PageData<ParamData>> getAttendList(ParamData pd);
}
