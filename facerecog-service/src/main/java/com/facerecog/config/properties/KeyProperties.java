package com.facerecog.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Classname KeyProperties
 * @Description
 * @Date 2019/7/26 10:43
 * @Created by cjw
 */
@ConfigurationProperties(prefix = "facerecog.activatekey")
public class KeyProperties {
    private String arcSoftAppID;
    private String arcSoftSdkKey;

    public String getArcSoftAppID() {
        return arcSoftAppID;
    }

    public void setArcSoftAppID(String arcSoftAppID) {
        this.arcSoftAppID = arcSoftAppID;
    }

    public String getArcSoftSdkKey() {
        return arcSoftSdkKey;
    }

    public void setArcSoftSdkKey(String arcSoftSdkKey) {
        this.arcSoftSdkKey = arcSoftSdkKey;
    }
}
