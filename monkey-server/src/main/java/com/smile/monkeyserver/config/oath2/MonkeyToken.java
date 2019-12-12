package com.smile.monkeyserver.config.oath2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName MonkeyToken
 * @Author kris
 * @Date 2019/12/11
 **/
public class MonkeyToken implements AuthenticationToken {

    private String token;

    public MonkeyToken (String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
