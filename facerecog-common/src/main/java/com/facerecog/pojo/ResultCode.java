package com.facerecog.pojo;

/**
 * Created by cjw on 2018/3/5.
 * 10000-- 通用错误代码
 * 22000-- 媒资错误代码
 * 23000-- 用户中心错误代码
 * 24000-- cms错误代码
 * 25000-- 文件系统
 */
public interface ResultCode {
    //操作代码
    int getCode();
    //提示信息
    String getMessage();

}