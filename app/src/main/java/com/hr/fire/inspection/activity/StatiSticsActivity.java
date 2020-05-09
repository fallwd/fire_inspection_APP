package com.hr.fire.inspection.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.hr.fire.inspection.R;

import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;


import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TextSpannableUtil;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatiSticsActivity extends AppCompatActivity {

    private List<String> timeList = new ArrayList<String>();

    private Button clear_statisty;
    // 选中的年份
    private String selectTime="2019";
    // 选中的公司名称
    private String companyName="";
    // 选中的油田名称
    private String oilName="";
    // 选中的平台名称
    private String platName="";

    private List<HashMap> companyChartData;
    private List<HashMap> oilChartData;
    private List<HashMap> platformChartData;
    private List<HashMap> systemChartData;
    private TextView tvInspectionPro;

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

        final Spinner spinner_time = findViewById(R.id.spinner_time);
        clear_statisty = findViewById(R.id.clear_statisty);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);

        // 初始化时间选择器
        timeList.add("2019");
        timeList.add("2020");
        InitSetSpinner(spinner_time, (ArrayList<String>) timeList);

        // 清空事件
        HandelClear();

        String text = new StringBuilder().append("隐患库 > 统计分析").toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 0, text.length());
        tvInspectionPro.setText(showTextColor);

        // 定位到webview
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 默认传入公司数据
        companyChartData = ServiceFactory.getAnalysisService().getCompanyCountByYearCheck(selectTime);
        JSONArray companyChartResult = new JSONArray(companyChartData);

        mWebView.loadUrl("file:///android_asset/webview.html");

        mWebView.addJavascriptInterface(new MyJavascriptInterface(this), "injectedObject");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if(companyChartResult.length()==0){
                    Toast.makeText(StatiSticsActivity.this, "查询结果为空", Toast.LENGTH_SHORT).show();
                } else {
                    mWebView.loadUrl("javascript:runJs('" + true + "', '" + companyChartResult + "')");
                }
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
        spinner_time.setSelection(0, true);
        spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTime = (String) parent.getItemAtPosition(position);
                companyChartData = ServiceFactory.getAnalysisService().getCompanyCountByYearCheck(selectTime);
                JSONArray companyChartResult = new JSONArray(companyChartData);
                mWebView.loadUrl("javascript:runJs('" + true + "', '" + companyChartResult + "')");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                timeList.add("请选择年份");
                InitSetSpinner(spinner_time, (ArrayList<String>) timeList);
            }
        });
    }
    private void HandelClear(){
        clear_statisty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                companyChartData = ServiceFactory.getAnalysisService().getCompanyCountByYearCheck(selectTime);
                JSONArray companyChartResult = new JSONArray(companyChartData);
                mWebView.loadUrl("javascript:runJs('" + true + "', '" + companyChartResult + "')");
            }
        });
    }
    private void InitSetSpinner(final Spinner spinner, ArrayList<String> list) {
        //适配器
        ArrayAdapter<String> arr_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
    }
}
