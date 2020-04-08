package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;

import com.hr.fire.inspection.R;

public class RuleContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_content);
        TextView textcont=(TextView)findViewById(R.id.textcont);
        Bundle b=getIntent().getExtras();
        //获取Bundle的信息
        String infocont=b.getString("context");
        String infoid=b.getString("id");
        textcont.setText(infocont);
    }
}
