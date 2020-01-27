package com.legensity.b2bmall.module.meaage.service.impl;

import com.legensity.b2bmall.message.MessageManager;
import com.legensity.b2bmall.module.meaage.service.IMessageService;
import com.legensity.b2bmall.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @program: b2bmall
 * @description: 消息业务类
 * @author: wutao
 * @create: 2020/01/26 21:31
 **/
@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    private MessageManager messageManager;

    @Override
    public String sendVerificationCode(String identifier) {
        if (StringUtils.isEmpty(identifier)) {
            return null;
        }
        String code = IdUtil.getUniqueDigit(4);
        if (identifier.contains("@")) {

        } else {
            messageManager.sendVerificationCodeByMobile(identifier, code, 1000, 1);
        }

        return code;
    }

    @Override
    public Boolean verifyCode(String identifier, String verificationCode){
        return messageManager.verifyCode(identifier, verificationCode);
    }
}
