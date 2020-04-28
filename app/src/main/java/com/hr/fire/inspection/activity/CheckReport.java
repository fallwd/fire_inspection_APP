package com.hr.fire.inspection.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CheckReporItemAdapter;
import com.hr.fire.inspection.adapter.SearchAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckReport extends AppCompatActivity {
    private List<HashMap> mapList; // 获取报告列表
    private ArrayList<String> bessy_list;
    private ArrayList<String> yt_list;
    private String company_name;
    private String oil_name;
    private String Platform_name;
    private LinearLayout empty;
    private AutoCompleteTextView search;
    private List stringArrayList;
    private CheckReporItemAdapter mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_report);


        // 获取Bundle的信息
        // 获得公司名称  油田名称
        Bundle b = getIntent().getExtras();
        assert b != null;
        oil_name = b.getString("oil_name");
        company_name = b.getString("company_name");
        Platform_name = b.getString("Platform_name");
        initData(); // 初始化接口数据

        // 点击返回上一页
        ImageButton imageButton = findViewById(R.id.backHome);
        imageButton.setOnClickListener(v -> finish());
        final Spinner spinner_buss = findViewById(R.id.spinner_bussy);
        final Spinner spinner_yt = findViewById(R.id.spinner_yt);
        // 选择公司 搜索油田数据
        spinner_buss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sectName = (String) parent.getItemAtPosition(position);
                init_yt_spinner(sectName);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bessy_list.clear();
                bessy_list.add("请选择公司");
                InitSetSpinner(spinner_buss, bessy_list);
            }
        });
        // 选择油田 搜索油田平台
        spinner_yt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sectName = (String) parent.getItemAtPosition(position);
                init_platform_spinner(sectName);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                yt_list.clear();
                yt_list.add("请选择油田");
                InitSetSpinner(spinner_yt, yt_list);
            }
        });
        // 模糊查询
        empty = findViewById(R.id.empty);
        search = findViewById(R.id.search);
        empty.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.empty:
                    search.setText("");
                    break;
            }
        });
        CheckReporItemAdapter fireReportItemAdapter = new CheckReporItemAdapter(this);
        ListView main_list2 = findViewById(R.id.main_list2);
        search.setOnEditorActionListener((v, actionId, event) -> {
            // TODO Auto-generated method stub
            if(actionId == EditorInfo.IME_ACTION_SEARCH)
            {
                Toast.makeText(CheckReport.this,search.getText().toString(),Toast.LENGTH_SHORT).show();
                // search pressed and perform your functionality.
                //加载适配器
                List<String> arr = new ArrayList<>();
                arr.add(search.getText().toString());
                fireReportItemAdapter.refresh(arr);
                main_list2.setAdapter(fireReportItemAdapter);
                View view = getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
            return false;
        });
        // 自动提示适配器
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        // 支持拼音检索
        String[] str = (String[]) stringArrayList.toArray(new String[stringArrayList.size()]);
        SearchAdapter adapter = new SearchAdapter<>(CheckReport.this,
                android.R.layout.simple_list_item_1, str, SearchAdapter.ALL);
        search.setAdapter(adapter);
    }

    private void initData() {
        //获取报告列表
        mapList = ServiceFactory.getInspectionService().getOutputList();
        Log.d("key", String.valueOf(mapList));
        stringArrayList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            HashMap hashMap = mapList.get(i);
            String ret = (String) hashMap.get("ret");
            stringArrayList.add(ret);
        }
        init_company_spinner(); // 初始化公司列表

    }
    private void init_company_spinner (){
        // 获取公司列表
        final Spinner spinner_buss = findViewById(R.id.spinner_bussy);
        List<CompanyInfo> bussy_dataList = ServiceFactory.getCompanyInfoService().getCompanyList();
        bessy_list = new ArrayList<>();
        for (int i = 0; i < bussy_dataList.size(); i++) {
            CompanyInfo CompanyListItem = bussy_dataList.get(i);
            String companyName = CompanyListItem.getCompanyName();
            if (companyName != null && !companyName.equals("")) {
                bessy_list.add(companyName);
            }
        }
        InitSetSpinner(spinner_buss, bessy_list);
        init_yt_spinner(company_name);
    }
    private void init_yt_spinner(String companyName) {
        // 请选择油田  ->>> 传入公司参数 companyName
        final Spinner spinner_yt = findViewById(R.id.spinner_yt);
        List<CompanyInfo> yt_dataList = ServiceFactory.getCompanyInfoService().getOilfieldList(companyName);
        yt_list = new ArrayList<>();
        for (int i = 0; i < yt_dataList.size(); i++) {
            CompanyInfo CompanyListItem = yt_dataList.get(i);
            String getOilName = CompanyListItem.getOilfieldName();
            if(getOilName != null && !getOilName.equals("")){
                yt_list.add(getOilName);
            }
        }
        InitSetSpinner(spinner_yt, yt_list);
    }
    private void init_platform_spinner(String OilName) {
        // 请选择平台  ->>> 传入油田参数 OilName
        Spinner spinner_pt =  findViewById(R.id.spinner_pt);
        List<CompanyInfo> yt_dataList = ServiceFactory.getCompanyInfoService().getPlatformList(OilName);
        ArrayList<String> platform_list = new ArrayList<>();
        for (int i = 0; i < yt_dataList.size(); i++) {
            CompanyInfo CompanyListItem = yt_dataList.get(i);
            String getPlatformName = CompanyListItem.getPlatformName();
            if(getPlatformName != null && !getPlatformName.equals("")){
                platform_list.add(getPlatformName);
            }
        }
        InitSetSpinner(spinner_pt, platform_list);
    }
    protected void onStart() {
        super.onStart();
        //设置样式
        CheckReporItemAdapter fireReportItemAdapter = new CheckReporItemAdapter(this);
        fireReportItemAdapter.get_oil_name(oil_name);
        fireReportItemAdapter.get_Platform_name(Platform_name);
        fireReportItemAdapter.get_company_name(company_name);
        fireReportItemAdapter.setData(mapList);
        //加载适配器
        ListView main_list2 = findViewById(R.id.main_list2);
        main_list2.setAdapter(fireReportItemAdapter);
    }

    private void InitSetSpinner(final Spinner spinner, ArrayList<String> list) {
        //适配器
        ArrayAdapter<String> arr_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
    }
}
