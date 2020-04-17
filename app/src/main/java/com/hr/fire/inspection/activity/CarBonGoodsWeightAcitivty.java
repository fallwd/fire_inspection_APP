package com.hr.fire.inspection.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.GoodsAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.HYLogUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CarBonGoodsWeightAcitivty extends AppCompatActivity {
    public static String CHECK_ID = "checklist_num";
    public static String CHECK_SYS_DATA = "check_sys_data";  //整个系统数据得对象
    public static String CHECK_DIVICE_ID = "check_divice_id"; //设备表得id
    private List<YearCheck> checkDataEasy;
    private IntentTransmit its;
    private long check_id;
    private long divice_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_goods_weight);
        requestPerssionAtFirst();
        initData();
        //动态申请权限
    }

    private void initData() {
        Intent intent = getIntent();
        check_id = intent.getLongExtra(CHECK_ID, 0);
        divice_id = intent.getLongExtra(CHECK_DIVICE_ID, 0);
        //之前页面传过来的数据
        its = (IntentTransmit) intent.getSerializableExtra(CHECK_SYS_DATA); //系统数据对象
        //药剂瓶和氮气瓶这种设备表，需要再请求一次这个方法，获取检查表id，然后带到getCheckDataEasy就行了
        List<CheckType> checkTypes = ServiceFactory.getYearCheckService().gettableNameData(check_id);
        if (checkTypes == null) {
            return;
        }
        //获取检查条目的数据,主要用于展示
        checkDataEasy = ServiceFactory.getYearCheckService().getCheckDataEasy(checkTypes.get(0).getId());
        //获取用户需要填写的数据
        Log.e("dong", "系统位号==" + its.number);
        List<YearCheckResult> checkResultDataEasy = ServiceFactory.getYearCheckService().getCheckResultDataEasy(divice_id, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
        if (checkResultDataEasy == null || checkResultDataEasy.size() == 0) {
            List<YearCheck> checkDataEasy = ServiceFactory.getYearCheckService().getCheckDataEasy(check_id);
            Log.e("dong", "checkDataEasy==" + checkDataEasy.toString());
        }
        HYLogUtil.getInstance().d(checkResultDataEasy.toString());
        Log.e("dong", "系统位置" + checkResultDataEasy.toString());
        //获取已有的检查结果的数据
        initView();
    }

    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        Button cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button submit_btn = findViewById(R.id.submit_btn);
        ListView list = findViewById(R.id.list);
        GoodsAdapter goodsAdapter = new GoodsAdapter(this, checkDataEasy);
        list.setAdapter(goodsAdapter);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ServiceFactory.getYearCheckService().insertCheckResultDataEasy();
            }
        });
    }

    private static final String[] permissions = {Manifest.permission.CAMERA};

    /**
     * 检查权限有没有获取
     */
    public void requestPerssionAtFirst() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                startRequestPermission();
            }
        }
    }

    // 开始提交请求权限
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        goToAppSetting();
                    } else
                        finish();
                } else {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 123);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    goToAppSetting();
                } else {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
