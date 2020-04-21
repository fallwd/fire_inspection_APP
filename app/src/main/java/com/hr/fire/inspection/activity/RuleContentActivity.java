package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.LawContentItemAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

public class RuleContentActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listItems2 = new ArrayList<>();
    private File[] wordfile;
    String[] wordlist, wordlist2;
    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_content);

        TextView textcont = findViewById(R.id.textcont);
        Bundle b = getIntent().getExtras();
        //获取Bundle的信息
        String infocont = b.getString("context");
        String infoid = b.getString("id");
        textcont.setText(infocont);


        String str_con = "火灾自动报警系统设计规范";
        String str_con2 = "自动喷水灭火系统设计规范";
        String str_con3 = "可燃气体探测报警系统设计规范";

        ArrayList<String> mList = new ArrayList<>();
        mList.add(str_con);
        mList.add(str_con2);
        mList.add(str_con3);
        //列表
        ListView main_list = findViewById(R.id.law_content_list);
        LawContentItemAdapter lawContentItemAdapter = new LawContentItemAdapter(this);
        lawContentItemAdapter.setData(mList);
        main_list.setAdapter(lawContentItemAdapter);

        // 点击返回上一页
        ImageButton imageButton = (ImageButton) findViewById(R.id.backHome);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
//                    // TODO Auto-generated method stub
//                    Toast.makeText(RuleContentActivity.this,
//                            "Filepath::::" + wordlist2[arg2], Toast.LENGTH_LONG).show();
//                    File stringtofile = new File(wordlist2[arg2]);
//                    PackageManager packageManager = getPackageManager();
//                    Intent testIntent = new Intent(Intent.ACTION_VIEW);
//                    testIntent.setType("application/msword");
//                    List list = packageManager.queryIntentActivities(testIntent,
//                            PackageManager.MATCH_DEFAULT_ONLY);
//
//                    if (stringtofile.isFile()) {
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        intent.addCategory("android.intent.category.DEFAULT");
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.setAction(Intent.ACTION_VIEW);
//
////                    Uri uri = Uri.fromFile(stringtofile.getAbsoluteFile());
//                        Uri uri = FileProvider.getUriForFile(RuleContentActivity.this, getApplication().getApplicationContext().getPackageName() + ".fileProvider", stringtofile);
//                        intent.setDataAndType(uri, "application/pdf");
//
//                        startActivity(intent);
//                    }
//                }
//            }
//        });
//        list = findViewById(R.id.list);
//        wordfile = getSDCardPath().listFiles();
//        // 获取sdcard里面pdf文件信息
//        Log.d("sdcard内PDF路径信息:", String.valueOf(wordfile));
//        Intent intent = getIntent();
//        Log.d("sdcard内PDF路径信息:",getSDCardPath());
//        CharSequence cs = intent.getCharSequenceExtra(getSDCardPath()); //filePath 为传入的文件路径信息
//        Log.d("sdcard内PDF路径信息:", String.valueOf(cs));
//        if (cs != null) {
//            File file = new File(cs.toString());
//            wordfile = file.listFiles();
//        } else {
//            File sdFile = Environment.getExternalStorageDirectory();
//            wordfile = sdFile.listFiles();
//        };

//        wordlist = new String[listItems.size()];
//        wordlist = listItems.toArray(wordlist);
//        wordlist2 = new String[listItems2.size()];
//        wordlist2 = listItems2.toArray(wordlist2);
//
//        adapter = new ArrayAdapter<String>(this, R.layout.listitemsimple,
//                R.id.txtsimpleitem, wordlist);
//        list.setAdapter(adapter);
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//                File stringtofile = new File(wordlist2[arg2]);
//                if (stringtofile.isFile()) {
//                    Uri uri = FileProvider.getUriForFile(RuleContentActivity.this, getApplication().getApplicationContext().getPackageName() + ".fileProvider", stringtofile);
//                    onpenPDF(uri);
//                }
            }
        });
    }}



//    private void onpenPDF(Uri pdfUrl) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(pdfUrl, "application/pdf");
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        try {
//            startActivity(intent);
//        }
//        catch (ActivityNotFoundException e) {
//            Toast.makeText(RuleContentActivity.this,"请先安装相关PDF程序", Toast.LENGTH_LONG).show();
////            ToastUtil.showSystemToast(this,"请先安装相关PDF程序");
//        }
//    }
//    public void walker(File dir) {
//        // 遍历文件夹，如果文件是pdf文件则存入数组
//        String pdfPattern = ".pdf";
//        File listFile[] = dir.listFiles();
//        if (listFile != null) {
//            for (File file : listFile) {
//
//                if (file.isDirectory()) {
//                    walker(file);
//                } else {
//                    if (file.getAbsolutePath().endsWith(pdfPattern)) {
//                        // Do what ever u want
//                        listItems.add(file.getName());
//                        listItems2.add(file.getAbsolutePath());
//                        System.out.println(file);
//                    }
//                }
//            }
//        }
//    }

//    /**
//          * 获取SDCard的目录路径功能
//          * @return
//         
//     * @return*/
//        private File getSDCardPath(){
//            File sdcardDir = null;
//            //判断SDCard是否存在
//            boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
//            if(sdcardExist){
//            sdcardDir = Environment.getExternalStorageDirectory();
//            }else{
//                Toast.makeText(RuleContentActivity.this, "SD卡未挂载", Toast.LENGTH_SHORT).show();
//                RuleContentActivity.this.finish();
//            }
//            return sdcardDir;
//        }