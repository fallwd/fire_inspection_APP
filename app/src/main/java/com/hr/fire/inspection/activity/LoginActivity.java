package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login_btn = (Button) this.findViewById(R.id.btnLogin);
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usernameStr = "";
                String pwdStr = "";
                EditText usernameText =(EditText)findViewById(R.id.userName);
                EditText pwdText = (EditText)findViewById(R.id.pwd);
                usernameStr = usernameText.getText().toString();
                pwdStr = pwdText.getText().toString();

                if(usernameStr.length() != 0 && pwdStr.length() != 0){
//                  Toast toast = Toast.makeText(LoginActivity.this,pwdStr,Toast.LENGTH_SHORT );
//                  toast.setGravity(Gravity.CENTER, 0, 0);
//                  toast.show();
//                   Toast.makeText(LoginActivity.this,usernameText.getText().toString().getClass().toString()+":"+usernameText.getText().toString(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}