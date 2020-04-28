package com.hr.fire.inspection.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.hr.fire.inspection.R;

import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;

import com.hr.fire.inspection.service.ServiceFactory;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatiSticsActivity extends AppCompatActivity {

    private ArrayList<String> bessy_list;
    private ArrayList<String> yt_list;
    private ArrayList<String> platform_list;
    private List<String> timeList = new ArrayList<String>();

    // 选中的年份
    private String selectTime="2019";
    // 选中的公司名称
    private String companyName="";
    // 选中的油田名称
    private String oilName="";
    // 选中的平台名称
    private String platName="";

    private  List<HashMap> companyChartData;
    private  List<HashMap> oilChartData;
    private  List<HashMap> platformChartData;
    private  List<HashMap> systemChartData;

    WebView mWebView;

    // 定义一个类
    private final class MyJavascriptInterface{
        private Context context;

        public MyJavascriptInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void getOil(String data) {
            //  这里接收到参数  需要去查数据库
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 通过公司名称 获取油田
                    companyName = data;
                    oilChartData = ServiceFactory.getAnalysisService().getOilfieldCountByYearCheck(selectTime, companyName);
                    JSONArray oilChartResult = new JSONArray(oilChartData);
                    mWebView.loadUrl("javascript:runJs('" + true  + "', '" + oilChartResult + "')");
                }
            });
        }

        @JavascriptInterface
        public void getPlatform(String data) {
            //  这里接收到参数  需要去查数据库
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    oilName = data;
                    // 传入公司名称 油田名称
                    platformChartData = ServiceFactory.getAnalysisService().getPlatformCountByYearCheck(selectTime, companyName, oilName);;
                    JSONArray platformChartResult = new JSONArray(platformChartData);
                    mWebView.loadUrl("javascript:runJs('" + true  + "', '" + platformChartResult + "')");
                }
            });
        }
        @JavascriptInterface
        public void getSystem(String data) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    platName = data;
                    // 传入公司名称 油田名称 平台名称
                    systemChartData = ServiceFactory.getAnalysisService().getSystemCountByYearCheck(selectTime, companyName, oilName, platName);;
                    JSONArray systemChartResult = new JSONArray(systemChartData);
                    mWebView.loadUrl("javascript:runJs('" + true  + "', '" + systemChartResult + "')");
                }
            });
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        final Spinner spinner_buss = findViewById(R.id.spinner_bussy);
        final Spinner spinner_yt = findViewById(R.id.spinner_yt);
        final Spinner spinner_pt = findViewById(R.id.spinner_pt);
        final Spinner spinner_time = findViewById(R.id.spinner_time);

        initData();
        // 初始化时间选择器
        timeList.add("2019");
        timeList.add("2020");
        InitSetSpinner(spinner_time, (ArrayList<String>) timeList);

        // 定位到webview
        mWebView =(WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 默认传入公司数据
        companyChartData= ServiceFactory.getAnalysisService().getCompanyCountByYearCheck(selectTime);
        JSONArray companyChartResult = new JSONArray(companyChartData);
        mWebView.loadUrl("file:///android_asset/webview.html");
        mWebView.addJavascriptInterface(new MyJavascriptInterface(this), "injectedObject");


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.loadUrl("javascript:runJs('" + true  + "', '" + companyChartResult + "')");
                super.onPageFinished(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(StatiSticsActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });

        //  默认查询2020的
        spinner_time.setSelection(0,true);
        spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTime = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                timeList.add("请选择年份");
                InitSetSpinner(spinner_time, (ArrayList<String>) timeList);
            }
        });

        // 选择公司 搜索油田数据
        spinner_buss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                companyName = (String) parent.getItemAtPosition(position);
