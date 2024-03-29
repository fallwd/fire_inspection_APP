package com.hr.fire.inspection.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.PlatformAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TextSpannableUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlatformActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> list;
    private ArrayList<Long> idlist;
    private List<CompanyInfo> dataList;
    private String oil_name;
    private String company_name;

    private String f_title;
    private String duty;
    private String check_name;
    private String check_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform);
        Bundle b = getIntent().getExtras();
        // 获取Bundle的信息 公司名称  油田名称    若从消防巡检点过来   则需要获取其他参数
        oil_name = b.getString("oil_name");
        company_name = b.getString("company_name");

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        f_title = b.getString("f_title");
        if (f_title.equals("xunjian")) {
            getIntentInfo();
        }

        //后期需要使用接口回掉.关闭页面
        finishImpl();
        ImageView insert_btn = (ImageView) this.findViewById(R.id.insert_btn);
        ImageView iv_finish = (ImageView) this.findViewById(R.id.iv_finish);
        TextView tv_inspection_pro = (TextView) this.findViewById(R.id.tv_inspection_pro);
        dataList = ServiceFactory.getCompanyInfoService().getPlatformList(oil_name);
        Log.i("AAAAAAA", "这是平台的列表数据"+ dataList);



        idlist = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            CompanyInfo CompanyListItem = dataList.get(i);
            String companyName = CompanyListItem.getPlatformName();
            long id = CompanyListItem.getId();
            if (companyName != null && companyName != "") {
                list.add(companyName);
                idlist.add(id);
            }
        }
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (company_name != null && oil_name != null) {
            String srt = new StringBuffer().append("检查所属  >  ").append(company_name).
                    append("  >  ").append(oil_name).append("  >  ").append("请选择平台").toString();
            SpannableString textColor = TextSpannableUtil.showTextColor(srt, "#00A779", srt.length() - 5, srt.length());
            tv_inspection_pro.setText(textColor);
        }

        ListView platform_list_item = findViewById(R.id.platform_list_item);

        PlatformAdapter platformAdapter = new PlatformAdapter(this, this);
        platformAdapter.setData(list);
        platform_list_item.setAdapter(platformAdapter);
        final Intent intent = new Intent();
        // 监听点击事件
        platform_list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long current_id = idlist.get(position);
                String Platform_name = list.get(position);
                // 此处判断是不是消防巡检点击进来  是则需传入检查专业  检查人等
                if (f_title.equals("xunjian")) {
                    intent.setClass(PlatformActivity.this, CheckActivity.class);
                    intent.putExtra("Platform_ID", current_id);
                    intent.putExtra("Platform_name", Platform_name);
                    intent.putExtra("company_name", company_name);
                    intent.putExtra("oil_name", oil_name);
                    intent.putExtra("f_title", f_title);
                    intent.putExtra("duty", duty);
                    intent.putExtra("check_name", check_name);
                    intent.putExtra("check_date", check_date);
                } else {
                    intent.setClass(PlatformActivity.this, FireActivity.class);
                    intent.putExtra("Platform_ID", current_id);
                    intent.putExtra("Platform_name", Platform_name);
                    intent.putExtra("company_name", company_name);
                    intent.putExtra("oil_name", oil_name);
                    intent.putExtra("f_title", f_title);
                }
                startActivity(intent);
            }
        });

        insert_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PlatformActivity.this, PlatformInsertActivity.class);
                Log.i("aaaa", "点击加号拉company_name= " + company_name);
                intent.putExtra("company_name", company_name);
                intent.putExtra("oil_name", oil_name);
                intent.putExtra("f_title", f_title);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        dataList = ServiceFactory.getCompanyInfoService().getPlatformList(oil_name);



        idlist = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            CompanyInfo CompanyListItem = dataList.get(i);
            String companyName = CompanyListItem.getPlatformName();
            long id = CompanyListItem.getId();
            if (companyName != null && companyName != "") {
                list.add(companyName);
                idlist.add(id);
            }
        }
        ListView platform_list_item = findViewById(R.id.platform_list_item);
        PlatformAdapter platformAdapter = new PlatformAdapter(PlatformActivity.this, PlatformActivity.this);
        platformAdapter.setData(list);
        platform_list_item.setAdapter(platformAdapter);

    }

    private void getIntentInfo() {
        Bundle b = getIntent().getExtras();
        f_title = b.getString("f_title");
        duty = b.getString("duty");
        check_name = b.getString("check_name");
        check_date = b.getString("check_date");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.del_btn:   //lv条目中 iv_del
                final int position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转
                //点击删除按钮之后，给出dialog提示
                AlertDialog.Builder builder = new AlertDialog.Builder(PlatformActivity.this);
                builder.setTitle("确认删除?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oilValue = list.get(position);
                        String type = "platform";
                        long ret = ServiceFactory.getCompanyInfoService().deleteData(oilValue, type);
                        if (ret == 0) {
                            Toast.makeText(PlatformActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            dataList = ServiceFactory.getCompanyInfoService().getPlatformList(oil_name);
                            list = new ArrayList<>();
                            for (int i = 0; i < dataList.size(); i++) {
                                CompanyInfo CompanyListItem = dataList.get(i);
                                String companyName = CompanyListItem.getOilfieldName();
                                if (companyName != null && companyName != "") {
                                    list.add(companyName);
                                }
                            }

                            ListView platform_list_item = findViewById(R.id.platform_list_item);
                            PlatformAdapter platformAdapter = new PlatformAdapter(PlatformActivity.this, PlatformActivity.this);
                            platformAdapter.setData(list);
                            platform_list_item.setAdapter(platformAdapter);
                        } else {
                            Toast.makeText(PlatformActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.edit_btn:   //lv条目中 iv_del
                final int edit_position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转

                String NameItem = list.get(edit_position);
                Intent intent = new Intent(PlatformActivity.this, PlatformOperationActivity.class);
                // 跳转携带参数
                intent.putExtra("platform_name", NameItem);
                intent.putExtra("company_name", company_name);
                intent.putExtra("oil_name", oil_name);
                startActivity(intent);
                break;
        }
    }

    private void finishImpl() {

    }
}