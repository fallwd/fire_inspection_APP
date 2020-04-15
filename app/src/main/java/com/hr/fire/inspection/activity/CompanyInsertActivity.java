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

public class CompanyInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_insert);

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        /*
         * @desc 按钮确认事件
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String companyValue = "";
                String oilName= "";
                String platformName = "";

                EditText companyNameText = (EditText) findViewById(R.id.company_name);
                companyNameText.clearFocus();
                companyValue = companyNameText.getText().toString();


                if (companyValue.isEmpty()) {
                    Toast.makeText(CompanyInsertActivity.this, "请将表单信息填写完整", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    long ret = ServiceFactory.getCompanyInfoService().addData(companyValue,oilName,platformName);

                    if(ret==0){
                        Toast.makeText(CompanyInsertActivity.this, "添加公司成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CompanyInsertActivity.this, ChooseCompanyActivity.class);
                        startActivity(intent);
                    }else if (ret==1){
                        Toast.makeText(CompanyInsertActivity.this, "该名称已存在", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CompanyInsertActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CompanyInsertActivity.this, ChooseCompanyActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void cancelInput(View Button) {
        Toast.makeText(CompanyInsertActivity.this, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CompanyInsertActivity.this, ChooseCompanyActivity.class);
        startActivity(intent);
    }
}
