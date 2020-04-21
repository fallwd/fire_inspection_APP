package com.hr.fire.inspection.utils;

import android.os.Environment;
import java.io.File;

public class FileRoute {
    //巡检中拍照时,统一文件夹的路径,  都必须使用这个方法获取文件路径
    public static File getFilePath() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        long timeMillis = System.currentTimeMillis();
        String sPath = new StringBuilder().append(timeMillis).append(".jpg").toString();
        File outputImage = new File(externalStorageDirectory, sPath);
        return outputImage;
    }
}
