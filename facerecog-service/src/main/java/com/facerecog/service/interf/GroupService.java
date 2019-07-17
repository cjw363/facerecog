package com.facerecog.service.interf;

import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;

/**
 * @Classname GroupService
 * @Description
 * @Date 2018/11/14 10:17
 * @Created by cjw
 */
public interface GroupService {
    ResultData<PageData<ParamData>> getGroupList(ParamData pd);

    ParamData queryGroup(ParamData pd);

    ResultData<ParamData> addGroup(ParamData pd);

    ResultData<ParamData> addGroupPerson(ParamData pd);

    ResultData<ParamData> addGroupDevice(ParamData pd);

    ResultData<ParamData> updateGroupInfo(ParamData pd);

    ResultData<ParamData> deleteGroup(ParamData pd);

    ResultData<ParamData> deleteGroupPerson(ParamData pd);

    ResultData<ParamData> deleteGroupDevice(ParamData pd);
}
