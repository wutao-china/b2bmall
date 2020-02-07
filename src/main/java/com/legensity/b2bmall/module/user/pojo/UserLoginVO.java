package com.legensity.b2bmall.module.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: b2bmall
 * @description: 用户登录信息值对象
 * @author: wutao
 * @create: 2020/02/04 17:17
 **/
@Setter
@Getter
public class UserLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(notes = "手机号", dataType = "String")
    private String mobile;

    @ApiModelProperty(notes = "用户名 ", dataType = "String")
    private String name;

    @ApiModelProperty(notes = "类型 0 经销商 1 管理人员", dataType = "Integer")
    private Integer type;

    @ApiModelProperty(notes = "创建时间", dataType = "Date")
    private Date createTime;

    @ApiModelProperty(notes = "更新时间", dataType = "Date")
    private Date updateTime;

    @ApiModelProperty(notes = "token", dataType = "String")
    private String token;

    public UserLoginVO(User user) {
        super();
        if (user != null) {
            BeanUtils.copyProperties(user, this);
        }
    }

    public UserLoginVO(User user, String token) {
        super();
        if (user != null) {
            BeanUtils.copyProperties(user, this);
        }

        this.setToken(token);
    }
}
