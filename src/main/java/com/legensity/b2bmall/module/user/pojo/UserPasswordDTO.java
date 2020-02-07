package com.legensity.b2bmall.module.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @program: b2bmall
 * @description: 用户密码DTO
 * @author: wutao
 * @create: 2020/02/06 13:41
 **/
@Setter
@Getter
public class UserPasswordDTO {
    @NotBlank
    @ApiModelProperty(notes = "手机号", dataType = "String")
    private String mobile;

    @NotBlank
    @ApiModelProperty(notes = "密码", dataType = "String")
    private String password;

    @NotBlank
    @ApiModelProperty(notes = "用户类型 0 经销商 1 后台管理人员", dataType = "Integer")
    private Integer type;

    @NotBlank
    @ApiModelProperty(notes = "验证码", dataType = "String")
    private String verificationCode;
}
