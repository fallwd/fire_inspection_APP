package com.hr.fire.inspection.adapter;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.deepoove.poi.XWPFTemplate;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.FireReportActivity;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.SystemList;

import org.apache.xmlbeans.GDuration;

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

public class FireReportItemAdapter extends BaseAdapter {

    private ArrayList<SystemList> stringArrayList;
    private final FireReportActivity mContext;
    private static final int PERMISSON_REQUESTCODE = 0;
    private boolean isNeedCheck = true;
    private static String[] needPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public FireReportItemAdapter(FireReportActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return stringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_fire_report_item, parent, false);
            holder = new ViewHolder();
            holder.viewBtn = (Button) convertView.findViewById(R.id.output_word);
            holder.sayTextView = (TextView) convertView.findViewById(R.id.dong_context);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sayTextView.setText(stringArrayList.get(position).getName());
        // 点击导出按钮 生成报告
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final String itemCon = stringArrayList.get(position).getName();
//                Log.d("key1111", itemCon);

                Map<String, Object> tempdatas = new HashMap<String, Object>() {{
                    put("name", itemCon); // 名称
                    put("Data", getData()); // 日期
                    put("nextCheckTime", netCheckTime()); // 下次检验日期
                    put("Facility_name", ""); // 设施名称Facility Name ->> ** 油田/公司
                    put("Place_of _Service", ""); // 检验地点  ->  ** 平台
                }};
                try {
                    initWordTem(itemCon,tempdatas);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }

    public void setData(ArrayList<SystemList> mList) {
        stringArrayList = mList;
    }

    static class ViewHolder {
        public Button viewBtn;
        TextView sayTextView;
    }


    // 生成报告 参数
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initWordTem(String itemCon, Map<String, Object> tempdatas) throws IOException {
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        InputStream open = mContext.getAssets().open("In_template.docx");
        XWPFTemplate template = XWPFTemplate.compile(open).render(tempdatas);


        try {
            FileOutputStream out;
            String path = Environment.getExternalStorageDirectory().getPath();
//            Log.d("path111111111", path);
            File file = new File(path + "/"+itemCon+".docx");
            Log.d("key", String.valueOf(file));
            out = new FileOutputStream(file);
            template.write(out);
            out.flush();
            out.close();
            template.close();
            Toast.makeText(mContext, "报告生成成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "报告生成失败", Toast.LENGTH_SHORT).show();
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
}
