package com.facerecog.service.impl;


import com.facerecog.bo.AppDevice;
import com.facerecog.dao.DeviceDao;
import com.facerecog.dao.GrantDao;
import com.facerecog.dao.GroupDao;
import com.facerecog.dao.PersonDao;
import com.facerecog.dao.TableDao;
import com.facerecog.dao.app.FaceAppDao;
import com.facerecog.ehcache.AppCache;
import com.facerecog.pojo.ResultEnum;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.pojo.SocketEnum;
import com.facerecog.service.interf.FaceAppService;
import com.facerecog.utils.CommConst;
import com.facerecog.utils.CommUtil;
import com.facerecog.utils.SystemConfig;
import com.facerecog.websocket.SocketMessageHandle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Classname AppServiceImpl
 * @Description
 * @Date 2018/11/22 15:25
 * @Created by cjw
 */
@Service
public class FaceAppServiceImpl implements FaceAppService {
    @Resource
    private FaceAppDao mFaceAppDao;
    @Resource
    private DeviceDao mDeviceDao;
    @Resource
    private PersonDao mPersonDao;
    @Resource
    private GrantDao mGrantDao;
    @Resource
    private GroupDao mGroupDao;
    @Resource
    private TableDao mTableDao;
    @Autowired
    private AppCache memory;
    @Autowired
    private SocketMessageHandle mSocketMessageHandle;

    @Transactional
    @Override
    public ResultData<ParamData> register(ParamData pd) {
        String deviceSn = pd.getString(CommConst.DEVICE_SN);
        if (StringUtils.isEmpty(deviceSn))
            return new ResultData<>(ResultEnum.FAIL, "设备序列号不能为空");
        ParamData paramData = mDeviceDao.selectInActDevice(pd);
        if (paramData == null) {
            if (mFaceAppDao.insertInActDevice(pd)) {
                return new ResultData<>(ResultEnum.SUCCESS, "已注册新设备");
            }
        } else
            return new ResultData<>(ResultEnum.SUCCESS, "设备已注册");
        return new ResultData<>(ResultEnum.FAIL, "设备注册失败，请重新连接");
    }

    @Override
    public ResultData<ParamData> getDeviceInfo(ParamData pd) {
        ParamData paramData = mFaceAppDao.selectDevice(pd);
        return new ResultData<>(ResultEnum.SUCCESS, paramData);
    }

    @Override
    public ResultData<List<ParamData>> getPersonBase64List(ParamData pd) {
        System.out.println("===================================");
        System.out.println(new Date().toString());
        System.out.println("客户端 " + pd.getString(CommConst.DEVICE_SN) + "开始下载人员");
        List<ParamData> list = mFaceAppDao.selectPersonListWithBlob(pd);
        return new ResultData<>(ResultEnum.SUCCESS, list);
    }

    @Override
    public ResultData<List<ParamData>> getGrantList(ParamData pd) {
        List<ParamData> list = mFaceAppDao.selectGrantList(pd);
        return new ResultData<>(ResultEnum.SUCCESS, list);
    }

