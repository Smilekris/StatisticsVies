package com.smile.monkeyserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Author yamei
 * @Date 2019/7/12
 **/
public class DateUtil {

    public static String getCurrentTimeStr(){
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return dateString;
    }
}
