package com.hr.fire.inspection.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.GridRecordAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.Function;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.Class2Map;
import com.hr.fire.inspection.utils.ExcelUtils;
import com.hr.fire.inspection.utils.SystemConstant;
import com.hr.fire.inspection.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//二氧化碳年检记录
public class  CarbondioxideRecordAcitivty extends AppCompatActivity implements View.OnClickListener {
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
    private List<CheckType> checkTypes;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private List<YearCheck> checkDataEasy;   //左侧需要检查的内容
    private List<YearCheckResult> yearCheckResults; //右侧需要用户填写的内容


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

        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(sys_id);
        if (checkTypes == null) {
            Toast.makeText(this, "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        Log.i("获取checkTypes","checkTypes:"+ checkTypes);
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
        TextView exportData = findViewById(R.id.exportData);

        iv_finish.setOnClickListener(this);
        edit.setOnClickListener(this);
        oldDataNext.setOnClickListener(this);
        newNext.setOnClickListener(this);
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
                if (selected_tag == -1) {
                    Toast.makeText(CarbondioxideRecordAcitivty.this, "请先选择历史数据", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap hashMap = historyList.get(selected_tag);
                    Log.i("aaa","hasmap="+ hashMap);
                    long companyId = (long) hashMap.get("companyInfoId");
                    String number = (String) hashMap.get("systemNumber");
                    long systemId = (long) hashMap.get("systemId");
                    Date checkDate = (Date) hashMap.get("checkDate"); //时间

                    checkTypes = ServiceFactory.getYearCheckService().gettableNameData(systemId);
                    if (checkTypes == null) {
                        Toast.makeText(this, "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
                    }


                    ExcelUtils excelUtils = new ExcelUtils();
                    int checkResultIndex = SystemConstant.getInstance().getCheckReusltIndexBySystemId(sys_id);

                    for (int i=0;i<checkTypes.size();i++) {
                        CheckType checkType = checkTypes.get(i);
                        Map<String, Object> system = SystemConstant.getInstance().getSystem(checkType.getId());

                        String[][] columns = (String[][]) system.get("columns");
                        String title = (String) system.get("title");
                        if (columns != null) {
                            String[] columnWidth = new String[columns[0].length];

                            for (int j = 0; j < columnWidth.length; j++) {
                                columnWidth[j] = "30";
                            }

                            List<Map<String, Object>> items = new ArrayList<>();
                            if (i < checkResultIndex) {
                                Log.i("checkType.getId()checkType.getId()" ,"checkType.getId()checkType.getId()" + checkType.getId());
                                List<ItemInfo> itemDataEasy = ServiceFactory.getYearCheckService().getItemDataEasy(companyId, checkType.getId(), number == null ? "" : number, checkDate);
                                Log.i("itemDataEasyitemDataEasy" ,"itemDataEasy" + itemDataEasy);
                                for (ItemInfo itemInfo : itemDataEasy) {

                                    Map<String, Object> mapParams = Class2Map.getMapParams(itemInfo);
                                    items.add(mapParams);

                                    List<CheckType> checkTypes = ServiceFactory.getYearCheckService().gettableNameData(checkType.getId());
                                    Log.i("checkTypescheckTypescheckTypescheckTypes" ,"aaa" + checkTypes);
                                    //2.获取检查条目的数据,主要用于展示
                                    if(checkTypes != null && checkTypes.size() > 0){
                                        checkDataEasy = ServiceFactory.getYearCheckService().getCheckDataEasy(checkTypes.get(0).getId());
                                        Log.i("checkDataEasy",new Gson().toJson(checkDataEasy));
                                        mapParams.put("checkDataEasy",new Gson().toJson(checkDataEasy));
                                        //3.获取用户需要填写的数据,如果没有数据,就需要插入的默认数据（流程4）。如果有数据就
                                        yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(itemInfo.getId(), companyId, checkTypes.get(0).getId(), number, checkDate);
                                        mapParams.put("yearCheckResults",new Gson().toJson(yearCheckResults));
                                    }

                                }



                            } else {

                                List<YearCheckResult> checkResultDataEasy = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, companyId, checkType.getId(), number, checkDate);

                                for (YearCheckResult yearCheckResult : checkResultDataEasy) {
                                    Map<String,Object> yearcheck = Class2Map.getMapParams(yearCheckResult.getYearCheck());
                                    Map<String, Object> mapParams = Class2Map.getMapParams(yearCheckResult);

                                    for (Map.Entry entry : yearcheck.entrySet()) {
                                        mapParams.put("yearCheck." + entry.getKey(), entry.getValue());
                                    }

                                    items.add(mapParams);
                                }
                            }

                            excelUtils.genSheet(CarbondioxideRecordAcitivty.this,title, columns, columnWidth, items, title);

                            String ret = (String) hashMap.get("ret");
                            excelUtils.exportExcel(CarbondioxideRecordAcitivty.this, ret);
                        }
                    }
                }
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
