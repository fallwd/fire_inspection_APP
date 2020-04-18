package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.GridRecordAdapter;
import com.hr.fire.inspection.adapter.HFC1Adapter;
import com.hr.fire.inspection.entity.Function;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TimeUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//二氧化碳年检记录
public class CarbondioxideRecordAcitivty extends AppCompatActivity implements View.OnClickListener {
    public static int[] icon = {R.mipmap.file};
    private long sys_id;
    private long platform_id;
    private String f_title;
    private String sys_number;
    private List<HashMap> historyList;
    private int selected_tag = -1;  //用户选中的条目

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_carbondioxide_record);
        Intent intent = getIntent();
        //CarbonDioxideAcitivty
        sys_id = intent.getLongExtra("sys_id", 0); //系统ID
        platform_id = intent.getLongExtra("platform_id", 0);  //平台ID
        f_title = intent.getStringExtra("f_title");  //传过来的系统名称
        sys_number = intent.getStringExtra("sys_number");  //系统位号

    }

    @Override
    protected void onStart() {
        super.onStart();
        historyList = ServiceFactory.getYearCheckService().getHistoryList(platform_id, sys_id);
        initView();
    }

    private ArrayList hot = new ArrayList<>();

    private void initView() {
        if (historyList.size() == 0) {
            Toast.makeText(this, "没有历史年检记录,请点击\"下一步\"进行新建", Toast.LENGTH_SHORT).show();
        }
        ImageView iv_finish = findViewById(R.id.iv_finish);
        TextView tv_inspection_pro = findViewById(R.id.tv_inspection_pro);
        RecyclerView rc_list = findViewById(R.id.rc_list);
        Button bt_next = findViewById(R.id.bt_next);
        iv_finish.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        for (int i = 0; i < historyList.size(); i++) {
            hot.add(new Function((String) historyList.get(i).get("ret"), 0, false));
        }
        
        rc_list.setLayoutManager(new GridLayoutManager(this, 4));
        GridRecordAdapter toolAdapter = new GridRecordAdapter(this, hot, historyList.size());
        rc_list.setAdapter(toolAdapter);
        toolAdapter.setOnItemClickListener(new GridRecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int tag) {
                //点击选中的记录
                selected_tag = tag;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.bt_next:
                Intent intent = new Intent(this, CarbonDioxideAcitivty.class);
                if (selected_tag == -1) {
                    Date curDateHHmm = TimeUtil.getCurDateHHmm();
                    //selected_tag=-1时,表示用户没有选择任何记录,  新建一个巡检记录,新建记录是根据date来判断的.
                    intent.putExtra("srt_Date", curDateHHmm); //记录的时间
                    intent.putExtra("systemId", sys_id);    //系统ID
                    intent.putExtra("platform_id", platform_id);    //公司ID
                    intent.putExtra("f_title", f_title); //系统名称 :高压二氧化碳灭火系统
                    intent.putExtra("sys_number", sys_number); //系统位号 ：SD002(用户自己填写的)
                    startActivity(intent);
                } else {
                    HashMap hashMap = historyList.get(selected_tag);
                    //拿到历史数据中的记录, 并修改历史记录
                    Date checkDate = (Date) hashMap.get("checkDate"); //时间
                    intent.putExtra("srt_Date", checkDate); //记录的时间
                    intent.putExtra("systemId", sys_id);    //系统ID
                    intent.putExtra("platform_id", platform_id);    //公司ID
                    intent.putExtra("f_title", f_title); //系统名称 :高压二氧化碳灭火系统
                    intent.putExtra("sys_number", sys_number); //系统位号 ：SD002(用户自己填写的)
                    startActivity(intent);
                }
                break;
        }
    }
}
