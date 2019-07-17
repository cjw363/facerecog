package com.facerecog.dao;

import com.facerecog.pojo.ParamData;

/**
 * @Classname GrantDao
 * @Description
 * @Date 2018/11/15 17:24
 * @Created by cjw
 */
public interface GrantDao {
    boolean insertGrant(ParamData pd);

    boolean updateGrantBan(ParamData pd);

    boolean insertGrantDeviceSnPersonId(ParamData pd);
}
