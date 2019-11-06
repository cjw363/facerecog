package com.facerecog.service.interf;

import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;

/**
 * @Classname GrantDao
 * @Description
 * @Date 2018/11/15 17:25
 * @Created by cjw
 */
public interface GrantService {
    ResultData<ParamData> addGrant(ParamData pd) throws Exception;

    ResultData<ParamData> banGrant(ParamData pd) throws Exception;

    ResultData<ParamData> getListDevicePerson(ParamData pd);
}
