package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.hr.fire.inspection.fragment.AutomaticFireAlarm1;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm2;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm3;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm4;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm5;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm6;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm7;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm8;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm9;
import com.hr.fire.inspection.fragment.CarbonFragment1;
import com.hr.fire.inspection.utils.TextSpannableUtil;
import com.hr.fire.inspection.utils.TimeUtil;

//import org.apache.poi.util.NullLogger;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressLint("Registered")
public class AutomaticFireAlarmAcitivty extends AppCompatActivity {
    private static final String TAG = "AutomaticFireAlarmAcitivty";
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

    private String f_title;
    private String sys_number;  //系统位号
    private IntentTransmit it;
    private Date check_date; // 检查时间


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
        long companyInfoId = intent.getLongExtra("companyInfoId", 0);  //公司ID
        long systemId = intent.getLongExtra("systemId", 0);   //系统Id
        long platform_id = intent.getLongExtra("platform_id", 0);   //系统Id
        Date srt_Date = (Date) intent.getSerializableExtra("srt_Date");  //传过来的时间
        f_title = intent.getStringExtra("f_title"); //传过来的名称
        sys_number = intent.getStringExtra("sys_number"); //传过来的名称
        it = new IntentTransmit();
        it.companyInfoId = platform_id;
        it.systemId = systemId;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long nowTime = srt_Date.getTime();
        String d = format.format(nowTime);
        try {
            it.srt_Date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        it.number = sys_number;
        check_date = srt_Date;
    }



    public void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        iv_add_table = findViewById(R.id.iv_add_table);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        iv_save = findViewById(R.id.iv_save);
        String text = new StringBuilder().append("消防年检  >  ").append(f_title).toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 8, text.length());
        tvInspectionPro.setText(showTextColor);

        //显示顶部展示系统位号、保护区域、检查时间的LinearLayout
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.VISIBLE);
        // 系统位号文字显示
        LinearLayout sys_numberCode = (LinearLayout) this.findViewById(R.id.sys_number);
        sys_numberCode.setVisibility(View.GONE);
        // 保护区域文字显示
        LinearLayout protect_areaCode = (LinearLayout) this.findViewById(R.id.protect_area);
        protect_areaCode.setVisibility(View.GONE);
        // 检查时间文字显示
        LinearLayout check_dateCode = (LinearLayout) this.findViewById(R.id.check_date);
        check_dateCode.setVisibility(View.VISIBLE);
        TextView check_date_text = (TextView) this.findViewById(R.id.check_date_text);
        if (check_date == null) {
            check_date_text.setText("检查时间为空");
        } else {
            String mProdDate = DateFormatUtils.format(check_date,"yyyy-MM-dd");
            check_date_text.setText(mProdDate);
        }

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


//        Log.i(TAG, "火灾系统传入的参数=" + it);

        mAutomaticFireAlarm1 = AutomaticFireAlarm1.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);
        mAutomaticFireAlarm2 = AutomaticFireAlarm2.newInstance(ConstantInspection.YEARLY_ON_SITE_F2, it);
        mAutomaticFireAlarm3 = AutomaticFireAlarm3.newInstance(ConstantInspection.YEARLY_ON_SITE_F3, it);
        mAutomaticFireAlarm4 = AutomaticFireAlarm4.newInstance(ConstantInspection.YEARLY_ON_SITE_F4, it);
        mAutomaticFireAlarm5 = AutomaticFireAlarm5.newInstance(ConstantInspection.YEARLY_ON_SITE_F5, it);
        mAutomaticFireAlarm6 = AutomaticFireAlarm6.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);
        mAutomaticFireAlarm7 = AutomaticFireAlarm7.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);
        mAutomaticFireAlarm8 = AutomaticFireAlarm8.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);
        mAutomaticFireAlarm9 = AutomaticFireAlarm9.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);


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
                        mAutomaticFireAlarm1.upData();
                    }else if (fragment instanceof AutomaticFireAlarm2) {
                        mAutomaticFireAlarm2.upData();
                    }else if (fragment instanceof AutomaticFireAlarm3) {
                        mAutomaticFireAlarm3.upData();
                    } else if (fragment instanceof AutomaticFireAlarm4) {
                        mAutomaticFireAlarm4.upData();
                    } else if (fragment instanceof AutomaticFireAlarm5) {
                        mAutomaticFireAlarm5.upData();
                    }else if (fragment instanceof AutomaticFireAlarm6) {
                        mAutomaticFireAlarm6.upData();
                    }else if (fragment instanceof AutomaticFireAlarm7) {
                        mAutomaticFireAlarm7.upData();
                    } else if (fragment instanceof AutomaticFireAlarm8) {
                        mAutomaticFireAlarm8.upData();
                    } else if (fragment instanceof AutomaticFireAlarm9) {
                        mAutomaticFireAlarm9.upData();
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
        mAutomaticFireAlarm1 = null;
        mAutomaticFireAlarm2 = null;
        mAutomaticFireAlarm3 = null;
        mAutomaticFireAlarm4 = null;
        mAutomaticFireAlarm5 = null;
        mAutomaticFireAlarm6 = null;
        mAutomaticFireAlarm7 = null;
        mAutomaticFireAlarm8 = null;
        mAutomaticFireAlarm9 = null;
        finish();
    }
}
