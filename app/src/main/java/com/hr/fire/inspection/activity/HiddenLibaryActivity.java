package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.constant.ConstantInspection;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.fragment.HiddenLibaryFragment1;
import com.hr.fire.inspection.fragment.HiddenLibaryFragment2;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TextSpannableUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HiddenLibaryActivity extends AppCompatActivity {
    private static final String TAG = "HiddenLibaryActivity";
    private List<String> titleList = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_finish;
    private TextView tvInspectionPro;
    private Button Statistics;
    private int currentPager;

    private HiddenLibaryFragment1 hiddenLibaryFragment1;
    private HiddenLibaryFragment2 hiddenLibaryFragment2;
    private String str_title;
    private String duty;
    private String check_name;
    private String check_date;
    private IntentTransmit it;
    private ArrayList<String> bessy_list;
    private ArrayList<String> yt_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_library);
        initView();
        initListner();
        initData();
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
    }

    private void getIntentParameter() {
        //历史中的companyInfoId  ,  systemId和再公司、平台那边传过来的都是一样的ID，使用哪一个都行
        Intent intent = getIntent();
        long systemId = intent.getLongExtra("systemId", 0);   //系统Id
        long platform_id = intent.getLongExtra("platform_id", 0);   //系统Id
        str_title = intent.getStringExtra("str_title"); //传过来的名称
        it = new IntentTransmit();
        it.companyInfoId = platform_id;
        it.systemId = systemId;
    }

    public void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);

        //  统计跳转
        Statistics = findViewById(R.id.Statistics);
        Statistics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HiddenLibaryActivity.this, StatiSticsActivity.class);
                startActivity(intent);
                finish();
            }
        });



        String text = new StringBuilder().append("隐患库").toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 0, text.length());
        tvInspectionPro.setText(showTextColor);

        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);
        Statistics = findViewById(R.id.Statistics);
        titleList.add("消防年检隐患库");
        titleList.add("消防巡检隐患库");

        hiddenLibaryFragment1 =HiddenLibaryFragment1.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);
        hiddenLibaryFragment2 =HiddenLibaryFragment2.newInstance(ConstantInspection.YEARLY_ON_SITE_F2, it);
        fragments.add(hiddenLibaryFragment1);
        fragments.add(hiddenLibaryFragment2);


        //设置缓存的页面数据
        mViewPager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPager = i;
                init_system_spinner(currentPager);
                if (i == 1) {
                   Statistics.setVisibility(View.GONE);
                } else {
                    Statistics.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //每项只进入一次
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initListner() {
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hiddenLibaryFragment1 = null;
        hiddenLibaryFragment2 = null;
        finish();
    }
    private void initData() {

        init_company_spinner(); // 初始化公司列表
        init_system_spinner(0); //初始化年检系统列表
    }
    // 返回公司数据
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
    }
    // 油田类型
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
    // 平台类型
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
    // 年检系统类型
    private void init_system_spinner(int index) {
        // 请选择系统
        Spinner spinner_sys =  findViewById(R.id.spinner_sys);
        // 根据页面滑动 区别返回系统参数 0 -> 年检 1-> 巡检
        List<CheckType> system_dataList;
        if (index == 0){
            system_dataList = ServiceFactory.getYearCheckService().getSystemNameData(); //获取年检系统名
        }else{
            system_dataList = ServiceFactory.getInspectionService().getSystemNameData();//获取巡检系统名
        }
        ArrayList<String> system_list = new ArrayList<>();
        for (int i = 0; i < system_dataList.size(); i++) {
            CheckType SystemListItem = system_dataList.get(i);
            String getSystemName = SystemListItem.getName();
            if(getSystemName != null && !getSystemName.equals("")){
                system_list.add(getSystemName);
            }
        }
        InitSetSpinner(spinner_sys, system_list);
    }
    // 下拉框
    private void InitSetSpinner(final Spinner spinner, ArrayList<String> list) {
        //适配器
        ArrayAdapter<String> arr_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
    }
}
