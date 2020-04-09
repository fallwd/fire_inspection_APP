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
import com.hr.fire.inspection.adapter.RoutingInspectionAdapter;

import java.util.ArrayList;

public class RoutingInspectionActivity extends AppCompatActivity {

    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing_inspection);

        String str_con = "灭火器";
        String str_con2 = "气体灭火系统";
        String str_con3 = "防火风闸";
        String str_con4 = "玉淋闸";
        String str_con5 = "消防软管站";
        String str_con6 = "消防水龙带";
        String str_con7 = "活期探头及火灾盘";
        String str_con8 = "火气监督系统";
        String str_con9 = "厨房湿粉灭火系统";


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
        RoutingInspectionAdapter routingInspectionAdapter = new RoutingInspectionAdapter(this);
        routingInspectionAdapter.setData(mList);
        main_list.setAdapter(routingInspectionAdapter);

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
                Intent intent = new Intent(RoutingInspectionActivity.this,CarbonDioxideAcitivty.class);
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