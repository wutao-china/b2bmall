package com.legensity.b2bmall.module.user.controller;

import com.legensity.b2bmall.enums.ErrorCode;
import com.legensity.b2bmall.module.user.bean.User;
import com.legensity.b2bmall.module.user.bean.UserCompany;
import com.legensity.b2bmall.module.user.service.IUserService;
import com.legensity.b2bmall.module.user.vo.UserLoginVO;
import com.legensity.b2bmall.result.ResponseData;
import com.legensity.b2bmall.result.ResponseDataUtil;
import com.legensity.b2bmall.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 登录
     * @param mobile
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value="登录", notes="用户名密码登录", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "String")
    })
    public ResponseData login(String mobile, String password) {
        User user = userService.selectUserWithDetailByMobile(mobile);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_USER_PASSWORD_ERROR);
        }
        //根据电话号码和密码加密生成
        String token = JwtUtil.sign(user.getMobile(), user.getPassword());
        log.debug("登录token={}", token);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("token", token);
        dataMap.put("user", user);
        return ResponseDataUtil.success(dataMap);
    }

    @PostMapping("/register")
    @ApiOperation(value="注册", notes="用户名密码注册", produces="application/json")
    public ResponseData<User> register(@RequestBody UserLoginVO userLoginVO) {
        if (userLoginVO == null) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_PARAM_MISS);
        }

        // 检查手机号
        if (userService.checkMobileExist(userLoginVO.getMobile())) {
            return ResponseDataUtil.failure(ErrorCode.INTERFACE_MOBILE_REGISTERED);
        }

        UserCompany userCompany = new UserCompany();
        userCompany.setName(userLoginVO.getOrgName());
        BeanUtils.copyProperties(userLoginVO, userCompany);

        User user = new User();
        BeanUtils.copyProperties(userLoginVO, user);

        user.setUserCompany(userCompany);

        User register = userService.register(user);

        return ResponseDataUtil.success(register);
    }
}
