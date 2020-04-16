package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.service.ServiceFactory;

public class OilInsertActivity extends AppCompatActivity {
    private String companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_insert);
        Bundle b = getIntent().getExtras();

        companyName = b.getString("company_name");

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);
        ImageView iv_finish = (ImageView) this.findViewById(R.id.iv_finish);
        TextView tv_inspection_pro = (TextView) this.findViewById(R.id.tv_inspection_pro);
        tv_inspection_pro.setText("请添加平台");
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*
         * @desc 按钮确认事件
         *
         *
         *
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String oilValue = "";
                String type = "oilfield";
                EditText oilNameText = (EditText) findViewById(R.id.oil_name);
                oilNameText.clearFocus();
                oilValue = oilNameText.getText().toString();

                if (oilValue.isEmpty()) {
                    Toast.makeText(OilInsertActivity.this, "请将表单信息填写完整", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    long ret = ServiceFactory.getCompanyInfoService().addData(companyName, oilValue, type);

                    if (ret == 0) {
                        Toast.makeText(OilInsertActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OilInsertActivity.this, OilFieldActivity.class);
                        intent.putExtra("company_name", companyName);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(OilInsertActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void cancelInput(View Button) {
        Toast.makeText(OilInsertActivity.this, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
        finish();
    }
}
