package com.facerecog.service.impl;

import com.facerecog.bo.AppDevice;
import com.facerecog.bo.UserInfo;
import com.facerecog.config.properties.KeyProperties;
import com.facerecog.dao.DeviceDao;
import com.facerecog.dao.GroupDao;
import com.facerecog.dao.PersonDao;
import com.facerecog.dao.UserDao;
import com.facerecog.ehcache.AppCache;
import com.facerecog.ehcache.WebCache;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.pojo.SocketEnum;
import com.facerecog.service.interf.DeviceService;
import com.facerecog.utils.CommConst;
import com.facerecog.utils.CommUtil;
import com.facerecog.utils.SystemConfig;
import com.facerecog.websocket.SocketMessageHandle;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Classname DeviceServiceImpl
 * @Description
 * @Date 2018/10/19 11:22
 * @Created by cjw
 */
@Service
@EnableConfigurationProperties(KeyProperties.class)
public class DeviceServiceImpl implements DeviceService {

    private static final int STATUS_1_DEVICE_ONLINE = 1;
    private static final int STATUS_1_DEVICE_ACTIVATED = 1;

    @Resource
    private DeviceDao mDeviceDao;
    @Resource
    private PersonDao mPersonDao;
    @Resource
    private GroupDao mGroupDao;
    @Resource
    private UserDao mUserDao;
    @Autowired
    private WebCache memory;
    @Autowired
    private AppCache mAppCache;
    @Autowired
    private SocketMessageHandle mSocketMessageHandle;
    @Autowired
    private KeyProperties mKeyProperties;

    @Override
    public ResultData<PageData<ParamData>> getDeviceList(ParamData pd) {
        int pageNum = CommUtil.paramConvert(pd.getString("pageNum"), 0);//当前页
        int pageSize = CommUtil.paramConvert(pd.getString("pageSize"), 0);//每一页10条数据

        if (pageSize != 0)
            PageHelper.startPage(pageNum, pageSize);
        List<ParamData> deviceList = mDeviceDao.selectDeviceList(pd);
        return new ResultData<>(HandleEnum.SUCCESS, new PageData<>(deviceList));
    }

    @Override
    public ResultData<PageData<ParamData>> getInActDeviceList(ParamData pd) {
        int pageNum = CommUtil.paramConvert(pd.getString("pageNum"), 0);//当前页
        int pageSize = CommUtil.paramConvert(pd.getString("pageSize"), 0);//每一页10条数据

        if (pageSize != 0)
            PageHelper.startPage(pageNum, pageSize);
        List<ParamData> inActDeviceList = mDeviceDao.selectInActDeviceList(pd);
        return new ResultData<>(HandleEnum.SUCCESS, new PageData<>(inActDeviceList));
    }

