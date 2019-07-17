package com.facerecog.dao;

import com.facerecog.pojo.ParamData;

import java.util.List;

/**
 * @Classname RecordDao
 * @Description
 * @Date 2018/12/5 10:46
 * @Created by cjw
 */
public interface RecordDao {
    List<ParamData> selectRecordListWithBlob(ParamData pd);

    boolean deleteRecord(ParamData pd);

    boolean deleteRecordLists(ParamData pd);

    boolean deleteRecodeAll(ParamData pd);
}
