package com.smile.monkeyserver.config.oath2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smile.monkeyserver.enity.MonkeyAccountEnity;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName MonkeyRealm
 * @Author kris
 * @Date 2019/12/11
 **/
public class MonkeyRealm extends AuthorizingRealm {

    private static final Algorithm ALGORITHM= Algorithm.HMAC256("monkey");
    private static final JWTVerifier JWT_VERIFIER= JWT.require(ALGORITHM).withIssuer("bigbro").build();
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        MonkeyAccountEnity account = (MonkeyAccountEnity)principalCollection.getPrimaryPrincipal();
        //查询用户权限（后续可将用户的权限缓存到redis）
        Set<String> permsSet = new HashSet<>();
        permsSet.add("user:all");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String monkeyToken = (String) token.getPrincipal();
        DecodedJWT decodedJWT = JWT_VERIFIER.verify(monkeyToken);
//        decodedJWT.getClaim()
        MonkeyAccountEnity account = new MonkeyAccountEnity();
        account.setAccount(decodedJWT.getClaim("account").as(String.class));
        account.setNickName(decodedJWT.getClaim("nickname").as(String.class));
        account.setId(decodedJWT.getClaim("id").as(Integer.class));
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, monkeyToken, getName());
        return info;
    }
}
