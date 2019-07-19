package com.smile.monkeyserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Author yamei
 * @Date 2019/7/12
 **/
public class DateUtil {

    private static final String DATEREG = "yyyy-MM-dd HH:mm:ss";

    public static SimpleDateFormat getTimeByThreadLocal(){
        ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat(DATEREG);
            }
        };
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        return simpleDateFormat;
    }

    public static String getCurrentTimeStr(){
        Date date = new Date();
        String dateString = getTimeByThreadLocal().format(date);
        return dateString;
    }
}