    @Transactional
    @Override
    @Deprecated
    public ResultData<ParamData> addRecord(MultipartFile file, HttpServletRequest request) throws Exception{
        if (file.getSize() / 1024 > 65)
            return new ResultData<>(ResultEnum.FAIL, "上传失败，图片过大!");
        if (!file.getContentType().contains("image"))
            return new ResultData<>(ResultEnum.FAIL, "文件类型有误!");
        ParamData pd = new ParamData();
        AppDevice cache = memory.getCache(getTokenFromRequest(request));
        pd.put(CommConst.DEVICE_SN, cache.getDeviceSn());
        pd.put("person_id", request.getParameter("person_id"));
        pd.put("recog_type", request.getParameter("recog_type"));
        pd.put("record_image", file.getBytes());
        pd.put("wid", cache.getWid());
        if (mFaceAppDao.insertRecord(pd)) {
            return new ResultData<>(ResultEnum.SUCCESS);
        }

        return new ResultData<>(ResultEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> addRecord(ParamData pd) throws Exception {
        String base64Image = pd.getString("file");
        if (StringUtils.isEmpty(base64Image))
            return new ResultData<>(ResultEnum.FAIL);
        byte[] blobImage = Base64Utils.decodeFromString(base64Image);
        if (blobImage != null) {
            if (blobImage.length / 1024 > 65)
                return new ResultData<>(ResultEnum.FAIL, "上传失败，图片过大!");
            pd.put("record_image", blobImage);
            if (mFaceAppDao.insertRecord(pd) && mFaceAppDao.updateGrantPassNumber(pd))
                return new ResultData<>(ResultEnum.SUCCESS);

        }
        return new ResultData<>(ResultEnum.FAIL);
    }

    @Transactional
    @Override
    @Deprecated
    public boolean uploadRecordImage(MultipartFile file, ParamData pd) throws Exception {
        BufferedOutputStream fos = null;
        try {
            String realPath = SystemConfig.UPLOAD_RECORD_IMAGE_DIR;
            CommUtil.mkdir(realPath);
            String[] suffix = file.getContentType().split("/");
            String fileName = CommUtil.get32UUID() + "." + suffix[1];
            String filePath = realPath + fileName;

            fos = new BufferedOutputStream(new FileOutputStream(filePath));
            fos.write(file.getBytes());
            fos.flush();

            //判断文件是否上传成功
            File file1 = new File(filePath);
            if (file1.exists() && file1.length() == file.getSize()) {
                pd.put("image_path", filePath);
                return true;
            }
        } finally {
            if (fos != null)
                fos.close();
        }
        return false;
    }

    @Override
    public ResultData<ParamData> getCurrentDate() {
        ParamData pd = new ParamData();
        pd.put("current_date", mFaceAppDao.selectNow());
        return new ResultData<>(ResultEnum.SUCCESS, pd);
    }

    @Transactional
    @Override
    @Deprecated
    public ResultData<ParamData> addPersonWithGrant(MultipartFile file, HttpServletRequest request) throws Exception {
        if (file.getSize() / 1024 > 65)
            return new ResultData<>(ResultEnum.FAIL, "上传失败，图片过大!");
        if (!file.getContentType().contains("image"))
            return new ResultData<>(ResultEnum.FAIL, "文件类型有误!");

        ParamData pd = new ParamData();
        AppDevice cache = memory.getCache(getTokenFromRequest(request));
        pd.put(CommConst.DEVICE_SN, cache.getDeviceSn());
        pd.put("person_name", request.getParameter("person_name"));
        pd.put("emp_number", request.getParameter("emp_number"));
        pd.put("blob_image", file.getBytes());
        pd.put("wid", cache.getWid());

        boolean a = mPersonDao.insertPerson(pd);
        boolean b = mGrantDao.insertGrantDeviceSnPersonId(pd);

        String groupName = request.getParameter("group_name");
        pd.put("group_name", groupName);
        if (!StringUtils.isEmpty(groupName)) {
            ParamData group = mGroupDao.selectGroupByGroupName(pd);
            if (group != null) {
                pd.put("group_id", group.get("group_id") + "");
                mGroupDao.insertGroupPerson(pd);
            }
        }

        if (a && b) {
            mSocketMessageHandle.sendMessageToDevice(cache.getDeviceSn(), mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1004_GRANT_UPDATE, null));
            ParamData person = mPersonDao.selectPerson(pd);
            return new ResultData<>(ResultEnum.SUCCESS, person);
        }

        return new ResultData<>(ResultEnum.FAIL);
    }

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
            boolean b = mGrantDao.insertGrantDeviceSnPersonId(pd);

            String groupName = pd.getString("group_name");
            pd.put("group_name", groupName);
            if (!StringUtils.isEmpty(groupName)) {
                ParamData group = mGroupDao.selectGroupByGroupName(pd);
                if (group != null) {
                    pd.put("group_id", group.get("group_id") + "");
                    mGroupDao.insertGroupPerson(pd);
                }
            }

            if (a && b) {
                mSocketMessageHandle.sendMessageToDevice(pd.getString(CommConst.DEVICE_SN), mSocketMessageHandle.obtainMessage(SocketEnum.CODE_1004_GRANT_UPDATE, null));

                ParamData person = mFaceAppDao.selectPerson(pd);
                return new ResultData<>(ResultEnum.SUCCESS, person);
            }
        }
        return new ResultData<>(ResultEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<List<ParamData>> syncPerson(ParamData pd) {
        String personIdsStr = pd.getString("person_ids");
        String[] idStatusArr = personIdsStr.split(",");
        ArrayList<ParamData> list = new ArrayList<>();
        String[] personIds = new String[idStatusArr.length];
        for (int i = 0; i < idStatusArr.length; i++) {
            String[] idAndStatus = idStatusArr[i].split("/");
            if (idAndStatus.length > 1) {
                ParamData data = new ParamData();
                data.put("person_id", idAndStatus[0]);
                data.put("sync_status", idAndStatus[1]);
                list.add(data);
                personIds[i] = idAndStatus[0];
            }
        }
        pd.put("person_ids", StringUtils.join(personIds, ","));
        pd.put("list", list);
        if (list.size() > 0)
            mFaceAppDao.updateGrantSyncStatus(pd);
        return new ResultData<>(ResultEnum.SUCCESS, mFaceAppDao.selectPersonListNoSync(pd));
    }

    @Override
    public void downloadApk(ParamData pd, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedInputStream bis = null;
        BufferedOutputStream fos = null;
        try {
            String applicationId = pd.getString("application_id");

            String projectDlPath = CommUtil.getProjectDlPath();
            if (StringUtils.isEmpty(projectDlPath))
                throw new Exception("更新路径不存在");
            File dir = new File(projectDlPath);
            File[] files = dir.listFiles();//绝对路径
            for (File file : files) {
                if (file.getName().contains(applicationId)) {
                    bis = new BufferedInputStream(new FileInputStream(file));
                    fos = new BufferedOutputStream(response.getOutputStream());

                    response.setContentLength(bis.available());
                    //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                    //2.设置文件头：最后一个参数是设置下载文件名
                    response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());

                    byte[] buffer = new byte[1024 * 10];
                    int length;
                    while ((length = bis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.flush();
                }
            }


        } finally {
            if (bis != null)
                bis.close();
            if (fos != null)
                fos.close();
        }
    }

    @Override
    public ResultData<ParamData> getLastVersionInfo(ParamData pd) {
        String projectDlPath = CommUtil.getProjectDlPath();
        if(StringUtils.isEmpty(projectDlPath))
            return new ResultData<>(ResultEnum.FAIL, "更新路径不存在");
        File dir = new File(projectDlPath);

        File[] files = dir.listFiles();//绝对路径
        if(files==null||files.length==0)
            return new ResultData<>(ResultEnum.SUCCESS, "暂无更新");

        ParamData data = new ParamData();
        data.put("version", "-1");
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.contains(pd.getString("application_id"))) {
                String version = fileName.split("_")[1].split("\\.")[0];
                data.put("version", version);
            }
        }

        data.put("description", "暂无");
        return new ResultData<>(ResultEnum.SUCCESS, data);
    }

    @Override
    public void downloadImage(ParamData pd, HttpServletResponse response) throws Exception {
        BufferedOutputStream bos = null;
        try {
            ParamData paramData = mPersonDao.selectImage(pd);
            byte[] byteImage = (byte[]) paramData.get("blob_image");

            bos = new BufferedOutputStream(response.getOutputStream());
            bos.write(byteImage);
            bos.flush();
        } finally {
            if (bos != null)
                bos.close();
        }
    }

    @Override
    public ResultData<List<ParamData>> getPersonList(ParamData pd) {
        return new ResultData<>(ResultEnum.SUCCESS, mFaceAppDao.selectPersonList(pd));
    }

    @Override
    public ResultData<ParamData> uploadDeviceInfo(ParamData pd) {
        if (mFaceAppDao.updateDeviceConfig(pd))
            return new ResultData<>(ResultEnum.SUCCESS);
        return new ResultData<>(ResultEnum.FAIL);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(CommConst.ACCESS_APP_TOKEN);
        if (StringUtils.isEmpty(token)) {
            // 从请求信息中获取token值
            token = request.getParameter(CommConst.ACCESS_APP_TOKEN);
        }
        return token;
    }
}
