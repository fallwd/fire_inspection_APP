package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.FireItemAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;

public class ChooseCompanyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_company);
        List<CompanyInfo> dataList = ServiceFactory.getCompanyInfoService().getAll();
        for(int i = 0;i < dataList.size();i++){
            CompanyInfo data = dataList.get(i);
            String str = data.getCompanyName();
            Log.i("getCompanyInfoService",data.toString());
        }

        ListView main_list = findViewById(R.id.main_list);
        FireItemAdapter fireItemAdapter = new FireItemAdapter(this);
        fireItemAdapter.setData(mList);
        main_list.setAdapter(fireItemAdapter);



    }
}
