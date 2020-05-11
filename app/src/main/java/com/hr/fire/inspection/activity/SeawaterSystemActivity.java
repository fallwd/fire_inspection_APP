package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.constant.ConstantInspection;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.fragment.SeawaterSystemFragment1;
import com.hr.fire.inspection.fragment.SeawaterSystemFragment2;
import com.hr.fire.inspection.fragment.SeawaterSystemFragment3;
import com.hr.fire.inspection.utils.TextSpannableUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SeawaterSystemActivity  extends AppCompatActivity {
    private List<String> titleList = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_finish;
    private TextView iv_save;
    private ImageView iv_add_table;
    private TextView tvInspectionPro;
    private int currentPager;
    private SeawaterSystemFragment1 SeawaterSystemFragment1;
    private SeawaterSystemFragment2 SeawaterSystemFragment2;
    private SeawaterSystemFragment3 SeawaterSystemFragment3;
    private String f_title;
    private String sys_number;  //系统位号
    private IntentTransmit it;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_carbon_dioxide);
        getIntentParameter();
        initView();
        initListner();
    }

    private void getIntentParameter() {
        //历史中的companyInfoId  ,  systemId和再公司、平台那边传过来的都是一样的ID，使用哪一个都行
        Intent intent = getIntent();
        long systemId = intent.getLongExtra("systemId", 0);   //系统Id
        long platform_id = intent.getLongExtra("platform_id", 0);   //系统Id
        Date srt_Date = (Date) intent.getSerializableExtra("srt_Date");  //传过来的时间
        f_title = intent.getStringExtra("f_title"); //传过来的名称
        sys_number = intent.getStringExtra("sys_number"); //传过来的名称
        it = new IntentTransmit();
        it.companyInfoId = platform_id;
        it.systemId = systemId;
        it.srt_Date = srt_Date;
        it.number = sys_number;
    }

    public void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        iv_add_table = findViewById(R.id.iv_add_table);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        iv_save = findViewById(R.id.iv_save);
        iv_add_table.setVisibility(View.GONE); //不显示加号按钮
        String text = "消防巡检维护专用工具";
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#E51C23", 0, 3);
        tvInspectionPro.setText(showTextColor);

        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);
        titleList.add("雨淋阀");
        titleList.add("关键控制性阀门");
        titleList.add("功能性试验");

        SeawaterSystemFragment1 = SeawaterSystemFragment1.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);
        SeawaterSystemFragment2 = SeawaterSystemFragment2.newInstance(ConstantInspection.YEARLY_ON_SITE_F2, it);
        SeawaterSystemFragment3 = SeawaterSystemFragment3.newInstance(ConstantInspection.YEARLY_ON_SITE_F3, it);

        fragments.add(SeawaterSystemFragment1);
        fragments.add(SeawaterSystemFragment2);
        fragments.add(SeawaterSystemFragment3);

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
//        mTabLayout.getTabAt(1).select();//设置第一个为选中
    }

    private void initListner() {
        iv_add_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragments != null && fragments.size() != 0) {
                    Log.d("dong", "instanceof CarbonFragment1  onClick");
                    Fragment fragment = fragments.get(currentPager);
                    if (fragment instanceof SeawaterSystemFragment1) {

                    }else if(fragment instanceof SeawaterSystemFragment2){

                    }else if(fragment instanceof SeawaterSystemFragment3){

                    }
                }
//                currentPager  拿到当前的页面
            }
        });
        //点击保存时候调用
        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragments != null && fragments.size() != 0) {
                    Fragment fragment = fragments.get(currentPager);
                    if (fragment instanceof SeawaterSystemFragment1) {
                        SeawaterSystemFragment1.saveData();
                    }else if(fragment instanceof SeawaterSystemFragment2){
                        SeawaterSystemFragment2.saveData();
                    }else if(fragment instanceof SeawaterSystemFragment3){
                        SeawaterSystemFragment3.saveData();
                    }
                }
            }
        });
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
        finish();
    }
}
