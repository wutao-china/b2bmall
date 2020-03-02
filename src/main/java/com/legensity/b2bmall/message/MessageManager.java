package com.legensity.b2bmall.message;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.legensity.b2bmall.config.SMSConfig;
import com.legensity.b2bmall.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

/**
 * @program: b2bmall
 * @description: 消息管理
 * @author: wutao
 * @create: 2020/01/26 20:52
 **/
@Component
@Slf4j
public class MessageManager {
    @Autowired
    private SMSConfig smsConfig;

    @Autowired
    private RedisUtil redisUtil;

    public boolean sendVerificationCodeByMobile(String mobileNo, String verificationCode, int timeWait, int type) {
        boolean bRtn = false;

        try {
            String templateId = "";
            CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
            restAPI.init(smsConfig.serviceURL, "8883");
            restAPI.setAccount(smsConfig.serviceSID, smsConfig.serviceToken);

            switch (type) {
                case 1:   // 劳务宝
                    restAPI.setAppId(smsConfig.serviceAppID);
                    templateId = smsConfig.smsVerifyCodeTemplate;
                    break;
                case 2:   // 爱建造
//                    restAPI.setAppId(this.serviceAppID_ajz);
//                    templateId = this.smsVerifyCodeTemplate_ajz;
//                    break;
                default:
                    restAPI.setAppId(smsConfig.serviceAppID);
                    templateId = smsConfig.smsVerifyCodeTemplate;
                    break;
            }

            redisUtil.set(mobileNo, verificationCode, 1000);

            HashMap<String, Object> result = restAPI.sendTemplateSMS(mobileNo, templateId, new String[]{verificationCode, String.valueOf(timeWait)});

            //System.out.println("SDKTestGetSubAccounts result=" + result);
            if ("000000".equals(result.get("statusCode"))) {
                //正常返回输出data包体信息（map）
				/*
				HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
				Set<String> keySet = data.keySet();
				for(String key:keySet){
					Object object = data.get(key);
					System.out.println(key +" = "+object);
				}
				*/

                bRtn = true;

            } else {
                //异常返回输出错误码和错误信息
                log.error("错误码={} 错误信息={}", result.get("statusCode"), result.get("statusMsg"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bRtn;

    }

    public Boolean verifyCode(String identifier, String verificationCode) {
        if (redisUtil.get(identifier) != null && Objects.equals(verificationCode, redisUtil.get(identifier).toString())) {
            return true;
        }
        return false;
    }
}
