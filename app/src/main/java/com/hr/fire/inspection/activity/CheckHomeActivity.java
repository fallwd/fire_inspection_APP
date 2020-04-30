package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;
import com.hr.fire.inspection.view.tableview.timeselector.TimeSelector;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CheckHomeActivity extends AppCompatActivity {
    private EditText dutyText;
    private EditText checkNameText;
    private TextView checkDateText;
    private String checkDateStringValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_home);

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);
        LinearLayout ll_time = (LinearLayout) this.findViewById(R.id.ll_time);

        checkDateText = (TextView) findViewById(R.id.check_date);


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

                dutyValue = dutyText.getText().toString();
                checkNameValue = checkNameText.getText().toString();
                if (checkDateStringValue == null) {
                    ToastUtil.show(CheckHomeActivity.this, "请选择巡检时间", Toast.LENGTH_SHORT);
                    return;
                }
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

        ll_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStartTime();
            }
        });
    }

    public void cancelInput(View Button) {
        finish();
    }

    private Date mStartDate;

    void selectStartTime() {
        TimeSelector timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(Date time) {
                mStartDate = time;
                checkDateStringValue = TimeUtil.getInstance().dataToHHmmss(time);
                checkDateText.setText(TimeUtil.getInstance().dataToHHmmss(time));
            }
        }, "1990-01-01 00:00", "2050-12-31 23:59");

        if (mStartDate == null) {
            mStartDate = new Date();
        }
        timeSelector.setMode(TimeSelector.MODE.YMDHM);
        timeSelector.setIsLoop(true);
        timeSelector.show(mStartDate);
    }
}
