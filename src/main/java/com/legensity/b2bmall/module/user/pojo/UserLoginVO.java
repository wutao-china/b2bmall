package com.legensity.b2bmall.module.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class UserLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(notes = "手机号", dataType = "String")
    private String mobile;

    @ApiModelProperty(notes = "密码", dataType = "String")
    private String password;

    @ApiModelProperty(notes = "盐", dataType = "String")
    private String salt;

    @ApiModelProperty(notes = "状态", dataType = "Integer")
    private Integer status;

    @ApiModelProperty(notes = "用户名 ", dataType = "String")
    private String name;

    @ApiModelProperty(notes = "性别", dataType = "Integer")
    private Integer sex;

    @ApiModelProperty(notes = "创建时间", dataType = "Date")
    private Date createTime;

    @ApiModelProperty(notes = "更新时间", dataType = "Date")
    private Date updateTime;

    @ApiModelProperty(notes = "公司名称", dataType = "String")
    private String orgName;

    @ApiModelProperty(notes = "公司简称", dataType = "String")
    private String shortName;

    @ApiModelProperty(notes = "省", dataType = "String")
    private String province;

    @ApiModelProperty(notes = "市", dataType = "String")
    private String city;

    @ApiModelProperty(notes = "地址", dataType = "String")
    private String address;

    @ApiModelProperty(notes = "log地址", dataType = "String")
    private String logUrl;

    @ApiModelProperty(notes = "营业执照地址", dataType = "String")
    private String businessLicenseUrl;

    @ApiModelProperty(notes = "社会信用代码", dataType = "String")
    private String orgCode;

    @ApiModelProperty(notes = "联系人id", dataType = "Integer")
    private Integer userId;
}
