package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CheckHomeActivity  extends AppCompatActivity {
    private EditText dutyText;
    private EditText checkNameText;
    private EditText checkDateText;
    Date checkDateValue;
    private String checkDateStringValue;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_home);

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        checkDateText = (EditText) findViewById(R.id.check_date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            checkDateValue = format.parse("2020-04-23");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkDateStringValue = format.format(checkDateValue);
        checkDateText.setText(checkDateStringValue);

        /*
         * @desc 按钮确认事件
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String dutyValue = "";
                String checkNameValue = "";
                // 如果 公司 平台 油田  页面复用年检的  则需要 定义一个这样的状态 然后将所填的参数传过去  然后对应的页面通过这个参数进行获取
                String f_title = "xunjian";

                dutyText = (EditText) findViewById(R.id.duty);
                checkNameText = (EditText) findViewById(R.id.check_name);

                dutyText.clearFocus();
                checkNameText.clearFocus();
                checkDateText.clearFocus();

                dutyValue = dutyText.getText().toString();
                checkNameValue = checkNameText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("f_title", f_title);
                intent.putExtra("duty", dutyValue);
                intent.putExtra("check_name", checkNameValue);
                intent.putExtra("check_date", checkDateStringValue);
                intent.setClass(CheckHomeActivity.this, ChooseCompanyActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void cancelInput(View Button) {
        finish();
    }
}
