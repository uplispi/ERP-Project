package com.nju.edu.erp.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDateFromString {

    public static Date dateFormat(String str){
        if (str == null)return null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = null;
        try {
            result = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }
}
