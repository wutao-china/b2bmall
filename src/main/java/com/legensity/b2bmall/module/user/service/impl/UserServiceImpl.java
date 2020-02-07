package com.legensity.b2bmall.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.module.company.pojo.Company;
import com.legensity.b2bmall.module.company.service.ICompanyService;
import com.legensity.b2bmall.module.user.dao.UserMapper;
import com.legensity.b2bmall.module.user.pojo.User;
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

    /**
     * 注册
     * type 不是0，1时返回null
     * @param register
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public UserRegisterDTO register(UserRegisterDTO register){
        if (register.getType() == 0) { // 经销商
            // 用户
            User user = new User();
            BeanUtils.copyProperties(register, user);
            //user.setCompanyId(userCompany.getId());
            user.setCreateTime(new Date());
            int save = userMapper.insert(user);
            if (save > 0) {
                //公司
                Company userCompany = new Company();
                BeanUtils.copyProperties(register, userCompany);
                userCompany.setName(register.getOrgName());
                userCompany.setCreateTime(user.getCreateTime());
                userCompany.setLinkManId(user.getId());
                userCompany.setLinkManName(register.getName());
                userCompany.setLinkManTel(register.getMobile());
                companyService.save(userCompany);

                return register;
            }
        } else if (register.getType() == 1) { // 管理人员
            User user = new User();
            BeanUtils.copyProperties(register, user);
            user.setCreateTime(new Date());
            userMapper.insert(user);
            return register;
        }

        return null;
    }

    @Override
    public Boolean checkMobileExist(String mobile, Integer type) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getMobile, mobile)
                .eq(User::getType, type);
        return userMapper.selectCount(queryWrapper) ==0?false:true;
    }
}
