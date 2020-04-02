package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;


public class NavigationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        ImageView btn1 = (ImageView) findViewById(R.id.ImageView1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((ImageView) v).setImageDrawable(getResources().getDrawable(R.mipmap.navigation1_click));
                Intent intent = new Intent(NavigationActivity.this, InspectionActivity.class);
                startActivity(intent);
            }
        });

        ImageView btn2 = (ImageView) findViewById(R.id.ImageView2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((ImageView) v).setImageDrawable(getResources().getDrawable(R.mipmap.navigation2_click));
                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView btn3 = (ImageView) findViewById(R.id.ImageView3);
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((ImageView) v).setImageDrawable(getResources().getDrawable(R.mipmap.navigation3_click));
                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView btn4 = (ImageView) findViewById(R.id.ImageView4);
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((ImageView) v).setImageDrawable(getResources().getDrawable(R.mipmap.navigation4_click));
                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
