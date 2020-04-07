package com.hr.fire.inspection.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.fragment.CarbonFragment1;
import com.hr.fire.inspection.fragment.CarbonFragment2;
import com.hr.fire.inspection.fragment.CarbonFragment3;
import com.hr.fire.inspection.fragment.CarbonFragment4;
import com.hr.fire.inspection.fragment.CarbonFragment5;


import java.util.ArrayList;
import java.util.List;

public class CarbonDioxideAcitivty extends AppCompatActivity {
    private List<String> titleList = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_finish;
    private TextView tvInspectionPro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_carbon_dioxide);
        initView();
        initListner();
    }

    public void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);
        titleList.add("药剂瓶");
        titleList.add("氮气瓶");
        titleList.add("保护区");
        titleList.add("管线管件");
        titleList.add("功能性试验");
        fragments.add(CarbonFragment1.newInstance("", ""));
        fragments.add(CarbonFragment2.newInstance("", ""));
        fragments.add(CarbonFragment3.newInstance("", ""));
        fragments.add(CarbonFragment4.newInstance("", ""));
        fragments.add(CarbonFragment5.newInstance("", ""));
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
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(1).select();//设置第一个为选中
    }

    private void initListner() {
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
