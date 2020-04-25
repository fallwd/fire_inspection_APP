package com.hr.fire.inspection.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.hr.fire.inspection.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleContentActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    private ListView lvFiles;
    private Button btnParent;
    private File[] currentFiles;
    private File[] searhResData;
    // 记录当前的父文件夹
    File currentParent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_content);

        lvFiles = (ListView) this.findViewById(R.id.law_content_list);
        EditText seach_text = (EditText)this.findViewById(R.id.seach_text);

        ArrayList<String> listItems = new ArrayList<String>();
        ArrayList<String> listItems2 = new ArrayList<>();

        TextView textcont = findViewById(R.id.textcont);
        Bundle b = getIntent().getExtras();
        //获取Bundle的信息
        String infocont = b.getString("context");
        textcont.setText(infocont);






        // 获取系统的SDCard的目录
        File sdcard = Environment.getExternalStorageDirectory();
        if (infocont.equals(" ")) {
            Toast.makeText(RuleContentActivity.this,"参数为空，请联系管理员", Toast.LENGTH_SHORT).show();
        } else {
            if (infocont.equals("行业标准")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/行业标准");
            } else if(infocont.equals("企业标准")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/企业标准");
            } else if(infocont.equals("国家法律")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/国家法律");
            } else if(infocont.equals("国际标准")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/国际标准");
            } else if(infocont.equals("行政规范")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/行政规范");
            } else if(infocont.equals("行业规章")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/行业规章");
            } else if(infocont.equals("国家标准")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/国家标准");
            } else if(infocont.equals("良好作业实践及指导性文件")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/良好作业实践及指导性文件");
            } else if(infocont.equals("监管性机构规范性文件")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/监管性机构规范性文件");
            } else if(infocont.equals("其他")) {
                sdcard = Environment.getExternalStoragePublicDirectory("Android/pdf/其他");
            }
        }

        // 如果SD卡存在的话
        if (sdcard.exists()) {
            currentParent = sdcard;
            currentFiles = sdcard.listFiles();
            // 使用当前目录下的全部文件、文件夹来填充ListView
            Log.i("md", "currentFiles11111111111" + currentFiles);
            inflateListView(currentFiles);
        }


        for (int i = 0; i<currentFiles.length; i++) {
            Log.i("data","获取全部数据" + currentFiles[i]);
        }

        seach_text.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable edit) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                File sdcard2 = Environment.getExternalStoragePublicDirectory("Android/pdf");
                searhResData = sdcard2.listFiles();

                for (int i = 0; i < searhResData.length; i++) {
//                    Log.i("123", "1111111111"+searhResData[i].getAbsolutePath().endsWith(text));

                }
            }

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count,int after) {

            }

        });

        // 点击列表
        lvFiles.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int position, long id) {
                // 如果用户单击了文件，直接返回，不做任何处理
                if (currentFiles[position].isFile()) {
                    // 也可自定义扩展打开这个文件等
                    File stringtofile = currentFiles[position];
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = FileProvider.getUriForFile(RuleContentActivity.this, getApplication().getApplicationContext().getPackageName() + ".fileProvider", stringtofile);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    try {
                        startActivity(intent);
                    }
                    catch (ActivityNotFoundException e) {
                        Log.i("md", "22222222222222222222");
                        Toast.makeText(RuleContentActivity.this,
                                "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                // 获取用户点击的文件夹 下的所有文件
                File[] tem = currentFiles[position].listFiles();
                if (tem == null || tem.length == 0) {
                    Toast.makeText(RuleContentActivity.this, "当前路径不可访问或者该路径下没有文件", Toast.LENGTH_LONG).show();
                } else {
                    // 获取用户单击的列表项对应的文件夹，设为当前的父文件夹
                    currentParent = currentFiles[position];
                    // 保存当前的父文件夹内的全部文件和文件夹
                    currentFiles = tem;
                    // 再次更新ListView
                    inflateListView(currentFiles);
                }
            }
        });
    }

    /**
     * 根据文件夹填充ListView
     *
     * @param files
     */

    private void inflateListView(File[] files) {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < files.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            if (files[i].isDirectory()) {
                // 如果是文件夹就显示的图片为文件夹的图片
                listItem.put("icon", R.drawable.bg_gray);
            } else {
                listItem.put("icon", R.drawable.bottom);
            }

            // 添加一个文件名称
            listItem.put("filename", files[i].getName());
            File myFile = new File(files[i].getName());

            // 获取文件的最后修改日期

            long modTime = myFile.lastModified();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");

            System.out.println(dateFormat.format(new Date(modTime)));

            // 添加一个最后修改日期
            listItem.put("modify",  "修改日期：" + dateFormat.format(new Date(modTime)));
            listItems.add(listItem);

        }
        // 定义一个SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(
                RuleContentActivity.this, listItems, R.layout.listitemsimple,
                new String[] { "filename", "icon", "modify" }, new int[] {  R.id.file_name, R.id.icon, R.id.file_modify });
        // 填充数据集
        lvFiles.setAdapter(adapter);
    }
}