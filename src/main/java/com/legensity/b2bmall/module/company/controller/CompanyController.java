package com.legensity.b2bmall.module.company.controller;


import com.legensity.b2bmall.common.BaseController;
import com.legensity.b2bmall.module.company.service.ICompanyService;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.result.ResponseData;
import com.legensity.b2bmall.result.ResponseDataUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 公司 前端控制器
 * </p>
 *
 * @author wutao
 * @since 2020-02-03
 */
@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Autowired
    private ICompanyService companyService;

    @ApiOperation(value = "根据手机号查询经销商")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号（不传查当前用户的）", paramType = "query", required = false),
            @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", required = true)
    })
    @RequestMapping(value = "/getCompanyByMobile", method = RequestMethod.GET)
    public ResponseData getCompanyByMobile(@RequestParam(required = false) String mobile) {
        User user = getCurrentUser();
        if (StringUtils.isEmpty(mobile)) {
            mobile = user.getMobile();
        }
        return ResponseDataUtil.success(companyService.getUserCompanyByMobile(mobile));
    }
}

