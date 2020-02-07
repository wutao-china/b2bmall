
package com.legensity.b2bmall.module.company.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.legensity.b2bmall.module.company.pojo.CompanyApply;
import com.legensity.b2bmall.module.user.pojo.CompanyDTO;
import com.legensity.b2bmall.module.user.pojo.User;

/**
 * <p>
 * 公司审核表 服务类
 * </p>
 *
 * @author wutao
 * @since 2020-02-06
 */
public interface ICompanyApplyService extends IService<CompanyApply> {

        /**
         * 提交公司认证申请
         * @param companyDTO
         * @return
         */
        Boolean submitCompanyApply(CompanyDTO companyDTO);

        /**
         * 确认公司认证申请
         * @param id
         * @return
         */
        Boolean confirmCompanyApply(Integer id, User user);

        /**
         * 拒绝公司认证申请
         * @param id
         * @return
         */
        Boolean refuseCompanyApply(Integer id, User currentUser, String refuseReason);
}