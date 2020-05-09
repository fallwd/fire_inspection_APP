package com.hr.fire.inspection.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片文件操作
 */
public class FileUtilcll {
    public static String MEDIA_ROOT_PATH_SEND = Environment
            .getExternalStorageDirectory().getPath()
            + "/Haiyou"
            + "/checkPic";


    /**
     * 保存Bitmap到本地SD卡上
     *
     * @param bitmap
     * @param picName 图片的名字
     * @throws IOException
     */
    public static File saveBitmap(Bitmap bitmap, String picName) throws IOException {
        File file = new File(MEDIA_ROOT_PATH_SEND, picName);
        if (!file.getParentFile().exists()) { //如果目标文件所在的目录不存在，则创建父目录
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) { //判断目标文件所在的目录是否存在
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
        out.flush();
        out.close();
        return file;
    }
}