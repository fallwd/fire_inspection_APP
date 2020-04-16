package com.hr.fire.inspection.utils;

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
     * 获取系统当前时间:
     *
     * @return :    Thu Mar 23 10:32:17 GMT+08:00 2017
     */
    public static Date getCurDate() {
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return curDate;
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
     * 获取系统当前时间,String可以用SubString截取根据业务需求判断事情
     *
     * @return : 2017/03/23
     */
    public static String getCurData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strMillis = format.format(date);
        return strMillis;
    }

    //将data 转  时间2019-07-23
    public Date hhmmssTodata(String srt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
     * 格式字符串日期，去掉秒
     *
     * @param str_time
     * @return
     */
    public static String cutSec(String str_time) {
        String s_date = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = formatter.parse(str_time);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 格式化字符串时间类型
     *
     * @param time
     * @return
     */
    public static Date getStrByDate(String time) {
        Date parse = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            parse = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }


    /**
     * 将误差转变为d日h时m分s秒格式
     *
     * @param lTime
     * @return
     */
    public static String setLtime(long lTime) {
        long mTime = Math.abs(lTime);
        String str;
        long d = mTime / (1000 * 60 * 60 * 24);
        long h = mTime % (1000 * 60 * 60 * 24) / (1000 * 60 * 60);
        long m = mTime % (1000 * 60 * 60 * 24) % (1000 * 60 * 60) / (1000 * 60);
        long s = mTime % (1000 * 60 * 60 * 24) % (1000 * 60 * 60) % (1000 * 60) / 1000;
        if (d != 0) {
            if (lTime > 0) {
                str = String.format("  快%d日%d时%02d分%02d秒", d, h, m, s);
            } else {
                str = String.format("  慢%d日%d时%02d分%02d秒", d, h, m, s);
            }
        } else if (h != 0) {
            if (lTime > 0) {
                str = String.format("  快%d时%02d分%02d秒", h, m, s);
            } else {
                str = String.format("  慢%d时%02d分%02d秒", h, m, s);
            }
        } else if (h == 0 && m != 0) {
            if (lTime > 0) {
                str = String.format("  快%02d分%02d秒", m, s);
            } else {
                str = String.format("  慢%02d分%02d秒", m, s);
            }
        } else if (h == 0 && m == 0 && s != 0) {
            if (lTime > 0) {
                str = String.format("  快%02d秒", s);
            } else {
                str = String.format("  慢%02d秒", s);
            }
        } else {
            str = "  (0\")";
        }
        return str;
    }

}
