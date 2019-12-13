package com.smile.monkeyserver.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smile.monkeyserver.enity.MonkeyAccountEnity;

import java.util.Date;

/**
 * @ClassName MonkeySSOGenerator
 * @Author kris
 * @Date 2019/12/12
 **/
public class MonkeySSOGenerator {

    private static final String ISSSUER = "bigbro";
    private static final String MONKEY_ACCOUNT = "account";
    private static final Algorithm ALGORITHM= Algorithm.HMAC256("monkey");
    private static final JWTVerifier JWT_VERIFIER= JWT.require(ALGORITHM).withIssuer(ISSSUER).build();
    public static final Integer LASTTIME = 4;

    public static JWTVerifier getJwtVerifier(){
        return JWT_VERIFIER;
    }

    public static String generateSSO(MonkeyAccountEnity monkeyAccountEnity){
        String monkeySSO = JWT.create()
                //设置签发者
                .withIssuer(ISSSUER)
                //设置过期时间为四个小时
                .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000*LASTTIME))
                //设置用户信息
                .withClaim(MONKEY_ACCOUNT, JSONObject.toJSONString(monkeyAccountEnity))
                .sign(ALGORITHM);

        return monkeySSO;
    }

    public static <T> T decodeSSO(Class<T> clazz,String monkeySSO){
        DecodedJWT decodedJWT = JWT_VERIFIER.verify(monkeySSO);
        Claim monkeyAccountClaim = decodedJWT.getClaim(MONKEY_ACCOUNT);
        String monkeyAccountStr = monkeyAccountClaim.as(String.class);
        T target = JSONObject.parseObject(monkeyAccountStr, clazz);
        return target;
    }
}
