package com.legensity.b2bmall.module.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.legensity.b2bmall.enums.ErrorCode;
import com.legensity.b2bmall.module.meaage.service.IMessageService;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.pojo.UserLoginVO;
import com.legensity.b2bmall.module.user.pojo.UserRegisterDTO;
import com.legensity.b2bmall.module.user.service.IUserService;
import com.legensity.b2bmall.result.ResponseData;
import com.legensity.b2bmall.result.ResponseDataUtil;
import com.legensity.b2bmall.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wutao
 * @date 2020-01-23 21:36
 */
@RestController
@Slf4j
@Api(tags = "用户登录API接口")
public class LoginController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;

    @PostMapping("/login")
    @ApiOperation(value="用户名密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true)
    })
    public ResponseData<UserLoginVO> login(@RequestParam(name = "mobile") String mobile, @RequestParam(name = "password") String password) {
        User user = userService.getOne(new QueryWrapper<User>().eq(User.MOBILE, mobile));
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_USER_PASSWORD_ERROR);
        }
        //根据电话号码和密码加密生成
        String token = JwtUtil.sign(user.getMobile(), user.getPassword());
        log.debug("登录token={}", token);
        return ResponseDataUtil.success(new UserLoginVO(user, token));
    }

    @PostMapping("/register")
    @ApiOperation(value="用户名密码注册")
    public ResponseData register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        // 检查手机号
        if (userService.checkMobileExist(userRegisterDTO.getMobile(), userRegisterDTO.getType())) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_MOBILE_REGISTERED);
        }

        // 验证码
        if (StringUtils.isEmpty(userRegisterDTO.getVerificationCode()) ||
                !messageService.verifyCode(userRegisterDTO.getMobile(), userRegisterDTO.getVerificationCode())) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_VERIFICATIONCODE_ERROR);
        }

        if (userService.register(userRegisterDTO) != null) {
            return ResponseDataUtil.success(null);
        }

        return ResponseDataUtil.failure(ErrorCode.INTERFACE_USER_REGISTER_ERROR);
    }
}
