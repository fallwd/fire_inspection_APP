package com.hr.fire.inspection.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.OilFieldAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class OilFieldActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_field);

        List<CompanyInfo> dataList = ServiceFactory.getCompanyInfoService().getOilfieldList("曹妃甸作业公司");
        list = new ArrayList<>();
        for(int i=0; i<dataList.size();i++){
            CompanyInfo CompanyListItem = dataList.get(i);
            String companyName = CompanyListItem.getOilfieldName();
            list.add(companyName);
        }

        ListView oil_list_item = findViewById(R.id.oil_list_item);

        OilFieldAdapter oilFieldAdapter = new OilFieldAdapter(this,this);
        oilFieldAdapter.setData(list);
        oil_list_item.setAdapter(oilFieldAdapter);

        // 监听点击事件
        oil_list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = list.get(position);
                Toast.makeText(OilFieldActivity.this,str ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.del_btn:   //lv条目中 iv_del
                final int position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转

                //点击删除按钮之后，给出dialog提示
                AlertDialog.Builder builder = new AlertDialog.Builder(OilFieldActivity.this);
                builder.setTitle( position + "号位置的删除按钮被点击，确认删除?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                    }
                });
                builder.show();
                break;
        }
    }
}