//                Toast.makeText(StatiSticsActivity.this, companyName, Toast.LENGTH_SHORT).show();
                init_yt_spinner(companyName);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bessy_list.clear();
                bessy_list.add("请选择");
                InitSetSpinner(spinner_buss, bessy_list);
            }
        });

        // 选择油田 搜索油田平台
        spinner_yt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                oilName = (String) parent.getItemAtPosition(position);
                init_platform_spinner(oilName);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                yt_list.clear();
                yt_list.add("请选择");
                InitSetSpinner(spinner_yt, yt_list);
            }
        });

        spinner_pt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                platName = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                platform_list.clear();
                platform_list.add("请选择");
                InitSetSpinner(spinner_pt, platform_list);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void initData() {
        init_company_spinner(); // 初始化公司列表
        init_system_spinner(0); //初始化年检系统列表
    }

    // 返回公司数据
    private void init_company_spinner (){
        // 获取公司列表
        final Spinner spinner_buss = findViewById(R.id.spinner_bussy);
        List<CompanyInfo> bussy_dataList = ServiceFactory.getCompanyInfoService().getCompanyList();
        bessy_list = new ArrayList<>();
        bessy_list.add("请选择");
        for (int i = 0; i < bussy_dataList.size(); i++) {
            CompanyInfo CompanyListItem = bussy_dataList.get(i);
            String companyName = CompanyListItem.getCompanyName();
            if (companyName != null && !companyName.equals("")) {
                bessy_list.add(companyName);
            }
        }
        InitSetSpinner(spinner_buss, bessy_list);
    }

    // 油田类型
    private void init_yt_spinner(String companyName) {
        // 请选择油田  ->>> 传入公司参数 companyName
        final Spinner spinner_yt = findViewById(R.id.spinner_yt);
        List<CompanyInfo> yt_dataList = ServiceFactory.getCompanyInfoService().getOilfieldList(companyName);
        yt_list = new ArrayList<>();
        yt_list.add("请选择");
        for (int i = 0; i < yt_dataList.size(); i++) {
            CompanyInfo CompanyListItem = yt_dataList.get(i);
            String getOilName = CompanyListItem.getOilfieldName();
            if(getOilName != null && !getOilName.equals("")){
                yt_list.add(getOilName);
            }
        }
        InitSetSpinner(spinner_yt, yt_list);
    }

    // 平台类型
    private void init_platform_spinner(String OilName) {
        // 请选择平台  ->>> 传入油田参数 OilName
        Spinner spinner_pt =  findViewById(R.id.spinner_pt);
        List<CompanyInfo> yt_dataList = ServiceFactory.getCompanyInfoService().getPlatformList(OilName);
        platform_list = new ArrayList<>();
        platform_list.add("请选择");
        for (int i = 0; i < yt_dataList.size(); i++) {
            CompanyInfo CompanyListItem = yt_dataList.get(i);
            String getPlatformName = CompanyListItem.getPlatformName();
            if(getPlatformName != null && !getPlatformName.equals("")){
                platform_list.add(getPlatformName);
            }
        }
        InitSetSpinner(spinner_pt, platform_list);
    }

    // 年检系统类型
    private void init_system_spinner(int index) {
        // 请选择系统
        Spinner spinner_sys =  findViewById(R.id.spinner_sys);
        // 根据页面滑动 区别返回系统参数 0 -> 年检 1-> 巡检
        List<CheckType> system_dataList;
        if (index == 0){
            system_dataList = ServiceFactory.getYearCheckService().getSystemNameData(); //获取年检系统名
        }else{
            system_dataList = ServiceFactory.getInspectionService().getSystemNameData();//获取巡检系统名
        }
        ArrayList<String> system_list = new ArrayList<>();
        system_list.add("请选择");
        for (int i = 0; i < system_dataList.size(); i++) {
            CheckType SystemListItem = system_dataList.get(i);
            String getSystemName = SystemListItem.getName();
            if(getSystemName != null && !getSystemName.equals("")){
                system_list.add(getSystemName);
            }
        }
        InitSetSpinner(spinner_sys, system_list);
    }


    // 下拉框
    private void InitSetSpinner(final Spinner spinner, ArrayList<String> list) {
        //适配器
        ArrayAdapter<String> arr_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
    }
}
