package com.legensity.b2bmall.module.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @NotNull
    @ApiModelProperty(notes = "用户类型 0 经销商 1 后台管理人员", dataType = "Integer")
    private Integer type;

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
