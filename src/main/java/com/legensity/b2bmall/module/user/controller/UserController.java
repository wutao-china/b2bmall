package com.legensity.b2bmall.module.user.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.legensity.b2bmall.common.BaseController;
import com.legensity.b2bmall.enums.ErrorCode;
import com.legensity.b2bmall.module.meaage.service.IMessageService;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.pojo.UserCompanyDTO;
import com.legensity.b2bmall.module.user.service.IUserService;
import com.legensity.b2bmall.result.ResponseData;
import com.legensity.b2bmall.result.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author wutao
 * @date 2020-01-23 21:36
 */
@Api(tags = "用户API接口")
@RestController
@RequestMapping("/api")
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;

    @ApiOperation(value = "根据手机号查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", required = true)
    })
    @RequestMapping(value = "/user/getUserByMobile", method = RequestMethod.GET)
    public ResponseData getUserByMobile(@RequestParam String mobile) {
        User user = getCurrentUser();

        UserCompanyDTO userCompanyDTO = userService.selectUserWithDetailByMobile(mobile);
        return ResponseDataUtil.success(userCompanyDTO);
    }

    @ApiOperation(value = "短信验证码修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "verificationCode", value = "验证码", paramType = "query", required = true),
    })
    @RequestMapping(value = "/user/updatePassWord", method = RequestMethod.GET)
    public ResponseData updatePassWord(@RequestParam String mobile,
                                       @RequestParam String password,
                                       @RequestParam String verificationCode) {
        // 检查手机号
        if (!userService.checkMobileExist(mobile)) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_MOBILE_UNREGISTERED);
        }

        // 验证码
        if (StringUtils.isEmpty(verificationCode) ||
                !messageService.verifyCode(mobile, verificationCode)) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_VERIFICATIONCODE_ERROR);
        }

        userService.update(
                new UpdateWrapper<User>()
                        .set(User.PASSWORD, password)
                        .eq(User.MOBILE, mobile)
        );

        return ResponseDataUtil.success();
    }
}
