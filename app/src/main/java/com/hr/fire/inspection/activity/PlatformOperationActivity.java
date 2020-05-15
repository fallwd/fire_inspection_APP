package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.service.ServiceFactory;

public class PlatformOperationActivity extends AppCompatActivity {
    private String oil_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_operation);

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        // 获得输入框节点信息
        EditText platformNameText = (EditText) findViewById(R.id.platform_name);
        ImageView iv_finish = (ImageView) findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle b = getIntent().getExtras();
        //获取Bundle的信息  公司名称  和平台名称
        oil_name = b.getString("oil_name");
        final String companyName = b.getString("company_name");
        final String platformName = b.getString("platform_name");

        // 设置文本内容
        platformNameText.setText(platformName);

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        /*
         * @desc 按钮确认事件
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String platformValue = "";
                String platformNameValue = platformName;
                String type = "platform";
                EditText platformNameText = (EditText) findViewById(R.id.platform_name);
                platformNameText.clearFocus();
                platformNameValue = platformNameText.getText().toString();

                if (platformNameValue.isEmpty()) {
                    Toast.makeText(PlatformOperationActivity.this, "请将表单信息填写完整", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    long ret = ServiceFactory.getCompanyInfoService().rename(platformName, platformNameValue, type);
                    if (ret == 0) {
                        Toast.makeText(PlatformOperationActivity.this, "修改名称成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(PlatformOperationActivity.this, "修改名称失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void cancelInput(View Button) {
        Toast.makeText(PlatformOperationActivity.this, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
        finish();
    }
}
