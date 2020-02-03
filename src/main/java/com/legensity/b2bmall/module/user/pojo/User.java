package com.legensity.b2bmall.module.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author wutao
 * @since 2020-01-23
 */
@Setter
@Getter
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