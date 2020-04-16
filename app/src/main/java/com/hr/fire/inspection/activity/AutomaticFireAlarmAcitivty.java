package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
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
import com.hr.fire.inspection.fragment.AutomaticFireAlarm1;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm2;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm3;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm4;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm5;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm6;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm7;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm8;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm9;
import com.hr.fire.inspection.utils.TextSpannableUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class AutomaticFireAlarmAcitivty extends AppCompatActivity {
    private List<String> titleList = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_finish;
    private TextView iv_save;
    private ImageView iv_add_table;
    private TextView tvInspectionPro;
    private int currentPager;
    private AutomaticFireAlarm1 mAutomaticFireAlarm1;
    private AutomaticFireAlarm2 mAutomaticFireAlarm2;
    private AutomaticFireAlarm3 mAutomaticFireAlarm3;
    private AutomaticFireAlarm4 mAutomaticFireAlarm4;
    private AutomaticFireAlarm5 mAutomaticFireAlarm5;
    private AutomaticFireAlarm6 mAutomaticFireAlarm6;
    private AutomaticFireAlarm7 mAutomaticFireAlarm7;
    private AutomaticFireAlarm8 mAutomaticFireAlarm8;
    private AutomaticFireAlarm9 mAutomaticFireAlarm9;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_carbon_dioxide);
        initView();
//        initListner();
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
        titleList.add("感烟探测器");
        titleList.add("感温探测器");
        titleList.add("火焰探测器");
        titleList.add("手动报警按钮");
        titleList.add("可燃气体探测器");
        titleList.add("氢气探测器");
        titleList.add("硫化氢探测器");
        titleList.add("CO探测器");
        titleList.add("火灾报警控制器");

        mAutomaticFireAlarm1 = AutomaticFireAlarm1.newInstance("", "");
        mAutomaticFireAlarm2 = AutomaticFireAlarm2.newInstance("", "");
        mAutomaticFireAlarm3 = AutomaticFireAlarm3.newInstance("", "");
        mAutomaticFireAlarm4 = AutomaticFireAlarm4.newInstance("", "");
        mAutomaticFireAlarm5 = AutomaticFireAlarm5.newInstance("", "");
        mAutomaticFireAlarm6 = AutomaticFireAlarm6.newInstance("", "");
        mAutomaticFireAlarm7 = AutomaticFireAlarm7.newInstance("", "");
        mAutomaticFireAlarm8 = AutomaticFireAlarm8.newInstance("", "");
        mAutomaticFireAlarm9 = AutomaticFireAlarm9.newInstance("", "");


        fragments.add(mAutomaticFireAlarm1);
        fragments.add(mAutomaticFireAlarm2);
        fragments.add(mAutomaticFireAlarm3);
        fragments.add(mAutomaticFireAlarm4);
        fragments.add(mAutomaticFireAlarm5);
        fragments.add(mAutomaticFireAlarm6);
        fragments.add(mAutomaticFireAlarm7);
        fragments.add(mAutomaticFireAlarm8);
        fragments.add(mAutomaticFireAlarm9);


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
                    if (fragment instanceof AutomaticFireAlarm1) {
                        Log.d("dong", "instanceof CarbonFragment1");
                        mAutomaticFireAlarm1.addItemView();
                    } else if (fragment instanceof AutomaticFireAlarm2){
                        mAutomaticFireAlarm2.addItemView();
                    } else if (fragment instanceof AutomaticFireAlarm3){
                        mAutomaticFireAlarm3.addItemView();
                    } else if (fragment instanceof AutomaticFireAlarm4){
                        mAutomaticFireAlarm4.addItemView();
                    } else if (fragment instanceof AutomaticFireAlarm5){
                        mAutomaticFireAlarm5.addItemView();
                    } else if (fragment instanceof AutomaticFireAlarm6){
                        mAutomaticFireAlarm6.addItemView();
                    } else if (fragment instanceof AutomaticFireAlarm7){
                        mAutomaticFireAlarm7.addItemView();
                    } else if (fragment instanceof AutomaticFireAlarm8){
                        mAutomaticFireAlarm8.addItemView();
                    } else if (fragment instanceof AutomaticFireAlarm9){
                        mAutomaticFireAlarm9.addItemView();
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
                    if (fragment instanceof AutomaticFireAlarm1) {
                        mAutomaticFireAlarm1.saveData();
                    } else if (fragment instanceof AutomaticFireAlarm2) {
                        mAutomaticFireAlarm2.saveData();
                    } else if (fragment instanceof AutomaticFireAlarm3) {
                        mAutomaticFireAlarm3.saveData();
                    } else if (fragment instanceof AutomaticFireAlarm4) {
                        mAutomaticFireAlarm4.saveData();
                    } else if (fragment instanceof AutomaticFireAlarm5) {
                        mAutomaticFireAlarm5.saveData();
                    } else if (fragment instanceof AutomaticFireAlarm6) {
                        mAutomaticFireAlarm6.saveData();
                    } else if (fragment instanceof AutomaticFireAlarm7) {
                        mAutomaticFireAlarm7.saveData();
                    } else if (fragment instanceof AutomaticFireAlarm8) {
                        mAutomaticFireAlarm8.saveData();
                    } else if (fragment instanceof AutomaticFireAlarm9) {
                        mAutomaticFireAlarm9.saveData();
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
