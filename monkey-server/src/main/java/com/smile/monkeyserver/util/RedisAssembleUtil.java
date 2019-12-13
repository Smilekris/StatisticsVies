package com.smile.monkeyserver.util;

/**
 * @ClassName RedisAssembleUtil
 * @Author kris
 * @Date 2019/12/13
 **/
public class RedisAssembleUtil {
    private static final String MONKEY_KING_REDIS = "monkey_account_";

    public static String assembleRedisAccount(String account){
        return MONKEY_KING_REDIS + account;
    }
}
