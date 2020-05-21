package com.hr.fire.inspection.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CompanyAdapter;
import com.hr.fire.inspection.adapter.GridRecordAdapter;
import com.hr.fire.inspection.adapter.HFC1Adapter;
import com.hr.fire.inspection.entity.CompanyInfo;
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
import java.util.Map;

//二氧化碳年检记录
public class CarbondioxideRecordAcitivty extends AppCompatActivity implements View.OnClickListener {
    CarbondioxideRecordAcitivty mContext = this;
    public static int[] icon = {R.mipmap.file};
    private long sys_id;
    private long platform_id;
    private String f_title;
    private String sys_number = "";  //有几个系统是没有这个数据的,
    private String protect_area = "";  //有几个系统是没有这个数据的,
    private List<HashMap> historyList;
    private HashMap historyListItem;
    private int selected_tag = -1;  //用户选中的条目
    private String company_name;
    private String oil_name;
    private String Platform_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_carbondioxide_record);
        Intent intent = getIntent();
        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);
        //CarbonDioxideAcitivty
        sys_id = intent.getLongExtra("sys_id", 0); //系统ID
        platform_id = intent.getLongExtra("platform_id", 0);  //平台ID
        f_title = intent.getStringExtra("f_title");  //传过来的系统名称
        sys_number = intent.getStringExtra("sys_number");  //系统位号
        protect_area = intent.getStringExtra("protect_area");  //保护区域
        selected_tag = -1;
    }

    @Override
    protected void onStart() {
        super.onStart();
        historyList = ServiceFactory.getYearCheckService().getHistoryList(platform_id, sys_id);
        initView();
    }

    private ArrayList<Function> hot = new ArrayList<>();
    private ArrayList<Boolean> hotSelecte = new ArrayList<>();

    private void initView() {
        if (historyList.size() == 0) {
            Toast.makeText(this, "没有历史年检记录,请点击\"下一步\"进行新建", Toast.LENGTH_SHORT).show();
        }
        ImageView iv_finish = findViewById(R.id.iv_finish);
        TextView tv_inspection_pro = findViewById(R.id.tv_inspection_pro);
        final RecyclerView rc_list = findViewById(R.id.rc_list);

        Button edit = findViewById(R.id.edit);
        Button oldDataNext = findViewById(R.id.oldDataNext);
        Button newNext = findViewById(R.id.newNext);
        TextView deleteHistoryData = findViewById(R.id.deleteHistoryData);
        TextView importData = findViewById(R.id.importData);
        TextView exportData = findViewById(R.id.exportData);

        iv_finish.setOnClickListener(this);
        edit.setOnClickListener(this);
        oldDataNext.setOnClickListener(this);
        newNext.setOnClickListener(this);
        importData.setOnClickListener(this);
        exportData.setOnClickListener(this);
        deleteHistoryData.setOnClickListener(this);
        hot.clear();

        for (int i = 0; i < historyList.size(); i++) {
            hot.add(new Function((String) historyList.get(i).get("ret"), 0));
        }
        rc_list.setLayoutManager(new GridLayoutManager(this, 4));
        final GridRecordAdapter toolAdapter = new GridRecordAdapter(this, hot, historyList.size());
        rc_list.setAdapter(toolAdapter);

        toolAdapter.setOnItemClickListener(new GridRecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int tag) {
                historyListItem = historyList.get(tag);
                hotSelecte.clear();
                //点击选中的记录
                selected_tag = tag;
                for (int i = 0; i < hot.size(); i++) {
                    Function function = hot.get(tag);
                    if (tag == i) {
                        function.setGray(true);
                        hotSelecte.add(i, true);
                    } else {
                        function.setGray(false);
                        hotSelecte.add(i, false);
                    }
                }
                toolAdapter.setCheckState(hotSelecte);
                toolAdapter.notifyDataSetChanged();
            }
        });
    }


    private void startRecordAcitivty() {
        HashMap hashMap = historyList.get(selected_tag);
        long companyId = (long) hashMap.get("companyInfoId");
        String number = (String) hashMap.get("systemNumber");
        long systemId = (long) hashMap.get("systemId");
        Date checkDate = (Date) hashMap.get("checkDate"); //时间

        Intent intent = regularIntent(); //不同的系统,匹配不同的跳转页面
        intent.putExtra("systemId", systemId);    //系统ID
        intent.putExtra("platform_id", companyId);    //公司ID
        intent.putExtra("f_title", f_title); //系统名称 :高压二氧化碳灭火系统
        intent.putExtra("sys_number", number); //系统位号 ：SD002(用户自己填写的)
        intent.putExtra("protect_area", protect_area); //系统位号 ：SD002(用户自己填写的)
        Log.i("aaa","我要查看编辑时候的时间"+ checkDate);
        intent.putExtra("srt_Date", checkDate); //记录的时间

        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.edit:
                //点击某一个历史记录直接跳转到表格，在表格页面显示相对应的历史数据
                if (selected_tag == -1) {
                    Toast.makeText(CarbondioxideRecordAcitivty.this, "请先选择历史数据", Toast.LENGTH_SHORT).show();
                } else {
                    startRecordAcitivty();
                }
                break;
            case R.id.newNext:
                //不同的系统,匹配不同的跳转页面
                Intent intent = regularIntent();
                intent.putExtra("systemId", sys_id);    //系统ID
                intent.putExtra("platform_id", platform_id);    //公司ID
                intent.putExtra("f_title", f_title); //系统名称 :高压二氧化碳灭火系统
                intent.putExtra("sys_number", sys_number); //系统位号 ：SD002(用户自己填写的)
                intent.putExtra("protect_area", protect_area); //系统位号 ：SD002(用户自己填写的)
                Date curDateHHmm = null;
                curDateHHmm = TimeUtil.getCurDateHHmm();
                // 新建一个巡检记录,新建记录是根据date来判断的.
                intent.putExtra("srt_Date", curDateHHmm); //记录的时间


                startActivity(intent);
                break;
            case R.id.oldDataNext:
                //基于历史数据新建
                if (selected_tag == -1) {
                    Toast.makeText(CarbondioxideRecordAcitivty.this, "请先选择历史数据", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap hashMap = historyList.get(selected_tag);
                    long companyId = (long) hashMap.get("companyInfoId");
                    String number = (String) hashMap.get("systemNumber");
                    long systemId = (long) hashMap.get("systemId");
                    Date checkDate = (Date) hashMap.get("checkDate"); //时间
                    String oldDataNext = "基于历史数据新建";

                    Intent intent2 = regularIntent(); //不同的系统,匹配不同的跳转页面
                    Log.i("aaa","获取跳转列表"+ intent2);
                    intent2.putExtra("systemId", systemId);    //系统ID
                    intent2.putExtra("platform_id", companyId);    //公司ID
                    intent2.putExtra("f_title", f_title); //系统名称 :高压二氧化碳灭火系统
                    intent2.putExtra("sys_number", number); //系统位号 ：SD002(用户自己填写的)
                    intent2.putExtra("protect_area", protect_area); //系统位号 ：SD002(用户自己填写的)
                    if(sys_id == 17 || sys_id == 19) { // 灭火器系统和火灾自动报警系统点击新建的时候  甲方要求表格内容为空，所以通过系统id判断一下,将时间处理为当前时间
                        Date curDateHHmm1 = TimeUtil.getCurDateHHmm();
                        // 新建一个巡检记录,新建记录是根据date来判断的.
                        intent2.putExtra("srt_Date", curDateHHmm1); //记录的时间
                    } else {
                        intent2.putExtra("srt_Date", checkDate); //记录的时间
                    }

                    intent2.putExtra("oldDataNext", oldDataNext); //基于历史数据新建

                    startActivity(intent2);
                }
                break;
            case R.id.deleteHistoryData:

                if (selected_tag == -1) {
                    Toast.makeText(CarbondioxideRecordAcitivty.this, "请先选择历史数据", Toast.LENGTH_SHORT).show();
                } else {
                    //点击删除按钮之后，给出dialog提示
                    AlertDialog.Builder builder = new AlertDialog.Builder(CarbondioxideRecordAcitivty.this);
                    builder.setTitle("确认删除?");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HashMap hashMap = historyList.get(selected_tag);
                            //拿到历史数据中的记录, 并修改历史记录
                            long companyId = (long) hashMap.get("companyInfoId");
                            String number = (String) hashMap.get("systemNumber");
                            long systemId = (long) hashMap.get("systemId");
                            Date checkDate = (Date) hashMap.get("checkDate"); //时间
                            ServiceFactory.getYearCheckService().deleteHistoryData(companyId,number,systemId, checkDate);
                            onStart();
                            Toast.makeText(CarbondioxideRecordAcitivty.this, "删除成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
                break;
            case R.id.exportData:
//                @GetMapping("excel");
//                public void exportExcel(HttpServletResponse response) {
//                    String [][] columnNames = {
//                            {"表头1","表头2","表头3","表头4","表头5"},
//                            {"header1","header2","header3","header4","header5"}
//                    };
//                    String [] columnWidth ={"30","20","20","10","15"};
//                    String title = "测试";
//
//                    //数据
//                    List<Map<String,Object>> list = new ArrayList<>();
//                    Map<String,Object> map1 = new HashMap<>();
//                    map1.put("header1",new Date());
//                    map1.put("header2",1);
//                    map1.put("header3","测试");
//                    map1.put("header4","test");
//                    map1.put("header5",1.5);
//
//                    list.add(map1);
//                    list.add(map1);
//
//                    util.ExcelUtils excelUtils = new util.ExcelUtils();
//                    excelUtils.genSheet("温度",columnNames,columnWidth,list,title);
//                    excelUtils.exportExcel(response,"测试");
//                }
                break;
        }
    }
    public void refresh() {
        onCreate(null);
    }

    //不同的系统跳转不同的页面,根据服务器ID来匹配
    private Intent regularIntent() {
        Intent intent = new Intent(this, CarbonDioxideAcitivty.class);
        switch ((int) sys_id) {
            case 1:  //二氧化碳系统
                intent.setClass(this, CarbonDioxideAcitivty.class);
                break;
            case 9:  //七氟丙烷灭火系统
                intent.setClass(this, HFCActivity.class);
                break;
            case 17:  //灭火器
                intent.setClass(this, NJFireExtinguisherActivity.class);
                break;
            case 19:  //火灾自动报警系统
                intent.setClass(this, AutomaticFireAlarmAcitivty.class);
                break;
            case 29:  //厨房设备灭火装置
                intent.setClass(this, NjKitchenAcitivty.class);
                break;
            case 36:  //海水雨淋灭火系统
                intent.setClass(this, SeawaterSystemActivity.class);
//                intent.setClass(this, CarbonDioxideAcitivty.class);
                break;
            case 40:  //消防水灭火系统
                intent.setClass(this, NjFireFightingWaterAcitivty.class);
                break;
            case 47:  //固定式干粉灭火系统
                intent.setClass(this, DryPowderFireSystemActivity.class);
                break;
            case 54:  //泡沫灭火系统
                intent.setClass(this, FoamFireActivity.class);
                break;
            case 59:  //消防员装备
                intent.setClass(this, DFXIAcitivty.class);
                break;
        }
        return intent;
    }
}
