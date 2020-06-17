package com.hr.fire.inspection.utils;

import android.content.Context;

/**
 * SharedPreferences的一个工具类
 * 调用put就能保存String, Integer, Boolean, Float, Long类型的参数
 * 同样调用get就能获取到保存在手机里面的数据
 *
 */
public class SharedPreferencesUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String CONFIG_NAME = "hiayou.cfg";
    /**  该文件是私有数据，只能被应用本身访问  */
    private static final int MODE = Context.MODE_PRIVATE;


    /**
     * 获取 String 类型的数据
     * @param context 上下文
     * @param key 键
     * @param defValue 默认值
     */
    public static String getString(Context context , String key, String defValue) {
        return context.getSharedPreferences(CONFIG_NAME, MODE).getString(key, defValue);
    }
    /**
     * 保存 String 类型的数据
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void putString(Context context , String key, String value) {
        context.getSharedPreferences(CONFIG_NAME, MODE).edit().putString(key, value).commit();
    }

}
