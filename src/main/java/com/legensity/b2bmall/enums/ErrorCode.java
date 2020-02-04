package com.legensity.b2bmall.enums;

public enum ErrorCode {

    INTERFACE_PARAM_MISS(1000, "参数缺失"),
    INTERFACE_MOBILE_REGISTERED(1001, "手机号已注册"),
    INTERFACE_USER_PASSWORD_ERROR(1002, "用户名或密码错误"),
    INTERFACE_VERIFICATIONCODE_ERROR(1003, "验证码错误"),
    INTERFACE_MOBILE_UNREGISTERED(1004, "手机号未注册"),
    INTERFACE_USER_UNAUTHORIZED_OPERATION(1005, "越权操作"),
    ;

    private Integer code;
    private String desc;

    ErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}