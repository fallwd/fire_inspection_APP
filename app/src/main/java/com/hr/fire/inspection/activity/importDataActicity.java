package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class importDataActicity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    private ListView lvFiles;
    private File[] currentFiles;
    private AutoCompleteTextView search;
    private List<Map<String,Object>> listItems3 = new ArrayList<>();;
    // 记录当前的父文件夹
    File currentParent;
    private List<CompanyInfo> dataList;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_data);

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        lvFiles = this.findViewById(R.id.law_content_list);
        getSdcardFileList();

        // 点击列表
        lvFiles.setOnItemClickListener((adapterView, view, position, id) -> {
            // 如果用户单击了文件，直接返回，不做任何处理
            if (currentFiles[position].isFile()) {
                // 也可自定义扩展打开这个文件等
                File stringtofile = currentFiles[position];

                String path = stringtofile.toString().substring(stringtofile.toString().indexOf("/"));

                String[] nameList=path.split("[_]");

                for (int i = 0; i < nameList.length; i++) {
                    Log.i("AAAA","strarray:"+ nameList[i]);
                }

                dataList = ServiceFactory.getCompanyInfoService().getPlatformList(nameList[1]);
                Log.i("AAAAAAA", "这是平台的列表数据11111"+ dataList);
                CompanyInfo CompanyListItem = null;
                for (int i = 0; i<dataList.size(); i++) {
                    CompanyListItem = dataList.get(i);
                    Log.i("AAAA","CompanyListItem3333:"+ CompanyListItem);
                    Log.i("AAAA","CompanyListItem1111:"+ nameList[2]);
                    Log.i("AAAA","CompanyListItem2222:"+ CompanyListItem.getPlatformName());
                    if (CompanyListItem.getPlatformName() != null) {

                        if (nameList[2] == CompanyListItem.getPlatformName()) {
                            Log.i("AAAA","CompanyListItem当前数据:"+ CompanyListItem);
                        }
                    }
                }




//                ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
            }
        });

    }
    // 获取系统的SDCard的目录
    private void getSdcardFileList(){
        File sdcard = Environment.getExternalStoragePublicDirectory("ExportData");
        // 如果SD卡存在的话
        if (sdcard.exists()) {
            currentParent = sdcard;
            currentFiles = sdcard.listFiles();
            // 使用当前目录下的全部文件、文件夹来填充ListView
            inflateListView(currentFiles);
        }
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
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");

            System.out.println(dateFormat.format(new Date(modTime)));

            // 添加一个最后修改日期
            listItem.put("modify",  "修改日期：" + dateFormat.format(new Date(modTime)));
            listItems.add(listItem);
            Log.d("listItems", listItems+"");
        }
        // 缓存遍历列表数据
        listItems3 = listItems;
        setAdpterdata(listItems);
    }

    // 定义一个SimpleAdapter 渲染列表
    private void setAdpterdata(List<Map<String, Object>> listItems){
        SimpleAdapter adapter = new SimpleAdapter(
                importDataActicity.this, listItems, R.layout.listitemsimple,
                new String[] { "filename", "icon", "modify" }, new int[] {  R.id.file_name, R.id.icon, R.id.file_modify });
        // 填充数据集
        lvFiles.setAdapter(adapter);
    }
}
