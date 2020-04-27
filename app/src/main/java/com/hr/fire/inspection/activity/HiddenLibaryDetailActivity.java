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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.HiddenLibraryDetailAdapter1;
import com.hr.fire.inspection.entity.HiddenLibaryDetail;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TextSpannableUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HiddenLibaryDetailActivity extends AppCompatActivity {
    private static final String TAG = "HiddenLibaryActivity";
    private List<String> titleList = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_finish;
    private TextView tvInspectionPro;
    private String str_title;
    private String duty;
    private String check_name;
    private String check_date;
    private HiddenLibaryDetail it;

    private List<YearCheckResult> yearCheckResults; //数据集合
    private long platformId;
    private long systemId;
    private HiddenLibraryDetailAdapter1 aaa;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_library_detail);
        getIntentParameter();
        initView();
    }

    private void getIntentParameter() {
        HashMap<Integer, String> map = (HashMap<Integer, String>) getIntent().getSerializableExtra("map");
        str_title = map.get("company") + " > " + map.get("system");
        it = new HiddenLibaryDetail();
        it.platformId = Long.valueOf(map.get("platformId"));
        it.systemId = Long.valueOf(map.get("systemId"));
        it.checkDate = map.get("checkDate");
        it.systemNumber = map.get("systemNumber");
        it.protectArea = map.get("protectArea");
        yearCheckResults = ServiceFactory.getAnalysisService().getYearCheckDetail(it.platformId, it.systemId,it.checkDate,it.systemNumber,it.protectArea);
    }

    public void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        String text = new StringBuilder().append(str_title).toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 0, text.length());
        tvInspectionPro.setText(showTextColor);

        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);

        final RecyclerView listRrcycler = findViewById(R.id.list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listRrcycler.setLayoutManager(layoutManager);

        aaa = new HiddenLibraryDetailAdapter1(this, yearCheckResults);
        listRrcycler.setAdapter(aaa);
        listRrcycler.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
