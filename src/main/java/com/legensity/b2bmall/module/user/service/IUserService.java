package com.legensity.b2bmall.module.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.legensity.b2bmall.module.user.bean.User;

/**
 * @author jinbin
 * @date 2017-12-01 21:22
 */
public interface IUserService extends IService<com.legensity.b2bmall.module.user.bean.User> {
    /**
     * 通过mobile查找用户信息
     */
    User selectUserWithDetailByMobile(String mobile);
}