    @Transactional
    @Override
    public ResultData<ParamData> activateDevice(ParamData pd) throws Exception {
        UserInfo userInfo = memory.getCache(pd.getString(CommConst.ACCESS_CPFR_TOKEN));
        ParamData paramData = mDeviceDao.selectInActDevice(pd);
        if (paramData == null)
            return new ResultData<>(HandleEnum.FAIL, "设备不存在");
        pd.put("user_id", userInfo.getUserId());
        pd.put("wid", userInfo.getWid());
        if (STATUS_1_DEVICE_ONLINE == (Integer) paramData.get("online")) {
            //激活成功，往对应仓库插入设备，返回
            if (mDeviceDao.insertDevice(pd)) {
                //激活成功 缓存token
                String device_sn = pd.getString(CommConst.DEVICE_SN);
                AppDevice device = new AppDevice(device_sn, userInfo.getWid());
                mAppCache.putCache(device);

                //通知设备激活成功
                ParamData data = new ParamData();
                data.put(CommConst.ACCESS_APP_TOKEN, mAppCache.getToken(device_sn));
                mSocketMessageHandle.sendMessageToDevice(device_sn, mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1001_DEVICE_ACTIVATE, data));
                return new ResultData<>(HandleEnum.SUCCESS);
            }
            return new ResultData<>(HandleEnum.FAIL);
        } else {
            return new ResultData<>(HandleEnum.FAIL, "设备不在线");
        }
    }

    @Override
    public ParamData queryInActDevice(ParamData pd) {
        ParamData data = mDeviceDao.selectInActDevice(pd);
        data.put("arcface_appid",mKeyProperties.getArcSoftAppID());
        data.put("arcface_sdkkey",mKeyProperties.getArcSoftSdkKey());
        return data;
    }

    @Override
    public ParamData queryDevice(ParamData pd) {
        ParamData data = mDeviceDao.selectDevice(pd);
        data.putAll(mDeviceDao.selectSyncDownlStatus(pd));
        data.put("group_list",mGroupDao.selectGroupListByDeviceSn(pd));
        return data;
    }

    @Override
    public void updateDeviceOnline(ParamData pd) {
        mDeviceDao.updateInActDeviceOnline(pd);
        int deviceStatus = mDeviceDao.selectDeviceStatusByDeviceSn(pd);
        pd.put("device_status", deviceStatus);
        if (STATUS_1_DEVICE_ACTIVATED == deviceStatus) {
            pd.put("wid", mDeviceDao.selectWidByDeviceSn(pd));
            mDeviceDao.updateDeviceOnline(pd);
        }
    }

    @Override
    public ResultData<PageData<ParamData>> getGrantPersonList(ParamData pd) {
        int pageNum = CommUtil.paramConvert(pd.getString("pageNum"), 0);//当前页
        int pageSize = CommUtil.paramConvert(pd.getString("pageSize"), 0);//每一页10条数据

        if (pageSize != 0)
            PageHelper.startPage(pageNum, pageSize);
        List<ParamData> personList = mPersonDao.selectGrantPersonListByDeviceSn(pd);
        return new ResultData<>(HandleEnum.SUCCESS, new PageData<>(personList));
    }

    @Transactional
    @Override
    public ResultData<ParamData> changeDeviceInfo(ParamData pd) throws Exception {
        if (mDeviceDao.updateDeviceInfo(pd)) {
            TextMessage message = mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1002_DEVICE_UPDATE, null);
            mSocketMessageHandle.sendMessageToDevice(pd.getString(CommConst.DEVICE_SN), message);
            return new ResultData<>(HandleEnum.SUCCESS);
        } else
            return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> deleteDevice(ParamData pd) throws Exception {
        String deviceSn = mDeviceDao.selectDeviceSnByDeviceID(pd);
        pd.put("device_sn",deviceSn);
        if (mDeviceDao.deleteDeviceByDeviceID(pd)) {
            TextMessage message = mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1005_DEVICE_DELETE, null);
            mSocketMessageHandle.sendMessageToDevice(deviceSn, message);
            //移除app缓存
            mAppCache.removeCache(deviceSn);
            return new ResultData<>(HandleEnum.SUCCESS);
        } else
            return new ResultData<>(HandleEnum.FAIL);
    }

    @Override
    public ParamData queryDeviceGrantKey(ParamData pd) {
        return mDeviceDao.selectDeviceGrantKey(pd);
    }

    @Override
    public ResultData<ParamData> checkAppVersionUpdate(ParamData pd) throws Exception {
        ParamData device = mDeviceDao.selectDevice(pd);
        String appVersionStr = device.getString("app_version");
        String[] appVersions = appVersionStr.split(",");

        File dir = new File(SystemConfig.DOWNLOAD_APK_PATH);
        File[] files = dir.listFiles();//绝对路径

        boolean hasNewVersion = false;
        for (String appVersion : appVersions) {
            String[] split = appVersion.split("_");
            String applicationId = split[0];

            for (File file : files) {
                if (file.getName().contains(applicationId)) {
                    int versionCode = Integer.parseInt(split[1]);
                    String appNewVersionStr = file.getName().split("_")[1].split("\\.")[0];
                    int appNewVersion = Integer.parseInt(appNewVersionStr);
                    if (appNewVersion > versionCode) {
                        hasNewVersion = true;
                    }
                }
            }

        }
        if (hasNewVersion) {
            TextMessage message = mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1008_NEW_APP_VERSION, null);
            mSocketMessageHandle.sendMessageToDevice(device.getString("device_sn"), message);
            return new ResultData<>(HandleEnum.NEW_APP_VERSION_105);
        }

        return new ResultData<>(HandleEnum.SUCCESS, "当前已是最新系统");
    }

    @Override
    public ResultData<ParamData> getGroupDeviceList(ParamData pd) {
        List<ParamData> groupList = mGroupDao.selectGroupDeviceListMap(pd);
        if (groupList == null)
            groupList = new ArrayList<>();
        ParamData other = new ParamData();
        other.put("group_id", 0);
        other.put("group_name", "未分组");
        other.put("device_list", mDeviceDao.selectDeviceListNoGroup(pd));
        groupList.add(other);

        ParamData result = new ParamData();
        result.put("list", groupList);
        return new ResultData<>(HandleEnum.SUCCESS, result);
    }

    @Override
    public ResultData<PageData<ParamData>> getDeviceListByGroup(ParamData pd) {
        int pageNum = CommUtil.paramConvert(pd.getString("pageNum"), 0);//当前页
        int pageSize = CommUtil.paramConvert(pd.getString("pageSize"), 0);//每一页10条数据

        if (pageSize != 0)
            PageHelper.startPage(pageNum, pageSize);
        List<ParamData> deviceList = mDeviceDao.selectDeviceListByGroupID(pd);
        return new ResultData<>(HandleEnum.SUCCESS, new PageData<>(deviceList));
    }

    @Override
    public void updateAllDeviceOffline() {
        List<ParamData> widList = mUserDao.selectWidList();
        ParamData data = new ParamData();
        data.put("list", widList);
        mDeviceDao.updateAllDeviceOffline(data);
    }

}
