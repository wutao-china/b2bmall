package com.legensity.b2bmall.module.meaage.controller;

import com.legensity.b2bmall.module.meaage.service.IMessageService;
import com.legensity.b2bmall.result.ResponseData;
import com.legensity.b2bmall.result.ResponseDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: b2bmall
 * @description: 消息控制类
 * @author: wutao
 * @create: 2020/01/26 21:18
 **/
@Controller
@RequestMapping("/api")
@Api(tags = "消息API接口")
public class MessageController {
    @Autowired
    private IMessageService messageService;

    /**
     * 发送验证码
     * @param identifier
     * @return
     */

    @ApiOperation(value="发送验证码", notes="用户名密码注册", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "identifier", value = "手机号", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", required = true, dataType = "String")
    })
    @GetMapping("/message/verificationCode")
    @ResponseBody
    public ResponseData sendVerificationCode(@RequestParam String identifier){
        String verificationCode = messageService.sendVerificationCode(identifier);
        return ResponseDataUtil.success(verificationCode);
    }
}
