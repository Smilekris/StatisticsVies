package com.smile.monkeyserver.service;

/**
 * @ClassName MockAccluateService
 * @Author kris
 * @Date 2019/8/14
 **/
public interface MockAccluateService {

    public int decreaseAmount(Integer userId,Integer amount);

    public void deductMoney(Integer userId,Integer amount);
}
