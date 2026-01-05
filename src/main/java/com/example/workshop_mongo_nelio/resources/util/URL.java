package com.example.workshop_mongo_nelio.resources.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
    public static String decodeParam(String s){
        try{
            return java.net.URLDecoder.decode(s, "UTF-8");
        }
        catch (Exception e){
            return "";
        }
    }

    public static Date convertDate(String textDate, Date defaultDate){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

            return sdf.parse(textDate);
        }
        catch (Exception e){
            return defaultDate;
        }
    }
}
