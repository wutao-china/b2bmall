package com.legensity.b2bmall.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.module.user.bean.User;
import com.legensity.b2bmall.module.user.dao.UserMapper;
import com.legensity.b2bmall.module.user.bean.UserCompany;
import com.legensity.b2bmall.module.user.service.IUserCompanyService;
import com.legensity.b2bmall.module.user.service.IUserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
            //IPage page = new Page();
            //UserCompany userCompany = userCompanyService.page(wrapper);
            user.setUserCompany(userCompany);
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public User register(User user) {
        user.setCreateTime(new Date());
        int insert = userMapper.insert(user);
        if (insert !=0) {
            user.getUserCompany().setCreateTime(user.getCreateTime());
            userCompanyService.insert(user.getUserCompany());
        }

        return user;
    }

    @Override
    public Boolean checkMobileExist(String mobile) {
        return userMapper.selectCount(new QueryWrapper<User>().eq(User.MOBILE, mobile)) ==0?false:true;
    }
}
