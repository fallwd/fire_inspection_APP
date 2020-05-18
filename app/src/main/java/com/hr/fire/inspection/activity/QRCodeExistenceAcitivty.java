package com.hr.fire.inspection.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.constant.ConstantInspection;

public class QRCodeExistenceAcitivty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_qrcode_existence);
        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);
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
    }
}
