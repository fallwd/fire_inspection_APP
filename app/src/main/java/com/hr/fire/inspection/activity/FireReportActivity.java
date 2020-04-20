package com.hr.fire.inspection.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.FireReportItemAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FireReportActivity extends AppCompatActivity {
    private List<HashMap> mapList; // 获取报告列表
    private FireReportItemAdapter fireReportItemAdapter;
    private List<CompanyInfo> bussy_dataList; // 获取公司列表
    private ArrayList<String> bussy_list;
    private List<CompanyInfo> yt_dataList; // 获取公司列表
    private ArrayList<String> yt_list;
    private String company_name;
    private String oil_name;
    private String Platform_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_report);

        // 获取Bundle的信息
        // 获得公司名称  油田名称
        Bundle b = getIntent().getExtras();
        oil_name = b.getString("oil_name");
        company_name = b.getString("company_name");
        Platform_name = b.getString("Platform_name");
        initData(); // 初始化接口数据

        // 点击返回上一页
        ImageButton imageButton = (ImageButton) findViewById(R.id.backHome);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 设置起止时间
        Button start_time = (Button) findViewById(R.id.start_time);
        Button end_time = (Button) findViewById(R.id.end_time);
        start_time.setText(getData());
        end_time.setText(netCheckTime());
        final Spinner spinner_buss = (Spinner) findViewById(R.id.spinner_bussy);
        final Spinner spinner_yt = (Spinner) findViewById(R.id.spinner_yt);
        Spinner spinner_sys = (Spinner) findViewById(R.id.spinner_sys);
        Spinner spinner_pt = (Spinner) findViewById(R.id.spinner_pt);
        Spinner spinner_per = (Spinner) findViewById(R.id.spinner_per);


        //数据
        ArrayList<String>
                data_list1 = new ArrayList<>(),
                data_list2 = new ArrayList<>(),
                data_list3 = new ArrayList<>(),
                data_list4 = new ArrayList<>(),
                data_list5 = new ArrayList<>();

//        data_list3.add(oil_name);
//        data_list4.add("深圳");
//        data_list5.add("深圳");

        // 初始化下拉框，并监听事件
        InitSetSpinner(spinner_buss,bussy_list);

        InitSetSpinner(spinner_sys, data_list3);
        InitSetSpinner(spinner_pt, data_list4);
        InitSetSpinner(spinner_per, data_list5);


        // 点击油田下拉框 获取公司选中的参数
//        spinner_yt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //
//                InitSetSpinner(spinner_yt, yt_list);
//                String str = spinner_buss.getSelectedItem().toString();
//                Toast.makeText(FireReportActivity.this, str, Toast.LENGTH_SHORT).show();
//                init_yt_Data(str);
//            }
//        });
    }

    private void initData() {
        //获取报告列表
        mapList = ServiceFactory.getYearCheckService().getOutputList();
        Log.d("key", String.valueOf(mapList));

        // 获取公司列表
        bussy_dataList = ServiceFactory.getCompanyInfoService().getCompanyList();
        bussy_list = new ArrayList<>();
        for (int i = 0; i < bussy_dataList.size(); i++) {
            CompanyInfo CompanyListItem = bussy_dataList.get(i);
            String companyName = CompanyListItem.getCompanyName();
            if (companyName != null && companyName != "") {
                bussy_list.add(companyName);
            }
        }
    }

    private void init_yt_Data(String infocontcompanyName) {
        // 请选择油田  ->>> 传入公司参数 infocontcompanyName
        yt_dataList = ServiceFactory.getCompanyInfoService().getOilfieldList(infocontcompanyName);
        yt_list = new ArrayList<>();

        for (int i = 0; i < yt_dataList.size(); i++) {
            CompanyInfo CompanyListItem = yt_dataList.get(i);
            String companyName = CompanyListItem.getOilfieldName();
            if(companyName != null && companyName != ""){
                yt_list.add(companyName);
            }
        }
    }
    //
    private ArrayList<String> initgetList(List datalist, String str){
        ArrayList<String> list;
        list = new ArrayList<>();

        for (int i = 0; i < datalist.size(); i++) {
            CompanyInfo ListItem = (CompanyInfo) datalist.get(i);
            String name = ListItem.getOilfieldName();
            if(name != null && name != ""){
                list.add(name);
            }
        }
        return list;
    };
    protected void onStart() {
        super.onStart();
        //设置样式
        fireReportItemAdapter = new FireReportItemAdapter(this);
        fireReportItemAdapter.get_oil_name(oil_name);
        fireReportItemAdapter.get_Platform_name(Platform_name);
        fireReportItemAdapter.get_company_name(company_name);
        fireReportItemAdapter.setData(mapList);
        //加载适配器
        ListView main_list2 = findViewById(R.id.main_list2);
        main_list2.setAdapter(fireReportItemAdapter);
        main_list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                final Long id1 = mList.get(position).getId();
//                Toast.makeText(FireReportActivity.this, "当前id为：" + id1, Toast.LENGTH_SHORT).show();
            }
        });
    }

        /**
         * 获取手机时间  下次检验日期推迟一年减一天
         * return 年/月/日
         **/
    
    private String netCheckTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, +1);
        calendar.add(Calendar.DATE, -1);//减1天
        date = calendar.getTime();
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String sim = dateFormat.format(date);
        Log.i("md", "推迟的时间为： " + sim);
        return sim;
    }

    /**
     * 获取手机时间  年/月/日
     **/

    private String getData() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String sim = dateFormat.format(date);
//        Log.i("md", "时间sim为： "+sim);
        return sim;
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
                Log.d("dong", String.valueOf(id));
                String data = (String) spinner.getItemAtPosition(position);//从spinner中获取被选择的数据
                fireReportItemAdapter.setSelectedData(data);
                Toast.makeText(FireReportActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("dong","===onNothingSelected");
            }
        });
    }
}
