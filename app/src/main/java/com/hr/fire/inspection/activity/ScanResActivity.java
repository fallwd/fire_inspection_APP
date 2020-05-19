package com.hr.fire.inspection.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.activity.CaptureActivity;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.utils.TextSpannableUtil;


public class ScanResActivity extends AppCompatActivity {
    private TextView scanres;
    private ImageView iv_finish;

    private TextView tvInspectionPro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanres);
        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        iv_finish = findViewById(R.id.iv_finish);

        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        tvInspectionPro.setText("扫描结果");
        Bundle b = getIntent().getExtras();
        // 获取Bundle的信息
        // 获得公司名称  油田名称
        String scanResult = b.getString("scanResult");
        scanres = (TextView) findViewById(R.id.scanres);
        scanres.setText(scanResult);

        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
