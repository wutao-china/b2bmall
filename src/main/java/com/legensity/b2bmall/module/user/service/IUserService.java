package com.legensity.b2bmall.module.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.pojo.UserRegisterDTO;

/**
 * @author jinbin
 * @date 2017-12-01 21:22
 */
public interface IUserService extends IService<User> {

    /**
     * 注册
     * type 不是0，1时返回null
     * @param register
     * @return
     */
    UserRegisterDTO register(UserRegisterDTO register);

    /**
     * 手机号是否存在
     * @param mobile
     * @param type
     * @return
     */
    Boolean checkMobileExist(String mobile, Integer type);


}
