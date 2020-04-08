package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.FireItemAdapter;

import java.util.ArrayList;

public class FireActivity extends AppCompatActivity {

    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        String str_con = "二氧化碳灭火系统";
        String str_con2 = "灭火器";
        String str_con3 = "火灾自动报警系统";
        String str_con4 = "海水灭火系统";
        String str_con5 = "厨房湿粉灭火系统";
        String str_con6 = "消防灭火系统";
        String str_con7 = "干粉灭火系统";
        String str_con8 = "七氟丙烷气体灭火系统";
        String str_con9 = "泡沫灭火系统";


        mList = new ArrayList<>();
        mList.add(str_con);
        mList.add(str_con2);
        mList.add(str_con3);
        mList.add(str_con4);
        mList.add(str_con5);
        mList.add(str_con6);
        mList.add(str_con7);
        mList.add(str_con8);
        mList.add(str_con9);

        //列表
        ListView main_list = findViewById(R.id.main_list);
        FireItemAdapter fireItemAdapter = new FireItemAdapter(this);
        fireItemAdapter.setData(mList);
        main_list.setAdapter(fireItemAdapter);

        //点击跳转详情页
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = mList.get(position);
                TextView F_title = findViewById(R.id.account_pwd);
                //跳转详情
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(id));
                bundle.putString("context", str);
                bundle.putString("f_title", (String) F_title.getText());
                // 执行跳转
                Intent intent = new Intent(FireActivity.this, SystemTagProtectionAreaActivity.class);
                // 页面传值
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // 点击生成报告
        Button pr_but = findViewById(R.id.product_report_button);
        pr_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(),"敬请期待", Toast.LENGTH_LONG).show();
            }
        });

        // 点击返回上一页
        ImageButton imageButton = (ImageButton) findViewById(R.id.backHome);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
