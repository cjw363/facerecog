package com.facerecog.dao;

import com.facerecog.pojo.ParamData;

import java.util.List;

/**
 * @Date 2019/3/14
 * @Created by xwr
 */
public interface AttendDao {
    boolean insertRule(ParamData pd);

    List<ParamData> selectRuleList(ParamData pd);

    ParamData selectAttend(ParamData pd);

    boolean deleteRule(ParamData pd);

    List<ParamData> selectAttendList(ParamData pd);
}
