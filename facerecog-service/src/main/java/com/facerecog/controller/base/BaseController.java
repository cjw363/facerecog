package com.facerecog.controller.base;

import com.facerecog.pojo.ParamData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


public class BaseController {
    /**
     * 初始化参数
     */
    public ParamData paramDataInit() {
        return new ParamData(this.getRequest());
    }

    /**
     * 得到ModelAndView
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    /**
     * 得到request对象
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
          .getRequest();
        return request;
    }
}