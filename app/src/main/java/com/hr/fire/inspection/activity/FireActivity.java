package com.hr.fire.inspection.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;

import com.deepoove.poi.XWPFTemplate;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.FireItemAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.service.ServiceFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//年检中: 设备列表
public class FireActivity extends AppCompatActivity {
    private long platform_id;
    private List<CheckType> systemNameData;
    private static final int PERMISSON_REQUESTCODE = 0;
    private static String[] needPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private boolean isNeedCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        //上一个页面传入的平台ID
        platform_id = (long) getIntent().getLongExtra("Platform_ID", 0);
        initData();
    }


    private void initData() {
        systemNameData = ServiceFactory.getYearCheckService().getSystemNameData();
    }

    Intent intent = new Intent();

    @Override
    protected void onStart() {
        super.onStart();
        //列表
        ListView main_list = findViewById(R.id.main_list);
        FireItemAdapter fireItemAdapter = new FireItemAdapter(this);
        fireItemAdapter.setData(systemNameData);
        main_list.setAdapter(fireItemAdapter);

        //点击跳转详情页
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long sys_id = systemNameData.get(position).getId();
                String f_title = systemNameData.get(position).getName();
                //跳转详情
                if (f_title.equals("高压二氧化碳灭火系统") || f_title.equals("七氟丙烷气体灭火系统")
                        || f_title.equals("海水雨淋灭火系统") || f_title.equals("干粉灭火系统")
                        || f_title.equals("泡沫灭火系统")) {
                    intent.setClass(FireActivity.this, SystemTagProtectionAreaActivity.class);
                } else {
                    intent.putExtra("sys_number", ""); //改页面是没有这个参数的
                    intent.setClass(FireActivity.this, CarbondioxideRecordAcitivty.class);

                }
                intent.putExtra("sys_id", sys_id);
                intent.putExtra("platform_id", platform_id);
                intent.putExtra("f_title", f_title);
                Log.d("dong", "sys_id-----" + sys_id+ "platform_id-------"+platform_id+ "f_title--------"+f_title);
                startActivity(intent);
                //这个时候,需要关闭当前页面,并且关闭之前所有选择的页面
                finish();
            }
        });

        // 动态获取权限
        checkPermissions(needPermissions);
        // 点击生成报告
        Button pr_but = findViewById(R.id.product_report_button);
        pr_but.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // 点击跳转
                Intent intent = new Intent(FireActivity.this, FireReportActivity.class);
                // 页面传值
                startActivity(intent);
            }
        });

        // 点击返回上一页
        ImageView iv_finish = (ImageView) findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initWordTem() throws IOException {
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        InputStream open = this.getAssets().open("In_template.docx");

        Map<String, Object> datas = new HashMap<String, Object>() {{
            put("name", "大庆油田");
            put("Data", getData());
            put("nextCheckTime", netCheckTime());
        }};
        XWPFTemplate template = XWPFTemplate.compile(open).render(datas);
        FileOutputStream out;

        String path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(path + "/out_template.docx");
        Log.d("key", String.valueOf(file));

        try {
            out = new FileOutputStream(file);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String sim = dateFormat.format(date);
        Log.i("md", "推迟的时间为： " + sim);
        return sim;
    }

    ;


    /**
     * 获取手机时间  年/月/日
     **/

    private String getData() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String sim = dateFormat.format(date);
//        Log.i("md", "时间sim为： "+sim);
        return sim;
    }

    ;


    /**
     * 检查权限
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否有的权限都已经授权
     *
     * @param grantResults
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
//                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }
}