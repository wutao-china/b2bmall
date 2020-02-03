
package com.legensity.b2bmall.module.company.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.legensity.b2bmall.module.company.dao.CompanyMapper;
import com.legensity.b2bmall.module.company.pojo.Company;
import com.legensity.b2bmall.module.company.service.ICompanyService;
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


}