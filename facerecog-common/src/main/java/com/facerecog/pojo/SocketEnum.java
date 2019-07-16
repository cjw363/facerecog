package com.facerecog.pojo;

/**
 * @Classname SocketEnum
 * @Description
 * @Date 2019/1/21 9:41
 * @Created by cjw
 */
public enum SocketEnum {
    CODE_999_FAIL(999,"操作失败"),
    CODE_1000_SUCCESS(1000,"操作成功"),
    CODE_1001_DEVICE_ACTIVATE(1001,"激活成功"),
    CODE_1002_DEVICE_UPDATE(1002,"设备更新"),
    CODE_1003_PERSON_UPDATE(1003,"人员更新"),
    CODE_1004_GRANT_UPDATE(1004,"权限更新"),
    CODE_1005_DEVICE_DELETE(1005,"设备删除"),
    CODE_1006_ACCESS_APP_TOKEN(1006,"连接成功"),
    CODE_1007_DEVICE_INACT(1007,"未激活"),
    CODE_1008_NEW_APP_VERSION(1008,"新的app版本"),
    ;

    private int code;
    private String message;

    SocketEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static SocketEnum codeOf(int index){
        for (SocketEnum code : values()){
            if (code.getCode()==index){
                return code;
            }
        }
        return null;
    }
}
