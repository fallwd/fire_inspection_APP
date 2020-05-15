package com.hr.fire.inspection.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CompanyAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TextSpannableUtil;

import static android.widget.Toast.*;

public class ChooseCompanyActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> list;
    private ImageView insert_btn;
    private String f_title;
    private String duty;
    private String check_name;
    private String check_date;
    List<CompanyInfo> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_company);

        insert_btn = (ImageView) this.findViewById(R.id.insert_btn);
        ImageView iv_finish = (ImageView) this.findViewById(R.id.iv_finish);
        TextView tv_inspection_pro = (TextView) this.findViewById(R.id.tv_inspection_pro);

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle b = getIntent().getExtras();


        f_title = b.getString("f_title");
        String system_title = "消防巡检";

        // 如果选件页点过来  那么则获取输入的三个参数
        if(f_title.equals("xunjian")){
            String srt = new StringBuffer().append("").append(system_title).toString();
            SpannableString textColor = TextSpannableUtil.showTextColor(srt, "#00A779", srt.length() - 0, srt.length());
            tv_inspection_pro.setText(textColor);
            getIntentInfo();
        }
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void getIntentInfo() {
        Bundle b = getIntent().getExtras();
        f_title = b.getString("f_title");
        duty = b.getString("duty");
        check_name = b.getString("check_name");
        check_date = b.getString("check_date");
    }

    private void initData() {
        dataList = ServiceFactory.getCompanyInfoService().getCompanyList();
        list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            CompanyInfo CompanyListItem = dataList.get(i);
            String companyName = CompanyListItem.getCompanyName();
            if (companyName != null && companyName != "") {
                list.add(companyName);
            }
        }
        // 渲染条目
        ListView company_list_item = findViewById(R.id.company_list_item);
        CompanyAdapter companyAdapter = new CompanyAdapter(this, this);
        companyAdapter.setData(list);
        company_list_item.setAdapter(companyAdapter);

        // 监听点击事件
        company_list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        String str = list.get(position);
                        Intent intent = new Intent(ChooseCompanyActivity.this, OilFieldActivity.class);
                        intent.putExtra("company_name", str);
                        intent.putExtra("f_title", f_title);
                        // 如果是消防巡检  点进去需要传这几个参数
                        if(f_title.equals("xunjian")){
                            intent.putExtra("duty", duty);
                            intent.putExtra("check_name",check_name);
                            intent.putExtra("check_date",check_date);
                        }
                        startActivity(intent);
            }
        });
        insert_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder insert_builder = new AlertDialog.Builder(ChooseCompanyActivity.this);
                insert_builder.setTitle("将要前往添加页面，确认离开?");
                insert_builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                insert_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ChooseCompanyActivity.this, CompanyInsertActivity.class);
                        startActivity(intent);
                    }
                });
                insert_builder.show();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.del_btn:   //lv条目中 iv_del
                final int position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转

                //点击删除按钮之后，给出dialog提示
                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseCompanyActivity.this);
                builder.setTitle("确认删除?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String companyValue = list.get(position);
                        String type = "company";
                        long ret = ServiceFactory.getCompanyInfoService().deleteData(companyValue, type);
                        if (ret == 0) {
                            Toast.makeText(ChooseCompanyActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            dataList = ServiceFactory.getCompanyInfoService().getCompanyList();
                            list = new ArrayList<>();

                            for (int i = 0; i < dataList.size(); i++) {
                                CompanyInfo CompanyListItem = dataList.get(i);
                                String companyName = CompanyListItem.getCompanyName();
                                if (companyName != null && companyName != "") {
                                    list.add(companyName);
                                }
                            }
                            ListView company_list_item = findViewById(R.id.company_list_item);
                            CompanyAdapter companyAdapter = new CompanyAdapter(ChooseCompanyActivity.this, ChooseCompanyActivity.this);
                            companyAdapter.setData(list);
                            company_list_item.setAdapter(companyAdapter);
                        } else {
                            Toast.makeText(ChooseCompanyActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.edit_btn:   //lv条目中 iv_del
                final int edit_position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转

                //点击编辑按钮之后，给出dialog提示
                AlertDialog.Builder edit_builder = new AlertDialog.Builder(ChooseCompanyActivity.this);
                edit_builder.setTitle("将要前往编辑页面，确认离开么?");
                edit_builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                edit_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String NameItem = list.get(edit_position);
                        Intent intent = new Intent(ChooseCompanyActivity.this, CompanyOperationActivity.class);
                        // 跳转携带参数
                        intent.putExtra("company_name", NameItem);
                        startActivity(intent);
                    }
                });
                edit_builder.show();
                break;
        }
    }
}
