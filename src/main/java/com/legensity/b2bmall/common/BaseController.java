package com.legensity.b2bmall.common;

import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.util.ShiroUtil;

/**
 * 基础控制类
 * @author wutao
 * @date 2020-01-26 09:25
 */
public class BaseController {

    /**
     * 获取当前登录的用户
     * @return
     */
    protected User getCurrentUser(){
        return ShiroUtil.getUser();
    }
}