package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.RecycleAdapter;
import com.hr.fire.inspection.adapter.XJFirstContentApapter;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.service.impl.InspectionServiceImpl;
import com.hr.fire.inspection.view.tableview.HListViewScrollView;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class XJFireWaterActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_finish;
    private TextView tv_inspection_pro;
    private TextView iv_save;
    private TextView tv_xh_title;
    private ImageView iv_add_table;
    private HListViewScrollView chs_datagroup;
    private RecyclerView rl_firstColumn;
    private RecyclerView rl_content;
    private long systemId;
    private long companyInfoId;
    private String str_title;
    private String duty;
    private String check_name;
    private String check_date;
    Date parse_check_date = null;
    private String srt_date;
    private List<InspectionResult> inspectionResults;
    private InspectionServiceImpl service;
    private RecycleAdapter recycleAdapter;
    public static final int TAKE_PHOTO = 1;//拍照
    private int imgPostion = -1;
    private static final String[] str={"泡沫组件是否完好","操作是否灵活","喷射性能和喷射强度是否合格", "泡沫炮直流/开花功能是否正常"};
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_j_fire_water);
        getIntentData();
        initData();
        initView();
        setHandleTitle();
        addTheads();
        initAdapter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish:
                finish();
                break;
        }
    }
    private void initData() {
        service = new InspectionServiceImpl();
        inspectionResults = service.getInspectionData(companyInfoId, systemId, parse_check_date);
        Log.d("dong", "inspectionData == " + inspectionResults.size());
        if (inspectionResults != null && inspectionResults.size() != 0) {
            Log.d("dong", "inspectionData == -----   " + inspectionResults.get(0).toString());
        }
    }

    @SuppressLint("WrongConstant")
    private void initAdapter() {
        recycleAdapter = new RecycleAdapter(this, inspectionResults);
        rl_content.setAdapter(recycleAdapter);
    }


    private void getIntentData() {
        Intent intent = getIntent();
        systemId = intent.getLongExtra("systemId", -1);
        companyInfoId = intent.getLongExtra("platform_id", -1);
        str_title = intent.getStringExtra("str_title"); //系统名称 :高压二氧化碳灭火系统
        duty = intent.getStringExtra("duty");  // 专业
        check_name = intent.getStringExtra("check_name"); // 检查人
        check_date = intent.getStringExtra("check_date"); //用户选择的时间
        //测试用, 因为前面传过来的时间格式有问题
        check_date = "2020-04-23 18:21";
        try {
            //这个解析方式是没有问题的 ,需要保证前面传入的数据是 2020-04-23 18:21 格式
            parse_check_date = sdf.parse(check_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        srt_date = intent.getStringExtra("srt_Date");   //检查日期,用户没选择,就是表示是新建
    }

    private void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        iv_save = findViewById(R.id.iv_save);
        iv_add_table = findViewById(R.id.iv_add_table);
        iv_finish.setOnClickListener(this);
        iv_add_table.setOnClickListener(this);
        iv_save.setOnClickListener(this);
    }
    //  循环增加表头
    private void addTheads(){
        LinearLayout Thead_titles = findViewById(R.id.Thead_titles);
        List<String> arr = Arrays.asList(str);
        Log.d("arr.size", arr.size()+"");
        for(int i = 0; i< arr.size();i++){
            Thead_titles.addView(textViewControl(arr.get(i)));
        }
    }
    // 动态textview模板
    @SuppressLint("ResourceAsColor")
    private TextView textViewControl(String title){
        TextView textView = new TextView(this);
        TextPaint tp = textView.getPaint();
        textView.setHeight(90);
        textView.setTextSize(12);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(8,0,8,0);
        tp.setFakeBoldText(true); // 粗体
        textView.setTextColor(XJFireWaterActivity.this.getResources().getColor(R.color.title_black));
        textView.setText(title);
        return textView;
    }
    // 设置表格标题
    private void setHandleTitle(){
        Bundle b = getIntent().getExtras();
        assert b != null;
        String str_title = b.getString("str_title");
        TextView text_title = findViewById(R.id.text_title);
        text_title.setText(str_title);
    }
    // 添加数据
    private void addItem(){

    }
}
