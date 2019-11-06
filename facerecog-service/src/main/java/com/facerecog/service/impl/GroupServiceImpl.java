package com.facerecog.service.impl;

import com.facerecog.dao.DeviceDao;
import com.facerecog.dao.GroupDao;
import com.facerecog.dao.PersonDao;
import com.facerecog.ehcache.WebCache;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.GroupService;
import com.facerecog.utils.CommUtil;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

/**
 * @Classname GroupServiceImpl
 * @Description
 * @Date 2018/11/14 10:20
 * @Created by cjw
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupDao mGroupDao;
    @Resource
    private PersonDao mPersonDao;
    @Resource
    private DeviceDao mDeviceDao;
    @Autowired
    private WebCache memory;

    @Override
    public ResultData<PageData<ParamData>> getGroupList(ParamData pd) {
        int pageNum = CommUtil.paramConvert(pd.getString("pageNum"), 0);//当前页
        int pageSize = CommUtil.paramConvert(pd.getString("pageSize"), 0);//每一页10条数据

        if (pageSize != 0) PageHelper.startPage(pageNum, pageSize);
        List<ParamData> groupList = mGroupDao.selectGroupList(pd);
        return new ResultData<>(HandleEnum.SUCCESS, new PageData<>(groupList));
    }

    @Override
    public ParamData queryGroup(ParamData pd) {
        return mGroupDao.selectGroup(pd);
    }

    @Transactional
    @Override
    public ResultData<ParamData> addGroup(ParamData pd) {
        if (mGroupDao.insertGroup(pd)) return new ResultData<>(HandleEnum.SUCCESS);
        else return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> addGroupPerson(ParamData pd) {
        String person_ids = pd.getString("person_ids");

        List<ParamData> list = new ArrayList<>();
        String[] personIdArr = person_ids.split(",");
        for (String personId : personIdArr) {
            ParamData paramData = new ParamData();
            int person_id = Integer.parseInt(personId);
            paramData.put("person_id", person_id);
            list.add(paramData);
        }
        pd.put("list", list);

        if (mGroupDao.insertGroupPerson(pd)) return new ResultData<>(HandleEnum.SUCCESS);
        else return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> addGroupDevice(ParamData pd) {
        String device_ids = pd.getString("device_ids");

        List<ParamData> list = new ArrayList<>();
        String[] deviceIdArr = device_ids.split(",");
        for (String deviceId : deviceIdArr) {
            ParamData paramData = new ParamData();
            int device_id = Integer.parseInt(deviceId);
            paramData.put("device_id", device_id);
            list.add(paramData);
        }
        pd.put("list", list);

        if (mGroupDao.insertGroupDevice(pd)) return new ResultData<>(HandleEnum.SUCCESS);
        else return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> updateGroupInfo(ParamData pd) {
        if (mGroupDao.updateGroupInfo(pd)) return new ResultData<>(HandleEnum.SUCCESS);
        return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> deleteGroup(ParamData pd) {
        if (mGroupDao.deleteGroup(pd)) return new ResultData<>(HandleEnum.SUCCESS);
        return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> deleteGroupPerson(ParamData pd) {
        if (mGroupDao.deleteGroupPerson(pd)) return new ResultData<>(HandleEnum.SUCCESS);
        return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> deleteGroupDevice(ParamData pd) {
        if (mGroupDao.deleteGroupDevice(pd)) return new ResultData<>(HandleEnum.SUCCESS);
        return new ResultData<>(HandleEnum.FAIL);
    }

    @Override
    public ResultData<ParamData> getListDevicePersonByGroup(ParamData pd) {
        List<ParamData> deviceList = mDeviceDao.selectDeviceListByGroupID(pd);
        List<ParamData> personList = mPersonDao.selectPersonListByGroupID(pd);
        ParamData data = new ParamData<>();
        data.put("device_list",deviceList);
        data.put("person_list",personList);
        return new ResultData<>(HandleEnum.SUCCESS,data);
    }
}
