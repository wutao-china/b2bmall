package com.legensity.b2bmall.enums;

public enum ErrorCode {

    INTERFACE_PARAM_MISS(1000, "参数缺失"),
    INTERFACE_MOBILE_REGISTERED(1001, "手机号已注册"),
    INTERFACE_USER_PASSWORD_ERROR(1002, "用户名或密码错误"),
    INTERFACE_VERIFICATIONCODE_ERROR(1003, "验证码错误"),
    INTERFACE_MOBILE_UNREGISTERED(1004, "手机号未注册"),
    INTERFACE_USER_REGISTER_ERROR(1005, "注册失败"),
    INTERFACE_COMPANY_NOT_EXIT_ERROR(1006, "公司不存在"),
    INTERFACE_COMPANY_STATUS_ERROR(1007, "公司状态错误"),
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