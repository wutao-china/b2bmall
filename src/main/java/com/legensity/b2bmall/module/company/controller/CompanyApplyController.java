package com.legensity.b2bmall.module.company.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.legensity.b2bmall.common.BaseController;
import com.legensity.b2bmall.enums.ErrorCode;
import com.legensity.b2bmall.module.company.pojo.Company;
import com.legensity.b2bmall.module.company.service.ICompanyApplyService;
import com.legensity.b2bmall.module.company.service.ICompanyService;
import com.legensity.b2bmall.module.user.pojo.CompanyDTO;
import com.legensity.b2bmall.result.ResponseData;
import com.legensity.b2bmall.result.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 公司审核 前端控制器
 * </p>
 *
 * @author wutao
 * @since 2020-02-06
 */
@RestController
@RequestMapping("/companyApply")
@Slf4j
@Api(tags = "公司申请审核API接口")
public class CompanyApplyController extends BaseController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ICompanyApplyService companyApplyService;

    @ApiOperation(value = "提交经销商审核申请")
    @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", required = true)
    @PostMapping(value = "/saveCompanyApply")
    public ResponseData<Boolean> saveCompanyApply(@Valid @RequestBody CompanyDTO companyDTO) {
        Company company = companyService.getOne(new QueryWrapper<Company>().eq(Company.ID, companyDTO.getId()));
        if (company == null) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_COMPANY_NOT_EXIT_ERROR);
        }

        // 初始状态和拒绝了才能申请
        if (company.getStatus() != 0 && company.getStatus() != 3) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_COMPANY_STATUS_ERROR);
        }

        return ResponseDataUtil.success(companyApplyService.submitCompanyApply(companyDTO));
    }

    @ApiOperation(value = "审核经销商审核申请")
    @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", required = true)
    @GetMapping(value = "/confirmCompanyApply")
    public ResponseData<Boolean> confirmCompanyApply(Integer id) {
        Company company = companyService.getOne(new QueryWrapper<Company>().eq(Company.ID, id));
        if (company == null) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_COMPANY_NOT_EXIT_ERROR);
        }

        // 审核中才能通过
        if (company.getStatus() != 1) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_COMPANY_STATUS_ERROR);
        }

        return ResponseDataUtil.success(companyApplyService.confirmCompanyApply(id, getCurrentUser()));
    }

    @ApiOperation(value = "拒绝经销商审核申请")
    @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", required = true)
    @GetMapping(value = "/refuseCompanyApply")
    public ResponseData<Boolean> refuseCompanyApply(Integer id, String refuseReason) {
        Company company = companyService.getOne(new QueryWrapper<Company>().eq(Company.ID, id));
        if (company == null) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_COMPANY_NOT_EXIT_ERROR);
        }

        // 审核中才能拒绝
        if (company.getStatus() != 1) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_COMPANY_STATUS_ERROR);
        }

        return ResponseDataUtil.success(companyApplyService.refuseCompanyApply(id, getCurrentUser(), refuseReason));
    }
}

