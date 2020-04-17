package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.FireReportItemAdapter;

import java.util.ArrayList;

public class FireReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_report);

        // 点击返回上一页
        ImageButton imageButton = (ImageButton) findViewById(R.id.backHome);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Spinner spinner_buss = (Spinner) findViewById(R.id.spinner_bussy);
        Spinner spinner_yt = (Spinner) findViewById(R.id.spinner_yt);
        Spinner spinner_sys = (Spinner) findViewById(R.id.spinner_sys);
        Spinner spinner_pt = (Spinner) findViewById(R.id.spinner_pt);
        Spinner spinner_per = (Spinner) findViewById(R.id.spinner_per);

        //数据
        ArrayList<String> data_list = new ArrayList<>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");

        // 初始化下拉框，并监听事件
        InitSetSpinner(spinner_buss, data_list);
        InitSetSpinner(spinner_yt,data_list);
        InitSetSpinner(spinner_sys,data_list);
        InitSetSpinner(spinner_pt,data_list);
        InitSetSpinner(spinner_per,data_list);


        String str_con = "二氧化碳灭火系统";
        String str_con2 = "灭火器";
        String str_con3 = "火灾自动报警系统";
        String str_con4 = "海水灭火系统";
        String str_con5 = "厨房湿粉灭火系统";
        String str_con6 = "消防灭火系统";
        String str_con7 = "干粉灭火系统";
        String str_con8 = "七氟丙烷气体灭火系统";
        String str_con9 = "泡沫灭火系统";


        ArrayList<String> mList = new ArrayList<>();
        mList.add(str_con);
        mList.add(str_con2);
        mList.add(str_con3);
        mList.add(str_con4);
        mList.add(str_con5);
        mList.add(str_con6);
        mList.add(str_con7);
        mList.add(str_con8);
        mList.add(str_con9);
        //设置样式
        FireReportItemAdapter fireReportItemAdapter = new FireReportItemAdapter(this);
        fireReportItemAdapter.setData(mList);
        //加载适配器
        ListView main_list2 = (ListView) findViewById(R.id.main_list2);
        main_list2.setAdapter(fireReportItemAdapter);
    }

    private void InitSetSpinner(final Spinner spinner, ArrayList<String> list) {

        //适配器
        ArrayAdapter<String> arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        //给Spinner添加事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //当选中某一个数据项时触发该方法
            /*
             * parent接收的是被选择的数据项所属的 Spinner对象，
             * view参数接收的是显示被选择的数据项的TextView对象
             * position接收的是被选择的数据项在适配器中的位置
             * id被选择的数据项的行号
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = (String)spinner.getItemAtPosition(position);//从spinner中获取被选择的数据
                Toast.makeText(FireReportActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
