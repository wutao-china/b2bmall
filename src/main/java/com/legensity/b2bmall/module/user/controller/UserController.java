package com.legensity.b2bmall.module.user.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.legensity.b2bmall.common.BaseController;
import com.legensity.b2bmall.enums.ErrorCode;
import com.legensity.b2bmall.module.meaage.service.IMessageService;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.pojo.UserPasswordDTO;
import com.legensity.b2bmall.module.user.service.IUserService;
import com.legensity.b2bmall.result.ResponseData;
import com.legensity.b2bmall.result.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wutao
 * @date 2020-01-23 21:36
 */
@Api(tags = "用户API接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;

    @ApiOperation(value = "短信验证码修改密码")
    @PostMapping(value = "/updateUserPassWord")
    public ResponseData updateUserPassWord(@Valid @RequestBody UserPasswordDTO passwordDTO) {
        // 检查手机号
        if (!userService.checkMobileExist(passwordDTO.getMobile(), passwordDTO.getType())) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_MOBILE_UNREGISTERED);
        }

        // 验证码
        if (StringUtils.isEmpty(passwordDTO.getVerificationCode()) ||
                !messageService.verifyCode(passwordDTO.getMobile(), passwordDTO.getVerificationCode())) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_VERIFICATIONCODE_ERROR);
        }

        userService.update(
                new UpdateWrapper<User>()
                        .set(User.PASSWORD, passwordDTO.getPassword())
                        .eq(User.MOBILE, passwordDTO.getMobile())
        );

        return ResponseDataUtil.success();
    }
}
