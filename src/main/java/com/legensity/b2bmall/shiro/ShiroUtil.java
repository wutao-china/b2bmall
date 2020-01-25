package com.legensity.b2bmall.shiro;

import com.legensity.b2bmall.module.user.bean.User;
import org.apache.shiro.SecurityUtils;

/**
 * 获取用户
 * @author wutao
 * @date 2020-01-25 22:08
 */
public class ShiroUtil {
    public static User getUser() {
        User principal = null;
        try {
            principal = (User) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return principal;
    }
}
