package com.hr.fire.inspection.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CheckItemAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.SystemList;
import com.hr.fire.inspection.service.ServiceFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//巡检设备列表
public class CheckActivity extends AppCompatActivity {
    private long platform_id;
    private String company_name;
    private String oil_name;
    private String Platform_name;
    private List<CheckType> systemNameData;
    private ArrayList<SystemList> listSys = new ArrayList<>();
    private String duty;
    private String check_name;
    private String check_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        //上一个页面传入的平台ID, 公司名，平台名
        platform_id = (long) getIntent().getLongExtra("Platform_ID", 0);
        Bundle b = getIntent().getExtras();
        // 获取Bundle的信息

        // 获得公司名称  油田名称  以及从巡检页填写的 检查人 检查专业 检查日期
        oil_name = b.getString("oil_name");
        Platform_name = b.getString("Platform_name");
        company_name = b.getString("company_name");
        //  需要时打开
        //  f_title = b.getString("f_title");
        duty = b.getString("duty");
        check_name = b.getString("check_name");
        check_date = b.getString("check_date");
        initData();

    }
    private void initData() {
        systemNameData = ServiceFactory.getInspectionService().getSystemNameData();
    }

    Intent intent = new Intent();

    @Override
    protected void onStart() {
        super.onStart();
        // 标题
        TextView tv_inspection_pro = findViewById(R.id.tv_inspection_pro);
        tv_inspection_pro.setText("消防巡检");
        //列表
        ListView main_list = findViewById(R.id.main_list);
        CheckItemAdapter fireItemAdapter = new CheckItemAdapter(this);
        fireItemAdapter.setData(systemNameData);
        main_list.setAdapter(fireItemAdapter);

//点击跳转详情页
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long sys_id = systemNameData.get(position).getId();
                String str_title = systemNameData.get(position).getName();
                intent.setClass(CheckActivity.this, FilesCheckActivity.class);

//                String f_title = systemNameData.get(position).getName();
//                //跳转详情
//                if (f_title.equals("高压二氧化碳灭火系统") || f_title.equals("七氟丙烷气体灭火系统")
//                        || f_title.equals("海水雨淋灭火系统") || f_title.equals("干粉灭火系统")
//                        || f_title.equals("泡沫灭火系统")) {
//                    intent.setClass(CheckActivity.this, FilesCheckActivity.class);
//                } else {
//                    intent.putExtra("sys_number", ""); //改页面是没有这个参数的
//                    intent.setClass(CheckActivity.this, FilesCheckActivity.class);
//
//                }

                intent.putExtra("sys_id", sys_id);
                intent.putExtra("platform_id", platform_id);
                intent.putExtra("str_title", str_title);
                intent.putExtra("duty",duty);
                intent.putExtra("check_name", check_name);
                intent.putExtra("check_date", check_date);

                Log.d("dong", "sys_id-----" + sys_id+ "platform_id-------"+platform_id+ "str_title--------"+str_title + "duty" + duty+ "check_name" + check_name+"check_date"+check_date);
                startActivity(intent);
                //这个时候,需要关闭当前页面,并且关闭之前所有选择的页面
                finish();
            }
        });



        // 点击跳转报告页
        Button pr_but = findViewById(R.id.product_report_button);
        pr_but.setOnClickListener(v -> {
            for (int i = 0; i < systemNameData.size(); i++) {
                listSys.add(new SystemList(systemNameData.get(i).getId(), systemNameData.get(i).getName()));
            }
            // 点击跳转
            Intent intent = new Intent(CheckActivity.this, CheckReport.class);
            intent.putExtra("list", (Serializable) listSys);
            intent.putExtra("company_name", company_name);
            intent.putExtra("oil_name", oil_name);
            intent.putExtra("Platform_name", Platform_name);
            intent.putExtra("platform_id", platform_id);
            // 页面传值
            startActivity(intent);
        });

        // 点击返回上一页
        ImageView iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(v -> finish());
    }

}