
package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;

/* 系统位号  保护区域*/
public class SystemTagProtectionAreaActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private long sys_id;
    private long platform_id;
    private String f_title;
    private EditText systemTagText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_tag);
        Intent intent = getIntent();
        //CarbonDioxideAcitivty
        sys_id = intent.getLongExtra("sys_id", 0); //系统ID
        platform_id = intent.getLongExtra("platform_id", 0);  //平台ID
        f_title = intent.getStringExtra("f_title");  //传过来的系统名称

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        /*
         * @desc 按钮确认事件
         * */
        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String systemTagValue = "";
                String protectAreaValue = "";
                systemTagText = (EditText) findViewById(R.id.system_tag);
                EditText protectAreaText = (EditText) findViewById(R.id.protect_area);
                systemTagText.clearFocus();
                protectAreaText.clearFocus();
                systemTagValue = systemTagText.getText().toString();
                protectAreaValue = protectAreaText.getText().toString();

//                if (systemTagValue.isEmpty() && protectAreaValue.isEmpty()) {
//                    Toast.makeText(SystemTagProtectionAreaActivity.this, "请将表单信息填写完整", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                Intent intent = new Intent();
                intent.putExtra("sys_id", sys_id);
                intent.putExtra("platform_id", platform_id);
                intent.putExtra("f_title", f_title);
                intent.putExtra("sys_number", systemTagText.getText().toString());
                intent.putExtra("protect_area", protectAreaText.getText().toString());
                intent.setClass(SystemTagProtectionAreaActivity.this, CarbondioxideRecordAcitivty.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void cancelInput(View Button) {
        finish();
    }
}
