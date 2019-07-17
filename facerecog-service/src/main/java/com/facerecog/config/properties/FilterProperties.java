package com.facerecog.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Classname FilterProperties
 * @Description
 * @Date 2019/7/17 11:38
 * @Created by cjw
 */
@ConfigurationProperties(prefix = "facerecog.filter")
public class FilterProperties {
    private List<String> webExcludePaths;

    private List<String> appExcludePaths;

    public List<String> getWebExcludePaths() {
        return webExcludePaths;
    }

    public void setWebExcludePaths(List<String> webExcludePaths) {
        this.webExcludePaths = webExcludePaths;
    }

    public List<String> getAppExcludePaths() {
        return appExcludePaths;
    }

    public void setAppExcludePaths(List<String> appExcludePaths) {
        this.appExcludePaths = appExcludePaths;
    }
}
