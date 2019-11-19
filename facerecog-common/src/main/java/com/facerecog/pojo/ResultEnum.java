package com.facerecog.pojo;


public enum ResultEnum implements ResultCode{
    FAIL(-1,"操作异常"),
    SUCCESS(0,"操作成功"),
    ADMIN_EXISTED_100(100,"该管理员用户已注册"),
    PARAM_ERROR_101(101,"参数错误"),
    SESSION_ERROR_102(102,"session失效或者未登录"),
    ADMIN_NOT_EXIST_103(103,"没有该管理员用户"),
    PASSWORD_ERROR_104(104,"密码错误"),
    NEW_APP_VERSION_105(105,"发现新版本APP"),
    INVALID_PARAM(106,"非法参数！"),
    SERVER_ERROR(999,"抱歉，系统繁忙，请稍后重试！"),
    ;

    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ResultEnum codeOf(int index){
        for (ResultEnum code : values()){
            if (code.getCode()==index){
                return code;
            }
        }
        return null;
    }
}