package com.facerecog.ehcache;


import com.facerecog.bo.UserInfo;
import com.facerecog.utils.SystemConfig;
import com.facerecog.utils.TokenProcessor;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Created by Administrator on 2018/1/4.
 */
@Component
public class WebCache {

    @Autowired
    private Cache ehcache;

    /**
     * 关闭缓存管理器
     */
    @PreDestroy
    protected void shutdown() {
        if (ehcache != null) {
            ehcache.getCacheManager().shutdown();
        }
    }

    /**
     * 保存当前登录用户信息
     * timeToLiveSeconds -->当对象自从被存放到缓存中后，如果处于缓存中的时间超过了 timeToLiveSeconds属性值,这个对象就会过期，
     * EHCache将把它从缓存中清除；即缓存自创建日期起能够存活的最长时间，单位为秒(s)
     * <p>
     * timeToIdleSeconds -->  当对象自从最近一次被访问后，如果处于空闲状态的时间超过了timeToIdleSeconds属性值，这个对象就会过期，
     * EHCache将把它从缓存中清空；即缓存被创建后，最后一次访问时间到缓存失效之时，两者之间的间隔，单位为秒(s)
     */
    public void putCache(UserInfo userInfo) {
        // 生成seed和token值
        String seed = TokenProcessor.getInstance().generateSeed(userInfo.getUserId() + "");
        String token = TokenProcessor.getInstance()
          .generateToken(userInfo.getName() + userInfo.getPassword());

        // 保存token到登录用户中
        userInfo.setToken(token);
        // 清空之前的登录信息
        removeCache(seed);
        // 保存新的token和登录信息
        ehcache.put(new Element(seed, token, SystemConfig.SESSION_TIME_TO_IDLE, SystemConfig.SESSION_TIME_LIVE_MAX));
        ehcache.put(new Element(token, userInfo, SystemConfig.SESSION_TIME_TO_IDLE, SystemConfig.SESSION_TIME_LIVE_MAX));
    }

    /**
     * 获取当前线程中的用户信息
     */
    public UserInfo getCache(String token) {
        Element element = ehcache.get(token);
        return element == null ? null : (UserInfo) element.getObjectValue();
    }

    /**
     * 根据token检查用户是否登录
     */
    public boolean checkCache(String token) {
        Element element = ehcache.get(token);
        return element != null && element.getObjectValue() != null;
    }

    /**
     * 清空登录信息
     */
    public void removeCache(String seed) {
        Element element = ehcache.get(seed);
        if(element!=null){
            String token = (String) element.getObjectValue();
            ehcache.remove(seed);
            ehcache.remove(token);
        }
    }
}