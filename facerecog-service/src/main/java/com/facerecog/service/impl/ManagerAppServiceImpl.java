package com.facerecog.service.impl;

import com.facerecog.dao.DeviceDao;
import com.facerecog.dao.PersonDao;
import com.facerecog.dao.TableDao;
import com.facerecog.dao.app.ManagerAppDao;
import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.pojo.SocketEnum;
import com.facerecog.service.interf.ManagerAppService;
import com.facerecog.utils.CommConst;
import com.facerecog.websocket.SocketMessageHandle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Classname ConfigServiceImpl
 * @Description
 * @Date 2019/5/16 16:11
 * @Created by cjw
 */
@Service
public class ManagerAppServiceImpl implements ManagerAppService {

    @Resource
    private PersonDao mPersonDao;
    @Resource
    private TableDao mTableDao;
    @Resource
    private ManagerAppDao mManagerAppDao;
    @Resource
    private DeviceDao mDeviceDao;
    @Autowired
    private SocketMessageHandle mSocketMessageHandle;

    @Transactional
    @Override
    public ResultData<ParamData> addPersonWithGrant(ParamData pd) throws Exception {
        String base64Image = pd.getString("file");
        if (StringUtils.isEmpty(base64Image))
            return new ResultData<>(ResultEnum.FAIL);
        byte[] blobImage = Base64Utils.decodeFromString(base64Image);
        if (blobImage != null) {
            if (blobImage.length / 1024 > 65)
                return new ResultData<>(ResultEnum.FAIL, "上传失败，图片过大!");

            pd.put("blob_image", blobImage);
            boolean a = mPersonDao.insertPerson(pd);
            pd.put("person_id", mTableDao.selectLastInsertID());

            String device_ids = pd.getString("device_ids");
            if (!StringUtils.isEmpty(device_ids)) {
                List<ParamData> list = new ArrayList<>();
                String[] deviceIdArr = device_ids.split(",");
                for (String deviceId : deviceIdArr) {
                    ParamData paramData = new ParamData();
                    int device_id = Integer.parseInt(deviceId);
                    paramData.put("device_id", device_id);
                    list.add(paramData);
                }
                pd.put("list", list);
                boolean b = mManagerAppDao.insertGrantDeviceIdsPersonId(pd);

                if (b) {
                    List<ParamData> deviceSnList = mDeviceDao.selectDeviceSnList(pd);
                    for (ParamData p : deviceSnList) {
                        String deviceSn = p.getString(CommConst.DEVICE_SN);
                        mSocketMessageHandle.sendMessageToDevice(deviceSn, mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1003_PERSON_UPDATE, null));
                        mSocketMessageHandle.sendMessageToDevice(deviceSn, mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1004_GRANT_UPDATE, null));
                    }

                }
                return new ResultData<>(ResultEnum.SUCCESS);
            }
        }
        return new ResultData<>(ResultEnum.FAIL);
    }
}
