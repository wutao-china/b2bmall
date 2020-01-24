package com.legensity.b2bmall.module.user.web;

import com.legensity.b2bmall.config.SysParamConfig;
import com.legensity.b2bmall.result.ResponseDataUtil;
import com.legensity.b2bmall.module.user.bean.User;
import com.legensity.b2bmall.module.user.service.IUserService;
import com.legensity.b2bmall.util.AESUtil;
import com.legensity.b2bmall.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinbin
 * @date 2017-12-01 21:36
 */
@RestController
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private IUserService userService;
    /**
     * 登录
     */
    @GetMapping("login")
    public String login(){
        return "请登录";
    }

    @PostMapping("/login")
    public Object doLogin(String username, String password) {
        User user = userService.selectUserWithDetailByMobile(username);
        if (user != null && user.getPassword().equals(password)) {
            //根据电话号码和密码加密生成
            String token = JwtUtil.sign(user.getMobile(), user.getPassword());
            System.out.println(token);
            String aes = null;
            try {
                aes = AESUtil.encryptAES(token, SysParamConfig.TOKEN_KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return aes;
        }
        return ResponseDataUtil.failure("登录失败!");
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
