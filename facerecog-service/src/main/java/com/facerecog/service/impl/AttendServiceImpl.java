package com.facerecog.service.impl;

import com.facerecog.dao.AttendDao;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;
import com.facerecog.service.interf.AttendService;
import com.facerecog.utils.CommUtil;
import com.facerecog.utils.ExportExcelUtils;
import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date 2019/3/14
 * @Created by xwr
 */
@Service
public class AttendServiceImpl implements AttendService {

    @Resource
    private AttendDao mAttendDao;

    @Override
    public ResultData<PageData<ParamData>> getAttendList(ParamData pd) {
        int pageNum = CommUtil.paramConvert(pd.getString("pageNum"), 0);//当前页
        int pageSize = CommUtil.paramConvert(pd.getString("pageSize"), 0);//每一页10条数据

        if (pageSize != 0) PageHelper.startPage(pageNum, pageSize);
        List<ParamData> attendList = mAttendDao.selectAttendList(pd);
        return new ResultData<>(HandleEnum.SUCCESS, new PageData<>(attendList));
    }

    @Override
    public void export(ParamData pd, HttpServletResponse response) {
        List<ParamData> attendList = mAttendDao.selectAttendList(pd);

        attendList.forEach(p->{
            String amPunchStatus="";
            String pmPunchStatus="";
            switch (Integer.parseInt(p.get("am_punch_status").toString())){
                case 0:amPunchStatus="正常";break;
                case 1:amPunchStatus="迟到";break;
                case 3:amPunchStatus="其他";break;
            }
            p.put("am_punch_status",amPunchStatus);

            switch (Integer.parseInt(p.get("pm_punch_status").toString())){
                case 0:pmPunchStatus="正常";break;
                case 2:pmPunchStatus="早退";break;
                case 3:pmPunchStatus="其他";break;
            }
            p.put("pm_punch_status",pmPunchStatus);
        });

        if(CollectionUtils.isEmpty(attendList))return;
        HashMap<String, String[]> map = new HashMap<>();
        map.put("record_time", new String[]{"0", "日期"});
        map.put("person_name",new String[]{"1", "姓名"});
        map.put("device_name",new String[]{"2", "设备"});
        map.put("am_punch_time",new String[]{"3", "上班打卡时间"});
        map.put("am_punch_status",new String[]{"4", "上班状态"});
        map.put("pm_punch_time",new String[]{"5", "下班打卡时间"});
        map.put("pm_punch_status",new String[]{"6", "下班状态"});
        map.put("group_name",new String[]{"7", "组名"});
        ExportExcelUtils.<ParamData>export("通行记录报表",attendList,map,response);
    }

}
