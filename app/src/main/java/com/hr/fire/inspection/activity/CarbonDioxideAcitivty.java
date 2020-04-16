package com.hr.fire.inspection.activity;


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
import com.hr.fire.inspection.fragment.CarbonFragment1;
import com.hr.fire.inspection.fragment.CarbonFragment2;
import com.hr.fire.inspection.fragment.CarbonFragment3;
import com.hr.fire.inspection.fragment.CarbonFragment4;
import com.hr.fire.inspection.fragment.CarbonFragment5;
import com.hr.fire.inspection.utils.TextSpannableUtil;
import java.util.ArrayList;
import java.util.List;

public class CarbonDioxideAcitivty extends AppCompatActivity {
    private List<String> titleList = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_finish;
    private TextView iv_save;
    private ImageView iv_add_table;
    private TextView tvInspectionPro;
    private int currentPager;
    private CarbonFragment1 carbonFragment1;
    private CarbonFragment2 carbonFragment2;
    private CarbonFragment3 carbonFragment3;
    private CarbonFragment4 carbonFragment4;
    private CarbonFragment5 carbonFragment5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_carbon_dioxide);
        initView();
        initListner();
    }

    public void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        iv_add_table = findViewById(R.id.iv_add_table);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        iv_save = findViewById(R.id.iv_save);
        String text = "消防巡检维护专用工具";
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#E51C23", 0, 3);
        tvInspectionPro.setText(showTextColor);

        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);
        titleList.add("药剂瓶");
        titleList.add("氮气瓶");
        titleList.add("保护区");
        titleList.add("管线管件");
        titleList.add("功能性试验");
        carbonFragment1 = CarbonFragment1.newInstance("", "");
        carbonFragment2 = CarbonFragment2.newInstance("", "");
        carbonFragment3 = CarbonFragment3.newInstance("", "");
        carbonFragment4 = CarbonFragment4.newInstance("", "");
        carbonFragment5 = CarbonFragment5.newInstance("", "");
        fragments.add(carbonFragment1);
        fragments.add(carbonFragment2);
        fragments.add(carbonFragment3);
        fragments.add(carbonFragment4);
        fragments.add(carbonFragment5);
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
                    if (fragment instanceof CarbonFragment1) {
                        Log.d("dong", "instanceof CarbonFragment1");
                        carbonFragment1.addItemView();
                    } else if (fragment instanceof CarbonFragment2) {
                        carbonFragment2.addItemView();
                    }
                }
//                currentPager  拿到当前的页面
            }
        });
        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragments != null && fragments.size() != 0) {
                    Fragment fragment = fragments.get(currentPager);
                    if (fragment instanceof CarbonFragment1) {
                        carbonFragment1.saveData();
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
        carbonFragment1 = null;
        carbonFragment2 = null;
        carbonFragment3 = null;
        carbonFragment4 = null;
        carbonFragment5 = null;
        finish();
    }
}
