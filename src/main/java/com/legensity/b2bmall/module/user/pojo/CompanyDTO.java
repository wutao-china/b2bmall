package com.legensity.b2bmall.module.user.pojo;

import com.legensity.b2bmall.module.company.pojo.Company;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: b2bmall
 * @description: 供应商DTO
 * @author: wutao
 * @create: 2020/02/03 13:17
 **/
@Setter
@Getter
public class CompanyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "公司id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(notes = "手机号", dataType = "String")
    private String mobile;

    @ApiModelProperty(notes = "用户名 ", dataType = "String")
    private String name;

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

    public CompanyDTO(User user, Company company) {
        super();
        if(company != null){
            BeanUtils.copyProperties(company, this);
            setOrgName(company.getName());
        }
        if(user != null){
            BeanUtils.copyProperties(user, this);
        }
    }

    public CompanyDTO(User user) {
        super();
        if(user != null){
            BeanUtils.copyProperties(user, this);
        }
    }
}
