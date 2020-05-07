package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.HiddenLibraryDetailAdapter2;
import com.hr.fire.inspection.adapter.XJDelugeValveContentApapter;
import com.hr.fire.inspection.adapter.XJFAGMContentApapter;
import com.hr.fire.inspection.adapter.XJFAGPContentApapter;
import com.hr.fire.inspection.adapter.XJFFESContentApapter;
import com.hr.fire.inspection.adapter.XJFireDamperContentApapter;
import com.hr.fire.inspection.adapter.XJFireEquipmentContentAdapter;
import com.hr.fire.inspection.adapter.XJFireHoseStationContentApapter;
import com.hr.fire.inspection.adapter.XJFirstColumnApapter;
import com.hr.fire.inspection.adapter.XJFirstContentApapter;
import com.hr.fire.inspection.adapter.XJGasContentAdapter;
import com.hr.fire.inspection.adapter.XJKitchenWetPowderContentAdapter;
import com.hr.fire.inspection.adapter.XJWaterHoseContentAdapter;
import com.hr.fire.inspection.adapter.xfb_contentAdapter;
import com.hr.fire.inspection.adapter.xfspAdapter;
import com.hr.fire.inspection.entity.HiddenLibaryDetail;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.service.impl.InspectionServiceImpl;
import com.hr.fire.inspection.utils.TextSpannableUtil;
import com.hr.fire.inspection.view.tableview.HListViewScrollView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HiddenLibaryDetailActivity2 extends AppCompatActivity {
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

    private List<InspectionResult> InspectionResults; //数据集合
    private long platformId;
    private long systemId;
    private HiddenLibraryDetailAdapter2 aaa;
    private LinearLayout layout_head;
    private TextView tv_inspection_pro;
    private TextView iv_save;
    private TextView tv_xh_title;
    private ImageView iv_add_table;
    private HListViewScrollView chs_datagroup;
    private RecyclerView rl_firstColumn;
    private RecyclerView rl_content;
    Date parse_check_date = null;
    private String srt_date;
    private InspectionServiceImpl service;
    private XJFirstColumnApapter firstColumnApapter;

    private XJFirstContentApapter contentApapter1;
    private XJGasContentAdapter contentApapter2;
    private XJFireDamperContentApapter contentApapter3;
    private XJDelugeValveContentApapter contentApapter4;
    private XJFireHoseStationContentApapter contentApapter5;
    private XJWaterHoseContentAdapter contentApapter6;
    private XJFAGPContentApapter contentApapter7;
    private XJFAGMContentApapter contentApapter8;
    private XJKitchenWetPowderContentAdapter contentApapter9;
    private XJFFESContentApapter contentApapter10;
    private xfb_contentAdapter contentApapter11;
    private XJFireEquipmentContentAdapter contentApapter12;
    private xfspAdapter contentApapter13;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_library_detail2);
//        接收数据
        HashMap<Integer, String> map = (HashMap<Integer, String>) getIntent().getSerializableExtra("map");
        str_title = map.get("company") + " > " + map.get("system");
        it = new HiddenLibaryDetail();
        it.platformId = Long.valueOf(map.get("platformId"));
        it.systemId = Long.valueOf(map.get("systemId"));
        it.checkDate = map.get("checkDate");
        it.checkPerson = map.get("checkPerson");
        it.profession = map.get("profession");
        systemId = it.systemId;

        InspectionResults = ServiceFactory.getAnalysisService().getInspectionDetail(it.platformId, it.systemId,it.checkDate,it.checkPerson,it.profession);
        for (int i = 0; i< InspectionResults.size(); i++) {
            InspectionResults.get(i).setParam26("隐患库");
        }

