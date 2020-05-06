package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.HiddenLibraryDetailAdapter2;
import com.hr.fire.inspection.adapter.XJFAGMContentApapter;
import com.hr.fire.inspection.adapter.XJFirstColumnApapter;
import com.hr.fire.inspection.entity.HiddenLibaryDetail;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.service.impl.InspectionServiceImpl;
import com.hr.fire.inspection.utils.TextSpannableUtil;
import com.hr.fire.inspection.utils.ToastUtil;
import com.hr.fire.inspection.view.tableview.HListViewScrollView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HiddenLibaryDetailActivity2 extends AppCompatActivity {
    private static final String TAG = "HiddenLibaryActivity";
    private List<String> titleList = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_finish;
    private TextView tvInspectionPro;
    private String str_title;
    private String duty;
    private String check_name;
    private String check_date;
    private HiddenLibaryDetail it;

    private List<InspectionResult> InspectionResult; //数据集合
    private long platformId;
    private long systemId;
    private HiddenLibraryDetailAdapter2 aaa;
    private LinearLayout layout_head;



    private TextView tv_inspection_pro;
    private TextView iv_save;
    private TextView tv_xh_title;
    private ImageView iv_add_table;
    private HListViewScrollView chs_datagroup;
    private RecyclerView rl_firstColumn;
    private RecyclerView rl_content;
    Date parse_check_date = null;
    private String srt_date;
    private InspectionServiceImpl service;
    private XJFirstColumnApapter firstColumnApapter;
    private XJFAGMContentApapter contentApapter;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final int TAKE_PHOTO = 1;//拍照
    private int imgPostion = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_library_detail2);
        layout_head = (LinearLayout) findViewById(R.id.layout_head);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View a1 = layoutInflater.inflate(R.layout.xj_fagm_layout,null);
        layout_head.addView(a1);
        getIntentData();
        initData();
        initView();
        initAdapter();
        setSlide();
    }





    private void getIntentData() {
//        Intent intent = getIntent();
//        systemId = intent.getLongExtra("systemId", -1);
//        companyInfoId = intent.getLongExtra("platform_id", -1);
//        str_title = intent.getStringExtra("str_title"); //系统名称 :高压二氧化碳灭火系统
//        duty = intent.getStringExtra("duty");  // 专业
//        check_name = intent.getStringExtra("check_name"); // 检查人
//        check_date = intent.getStringExtra("check_date"); //用户选择的时间
//        //测试用, 因为前面传过来的时间格式有问题
//        check_date = "2020-04-23 18:21";
//        try {
//            //这个解析方式是没有问题的 ,需要保证前面传入的数据是 2020-04-23 18:21 格式
//            parse_check_date = sdf.parse(check_date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        srt_date = intent.getStringExtra("srt_Date");   //检查日期,用户没选择,就是表示是新建

    }

    private void initData() {
//        service = new InspectionServiceImpl();
//        InspectionResult = service.getInspectionData(companyInfoId, systemId, parse_check_date);
//        Log.d("dong", "inspectionData == " + InspectionResult.size());
//        if (InspectionResult != null && InspectionResult.size() != 0) {
//            Log.d("dong", "inspectionData == -----   " + InspectionResult.get(0).toString());
//        }
        HashMap<Integer, String> map = (HashMap<Integer, String>) getIntent().getSerializableExtra("map");
        str_title = map.get("company") + " > " + map.get("system");
        it = new HiddenLibaryDetail();
        it.platformId = Long.valueOf(map.get("platformId"));
        it.systemId = Long.valueOf(map.get("systemId"));
        it.checkDate = map.get("checkDate");
        it.checkPerson = map.get("checkPerson");
        it.profession = map.get("profession");
        InspectionResult = ServiceFactory.getAnalysisService().getInspectionDetail(it.platformId, it.systemId,it.checkDate,it.checkPerson,it.profession);
    }

    private void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        tv_inspection_pro = findViewById(R.id.tv_inspection_pro);
        iv_save = findViewById(R.id.iv_save);
        tv_xh_title = findViewById(R.id.tv_xh_title);
        iv_add_table = findViewById(R.id.iv_add_table);
        chs_datagroup = findViewById(R.id.chs_datagroup); //横向滑动的vire
        rl_firstColumn = findViewById(R.id.rl_firstColumn);   //第一列
        rl_content = findViewById(R.id.rl_content);     //内容
//        iv_finish.setOnClickListener(this);
//        iv_add_table.setOnClickListener(this);
//        iv_save.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    private void initAdapter() {
        //创建线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_firstColumn.setLayoutManager(mLayoutManager);
        firstColumnApapter = new XJFirstColumnApapter(this, InspectionResult);
        rl_firstColumn.setAdapter(firstColumnApapter);


        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager2.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_content.setLayoutManager(mLayoutManager2);
        contentApapter = new XJFAGMContentApapter(this, InspectionResult);
        rl_content.setAdapter(contentApapter);
//        contentApapter.setmYCCamera(new XJFAGMContentApapter.YCCamera() {
//            @Override
//            public void startCamera(int postion) {
//                imgPostion = postion;
//                try {
//                    camera();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    //设置同步滑动
    private void setSlide() {
        rl_firstColumn.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rl_content.scrollBy(dx, dy);
                }
            }
        });
        rl_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rl_firstColumn.scrollBy(dx, dy);
                }
            }
        });
    }




    private Uri imgUri;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:  //拍照的回调
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory
                                .decodeStream(getContentResolver().openInputStream(imgUri));
                        // /external_path/Android/data/com.hr.fire.inspection/cache/1587460070369.jpg
                        String path = imgUri.getPath();
                        if (path != null && imgPostion != -1 && contentApapter != null) {
                            InspectionResult.get(imgPostion).setImgPath(path);
                            //TODO 会崩溃.
//                            contentApapter.notifyItemChanged(imgPostion);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
