package com.legensity.b2bmall.module.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author wutao
 * @since 2020-01-23
 */
@TableName("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value="ID", type= IdType.AUTO)
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

    @ApiModelProperty(notes = "用户公司", dataType = "userCompany")
    @TableField(exist=false)
    private UserCompany userCompany;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public void setUserCompany(UserCompany userCompany) {
        this.userCompany = userCompany;
    }

    public UserCompany getUserCompany() {
        return userCompany;
    }

    public static final String ID = "id";

    public static final String MOBILE = "mobile";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String STATUS = "status";

    public static final String NAME = "name";

    public static final String SEX = "sex";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mobile=" + mobile +
                ", password=" + password +
                ", salt=" + salt +
                ", status=" + status +
                ", name=" + name +
                ", sex=" + sex +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}