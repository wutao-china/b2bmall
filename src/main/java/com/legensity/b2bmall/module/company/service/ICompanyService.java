
package com.legensity.b2bmall.module.company.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.legensity.b2bmall.module.company.pojo.Company;
import com.legensity.b2bmall.module.user.pojo.CompanyDTO;

/**
 * <p>
 * 公司 服务类
 * </p>
 *
 * @author wutao
 * @since 2020-02-03
 */
public interface ICompanyService extends IService<Company> {
    /**
     * 通过mobile查找用户公司信息
     */
    CompanyDTO getUserCompanyByMobile(String mobile);
}