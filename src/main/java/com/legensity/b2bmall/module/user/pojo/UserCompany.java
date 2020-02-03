package com.legensity.b2bmall.module.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wutao
 * @since 2020-01-22
 */
@TableName("t_user_company")
public class UserCompany implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "id", dataType = "Integer")
    @TableId(value="ID", type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty(notes = "公司名称", dataType = "String")
    private String name;

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

    @ApiModelProperty(notes = "状态", dataType = "Integer")
    private Integer status;

    @ApiModelProperty(notes = "联系人id", dataType = "Integer")
    private Integer userId;

    @ApiModelProperty(notes = "创建时间", dataType = "Date")
    private Date createTime;

    @ApiModelProperty(notes = "创建时间", dataType = "Date")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }


    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
    }


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String SHORT_NAME = "short_name";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String ADDRESS = "address";

    public static final String LOG_URL = "log_url";

    public static final String BUSINESS_LICENSE_URL = "business_license_url";

    public static final String ORG_CODE = "org_code";

    public static final String STATUS = "status";

    public static final String USER_ID = "user_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "UserCompany{" +
                "id=" + id +
                ", name=" + name +
                ", shortName=" + shortName +
                ", province=" + province +
                ", city=" + city +
                ", address=" + address +
                ", logUrl=" + logUrl +
                ", businessLicenseUrl=" + businessLicenseUrl +
                ", orgCode=" + orgCode +
                ", status=" + status +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}