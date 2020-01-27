package com.legensity.b2bmall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: b2bmall
 * @description: 短信配置
 * @author: wutao
 * @create: 2020/01/26 20:55
 **/
@Configuration
public class SMSConfig {
    @Value("${message.serviceURL}")
    public String serviceURL;

    @Value("${message.serviceSID}")
    public String serviceSID;

    @Value("${message.serviceToken}")
    public String serviceToken;

    @Value("${message.serviceAppID}")
    public String serviceAppID;

    @Value("${message.smsVerifyCodeTemplate}")
    public String smsVerifyCodeTemplate;
}
