package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.GridRecordAdapter;
import com.hr.fire.inspection.entity.Function;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TextSpannableUtil;
import com.hr.fire.inspection.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FilesCheckActivity extends AppCompatActivity implements View.OnClickListener {
    FilesCheckActivity mContext = this;
    public static int[] icon = {R.mipmap.file};
    private long sys_id;
    private long platform_id;
    private String str_title;
    private String duty;
    private String check_name;
    private String check_date;
    private List<HashMap> historyList;
    private int selected_tag = -1;  //用户选中的条目

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_check);
        Intent intent = getIntent();
        TextView tv_inspection_pro = (TextView) this.findViewById(R.id.tv_inspection_pro);
        sys_id = intent.getLongExtra("sys_id", 0); //系统ID
        platform_id = intent.getLongExtra("platform_id", 0);  //平台ID
        str_title = intent.getStringExtra("str_title");  //传过来的系统名称
        duty = intent.getStringExtra("duty");
        check_name = intent.getStringExtra("check_name");
        check_date = intent.getStringExtra("check_date");
        String srt = new StringBuffer().append("").append("消防巡检").toString();
        SpannableString textColor = TextSpannableUtil.showTextColor(srt, "#00A779", srt.length() - 0, srt.length());
        tv_inspection_pro.setText(textColor);
    }


    @Override
    protected void onStart() {
        super.onStart();
        historyList = ServiceFactory.getInspectionService().getHistoryList(platform_id, sys_id);
        initView();
    }

    private ArrayList hot = new ArrayList<>();

    private void initView() {
        if (historyList.size() == 0) {
            Toast.makeText(this, "没有历史巡检记录,请点击\"下一步\"进行新建", Toast.LENGTH_SHORT).show();
        }
        ImageView iv_finish = findViewById(R.id.iv_finish);
        RecyclerView rc_list = findViewById(R.id.rc_list);
        Button bt_next = findViewById(R.id.bt_next);
        iv_finish.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        for (int i = 0; i < historyList.size(); i++) {
            hot.add(new Function((String) historyList.get(i).get("ret"), 0, false));
        }

        rc_list.setLayoutManager(new GridLayoutManager(this, 4));
        GridRecordAdapter toolAdapter = new GridRecordAdapter(this, hot, historyList.size());
        Log.d("检查记录：", String.valueOf(historyList));
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
                //不同的系统,匹配不同的跳转页面
                Intent intent = regularIntent();
                intent.putExtra("systemId", sys_id);    //系统ID
                intent.putExtra("platform_id", platform_id);    //公司ID
                intent.putExtra("str_title", str_title); //系统名称 :高压二氧化碳灭火系统
                intent.putExtra("duty", duty); // 专业
                intent.putExtra("check_name",check_name); // 检查人
                intent.putExtra("check_date",check_date); //  检查日期
                if (selected_tag == -1) {
                    Date curDateHHmm = TimeUtil.getCurDateHHmm();
                    //selected_tag=-1时,表示用户没有选择任何记录,  新建一个巡检记录,新建记录是根据date来判断的.
                    intent.putExtra("srt_Date", curDateHHmm); //记录的时间
                    startActivity(intent);
                } else {
                    HashMap hashMap = historyList.get(selected_tag);
                    //拿到历史数据中的记录, 并修改历史记录
                    Date checkDate = (Date) hashMap.get("checkDate"); //时间
                    intent.putExtra("srt_Date", checkDate); //记录的时间
                    startActivity(intent);
                }
                break;
        }
    }

    //不同的系统跳转不同的页面,根据服务器ID来匹配
    private Intent regularIntent() {
        Intent intent = new Intent(this, HiddenLibaryActivity.class);
        switch ((int) sys_id) {
            case 72:  //灭火器
//                intent.setClass(this, HiddenLibaryActivity.class);
                intent.setClass(this, XJFireExtinguisherActivity.class);

                break;
            case 73:  //气体灭火系统
                intent.setClass(this, XJGasFireSystem.class);
                break;
            case 74:  //防火风闸

                break;
            case 75:  //雨淋阀

                break;
            case 76:  //消防软管站

                break;
            case 77:  //消防水龙带

                break;
            case 78:  //火气探头及火灾盘

                break;
            case 79:  //火气监控系统

                break;
            case 80:  //厨房湿粉灭火系统

                break;
            case 81:  //泡沫灭火

                break;
            case 82:  //消防泵

                break;
            case 83:  //消防员装备箱

                break;
            case 84:  //消防水炮
                break;
        }
        return intent;
    }
}
