package com.facerecog.service.interf;

import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;

/**
 * @Classname ManagerAppService
 * @Description
 * @Date 2019/5/16 16:09
 * @Created by cjw
 */
public interface ManagerAppService {
    ResultData<ParamData> addPersonWithGrant(ParamData pd) throws Exception;
}
