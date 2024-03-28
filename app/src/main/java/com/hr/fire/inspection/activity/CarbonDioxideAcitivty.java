package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String protect_area;  //保护区域
    private Date check_date; // 检查时间
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
        //  历史中的companyInfoId  ,  systemId和再公司、平台那边传过来的都是一样的ID，使用哪一个都行
        Intent intent = getIntent();
        long systemId = intent.getLongExtra("systemId", 0);   //系统Id
        long platform_id = intent.getLongExtra("platform_id", 0);   //系统Id
        Date srt_Date = (Date) intent.getSerializableExtra("srt_Date");  //传过来的时间
        f_title = intent.getStringExtra("f_title"); //传过来的名称
        sys_number = intent.getStringExtra("sys_number"); //传过来的名称

        protect_area = intent.getStringExtra("protect_area"); //传过来的保护区域
        check_date = srt_Date;
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
        String oldDataNext = intent.getStringExtra("oldDataNext"); //基于历史数据新建
        it.name = oldDataNext;
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
        TextView sys_number_text = (TextView) this.findViewById(R.id.sys_number_text);
        if (sys_number == null || sys_number == "" || sys_number.isEmpty()) {
            sys_number_text.setText("系统位号为空");
        } else {
            sys_number_text.setText(sys_number);
        }

        EditText sys_number_ed = (EditText) this.findViewById(R.id.sys_number_ed);
        if (sys_number == null || sys_number == "" || sys_number.isEmpty()) {
            sys_number_ed.setText("");
        } else {
            sys_number_ed.setText(sys_number);
        }


        sys_number_ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // 文本变化之前执行的操作
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // 文本变化时执行的操作
                String inputText = charSequence.toString();
                // 在这里处理输入文本的变化，例如实时搜索、动态更新UI等
                carbonFragment1.its.number = inputText;
                carbonFragment2.its.number = inputText;
                carbonFragment3.its.number = inputText;
                carbonFragment4.its.number = inputText;
                carbonFragment5.its.number = inputText;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 文本变化之后执行的操作

            }
        });


        // 保护区域文字显示
        TextView protect_area_text = (TextView) this.findViewById(R.id.protect_area_text);
        if (protect_area == null || protect_area == "" || protect_area.isEmpty()) {
            protect_area_text.setText("保护区域为空");
        } else {
            protect_area_text.setText(protect_area);
        }

        EditText protect_area_ed = (EditText) this.findViewById(R.id.protect_area_ed);
        if (protect_area == null || protect_area == "" || protect_area.isEmpty()) {
            protect_area_ed.setText("");
        } else {
            protect_area_ed.setText(protect_area);
        }

        // 检查时间文字显示
        TextView check_date_text = (TextView) this.findViewById(R.id.check_date_text);
        if (check_date == null) {
            check_date_text.setText("检查为空");
        } else {
            String mProdDate = DateFormatUtils.format(check_date,"yyyy-MM-dd");
            check_date_text.setText(mProdDate);
        }

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
                upData();
            }
        });
        findViewById(R.id.iv_finish_onclick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_finish.performClick();
            }
        });
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击关闭页面的时候, 有可能用户已经修改了,需要更新数据。
                upData();
                finish();
            }
        });
    }

    //更新数据
    private void upData() {
        if (fragments != null && fragments.size() != 0) {
            Fragment fragment = fragments.get(currentPager);
            if (fragment instanceof CarbonFragment1) {
                carbonFragment1.upData();
            } else if (fragment instanceof CarbonFragment2) {
                carbonFragment2.upData();
            } else if (fragment instanceof CarbonFragment3) {
                carbonFragment3.upData();
            } else if (fragment instanceof CarbonFragment4) {
                carbonFragment4.upData();
            } else if (fragment instanceof CarbonFragment5) {
                carbonFragment5.upData();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        carbonFragment1 = null;
        carbonFragment2 = null;
        carbonFragment3 = null;
        carbonFragment4 = null;
        carbonFragment5 = null;
    }


}