//      判断是哪个系统
        layout_head = (LinearLayout) findViewById(R.id.layout_head);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        switch ((int) it.systemId) {
            case 72:  //灭火器
                View layout1 = layoutInflater.inflate(R.layout.xj_item_fire_layout,null);
                layout_head.addView(layout1);
                break;
            case 73:  //气体灭火系统
                View layout2 = layoutInflater.inflate(R.layout.xj_gas_fire_system_head,null);
                layout_head.addView(layout2);
                break;
            case 74:  //防火风闸
                View layout3 = layoutInflater.inflate(R.layout.xj_fire_damper_layout,null);
                layout_head.addView(layout3);
                break;
            case 75:  //雨淋阀
                View layout4 = layoutInflater.inflate(R.layout.xj_deluge_valve_layout,null);
                layout_head.addView(layout4);
                break;
            case 76:  //消防软管站
                View layout5 = layoutInflater.inflate(R.layout.xj_fire_hose_station_layout,null);
                layout_head.addView(layout5);
                break;
            case 77:  //消防水龙带
                View layout6 = layoutInflater.inflate(R.layout.xj_water_hose_head,null);
                layout_head.addView(layout6);
                break;
            case 78:  //火气探头及火灾盘
                View layout7 = layoutInflater.inflate(R.layout.xj_fagp_layout,null);
                layout_head.addView(layout7);
                break;
            case 79:  //火气监控系统
                View layout8 = layoutInflater.inflate(R.layout.xj_fagm_layout,null);
                layout_head.addView(layout8);
                break;
            case 80:  //厨房湿粉灭火系统
                View layout9 = layoutInflater.inflate(R.layout.xj_kitchen_wet_powder_head,null);
                layout_head.addView(layout9);
                break;
            case 81:  //泡沫灭火
                View layout10 = layoutInflater.inflate(R.layout.xj_ffes_layout,null);
                layout_head.addView(layout10);
                break;
            case 82:  //消防泵
                View layout11 = layoutInflater.inflate(R.layout.xfb_head,null);
                layout_head.addView(layout11);
                break;
            case 83:  //消防员装备箱
                View layout12 = layoutInflater.inflate(R.layout.xj_fire_equipment_head,null);
                layout_head.addView(layout12);
                break;
            case 84:  //消防水炮
                View layout13 = layoutInflater.inflate(R.layout.xfsp,null);
                layout_head.addView(layout13);
                break;
        }
        initView();
        initAdapter();
        setSlide();
        initListner();
    }


    private void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        tv_inspection_pro = findViewById(R.id.tv_inspection_pro);
        String text = new StringBuilder().append(str_title).toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 0, text.length());
        tv_inspection_pro.setText(showTextColor);

        iv_save = findViewById(R.id.iv_save);
        tv_xh_title = findViewById(R.id.tv_xh_title);
        iv_add_table = findViewById(R.id.iv_add_table);
        chs_datagroup = findViewById(R.id.chs_datagroup); //横向滑动的vire
        rl_firstColumn = findViewById(R.id.rl_firstColumn);   //第一列
        rl_content = findViewById(R.id.rl_content);     //内容
    }
    private void initListner() {
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @SuppressLint("WrongConstant")
    private void initAdapter() {
        //创建线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_firstColumn.setLayoutManager(mLayoutManager);
        firstColumnApapter = new XJFirstColumnApapter(this, InspectionResults);
        rl_firstColumn.setAdapter(firstColumnApapter);


        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager2.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_content.setLayoutManager(mLayoutManager2);
//        接口拿到数据后渲染
        switch ((int) systemId) {
            case 72:  //灭火器
                contentApapter1 = new XJFirstContentApapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter1);
                break;
            case 73:  //气体灭火系统
                contentApapter2 = new XJGasContentAdapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter2);
                break;
            case 74:  //防火风闸
                contentApapter3 = new XJFireDamperContentApapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter3);
                break;
            case 75:  //雨淋阀
                contentApapter4 = new XJDelugeValveContentApapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter4);
                break;
            case 76:  //消防软管站
                contentApapter5 = new XJFireHoseStationContentApapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter5);
                break;
            case 77:  //消防水龙带
                contentApapter6 = new XJWaterHoseContentAdapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter6);
                break;
            case 78:  //火气探头及火灾盘
                contentApapter7 = new XJFAGPContentApapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter7);
                break;
            case 79:  //火气监控系统
                contentApapter8 = new XJFAGMContentApapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter8);
                break;
            case 80:  //厨房湿粉灭火系统
                contentApapter9 = new XJKitchenWetPowderContentAdapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter9);
                break;
            case 81:  //泡沫灭火
                contentApapter10 = new XJFFESContentApapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter10);
                break;
            case 82:  //消防泵
                contentApapter11 = new xfb_contentAdapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter11);
                break;
            case 83:  //消防员装备箱
                contentApapter12 = new XJFireEquipmentContentAdapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter12);
                break;
            case 84:  //消防水炮
                contentApapter13 = new xfspAdapter(this, InspectionResults);
                rl_content.setAdapter(contentApapter13);
                break;
        }

    }

    //设置同步滑动
    private void setSlide() {
        rl_firstColumn.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rl_content.scrollBy(dx, dy);
                }
            }
        });
        rl_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rl_firstColumn.scrollBy(dx, dy);
                }
            }
        });
    }
}
