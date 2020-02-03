package com.legensity.b2bmall.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.module.meaage.service.IMessageService;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.dao.UserMapper;
import com.legensity.b2bmall.module.user.pojo.UserCompany;
import com.legensity.b2bmall.module.user.pojo.UserCompanyDTO;
import com.legensity.b2bmall.module.user.pojo.UserRegisterDTO;
import com.legensity.b2bmall.module.user.service.IUserCompanyService;
import com.legensity.b2bmall.module.user.service.IUserService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private IMessageService messageService;

    @Override
    public User selectUserWithDetailByMobile(String mobile) {
        return null;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public UserCompanyDTO register(UserRegisterDTO register){
        User user = new User();
        BeanUtils.copyProperties(register, user);
        user.setCreateTime(new Date());
        int insert = userMapper.insert(user);

        if (insert !=0) {
            register.setUserId(user.getId());
            //公司
            UserCompany userCompany = new UserCompany();
            BeanUtils.copyProperties(register, userCompany);
            userCompany.setName(register.getOrgName());
            userCompany.setCreateTime(user.getCreateTime());
            userCompanyService.insert(userCompany);

            // 注册成功验证码失效
            //if(userCompany.getId() != null){
            //}
        }

        User u = userMapper.selectUserWithDetailByMobile(register.getMobile());


        return null;
    }

    @Override
    public Boolean checkMobileExist(String mobile) {
        return userMapper.selectCount(new QueryWrapper<User>().eq(User.MOBILE, mobile)) ==0?false:true;
    }
}
