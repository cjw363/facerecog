package com.facerecog.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CacheConfiguration {
    /*
     * ehcache 主要的管理器
     */
//    @Bean(name = "appEhCacheCacheManager")
//    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
//        return new EhCacheCacheManager(bean.getObject());
//    }

    /*
     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/config-ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    @Autowired
    private CacheManager mCacheManager;

    @Bean(value = "myCache")
    public Cache cache() {
        return mCacheManager.getCache("myCache");
    }
    @Bean(value = "urlCache")
    public Cache cache2() {
        return mCacheManager.getCache("urlCache");
    }
}