package com.legensity.b2bmall.module.company.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 公司
 * </p>
 *
 * @author wutao
 * @since 2020-02-03
 */
@Setter
@Getter
@TableName("t_company")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "", dataType = "Integer")
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

    @ApiModelProperty(notes = "更新时间", dataType = "Date")
    private Date updateTime;


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

}