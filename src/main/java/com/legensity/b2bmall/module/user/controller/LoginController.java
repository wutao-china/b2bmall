package com.legensity.b2bmall.module.user.controller;

import com.legensity.b2bmall.module.user.bean.User;
import com.legensity.b2bmall.module.user.service.IUserService;
import com.legensity.b2bmall.result.ResponseDataUtil;
import com.legensity.b2bmall.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @GetMapping("login")
    public String login(){
        return "请登录";
    }

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
    public Object doLogin(String mobile, String password) {
        User user = userService.selectUserWithDetailByMobile(mobile);
        if (user != null && user.getPassword().equals(password)) {
            //根据电话号码和密码加密生成
            String token = JwtUtil.sign(user.getMobile(), user.getPassword());
            log.debug("登录token={}", token);
            return ResponseDataUtil.success(token);
        }
        return ResponseDataUtil.failure("登录失败!");
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
