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
import com.hr.fire.inspection.fragment.CarbonFragment1;
import com.hr.fire.inspection.fragment.CarbonFragment2;
import com.hr.fire.inspection.fragment.CarbonFragment3;
import com.hr.fire.inspection.fragment.CarbonFragment4;
import com.hr.fire.inspection.fragment.CarbonFragment5;
import com.hr.fire.inspection.utils.TextSpannableUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarbonDioxideAcitivty extends AppCompatActivity {
    private static final String TAG = "CarbonDioxideAcitivty";
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
        String text = new StringBuilder().append("消防年检  >  ").append(f_title).toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 8, text.length());
        tvInspectionPro.setText(showTextColor);

        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);
        titleList.add("药剂瓶");
        titleList.add("氮气瓶");
        titleList.add("管线管件");
        titleList.add("保护区");
        titleList.add("功能性试验");

        carbonFragment1 = CarbonFragment1.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);
        carbonFragment2 = CarbonFragment2.newInstance(ConstantInspection.YEARLY_ON_SITE_F2, it);
        carbonFragment3 = CarbonFragment3.newInstance(ConstantInspection.YEARLY_ON_SITE_F3, it);
        carbonFragment4 = CarbonFragment4.newInstance(ConstantInspection.YEARLY_ON_SITE_F4, it);
        carbonFragment5 = CarbonFragment5.newInstance(ConstantInspection.YEARLY_ON_SITE_F5, it);
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
                if (i == 2 || i == 3 || i == 4) {
                    iv_add_table.setVisibility(View.GONE);
                } else {
                    iv_add_table.setVisibility(View.VISIBLE);
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
//        mTabLayout.getTabAt(1).select();//设置第一个为选中
    }

    private void initListner() {
        iv_add_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragments != null && fragments.size() != 0) {
                    Fragment fragment = fragments.get(currentPager);
                    if (fragment instanceof CarbonFragment1) {
                        carbonFragment1.addItemView();
                    } else if (fragment instanceof CarbonFragment2) {
                        carbonFragment2.addItemView();
                    }
                }

            }
        });
        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragments != null && fragments.size() != 0) {
                    Fragment fragment = fragments.get(currentPager);
                    if (fragment instanceof CarbonFragment1) {
                        carbonFragment1.upData();
                    } else if (fragment instanceof CarbonFragment2) {
                        carbonFragment2.upData();
                    } else if (fragment instanceof CarbonFragment3) {
                        carbonFragment3.upData();
                    } else if (fragment instanceof CarbonFragment4) {
//                        carbonFragment4.upData();
                    } else if (fragment instanceof CarbonFragment5) {
                        carbonFragment5.upData();
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
