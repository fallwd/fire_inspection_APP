package com.hr.fire.inspection.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Button;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.FireItemAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.SystemList;
import com.hr.fire.inspection.service.ServiceFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//年检中: 设备列表
public class FireActivity extends AppCompatActivity {
    private long platform_id;
    private String company_name;
    private String oil_name;
    private String Platform_name;
    private List<CheckType> systemNameData;
    private ArrayList<SystemList> listSys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        //上一个页面传入的平台ID, 公司名，平台名
        platform_id = (long) getIntent().getLongExtra("Platform_ID", 0);
        Bundle b = getIntent().getExtras();
        // 获取Bundle的信息
        // 获得公司名称  油田名称
        oil_name = b.getString("oil_name");
        Platform_name = b.getString("Platform_name");
        company_name = b.getString("company_name");

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);
        initData();
    }
    private void initData() {
        systemNameData = ServiceFactory.getYearCheckService().getSystemNameData();
//        Log.d("dong", "s==== " + systemNameData.size() + "   " + systemNameData.toString());
    }

    Intent intent = new Intent();

    @Override
    protected void onStart() {
        super.onStart();
        //列表
        ListView main_list = findViewById(R.id.main_list);
        FireItemAdapter fireItemAdapter = new FireItemAdapter(this);
        fireItemAdapter.setData(systemNameData);
        main_list.setAdapter(fireItemAdapter);

        Button importData = findViewById(R.id.importData);

        importData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FireActivity.this, importDataActicity.class);
                startActivity(intent);
            }
        });

        //点击跳转详情页
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long sys_id = systemNameData.get(position).getId();
                String str_title = systemNameData.get(position).getName();
                intent.setClass(FireActivity.this, SystemTagProtectionAreaActivity.class);
                String f_title = systemNameData.get(position).getName();
                //跳转详情
                if (f_title.equals("高压二氧化碳灭火系统") || f_title.equals("七氟丙烷灭火系统")
                        || f_title.equals("海水雨淋灭火系统") || f_title.equals("固定式干粉灭火系统")
                        || f_title.equals("泡沫灭火系统")) {
                    intent.setClass(FireActivity.this, SystemTagProtectionAreaActivity.class);
                } else {
                    intent.putExtra("sys_number", ""); //改页面是没有这个参数的
                    intent.setClass(FireActivity.this, CarbondioxideRecordAcitivty.class);

                }
                intent.putExtra("sys_id", sys_id);
                intent.putExtra("platform_id", platform_id);
                intent.putExtra("f_title", f_title);
                Log.d("dong", "sys_id-----" + sys_id+ "platform_id-------"+platform_id+ "f_title--------"+f_title);
                startActivity(intent);
                //这个时候,需要关闭当前页面,并且关闭之前所有选择的页面
                finish();
            }
        });

        // 点击跳转报告页
        Button pr_but = findViewById(R.id.product_report_button);
        pr_but.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                for (int i = 0; i < systemNameData.size(); i++) {
                    listSys.add(new SystemList(systemNameData.get(i).getId(), systemNameData.get(i).getName()));
                }
                // 点击跳转
                Intent intent = new Intent(FireActivity.this, FireReportActivity.class);
                intent.putExtra("list", (Serializable) listSys);
                intent.putExtra("company_name", company_name);
                intent.putExtra("oil_name", oil_name);
                intent.putExtra("Platform_name", Platform_name);
                intent.putExtra("platform_id", platform_id);
                // 页面传值
                startActivity(intent);
            }
        });

        // 点击返回上一页
        ImageView iv_finish = (ImageView) findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}