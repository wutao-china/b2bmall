
package com.legensity.b2bmall.module.company.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.enums.ErrorCode;
import com.legensity.b2bmall.exception.MyException;
import com.legensity.b2bmall.module.company.dao.CompanyApplyMapper;
import com.legensity.b2bmall.module.company.pojo.Company;
import com.legensity.b2bmall.module.company.pojo.CompanyApply;
import com.legensity.b2bmall.module.company.service.ICompanyApplyService;
import com.legensity.b2bmall.module.company.service.ICompanyService;
import com.legensity.b2bmall.module.user.pojo.CompanyDTO;
import com.legensity.b2bmall.module.user.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 公司审核表 服务实现类
 * </p>
 *
 * @author wutao
 * @since 2020-02-06
 */
@Service

public class CompanyApplyServiceImpl extends ServiceImpl<CompanyApplyMapper, CompanyApply> implements ICompanyApplyService {

    @Autowired
    private CompanyApplyMapper companyApplyMapper;

    @Autowired
    private ICompanyService companyService;

    @Transactional
    @Override
    public Boolean submitCompanyApply(CompanyDTO companyDTO) {
        if (companyDTO == null || companyDTO.getId() == null) {
            return false;
        }
        Company company = new Company();
        BeanUtils.copyProperties(companyDTO, company);
        company.setName(companyDTO.getOrgName());
        company.setStatus(1);
        boolean b = companyService.updateById(company);

        if (b) {
            CompanyApply companyApply = new CompanyApply();
            companyApply.setCompanyId(company.getId());
            companyApply.setApplyTime(new Date());
            companyApply.setStatus(1);
            companyApplyMapper.insert(companyApply);
        }

        return false;
    }

    @Override
    public Boolean confirmCompanyApply(Integer id, User user) {
        QueryWrapper<CompanyApply> wrapper = new QueryWrapper<>();
        wrapper.eq(CompanyApply.COMPANY_ID, id)
                .orderByDesc(CompanyApply.APPLY_TIME)
                .last("limit 1");
        CompanyApply companyApply = companyApplyMapper.selectOne(wrapper);
        if (companyApply == null) {
            return false;
        }

        companyApply.setStatus(2);
        companyApply.setConfirmTime(new Date());
        companyApply.setConfirmerId(user.getId());
        companyApply.setConfirmerName(user.getName());

        int r = companyApplyMapper.updateById(companyApply);

        if (r > 0) {
            Company company = companyService.getById(id);
            if (company != null) {
                company.setStatus(2);
                return companyService.updateById(company);
            }
        }

        return false;
    }

    @Override
    public Boolean refuseCompanyApply(Integer id, User currentUser, String refuseReason) {
        QueryWrapper<CompanyApply> wrapper = new QueryWrapper<>();
        wrapper.eq(CompanyApply.COMPANY_ID, id)
                .orderByDesc(CompanyApply.APPLY_TIME)
                .last("limit 1");
        CompanyApply companyApply = companyApplyMapper.selectOne(wrapper);
        if (companyApply == null) {
            return false;
        }

        companyApply.setStatus(3);
        companyApply.setConfirmTime(new Date());
        companyApply.setConfirmerId(currentUser.getId());
        companyApply.setConfirmerName(currentUser.getName());
        companyApply.setRefuseReason(refuseReason);

        int r = companyApplyMapper.updateById(companyApply);

        if (r > 0) {
            Company company = companyService.getById(id);
            if (company != null) {
                company.setStatus(3);
                return companyService.updateById(company);
            }
        }

        return false;
    }
}