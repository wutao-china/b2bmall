package com.legensity.b2bmall.module.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.pojo.UserCompanyDTO;
import com.legensity.b2bmall.module.user.pojo.UserRegisterDTO;

/**
 * @author jinbin
 * @date 2017-12-01 21:22
 */
public interface IUserService extends IService<com.legensity.b2bmall.module.user.pojo.User> {
    /**
     * 通过mobile查找用户信息
     */
    User selectUserWithDetailByMobile(String mobile);

    /**
     * 用户注册
     * @param user
     * @return
     */
    UserCompanyDTO register(UserRegisterDTO user);

    /**
     * 手机号是否存在
     * @param mobile
     * @return
     */
    Boolean checkMobileExist(String mobile);


}
