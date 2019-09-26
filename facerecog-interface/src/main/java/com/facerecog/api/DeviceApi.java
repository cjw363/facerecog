package com.facerecog.api;

import com.facerecog.pojo.PageData;
import com.facerecog.pojo.ParamData;
import com.facerecog.pojo.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Classname DeviceApi
 * @Description
 * @Date 2019/9/26 14:48
 * @Created by cjw
 */
@Api(value = "设备接口")
public interface DeviceApi {

    @ApiOperation("获取已激活的设备列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name="access_cpfr_token",value = "access_cpfr_token",required=true,dataType="String")
    })
    ResultData<PageData<ParamData>> list();
}
