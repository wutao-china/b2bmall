package com.legensity.b2bmall.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.legensity.b2bmall.module.user.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * JwtUtil:用来进行签名和效验Token
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/12
 */
@Slf4j
@Component
public class JwtUtilRedis {
    /**
     * JWT验证过期时间 EXPIRE_TIME 分钟
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public boolean verify(String token, String username, String secret) {
        try {
            User user = (User)redisUtil.get(token);
            if(user != null && Objects.equals(username, user.getName()) && Objects.equals(user.getPassword(), secret)){
                return true;
            }

        } catch (Exception exception) {
            log.error("JwtUtil登录验证失败!");
        }
        return false;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public String getUsername(String token) {
        try {
            User user = (User)redisUtil.get(token);
            if(user != null){
                user.getName();
            }
            return null;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public User getUserByToken(String token) {
        try {
            return (User)redisUtil.get(token);

        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成token签名EXPIRE_TIME 分钟后过期
     *
     * @param user 用户名(电话号码)
     * @param secret   用户的密码
     * @return 加密的token
     */
    public String sign(User user, String secret) {
        UUID uuid = UUID.randomUUID();
        redisUtil.set(uuid.toString(), user, EXPIRE_TIME);
        // 附带username信息
        return uuid.toString();

    }

    public static void main(String[] args) {
//        /**
//         * 测试生成一个token
//         */
//        String sign = sign("18888888888", "123456");
//       log.warn("测试生成一个token\n"+sign);
    }
}
