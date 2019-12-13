package com.smile.monkeyserver.service.Impl;

import com.smile.monkeyserver.enity.MonkeyAccountEnity;
import com.smile.monkeyserver.mapper.LoginMapper;
import com.smile.monkeyserver.service.LoginService;
import com.smile.monkeyserver.util.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName MonkeyServiceImpl
 * @Author kris
 * @Date 2019/12/12
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;


    @Override
    public Integer register(MonkeyAccountEnity monkeyAccountEnity) {
        String salt = UUID.randomUUID().toString();
        PasswordEncryptor encoderMd5 = new PasswordEncryptor(salt, "sha-256");
        String pwd = encoderMd5.encode(monkeyAccountEnity.getPassword());
        monkeyAccountEnity.setSalt(salt);
        monkeyAccountEnity.setPassword(pwd);
        int register = loginMapper.register(monkeyAccountEnity);
        return register;
    }

    @Override
    public MonkeyAccountEnity login(MonkeyAccountEnity monkeyAccountEnity) {
        String account = monkeyAccountEnity.getAccount();
        MonkeyAccountEnity monkeyAccount = loginMapper.queryByAccount(account);
        PasswordEncryptor encoderMd5 = new PasswordEncryptor(monkeyAccount.getSalt(), "sha-256");
        if (monkeyAccount.getPassword().equals(encoderMd5.encode(monkeyAccountEnity.getPassword()))){
            return monkeyAccount;
        }else{
            return null;
        }
    }
}
