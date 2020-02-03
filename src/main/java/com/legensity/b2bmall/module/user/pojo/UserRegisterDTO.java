package com.legensity.b2bmall.module.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class UserRegisterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "用户id", dataType = "Integer")
    private Integer userId;

    @NotNull
    @ApiModelProperty(notes = "手机号", dataType = "String")
    private String mobile;

    @NotNull
    @ApiModelProperty(notes = "密码", dataType = "String")
    private String password;

    @NotNull
    @ApiModelProperty(notes = "用户名 ", dataType = "String")
    private String name;

    @NotNull
    @ApiModelProperty(notes = "验证码", dataType = "String")
    private String verificationCode;

    @NotNull
    @ApiModelProperty(notes = "公司名称", dataType = "String")
    private String orgName;

    @NotNull
    @ApiModelProperty(notes = "公司简称", dataType = "String")
    private String shortName;

    @NotNull
    @ApiModelProperty(notes = "省", dataType = "String")
    private String province;

    @NotNull
    @ApiModelProperty(notes = "市", dataType = "String")
    private String city;

    @NotNull
    @ApiModelProperty(notes = "地址", dataType = "String")
    private String address;
}
