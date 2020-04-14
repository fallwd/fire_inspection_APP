package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.GridRecordAdapter;
import com.hr.fire.inspection.entity.Function;

import java.util.ArrayList;

//二氧化碳年检记录
public class CarbondioxideRecordAcitivty extends AppCompatActivity implements View.OnClickListener {
    public static int[] icon = {R.mipmap.file};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_carbondioxide_record);
        initView();
    }

    private ArrayList hot = new ArrayList<>();

    private void initView() {
        ImageView iv_finish = findViewById(R.id.iv_finish);
        TextView tv_inspection_pro = findViewById(R.id.tv_inspection_pro);
        RecyclerView rc_list = findViewById(R.id.rc_list);
        Button bt_next = findViewById(R.id.bt_next);
        iv_finish.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        hot.add(new Function("签到", 0, false));
        hot.add(new Function("战友圈", 0, false));
        hot.add(new Function("点名", 0, false));
        hot.add(new Function("刷脸", 0, false));
        hot.add(new Function("数据5", 0, false));

        rc_list.setLayoutManager(new GridLayoutManager(this, 4));
        GridRecordAdapter toolAdapter = new GridRecordAdapter(this, hot, 8);
        rc_list.setAdapter(toolAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.bt_next:
                Intent intent = new Intent(this, CarbonDioxideAcitivty.class);
                startActivity(intent);
                break;
        }
    }
}
