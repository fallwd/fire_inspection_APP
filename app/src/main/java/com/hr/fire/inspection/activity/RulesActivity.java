package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.LawItemAdapter;

import java.util.ArrayList;


public class RulesActivity extends AppCompatActivity {

    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);


        TextView tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        String text = "法律法规";
        tvInspectionPro.setText(text);


        String str_con1 = "行业标准";
        String str_con2 = "企业标准";
        String str_con3 = "国家法律";
        String str_con4 = "国际标准";
        String str_con5 = "行政规范";
        String str_con6 = "行业规章";
        String str_con7 = "国家标准";
        String str_con8 = "良好作业实践及指导性文件";
        String str_con9 = "监管性机构规范性文件";
        String str_con10 = "其他";

        mList.add(str_con1);
        mList.add(str_con2);
        mList.add(str_con3);
        mList.add(str_con4);
        mList.add(str_con5);
        mList.add(str_con6);
        mList.add(str_con7);
        mList.add(str_con8);
        mList.add(str_con9);
        mList.add(str_con10);
        //列表
        ListView main_list = findViewById(R.id.main_list);
        LawItemAdapter lawItemAdapter = new LawItemAdapter(this);
        lawItemAdapter.setData(mList);
        main_list.setAdapter(lawItemAdapter);

        //点击跳转详情页
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = mList.get(position);
                //跳转详情
                Bundle bundle = new Bundle();
                bundle.putString("id",String.valueOf(id));
                bundle.putString("context",str);
                Intent intent = new Intent(RulesActivity.this, RuleContentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // 点击返回上一页
        ImageView iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(v -> finish());

    }
}
