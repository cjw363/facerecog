package com.facerecog.controller.base;

import com.facerecog.ehcache.WebMemory;
import com.facerecog.pojo.ParamData;
import com.facerecog.utils.CommConst;
import com.facerecog.utils.CommUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname WebBaseController
 * @Description
 * @Date 2019/2/20 17:08
 * @Created by cjw
 */
public class WebBaseController extends BaseController {
    @Autowired
    private WebMemory memory;

    /**
     * 初始化参数
     *
     * @return
     */
    public ParamData paramDataInit() {
        ParamData pd = new ParamData(this.getRequest());
        String token = CommUtil.getTokenFromRequest(this.getRequest());
        if (!StringUtils.isEmpty(token) && memory.checkCache(token)) {
            pd.put(CommConst.ACCESS_CPFR_TOKEN, token);
            pd.put("wid", memory.getCache(token).getWid());
        }
        return pd;
    }
}
