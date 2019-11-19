package com.facerecog.pojo;


public class ResultData<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultData() {

    }

    public ResultData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultData(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ResultData(ResultEnum operateEnum) {
        this.code = operateEnum.getCode();
        this.message = operateEnum.getMessage();
    }

    public ResultData(ResultEnum operateEnum, T data) {
        this.code = operateEnum.getCode();
        this.message = operateEnum.getMessage();
        this.data = data;
    }

    public ResultData(ResultEnum operateEnum, String message) {
        this.code = operateEnum.getCode();
        this.message = message;
    }

    public ResultData(ResultEnum operateEnum, String message, T data) {
        this.code = operateEnum.getCode();
        this.message = message;
        this.data = data;
    }
}