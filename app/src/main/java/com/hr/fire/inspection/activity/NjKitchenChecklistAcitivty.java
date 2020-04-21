package com.hr.fire.inspection.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.List;

public class NjKitchenChecklistAcitivty extends AppCompatActivity {
    public static String CHECK_ID = "checklist_num";
    public static String CHECK_SYS_DATA = "check_sys_data";  //整个系统数据得对象
    public static String CHECK_DIVICE_ID = "check_divice_id"; //设备表得id
    public static String CHECK_DIVICE = "check_divice"; //用来判断是哪个设备页面跳转进来的
    private List<YearCheck> checkDataEasy;
    private IntentTransmit its;
    private long check_id;
    private long divice_id;
    private Long item_id;
    private List<YearCheckResult> yearCheckResults;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_head);
        requestPerssionAtFirst();
        initData();
        //动态申请权限
    }

    private void initData() {
        Intent intent = getIntent();
        check_id = intent.getLongExtra(CHECK_ID, 0);
        divice_id = intent.getLongExtra(CHECK_DIVICE_ID, 0);
        item_id = intent.getLongExtra("item_id", 0);
        title = intent.getStringExtra(CHECK_DIVICE);
        //之前页面传过来的数据
        its = (IntentTransmit) intent.getSerializableExtra(CHECK_SYS_DATA); //系统数据对象
        Log.e("dong", "获取数据its" + its);
        //1.药剂瓶和氮气瓶这种设备表，需要再请求一次这个方法，获取检查表id，然后带到getCheckDataEasy就行了
        List<CheckType> checkTypes = ServiceFactory.getYearCheckService().gettableNameData(check_id);
        Log.e("dong", "获取数据checkTypes" + checkTypes);
        if (checkTypes == null) {
            return;
        }
        //2.获取检查条目的数据,主要用于展示
        checkDataEasy = ServiceFactory.getYearCheckService().getCheckDataEasy(checkTypes.get(0).getId());
        //3.获取用户需要填写的数据,如果没有数据,就需要插入的默认数据（流程4）。如果有数据就
        yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(divice_id, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
//        Log.e("dong", "获取用户填写的检查结果数据 系统位置" + yearCheckResults.size() + "  " + yearCheckResults.toString());
        if (yearCheckResults == null || yearCheckResults.size() == 0) {
            for (int i = 0; i < checkDataEasy.size(); i++) {
//                Log.d("dong", "第一次加载数据 = ");
                //3.进入系统就给用户默认插入两条数据, 用户点击保存时,就Updata数据库
                YearCheckResult ycr = new YearCheckResult();
                ycr.setIsPass(" -- ");
                ycr.setImageUrl("暂无");  //可以在iv7中获取
                ycr.setDescription("无描述");
                ycr.setSystemNumber(its.number);
                ycr.setProtectArea(" "); // 保护位号
                ycr.setCheckDate(its.srt_Date);  //检查日期
                ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, item_id, checkDataEasy.get(i).getId(), its.companyInfoId, check_id, its.number, its.srt_Date);
                yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(divice_id, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
            }
        }
        //获取已有的检查结果的数据
        initView();
    }

    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);
        Button cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button submit_btn = findViewById(R.id.submit_btn);
        final ListView list = findViewById(R.id.list);
        GoodsAdapter goodsAdapter = new GoodsAdapter(this, checkDataEasy, yearCheckResults);
        list.setAdapter(goodsAdapter);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int childCount = list.getChildCount();
//                Log.d("dong", "childCount==- " + childCount + "    数据条目 " + yearCheckResults.size());
                //两边的数据条数是一样的.
                if (yearCheckResults.size() == childCount) {
                    for (int i = 0; i < childCount; i++) {
                        LinearLayout childAt = (LinearLayout) list.getChildAt(i);
                        TextView tv6 = childAt.findViewById(R.id.tv6);
                        //图片参数
//                        TextView tv7 = childAt.findViewById(R.id.tv7);
                        ImageView iv7 = childAt.findViewById(R.id.iv7);
                        EditText ev8 = childAt.findViewById(R.id.ev8);

                        YearCheckResult yearCheckResult = yearCheckResults.get(i);
                        yearCheckResult.setIsPass(tv6.getText().toString().isEmpty() ? " -- " : tv6.getText().toString());
                        yearCheckResult.setImageUrl("暂无图片链接");  //可以在iv7中获取
                        yearCheckResult.setDescription(ev8.getText().toString().isEmpty() ? "无隐患描述" : ev8.getText().toString());
                        yearCheckResult.setSystemNumber(its.number);
                        yearCheckResult.setProtectArea(" "); // 保护位号
                        yearCheckResult.setCheckDate(its.srt_Date);  //检查日期
                        ServiceFactory.getYearCheckService().update(yearCheckResult);
                    }
                    Toast.makeText(NjKitchenChecklistAcitivty.this, "数据保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
