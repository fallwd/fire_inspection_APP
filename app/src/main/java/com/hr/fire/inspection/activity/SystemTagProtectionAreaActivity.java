/* 系统位号  保护区域*/

package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;

public class SystemTagProtectionAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_tag);
        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String  systemTagValue = "";
                String  protectAreaValue = "";
                EditText systemTagText =(EditText)findViewById(R.id.system_tag);
                EditText protectAreaText = (EditText)findViewById(R.id.protect_area);
                systemTagText.clearFocus();
                protectAreaText.clearFocus();
                systemTagValue = systemTagText.getText().toString();
                protectAreaValue = protectAreaText.getText().toString();

                if(systemTagValue.isEmpty() && protectAreaValue.isEmpty()){
                    Toast.makeText(SystemTagProtectionAreaActivity.this,"请将表单信息填写完整",Toast.LENGTH_SHORT).show();
                }else{
                    /*写sql*/
                    Intent intent = new Intent(SystemTagProtectionAreaActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
