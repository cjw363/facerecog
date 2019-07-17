package com.facerecog.service.impl;

import com.facerecog.dao.RecordDao;
import com.facerecog.ehcache.WebCache;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.RecordService;
import com.facerecog.utils.CommConst;
import com.facerecog.utils.CommUtil;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Classname RecordServiceImpl
 * @Description
 * @Date 2018/12/5 10:45
 * @Created by cjw
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    RecordDao mRecordDao;
    @Autowired
    private WebCache memory;

    @Override
    public ResultData<PageData<ParamData>> getRecordBase64List(ParamData pd) {
        int pageNum = CommUtil.paramConvert(pd.getString("pageNum"), 0);//当前页
        int pageSize = CommUtil.paramConvert(pd.getString("pageSize"), 0);//每一页10条数据

        if (pageSize != 0) PageHelper.startPage(pageNum, pageSize);
        List<ParamData> recordList = mRecordDao.selectRecordListWithBlob(pd);
        return new ResultData<>(HandleEnum.SUCCESS, new PageData<>(recordList));
    }

    @Transactional
    @Override
    public ResultData<ParamData> deleteRecord(ParamData pd) {
        if (mRecordDao.deleteRecord(pd)) {
            return new ResultData<>(HandleEnum.SUCCESS);
        } else return new ResultData<>(HandleEnum.FAIL);
    }

    @Transactional
    @Override
    public ResultData<ParamData> deleteRecordLists(ParamData pd) {
        String record_ids = pd.getString("record_ids");
        List<ParamData> list = new ArrayList<>();
        String[] recordIdArr = record_ids.split(",");
        for (String recordId : recordIdArr) {
                ParamData paramData = new ParamData();
                int record_id = Integer.parseInt(recordId);
                paramData.put("record_id", record_id);
                list.add(paramData);
        }

        ParamData paramData = new ParamData();
        paramData.put("wid", memory.getCache(pd.getString(CommConst.ACCESS_CPFR_TOKEN)).getWid());
        paramData.put("list", list);
        if (mRecordDao.deleteRecordLists(paramData)) {
            return new ResultData<>(HandleEnum.SUCCESS);
        }
        return new ResultData<>(HandleEnum.FAIL);

    }

    @Transactional
    @Override
    public ResultData<ParamData> clearRecord(ParamData pd) {
        if (mRecordDao.deleteRecodeAll(pd)) return new ResultData<>(HandleEnum.SUCCESS,"删除成功");
        else return new ResultData<>(HandleEnum.FAIL,"删除失败");
    }

}
