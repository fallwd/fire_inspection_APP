package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.SystemList;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FireReportActivity extends AppCompatActivity {
    private String[] pose_list = null;  //接收方数组
    private ArrayList<SystemList> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_report);
        checkPermissions();
        // 点击返回上一页
        ImageButton imageButton = (ImageButton) findViewById(R.id.backHome);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 设置起止事件
        Button start_time = (Button) findViewById(R.id.start_time);
        Button end_time = (Button) findViewById(R.id.end_time);
        start_time.setText(getData());
        end_time.setText(netCheckTime());
        final Spinner spinner_buss = (Spinner) findViewById(R.id.spinner_bussy);
        Spinner spinner_yt = (Spinner) findViewById(R.id.spinner_yt);
        Spinner spinner_sys = (Spinner) findViewById(R.id.spinner_sys);
        Spinner spinner_pt = (Spinner) findViewById(R.id.spinner_pt);
        Spinner spinner_per = (Spinner) findViewById(R.id.spinner_per);

//        Serializable bundleObject = getIntent().getBundleExtra("list").getSerializable("list");
        mList = (ArrayList<SystemList>) getIntent().getSerializableExtra("list");
//        Log.d("key22222222", String.valueOf(mList));
        //数据
        ArrayList<String> data_list = new ArrayList<>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");

        // 初始化下拉框，并监听事件
        InitSetSpinner(spinner_buss, data_list);
        InitSetSpinner(spinner_yt, data_list);
        InitSetSpinner(spinner_sys, data_list);
        InitSetSpinner(spinner_pt, data_list);
        InitSetSpinner(spinner_per, data_list);


        //设置样式
        FireReportItemAdapter fireReportItemAdapter = new FireReportItemAdapter(this);
        fireReportItemAdapter.setData(mList);
        //加载适配器
        ListView main_list2 = (ListView) findViewById(R.id.main_list2);
        main_list2.setAdapter(fireReportItemAdapter);
        main_list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Long id1 = mList.get(position).getId();
                final String itemCon = mList.get(position).getName();
                Toast.makeText(FireReportActivity.this, "当前id为：" + id1, Toast.LENGTH_SHORT).show();
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
                String data = (String) spinner.getItemAtPosition(position);//从spinner中获取被选择的数据
//                Toast.makeText(FireReportActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private boolean isNeedCheck = true;
    private static String[] needPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static final int PERMISSON_REQUESTCODE = 0;

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

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
//                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

}
