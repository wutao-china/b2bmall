
package com.legensity.b2bmall.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.legensity.b2bmall.module.user.bean.UserCompany;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wutao
 * @since 2020-01-21
 */
public interface IUserCompanyService extends IService<UserCompany> {

    Integer insert(UserCompany userCompany);
}