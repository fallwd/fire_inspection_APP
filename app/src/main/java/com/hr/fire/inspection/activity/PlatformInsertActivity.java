package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.service.ServiceFactory;

public class PlatformInsertActivity  extends AppCompatActivity {
    private String oil_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_insert);

        Bundle b = getIntent().getExtras();
        //获取Bundle的信息  公司名称  和平台名称
        oil_name = b.getString("oil_name");
        final String companyName = b.getString("company_name");

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        /*
         * @desc 按钮确认事件
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String platformName = "";
                EditText platformNameText = (EditText) findViewById(R.id.platform_name);
                platformNameText.clearFocus();
                platformName = platformNameText.getText().toString();

                if (platformName.isEmpty()) {
                    Toast.makeText(PlatformInsertActivity.this, "请将表单信息填写完整", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    long ret = ServiceFactory.getCompanyInfoService().addData(companyName, oil_name, platformName);
                    if(ret==0){
                        Toast.makeText(PlatformInsertActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PlatformInsertActivity.this, PlatformActivity.class);
                        intent.putExtra("oil_name", oil_name);
                        startActivity(intent);
                    } else {
                        Toast.makeText(PlatformInsertActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void cancelInput(View Button) {
        Toast.makeText(PlatformInsertActivity.this, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PlatformInsertActivity.this,PlatformActivity.class);
        intent.putExtra("oil_name", oil_name);
        startActivity(intent);
    }
}
