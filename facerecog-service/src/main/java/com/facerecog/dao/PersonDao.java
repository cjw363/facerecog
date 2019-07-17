package com.facerecog.dao;

import com.facerecog.pojo.ParamData;

import java.util.List;

/**
 * @Classname PersonDao
 * @Description
 * @Date 2018/11/6 10:42
 * @Created by cjw
 */
public interface PersonDao {
    ParamData selectPerson(ParamData pd);

    List<ParamData> selectPersonList(ParamData pd);

    boolean insertPerson(ParamData pd);

    ParamData selectImage(ParamData pd);

    List<ParamData> selectPersonListWithBlob(ParamData pd);

    List<ParamData> selectGrantPersonListByDeviceSn(ParamData pd);

    boolean updatePersonInfo(ParamData pd);

    boolean deletePerson(ParamData pd);

    List<ParamData> selectPersonListByGroupID(ParamData pd);

    List<ParamData> selectPersonListNoGroup(ParamData pd);

    boolean updatePersonAttendId(ParamData pd);

    List<ParamData> selectPersonIdByAttendId(ParamData pd);

    boolean deletePersonAttendId(ParamData pd);

}
