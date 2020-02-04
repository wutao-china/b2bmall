package com.legensity.b2bmall.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.module.company.pojo.Company;
import com.legensity.b2bmall.module.company.service.ICompanyService;
import com.legensity.b2bmall.module.user.dao.UserMapper;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.pojo.UserCompanyDTO;
import com.legensity.b2bmall.module.user.pojo.UserRegisterDTO;
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
    private ICompanyService companyService;

    @Override
    public UserCompanyDTO selectUserWithDetailByMobile(String mobile) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq(User.MOBILE, mobile));
        if(user != null){
            Company company = companyService.getById(user.getCompanyId());
            return new UserCompanyDTO(user, company);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public UserRegisterDTO register(UserRegisterDTO register){
        //公司
        Company userCompany = new Company();
        BeanUtils.copyProperties(register, userCompany);
        userCompany.setName(register.getOrgName());
        userCompany.setCreateTime(new Date());
        boolean save = companyService.save(userCompany);

        if (save) {
            User user = new User();
            BeanUtils.copyProperties(register, user);
            user.setCompanyId(userCompany.getId());
            user.setCreateTime(userCompany.getCreateTime());
            user.setStatus(1);
            userMapper.insert(user);

            // 注册成功验证码失效
            //if(userCompany.getId() != null){
            //}
        }

        return register;
    }

    @Override
    public Boolean checkMobileExist(String mobile) {
        return userMapper.selectCount(new QueryWrapper<User>().eq(User.MOBILE, mobile)) ==0?false:true;
    }
}
