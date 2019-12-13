package com.smile.monkeyserver.service;

import com.smile.monkeyserver.enity.MonkeyAccountEnity;

public interface LoginService {

    public Integer register(MonkeyAccountEnity monkeyAccountEnity);

    public MonkeyAccountEnity login(MonkeyAccountEnity monkeyAccountEnity);
}
