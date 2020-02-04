package com.legensity.b2bmall.module.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class UserRegisterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @ApiModelProperty(notes = "手机号", dataType = "String")
    private String mobile;

    @NotBlank
    @ApiModelProperty(notes = "密码", dataType = "String")
    private String password;

    @NotBlank
    @ApiModelProperty(notes = "用户名 ", dataType = "String")
    private String name;

    @NotBlank
    @ApiModelProperty(notes = "验证码", dataType = "String")
    private String verificationCode;

    @NotBlank
    @ApiModelProperty(notes = "公司名称", dataType = "String")
    private String orgName;

    @NotBlank
    @ApiModelProperty(notes = "公司简称", dataType = "String")
    private String shortName;

    @NotBlank
    @ApiModelProperty(notes = "省", dataType = "String")
    private String province;

    @NotBlank
    @ApiModelProperty(notes = "市", dataType = "String")
    private String city;

    @NotBlank
    @ApiModelProperty(notes = "地址", dataType = "String")
    private String address;
}
