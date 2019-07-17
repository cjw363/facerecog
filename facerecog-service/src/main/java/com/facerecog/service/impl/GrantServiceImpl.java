package com.facerecog.service.impl;


import com.facerecog.dao.DeviceDao;
import com.facerecog.dao.GrantDao;
import com.facerecog.ehcache.WebCache;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.pojo.SocketEnum;
import com.facerecog.service.interf.GrantService;
import com.facerecog.utils.CommConst;
import com.facerecog.websocket.SocketMessageHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

/**
 * @Classname GrantService
 * @Description
 * @Date 2018/11/15 17:26
 * @Created by cjw
 */
@Service
public class GrantServiceImpl implements GrantService {

    @Resource
    private GrantDao mGrantDao;
    @Resource
    private DeviceDao mDeviceDao;
    @Autowired
    private WebCache memory;
    @Autowired
    private SocketMessageHandle mSocketMessageHandle;

    @Transactional
    @Override
    public ResultData<ParamData> addGrant(ParamData pd) throws Exception {
        String person_ids = pd.getString("person_ids");
        String device_ids = pd.getString("device_ids");
        String pass_number = pd.getString("pass_number");
        String pass_start_time = pd.getString("pass_start_time");
        String pass_end_time = pd.getString("pass_end_time");

        List<ParamData> list = new ArrayList<>();
        String[] personIdArr = person_ids.split(",");
        String[] deviceIdArr = device_ids.split(",");
        for (String personId : personIdArr) {
            for (String deviceId : deviceIdArr) {
                ParamData paramData = new ParamData();
                int person_id = Integer.parseInt(personId);
                int device_id = Integer.parseInt(deviceId);
                paramData.put("person_id", person_id);
                paramData.put("device_id", device_id);
                paramData.put("pass_number", pass_number);
                paramData.put("pass_start_time", pass_start_time);
                paramData.put("pass_end_time", pass_end_time);
                list.add(paramData);
            }
        }

        ParamData paramData = new ParamData();
        paramData.put("wid", memory.getCache(pd.getString(CommConst.ACCESS_CPFR_TOKEN)).getWid());
        paramData.put("list", list);
        if (mGrantDao.insertGrant(paramData)) {
            //通知设备权限更新
            TextMessage personMessage = mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1003_PERSON_UPDATE, null);
            TextMessage grantMessage = mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1004_GRANT_UPDATE, null);
            List<ParamData> deviceSnList = mDeviceDao.selectDeviceSnList(paramData);
            for (ParamData p : deviceSnList) {
                mSocketMessageHandle.sendMessageToDevice(p.getString(CommConst.DEVICE_SN), personMessage);
                mSocketMessageHandle.sendMessageToDevice(p.getString(CommConst.DEVICE_SN), grantMessage);
            }
            return new ResultData<>(HandleEnum.SUCCESS);
        }
        return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> banGrant(ParamData pd) throws Exception {
        String person_ids = pd.getString("person_ids");
        String device_ids = pd.getString("device_ids");

        List<ParamData> list = new ArrayList<>();
        String[] personIdArr = person_ids.split(",");
        String[] deviceIdArr = device_ids.split(",");
        for (String personId : personIdArr) {
            for (String deviceId : deviceIdArr) {
                ParamData paramData = new ParamData();
                int person_id = Integer.parseInt(personId);
                int device_id = Integer.parseInt(deviceId);
                paramData.put("person_id", person_id);
                paramData.put("device_id", device_id);
                list.add(paramData);
            }
        }

        ParamData paramData = new ParamData();
        paramData.put("wid", memory.getCache(pd.getString(CommConst.ACCESS_CPFR_TOKEN)).getWid());
        paramData.put("list", list);
        if (mGrantDao.updateGrantBan(paramData)) {
            //通知设备权限更新
            TextMessage message = mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1004_GRANT_UPDATE, null);
            List<ParamData> deviceSnList = mDeviceDao.selectDeviceSnList(paramData);
            for (ParamData p : deviceSnList) {
                mSocketMessageHandle.sendMessageToDevice(p.getString(CommConst.DEVICE_SN), message);
            }
            return new ResultData<>(HandleEnum.SUCCESS);
        }
        return new ResultData<>(HandleEnum.FAIL);
    }
}
