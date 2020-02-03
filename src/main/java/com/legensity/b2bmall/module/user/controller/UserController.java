package com.legensity.b2bmall.module.user.controller;

import com.legensity.b2bmall.common.BaseController;
import com.legensity.b2bmall.module.user.pojo.User;
import com.legensity.b2bmall.module.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 用户查询.
     */
    @ApiOperation(value="根据手机号查询用户", notes="根据手机号查询用户", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", required = true, dataType = "String")
    })
    @RequestMapping(value = "/user/userList", method = RequestMethod.GET)
    @ResponseBody
    public User getUserByMobile(String mobile){
        User user = getCurrentUser();

        User userByMobile = userService.selectUserWithDetailByMobile(mobile);
        return userByMobile;
    }
}
