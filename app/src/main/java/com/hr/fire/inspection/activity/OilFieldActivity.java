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
import com.hr.fire.inspection.adapter.CompanyAdapter;
import com.hr.fire.inspection.adapter.OilFieldAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class OilFieldActivity extends AppCompatActivity implements View.OnClickListener{
    List<CompanyInfo> dataList;
    private ArrayList<String> list;
    private String infocontcompanyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_field);
        Bundle b1 = getIntent().getExtras();
        //获取Bundle的信息
        final String infocont = b1.getString("company_name");

        infocontcompanyName = infocont;
        ImageView insert_btn = (ImageView) this.findViewById(R.id.insert_btn);
        dataList = ServiceFactory.getCompanyInfoService().getOilfieldList(infocont);
        list = new ArrayList<>();

        for(int i=0; i<dataList.size();i++){
            CompanyInfo CompanyListItem = dataList.get(i);
            String companyName = CompanyListItem.getOilfieldName();
            if(companyName != null && companyName != ""){
                list.add(companyName);
            }
        }

        ListView oil_list_item = findViewById(R.id.oil_list_item);

        OilFieldAdapter oilFieldAdapter = new OilFieldAdapter(this,this);
        oilFieldAdapter.setData(list);
        oil_list_item.setAdapter(oilFieldAdapter);

        // 监听点击事件
        oil_list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder continue_builder = new AlertDialog.Builder(OilFieldActivity.this);
                continue_builder.setTitle( "是否选择该油田?");
                continue_builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                continue_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oil_name = list.get(position);
                        Intent intent = new Intent(OilFieldActivity.this, PlatformActivity.class);
                        // 跳转时传入下一页面 公司名称 油田名称
                        intent.putExtra("company_name", infocontcompanyName);
                        intent.putExtra("oil_name", oil_name);
                        startActivity(intent);
                    }
                });
                continue_builder.show();
            }
        });
        insert_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder insert_builder = new AlertDialog.Builder(OilFieldActivity.this);
                insert_builder.setTitle( "将要前往添加页面，确认离开?");
                insert_builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                insert_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(OilFieldActivity.this, OilInsertActivity.class);
                        intent.putExtra("company_name", infocontcompanyName);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OilFieldActivity.this);
                builder.setTitle( "确认删除?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oilValue = list.get(position);
                        String type = "oilfield";
                        long ret = ServiceFactory.getCompanyInfoService().deleteData(oilValue,type);
                        if(ret==0){
                            Toast.makeText(OilFieldActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

                            dataList = ServiceFactory.getCompanyInfoService().getOilfieldList(infocontcompanyName);
                            list = new ArrayList<>();

                            for(int i=0; i<dataList.size();i++){
                                CompanyInfo CompanyListItem = dataList.get(i);
                                String companyName = CompanyListItem.getOilfieldName();
                                if(companyName != null && companyName != ""){
                                    list.add(companyName);
                                }
                            }

                            ListView oil_list_item = findViewById(R.id.oil_list_item);

                            OilFieldAdapter oilFieldAdapter = new OilFieldAdapter(OilFieldActivity.this,OilFieldActivity.this);
                            oilFieldAdapter.setData(list);
                            oil_list_item.setAdapter(oilFieldAdapter);
                        }else{
                            Toast.makeText(OilFieldActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.edit_btn:   //lv条目中 iv_del
                final int edit_position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转

                //点击编辑按钮之后，给出dialog提示
                AlertDialog.Builder edit_builder = new AlertDialog.Builder(OilFieldActivity.this);
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
                        Intent intent = new Intent(OilFieldActivity.this, OilOperationActivity.class);
                        intent.putExtra("company_name", infocontcompanyName);
                        intent.putExtra("oil_name",NameItem);
                        startActivity(intent);
                    }
                });
                edit_builder.show();
                break;

        }
    }
}