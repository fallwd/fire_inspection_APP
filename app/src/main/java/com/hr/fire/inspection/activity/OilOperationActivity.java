package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.service.ServiceFactory;

public class OilOperationActivity extends AppCompatActivity {
    private String companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_operation);
        // 获得输入框节点信息
        EditText oilNameText = (EditText) findViewById(R.id.oil_name);
        ImageView iv_finish = (ImageView) findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle b = getIntent().getExtras();

        //获取Bundle的信息  公司名称  和平台名称
        final String infocont = b.getString("oil_name");
        companyName = b.getString("company_name");


        // 设置文本内容
        oilNameText.setText(infocont);
        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        /*
         * @desc 按钮确认事件
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String oilValue = "";
                String oldoilName = infocont;
                String type = "oilfield";
                EditText oilNameText = (EditText) findViewById(R.id.oil_name);
                oilNameText.clearFocus();
                oilValue = oilNameText.getText().toString();

                if (oilValue.isEmpty()) {
                    Toast.makeText(OilOperationActivity.this, "请将表单信息填写完整", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
//                    Toast.makeText(OilOperationActivity.this, oldoilName+"-"+oilValue+"-"+type, Toast.LENGTH_SHORT).show();
                    long ret = ServiceFactory.getCompanyInfoService().rename(oldoilName, oilValue, type);

                    if (ret == 0) {
                        Toast.makeText(OilOperationActivity.this, "修改名称成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(OilOperationActivity.this, "修改名称失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void cancelInput(View Button) {
        Toast.makeText(OilOperationActivity.this, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
        finish();
    }
}
