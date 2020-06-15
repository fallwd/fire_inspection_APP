package com.hr.fire.inspection.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class FileRoute {
    public static final int CAMERA_RESULT_CODE = 101;//拍照
    private final Context mContext;

    public FileRoute(Context context) {
        mContext = context;
    }

    //巡检中拍照时,统一文件夹的路径,  都必须使用这个方法获取文件路径
    public static File getFilePath() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        long timeMillis = System.currentTimeMillis();
        String sPath = new StringBuilder().append(timeMillis).append(".jpg").toString();
        File outputImage = new File(externalStorageDirectory, sPath);
        return outputImage;
    }

    /**
     * 创建原图像保存的文件
     *
     * @return
     * @throws IOException
     */
    public File createOriImageFile() throws IOException {
        File pictureDirOri = new File(mContext.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/OriPicture");
//        File pictureDirOri = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Haiyou/checkPic");
        if (!pictureDirOri.exists()) {
            pictureDirOri.mkdirs();

        }
        File image = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", pictureDirOri);
//        imgPathOri = image.getAbsolutePath();
        return image;
    }

    /**
     * 创建原图像保存的文件
     *
     * @return
     * @throws IOException
     */
    public File createOriVideoFile() throws IOException {
        File pictureDirOri = new File(mContext.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/OriPicture");
//        File pictureDirOri = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Haiyou/checkPic");
        if (!pictureDirOri.exists()) {
            pictureDirOri.mkdirs();

        }
        File video = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".mp4", pictureDirOri);
//        imgPathOri = image.getAbsolutePath();
        return video;
    }
}
