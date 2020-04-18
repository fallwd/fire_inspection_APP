package com.hr.fire.inspection.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private static TimeUtil timeUtil;

    public static TimeUtil getInstance() {
        if (timeUtil == null) {
            timeUtil = new TimeUtil();
        }
        return timeUtil;
    }

    /**
     * 获取系统当前时间:毫秒为单位
     *
     * @return
     */
    public long getCurMillis() {
        long millis = System.currentTimeMillis();
        return millis;
    }

    /**
     * 获取系统当前时间:
     *
     * @return :    Thu Mar 23 10:32:17 GMT+08:00 2017
     */
    public static Date getCurDate() {
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return curDate;
    }

    /**
     * 获取当前时间, 返回data格式日期,不带秒
     *
     * @return Data格式, 不带秒Sat Apr 18 05:22:00 GMT 2020   ,  后面的05:22:00. ,00表示不带秒
     */
    public static Date getCurDateHHmm() {
        Date date = null;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String s = sdf.format(new Date());
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 传入一个毫秒时间.将毫秒时间转换为 2017/03/22格式
     *
     * @return : 2017/03/22
     */
    public String getCurData(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(millis);//获取当前时间
        String strMillis = formatter.format(date);
        return strMillis;
    }

    // 2019-07-23  >  转  data
    public String dataToHHmmss(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strMillis = format.format(date);
        return strMillis;
    }

    //将data 转  时间2019-07-23
    public Date hhmmssTodata(String srt) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date strMillis = null;
        try {
            strMillis = simpleDateFormat.parse(srt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strMillis;
    }

    /**
     * 传入一个时间(毫秒单位),
     *
     * @param millis
     * @return 获取当前时间与传入时间,   相差的天数
     */
    public int getIntervalDada(long millis) {
        long curMillis = System.currentTimeMillis();
        long betweenTime = curMillis - millis;
        int days = (int) (betweenTime / 1000 / 60 / 60 / 24);
        return days;
    }


    /**
     * 获取当前时分
     *
     * @return
     */

    public static String getMinsec() {
        String minsec = "";
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        minsec = sdf2.format(new Date());
        return minsec;
    }

    /**
     * 字符串日期只获取时分秒
     *
     * @return
     */
    public static String getHourMins(String str_time) {
        String s_date = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = formatter.parse(str_time);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            s_date = format.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return s_date;
    }


    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
