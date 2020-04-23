package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;


public class CheckHomeActivity  extends AppCompatActivity {
    private EditText dutyText;
    private EditText checkNameText;
    private EditText checkDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_home);

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        /*
         * @desc 按钮确认事件
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String dutyValue = "";
                String checkNameValue = "";
                String checkDateValue = "";
                String f_title = "xunjian";

                dutyText = (EditText) findViewById(R.id.duty);
                checkNameText = (EditText) findViewById(R.id.check_name);
                checkDateText = (EditText) findViewById(R.id.check_date);
                dutyText.clearFocus();
                checkNameText.clearFocus();
                checkDateText.clearFocus();

                dutyValue = dutyText.getText().toString();
                checkNameValue = checkNameText.getText().toString();
                checkDateValue = checkDateText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("f_title", f_title);
                intent.putExtra("duty", dutyValue);
                intent.putExtra("check_name", checkNameValue);
                intent.putExtra("check_date", checkDateValue);

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
