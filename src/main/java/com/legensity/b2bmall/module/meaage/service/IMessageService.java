package com.legensity.b2bmall.module.meaage.service;

/**
 * @program: b2bmall
 * @description:
 * @author: wutao
 * @create: 2020/01/26 21:34
 **/
public interface IMessageService {
    String sendVerificationCode(String identifier);
}
