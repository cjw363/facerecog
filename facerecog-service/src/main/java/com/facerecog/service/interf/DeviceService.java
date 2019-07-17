package com.facerecog.service.interf;

import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;

/**
 * @Classname DeviceService
 * @Description
 * @Date 2018/10/19 11:20
 * @Created by cjw
 */
public interface DeviceService {
    ResultData<PageData<ParamData>> getDeviceList(ParamData pd);

    ResultData<PageData<ParamData>> getInActDeviceList(ParamData pd);

    ResultData<ParamData> activateDevice(ParamData pd) throws Exception;

    void updateDeviceOnline(ParamData pd);

    ParamData queryInActDevice(ParamData pd);

    ParamData queryDevice(ParamData pd);

    ResultData<PageData<ParamData>> getGrantPersonList(ParamData pd);

    ResultData<ParamData> changeDeviceInfo(ParamData pd) throws Exception;

    ResultData<ParamData> deleteDevice(ParamData pd) throws Exception;

    ParamData queryDeviceGrantKey(ParamData pd);

    ResultData<ParamData> checkAppVersionUpdate(ParamData pd) throws Exception;

    ResultData<ParamData> getGroupDeviceList(ParamData pd);

    ResultData<PageData<ParamData>> getDeviceListByGroup(ParamData pd);

    void updateAllDeviceOffline();
}
