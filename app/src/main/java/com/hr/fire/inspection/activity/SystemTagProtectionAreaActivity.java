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
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_tag);

        // 获取btn元素
        Button cancel_btn = (Button) this.findViewById(R.id.cancel_btn);
        Button submit_btn = (Button) this.findViewById(R.id.submit_btn);

       /*
       * @desc 按钮确认事件
       * */
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
                    // 跳转携带参数
//                    intent.putExtra(EXTRA_MESSAGE, message)
                    startActivity(intent);
                }
            }
        });

    }
    public void cancelInput(View Button){
        Toast.makeText(SystemTagProtectionAreaActivity.this,"您点击了取消按钮",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SystemTagProtectionAreaActivity.this,NavigationActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, "我还是曾经那个少年 没有一丝丝改变");
        startActivity(intent);
    }
}
