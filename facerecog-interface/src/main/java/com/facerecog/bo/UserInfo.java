package com.facerecog.bo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/4.
 */
public class UserInfo implements Serializable {
    private int userId;
    private int wid;
    private String name;
    private String password;
    private String token;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }
}
