
package com.legensity.b2bmall.module.company.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.module.company.dao.CompanyMapper;
import com.legensity.b2bmall.module.company.pojo.Company;
import com.legensity.b2bmall.module.company.service.ICompanyService;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.pojo.CompanyDTO;
import com.legensity.b2bmall.module.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 公司 服务实现类
 * </p>
 *
 * @author wutao
 * @since 2020-02-03
 */
@Service
@Transactional
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {


    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private IUserService userService;

    /**
     * 根据手机号查询经销商
     * @param mobile
     * @return
     */
    @Override
    public CompanyDTO getUserCompanyByMobile(String mobile) {
        Company company = companyMapper.selectOne(new QueryWrapper<Company>().eq(Company.LINK_MAN_TEL, mobile));
        if (company == null) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.lambda().eq(User::getMobile, company.getLinkManId()).eq(User::getType, 0);
            User user = userService.getOne(userQueryWrapper);
            return new CompanyDTO(user, company);
        }

        return null;
    }

}