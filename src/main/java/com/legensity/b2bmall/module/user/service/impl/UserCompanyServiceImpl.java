
package com.legensity.b2bmall.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.module.user.dao.UserCompanyMapper;
import com.legensity.b2bmall.module.user.bean.UserCompany;
import com.legensity.b2bmall.module.user.service.IUserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wutao
 * @since 2020-01-21
 */
@Service
@Transactional
public class UserCompanyServiceImpl extends ServiceImpl<UserCompanyMapper, UserCompany> implements IUserCompanyService {


    @Autowired
    private UserCompanyMapper userCompanyMapper;


}