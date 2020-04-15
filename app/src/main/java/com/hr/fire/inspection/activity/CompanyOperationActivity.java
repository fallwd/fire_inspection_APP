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

public class CompanyOperationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_operation);
        // 获得输入框节点信息
        EditText companyNameText = (EditText) findViewById(R.id.company_name);
        Bundle b = getIntent().getExtras();
        //获取Bundle的信息
        final String infocont = b.getString("company_name");
        // 设置文本内容
        companyNameText.setText(infocont);
        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        /*
         * @desc 按钮确认事件
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String companyValue = "";
                String oldCompanyName = infocont;
                String type = "company";
                EditText companyNameText = (EditText) findViewById(R.id.company_name);
                companyNameText.clearFocus();
                companyValue = companyNameText.getText().toString();

                if (companyValue.isEmpty()) {
                    Toast.makeText(CompanyOperationActivity.this, "请将表单信息填写完整", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    long ret = ServiceFactory.getCompanyInfoService().rename(oldCompanyName,companyValue,type);
                    if(ret==0){
                        Toast.makeText(CompanyOperationActivity.this, "修改名称成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CompanyOperationActivity.this, ChooseCompanyActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(CompanyOperationActivity.this, "修改名称失败", Toast.LENGTH_SHORT).show();
                    }

//                    Toast.makeText(CompanyOperationActivity.this, companyValue, Toast.LENGTH_SHORT).show();
                    /*写sql*/
//                    Intent intent = new Intent(CompanyOperationActivity.this, MainActivity.class);
//                    // 跳转携带参数
////                    intent.putExtra(EXTRA_MESSAGE, message)
//                    startActivity(intent);
                }
            }
        });
    }

    public void cancelInput(View Button) {
        Toast.makeText(CompanyOperationActivity.this, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CompanyOperationActivity.this, ChooseCompanyActivity.class);
        startActivity(intent);
    }
}
