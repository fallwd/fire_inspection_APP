//package com.hr.fire.inspection.activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import com.hr.fire.inspection.R;
//
//public class PhotoUploadActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photo_upload);
//    }
//}

package com.example.administrator.foundationdemo.getphoto;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.hr.fire.inspection.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class GetPhotoActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;//拍照
    public static final int CHOOSE_PHOTO = 2;//相册获取

    private Button take_photo ;
    private Button chooser_photo;
    private ImageView img_photo;
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(GetPhotoActivity.this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory
                                .decodeStream(getContentResolver().openInputStream(imgUri));
                        img_photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK){
                    if (Build.VERSION.SDK_INT >=19){
                        //4.4以上图片处理
                        handleImageOnKitKat(data);
                    }else {
                        //4.4以下图片处理
                        handleImageBeforeKitKat(data);
                    }
                }
                break;

            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageBeforeKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            //如果是 documents 类型的Uri ，则通过 document id 处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority()) ){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" +id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri , null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是 content 类型的 Uri，则使用普通方式
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            //如果是 file 类型的 Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }

        displayImage(imagePath);

    }

    private void handleImageOnKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (null == imagePath  && "".equals(imagePath)){
            Toast.makeText(GetPhotoActivity.this,"ImafePath is null",Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        img_photo.setImageBitmap(bitmap);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }



    private void initView() {
        setContentView(R.layout.activity_photo_upload);
        take_photo = (Button) findViewById(R.id.photo_take);
        img_photo = (ImageView) findViewById(R.id.photo_img);
        chooser_photo = (Button) findViewById(R.id.photo_chooser);
        //调用摄像头
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    camera();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("FLY","出错了====="+e.toString());
                }
            }
        });
        //调用相册
        chooser_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断有无权限没有则添加
                if (ContextCompat.checkSelfPermission(GetPhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(GetPhotoActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                    openAlbum();
                }
            }
        });


    }

    private void openAlbum() {
        //调取相册
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);

    }

    private void camera() throws IOException {
        //储存拍照图片file
        File outputImage = new File(getExternalCacheDir(),"putput_img.jpg");

        if (outputImage.exists()){
            outputImage.delete();
        }
        outputImage.createNewFile();
        //
        if (Build.VERSION.SDK_INT>=24){
            //7.0以上新增的方法 共享文件 FileProvider是一种特殊的内容提供者
            // 第二个参数为对应filepaths.xml中provider（内容提供者的）的name
            imgUri = FileProvider
                    .getUriForFile(GetPhotoActivity.this,"com.example.cameraalbumtest.fileprovider",outputImage);
        }else {
            imgUri = Uri.fromFile(outputImage);
        }
        //启动相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
        startActivityForResult(intent,TAKE_PHOTO);

    }
}
