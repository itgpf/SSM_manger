package com.itheima.utils;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: ssm_authority
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-06-10 09:12
 */
public class DateUtils {
    //日期转换成字符串
    public static String dateToString(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format=sdf.format(date);
        return format;
    }
    //字符串转换成日期
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }
}