package com.legensity.b2bmall.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.module.user.bean.User;
import com.legensity.b2bmall.module.user.dao.UserMapper;
import com.legensity.b2bmall.module.user.bean.UserCompany;
import com.legensity.b2bmall.module.user.service.IUserCompanyService;
import com.legensity.b2bmall.module.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserCompanyService userCompanyService;

    @Override
    public User selectUserWithDetailByMobile(String mobile) {
        User user = userMapper.selectUserByMobile(mobile);
        if(user != null){
            QueryWrapper<UserCompany> wrapper = new QueryWrapper<>();
            wrapper.eq(UserCompany.USER_ID , user.getId());
            UserCompany userCompany = userCompanyService.getOne(wrapper);
            user.setUserCompany(userCompany);
        }
        return user;
    }
}
