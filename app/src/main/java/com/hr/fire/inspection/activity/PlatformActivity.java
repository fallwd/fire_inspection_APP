package com.hr.fire.inspection.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.OilFieldAdapter;
import com.hr.fire.inspection.adapter.PlatformAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class PlatformActivity  extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<String> list;
    private ArrayList<Long> idlist;
    private List<CompanyInfo> dataList;
    private String oil_name;
    private String company_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform);
        Bundle b = getIntent().getExtras();

        // 获取Bundle的信息
        // 获得公司名称  油田名称
        oil_name = b.getString("oil_name");
        company_name = b.getString("company_name");

        ImageView insert_btn = (ImageView) this.findViewById(R.id.insert_btn);
        dataList = ServiceFactory.getCompanyInfoService().getPlatformList(oil_name);
        idlist = new ArrayList<>();
        list = new ArrayList<>();
        for(int i=0; i<dataList.size();i++){
            CompanyInfo CompanyListItem = dataList.get(i);
            String companyName = CompanyListItem.getPlatformName();
            long id = CompanyListItem.getId();
            if(companyName != null || companyName != ""){
                list.add(companyName);
                idlist.add(id);
            }
        }

        ListView platform_list_item = findViewById(R.id.platform_list_item);

        PlatformAdapter platformAdapter = new PlatformAdapter(this,this);
        platformAdapter.setData(list);
        platform_list_item.setAdapter(platformAdapter);

        // 监听点击事件
        platform_list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long current_id = idlist.get(position);
                Toast.makeText(PlatformActivity.this,current_id+"当前行id 下一页面" ,Toast.LENGTH_SHORT).show();
            }
        });

        insert_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder insert_builder = new AlertDialog.Builder(PlatformActivity.this);
                insert_builder.setTitle( "将要前往添加页面，确认离开?");
                insert_builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                insert_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PlatformActivity.this, PlatformInsertActivity.class);
                        intent.putExtra("company_name", company_name);
                        intent.putExtra("oil_name", oil_name);
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
                        long ret = ServiceFactory.getCompanyInfoService().deleteData(oilValue,type);
                        if(ret==0){
                            Toast.makeText(PlatformActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            dataList = ServiceFactory.getCompanyInfoService().getPlatformList(oil_name);
                            list = new ArrayList<>();
                            for(int i=0; i<dataList.size();i++){
                                CompanyInfo CompanyListItem = dataList.get(i);
                                String companyName = CompanyListItem.getOilfieldName();
                                if(companyName != null || companyName != ""){
                                    list.add(companyName);
                                }
                            }

                            ListView platform_list_item = findViewById(R.id.platform_list_item);
                            PlatformAdapter platformAdapter = new PlatformAdapter(PlatformActivity.this,PlatformActivity.this);
                            platformAdapter.setData(list);
                            platform_list_item.setAdapter(platformAdapter);

                        }else{
                            Toast.makeText(PlatformActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.edit_btn:   //lv条目中 iv_del
                final int edit_position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转

                //点击编辑按钮之后，给出dialog提示
                AlertDialog.Builder edit_builder = new AlertDialog.Builder(PlatformActivity.this);
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
                        Intent intent = new Intent(PlatformActivity.this, PlatformOperationActivity.class);
                        // 跳转携带参数
                        intent.putExtra("platform_name", NameItem);
                        intent.putExtra("company_name", company_name);
                        intent.putExtra("oil_name", oil_name );
                        startActivity(intent);
                    }
                });
                edit_builder.show();
                break;
        }
    }
}