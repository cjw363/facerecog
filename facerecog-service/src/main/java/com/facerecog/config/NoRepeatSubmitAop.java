package com.facerecog.config;

import com.facerecog.annot.NoRepeatSubmit;
import com.facerecog.pojo.HandleEnum;
import com.facerecog.pojo.ResultData;

import net.minidev.json.JSONObject;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
 * @功能描述 aop解析注解
 * @author www.gaozz.club
 * @date 2018-08-26
 */
@Aspect
@Component
public class NoRepeatSubmitAop {

    private Log logger = LogFactory.getLog(getClass());

    @Resource(name = "urlCache")
    private Cache cache;

    @Around("execution(* com.facerecog..*Controller.*(..)) && @annotation(nrs)")
    public Object checkRepeatSubmit(ProceedingJoinPoint pjp, NoRepeatSubmit nrs) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
            HttpServletRequest request = attributes.getRequest();
            String key = sessionId + "-" + request.getServletPath()+"-"+ JSONObject.toJSONString(request.getParameterMap());
            if (cache.get(key) == null) {// 如果缓存中有这个url视为重复提交
                Object o = pjp.proceed();
                cache.put(new Element(key, 0));
                return o;
            } else {
                logger.warn("重复提交");
                return new ResultData<>(HandleEnum.FAIL, "重复提交");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("验证重复提交时出现未知异常!");
            return new ResultData<>(HandleEnum.FAIL, "验证重复提交时出现未知异常!");
        }
    }
}