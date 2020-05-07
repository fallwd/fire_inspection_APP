package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;
import com.hr.fire.inspection.view.tableview.timeselector.TimeSelector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private IntentTransmit intentTransmit;
    private ArrayList<String> bessy_list;
    private ArrayList<String> yt_list;
    private List<CompanyInfo> bussy_dataList;
    private List<CompanyInfo> yt_dataList;
    private List<CompanyInfo> pt_dataList;
    private long platformId;
    private Button start_time;
    private Button end_time;
    private long System_id;
    private Spinner spinner_buss;
    private Spinner spinner_yt;
    private Spinner spinner_pt;
    private Spinner spinner_sys;
    private ArrayList<String> system_list;
    private List<CheckType> system_dataList;
    private ArrayList<String> platform_list;
    //    platformId没有的话填0，systemId没有的话填0，startDate没有的话填null,endDate没有的话填null
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_library);

        spinner_buss = findViewById(R.id.spinner_bussy);
        spinner_yt = findViewById(R.id.spinner_yt);
        spinner_pt = findViewById(R.id.spinner_pt);
        spinner_sys = findViewById(R.id.spinner_sys);
        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);
        Statistics = findViewById(R.id.Statistics);
        end_time = findViewById(R.id.end_time);
        start_time = findViewById(R.id.start_time);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        Statistics = findViewById(R.id.Statistics);//  统计跳转
        initView();
        initListner();
    }

    public void initView() {
        Log.d("intentTransmit===", intentTransmit+"");
        String text = new StringBuilder().append("隐患库").toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 0, text.length());
        tvInspectionPro.setText(showTextColor);
        initData();
        initTableView();
    }

    private  void initTableView() {
        fragments.clear();

        titleList.add("消防年检隐患库");
        titleList.add("消防巡检隐患库");
        hiddenLibaryFragment1 =HiddenLibaryFragment1.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, intentTransmit);
        hiddenLibaryFragment2 =HiddenLibaryFragment2.newInstance(ConstantInspection.YEARLY_ON_SITE_F2, intentTransmit);
        fragments.add(hiddenLibaryFragment1);
        fragments.add(hiddenLibaryFragment2);
        //设置缓存的页面数据
        mViewPager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                // 滚动中监听
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
                // 滚动结束
            }
        });
        //每项只进入一次
        mViewPager.setAdapter(new myAdapter(getSupportFragmentManager()));

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
    class myAdapter extends FragmentPagerAdapter {

        public myAdapter(FragmentManager fm) {
            super(fm);
        }

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
    }
    private void initListner() {
        iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(v -> finish());
        Statistics.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(HiddenLibaryActivity.this, StatiSticsActivity.class);
            startActivity(intent);
            finish();
        });
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
        // 时间选择器
        start_time.setOnClickListener(v -> selectStartTime("1990-01-01 00:00", "starttime"));
        end_time.setOnClickListener(v -> selectStartTime((String) start_time.getText(),"endtime"));
        // 触发表格数据更新
        spinner_pt.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner_sys.setOnItemSelectedListener(new SpinnerSelectedListener());
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
        bussy_dataList = ServiceFactory.getCompanyInfoService().getCompanyList();
        bessy_list = new ArrayList<>();
        bessy_list.add("请选择公司");
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
        yt_dataList = ServiceFactory.getCompanyInfoService().getOilfieldList(companyName);
        yt_list = new ArrayList<>();
        for (int i = 0; i < yt_dataList.size(); i++) {
            CompanyInfo CompanyListItem = yt_dataList.get(i);
            String getOilName = CompanyListItem.getOilfieldName();
            if(getOilName != null && !getOilName.equals("")){
                yt_list.add(getOilName);
            }
        }
        if (yt_dataList.size() == 0){
            yt_list.clear();
            yt_list.add("请选择油田");
        }
        InitSetSpinner(spinner_yt, yt_list);
    }
    // 平台类型
    private void init_platform_spinner(String OilName) {
        // 请选择平台  ->>> 传入油田参数 OilName
        Spinner spinner_pt =  findViewById(R.id.spinner_pt);
        pt_dataList = ServiceFactory.getCompanyInfoService().getPlatformList(OilName);
        platform_list = new ArrayList<>();
        for (int i = 0; i < pt_dataList.size(); i++) {
            CompanyInfo CompanyListItem = pt_dataList.get(i);
            String getPlatformName = CompanyListItem.getPlatformName();
            if(getPlatformName != null && !getPlatformName.equals("")){
                platform_list.add(getPlatformName);
            }
        }
        if (pt_dataList.size() == 0){
            platform_list.clear();
            platform_list.add("请选择平台");
        }
        InitSetSpinner(spinner_pt, platform_list);
    }
    // 年检系统类型
    private void init_system_spinner(int index) {
        // 请选择系统
        Spinner spinner_sys =  findViewById(R.id.spinner_sys);
        // 根据页面滑动 区别返回系统参数 0 -> 年检 1-> 巡检
        if (index == 0){
            system_dataList = ServiceFactory.getYearCheckService().getSystemNameData(); //获取年检系统名
        }else{
            system_dataList = ServiceFactory.getInspectionService().getSystemNameData();//获取巡检系统名
        }
        system_list = new ArrayList<>();
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
    // 事件选择器
    private Date mStartDate;
    private void selectStartTime(String stime, String TimeType) {
        TimeSelector timeSelector = new TimeSelector(this, time -> {
            mStartDate = time;
            if (TimeType == "starttime"){
                start_time.setText(TimeUtil.getInstance().dataToHHmmss(time));
            }else{
                // 做个判断 结束时间大于开始时间
                timeCompare(stime,TimeUtil.getInstance().dataToHHmmss(time));
            }
        }, stime, "2050-12-31 23:59");

        if (mStartDate == null) {
            mStartDate = new Date();
        }
        timeSelector.setMode(TimeSelector.MODE.YMDHM);
        timeSelector.setIsLoop(true);
        timeSelector.show(mStartDate);
    }

    /**
     * 判断2个时间大小
     * yyyy-MM-dd HH:mm 格式
     * @param startTime
     * @param endTime
     * @return
     */
    public void timeCompare(String startTime, String endTime){
        //注意：传过来的时间格式必须要和这里填入的时间格式相同
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime() > date1.getTime()){
                end_time.setText(endTime);
            }else{
                ToastUtil.show(HiddenLibaryActivity.this, "结束时间应大于开始时间", Toast.LENGTH_SHORT);
                end_time.setText("");
            }
        } catch (Exception e) {

        }
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String sectName = (String) parent.getItemAtPosition(position);
            intentTransmit = new IntentTransmit();
            getPlatformId(sectName);
            getSystemId(sectName);
            intentTransmit.start_time = (String) start_time.getText();
            intentTransmit.end_time = (String) end_time.getText();
            initTableView();
        }

        private void getPlatformId(String sectName){
            if (pt_dataList != null) {
                for (int i = 0; i < pt_dataList.size(); i++) {
                    if (pt_dataList.get(i).getPlatformName() == sectName) {
                        platformId = pt_dataList.get(i).getId();
                    }
                }
                intentTransmit.platformId = platformId;
            }
        }

        private  void getSystemId(String sectName){
//            Log.i("aaa", "系统数据==" + system_dataList);
            if (system_dataList != null) {
                for (int i = 0; i < system_dataList.size(); i++) {
                    if (system_dataList.get(i).getName() == sectName) {
                        System_id = system_dataList.get(i).getId();
                    }
                }
//                Log.i("系统数据id==", "" + System_id);
                intentTransmit.systemId = System_id;
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            platform_list.clear();
            platform_list.add("请选择平台");
            InitSetSpinner(spinner_pt, platform_list);
        }
    }
}
