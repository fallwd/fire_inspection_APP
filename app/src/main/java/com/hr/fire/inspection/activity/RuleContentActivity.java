package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.hr.fire.inspection.R;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.utils.HYLogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RuleContentActivity extends AppCompatActivity {
    public static final String TAG = "RuleContentActivity";
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listItems2 = new ArrayList<String>();
    private File[] wordfile;
    String[] wordlist, wordlist2;
    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_content);

        TextView textcont = (TextView) findViewById(R.id.textcont);
        Bundle b = getIntent().getExtras();
        //获取Bundle的信息
        String infocont = b.getString("context");
        String infoid = b.getString("id");
        textcont.setText(infocont);


        list = (ListView) findViewById(R.id.list);
        // /storage/emulated/0
        File sdcard = Environment.getExternalStorageDirectory();
        Log.d("dong", "sdcard= " + sdcard.getAbsolutePath());
        wordfile = sdcard.listFiles();

        for (int i = 0; i < wordfile.length; i++) {
            Log.i(TAG, "11111111111111111111111directory_pictures=" + wordfile[i]);
            if (wordfile[i].isDirectory()) {
                walkdir(wordfile[i]);
            } else {
                if (wordfile[i].getAbsolutePath().endsWith(".pdf")) {

                    listItems.add(wordfile[i].getName());
                    listItems2.add(wordfile[i].getAbsolutePath());

                } else if (wordfile[i].getAbsolutePath().endsWith(".doc")) {
                    listItems.add(wordfile[i].getName());
                    listItems2.add(wordfile[i].getAbsolutePath());
                } else if (wordfile[i].getAbsolutePath().endsWith(".txt")) {
                    listItems.add(wordfile[i].getName());
                    listItems2.add(wordfile[i].getAbsolutePath());
                } else if (wordfile[i].getAbsolutePath().endsWith(".docx")) {
                    listItems.add(wordfile[i].getName());
                    listItems2.add(wordfile[i].getAbsolutePath());
                } else if (wordfile[i].getAbsolutePath().endsWith(".xls")) {
                    listItems.add(wordfile[i].getName());
                    listItems2.add(wordfile[i].getAbsolutePath());
                }
            }
        }

        wordlist = new String[listItems.size()];
        wordlist = listItems.toArray(wordlist);
        wordlist2 = new String[listItems2.size()];
        wordlist2 = listItems2.toArray(wordlist2);

        adapter = new ArrayAdapter<String>(this, R.layout.listitemsimple,
                R.id.txtsimpleitem, wordlist);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(RuleContentActivity.this,
                        "Filepath::::" + wordlist2[arg2], Toast.LENGTH_LONG).show();
                File stringtofile = new File(wordlist2[arg2]);
                PackageManager packageManager = getPackageManager();
                Intent testIntent = new Intent(Intent.ACTION_VIEW);
                testIntent.setType("application/msword");
                List list = packageManager.queryIntentActivities(testIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);

                if (stringtofile.isFile()) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_VIEW);

//                    Uri uri = Uri.fromFile(stringtofile.getAbsoluteFile());
                    Uri uri = FileProvider.getUriForFile(RuleContentActivity.this, getApplication().getApplicationContext().getPackageName() + ".fileProvider", stringtofile);
                    intent.setDataAndType(uri, "application/pdf");

                    startActivity(intent);
                }
            }
        });
    }


    public void walkdir(File dir) {
        String pdfPattern = ".pdf";
        String docFile = ".doc";
        String txtFile = ".txt";
        String docxFile = ".docx";
        String excelFile = ".xls";

        File listFile[] = dir.listFiles();

        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    walkdir(listFile[i]);
                } else {
                    if (listFile[i].getAbsolutePath().endsWith(pdfPattern)) {
                        // Do what ever u want
                        listItems.add(listFile[i].getName());
                        listItems2.add(listFile[i].getAbsolutePath());
                        System.out.println(listFile[i]);

                    } else if (listFile[i].getAbsolutePath().endsWith(docFile)) {
                        listItems.add(listFile[i].getName());
                        listItems2.add(listFile[i].getAbsolutePath());
                        System.out.println(listFile[i]);
                    } else if (listFile[i].getAbsolutePath().endsWith(txtFile)) {
                        listItems.add(listFile[i].getName());
                        listItems2.add(listFile[i].getAbsolutePath());
                        System.out.println(listFile[i]);
                    } else if (listFile[i].getAbsolutePath().endsWith(docxFile)) {
                        listItems.add(listFile[i].getName());
                        listItems2.add(listFile[i].getAbsolutePath());
                        System.out.println(listFile[i]);
                    } else if (listFile[i].getAbsolutePath().endsWith(excelFile)) {
                        listItems.add(listFile[i].getName());
                        listItems2.add(listFile[i].getAbsolutePath());
                        System.out.println(listFile[i]);
                    }
                }
            }
        }
    }

}
