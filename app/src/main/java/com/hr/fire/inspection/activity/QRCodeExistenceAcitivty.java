package com.hr.fire.inspection.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.constant.ConstantInspection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class QRCodeExistenceAcitivty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_qrcode_existence);
        ImageView iv_rc_code = findViewById(R.id.iv_rc_code);
        TextView detailText = findViewById(R.id.detailText);
        //长按后显示的 Item
        final String[] items = new String[] { "保存图片"};
        //图片转成Bitmap数组
        final Bitmap[] bitmap = new Bitmap[1];

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        // 接收二维码数据
        byte buf[] = getIntent().getByteArrayExtra("photo_bmp");
        Bitmap bmap = BitmapFactory.decodeByteArray(buf, 0, buf.length);
        iv_rc_code.setImageBitmap(bmap);
        // 标题处理
        String title = getIntent().getStringExtra(ConstantInspection.CHECK_DIVICE);
        ImageView iv_finish = findViewById(R.id.iv_finish);
        TextView tv_inspection_pro = findViewById(R.id.tv_inspection_pro);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_inspection_pro.setText(new StringBuffer(title).append("二维码"));
        Bundle Intent = getIntent().getExtras();
        String titleValue =  Intent.getString("titleValue");

        detailText.setText("瓶号：“"+ new StringBuffer(titleValue).append("”的二维码， 长按图片保存至pad相册"));



        iv_rc_code.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //弹出的“保存图片”的Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(QRCodeExistenceAcitivty.this);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                if (bmap != null) {
                                    saveImageToGallery(QRCodeExistenceAcitivty.this, bmap);
                                } else {
                                    Toast.makeText(QRCodeExistenceAcitivty.this, "bitmap为空", Toast.LENGTH_SHORT).show();
                                }
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }


    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片 创建文件夹
        File appDir = new File(Environment.getExternalStorageDirectory(), "shy");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        //图片文件名称
        String fileName = "shy_"+System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        String path = file.getAbsolutePath();
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), path, fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        Toast.makeText(context, "数据保存成功", Toast.LENGTH_SHORT).show();
    }

}
