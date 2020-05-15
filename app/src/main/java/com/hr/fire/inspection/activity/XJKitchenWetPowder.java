package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.XJFAGMContentApapter;
import com.hr.fire.inspection.adapter.XJKitchenWetPowderColumnAdapter;
import com.hr.fire.inspection.adapter.XJKitchenWetPowderContentAdapter;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.impl.YCCamera;
import com.hr.fire.inspection.service.impl.InspectionServiceImpl;
import com.hr.fire.inspection.utils.FileRoute;
import com.hr.fire.inspection.utils.TextSpannableUtil;
import com.hr.fire.inspection.utils.ToastUtil;
import com.hr.fire.inspection.view.tableview.HListViewScrollView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class XJKitchenWetPowder extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_finish;
    private TextView tv_inspection_pro;
    private TextView iv_save;
    private TextView tv_xh_title;
    private ImageView iv_add_table;
    private HListViewScrollView chs_datagroup;
    private RecyclerView rl_firstColumn;
    private RecyclerView rl_content;
    private long systemId;
    private long companyInfoId;
    private String str_title;
    private String duty;
    private String check_name;
    private String check_date;
    Date parse_check_date = null;
    private String srt_date;
    private List<InspectionResult> inspectionResults;
    private InspectionServiceImpl service;
    private XJKitchenWetPowderColumnAdapter firstColumnApapter;
    private XJKitchenWetPowderContentAdapter contentApapter;
    private TextView tvInspectionPro;
    private Context mContent;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final int TAKE_PHOTO = 1;//拍照
    private int imgPostion = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xj_kitchen_wet_powder);
        mContent = getApplicationContext();
        getIntentData();
        initData();
        initView();
        initAdapter();
        setSlide();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        systemId = intent.getLongExtra("systemId", -1);
        companyInfoId = intent.getLongExtra("platform_id", -1);
        str_title = intent.getStringExtra("str_title"); //系统名称 :高压二氧化碳灭火系统
        duty = intent.getStringExtra("duty");  // 专业
        check_name = intent.getStringExtra("check_name"); // 检查人
        check_date = intent.getStringExtra("check_date"); //用户选择的时间
        //测试用, 因为前面传过来的时间格式有问题
        check_date = "2020-04-23 18:21";


        try {
            //这个解析方式是没有问题的 ,需要保证前面传入的数据是 2020-04-23 18:21 格式
            parse_check_date = sdf.parse(check_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        srt_date = intent.getStringExtra("srt_Date");   //检查日期,用户没选择,就是表示是新建
    }

    private void initData() {
        service = new InspectionServiceImpl();
        inspectionResults = service.getInspectionData(companyInfoId, systemId, parse_check_date);
        Log.d("dong", "inspectionData == " + inspectionResults.size());
        if (inspectionResults != null && inspectionResults.size() != 0) {
            Log.d("dong", "inspectionData == -----   " + inspectionResults.get(0).toString());
        }
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
        iv_finish.setOnClickListener(this);
        iv_add_table.setOnClickListener(this);
        iv_save.setOnClickListener(this);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        String text = new StringBuilder().append("消防巡检>厨房湿粉灭火系统").toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 0, text.length());
        tvInspectionPro.setText(showTextColor);
    }

    @SuppressLint("WrongConstant")
    private void initAdapter() {
        //创建线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_firstColumn.setLayoutManager(mLayoutManager);
        firstColumnApapter = new XJKitchenWetPowderColumnAdapter(this, inspectionResults);
        rl_firstColumn.setAdapter(firstColumnApapter);


        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager2.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_content.setLayoutManager(mLayoutManager2);
        contentApapter = new XJKitchenWetPowderContentAdapter(this, inspectionResults);
        rl_content.setAdapter(contentApapter);
        contentApapter.setmYCCamera(new YCCamera() {
            @Override
            public void startCamera(int postion) {
                imgPostion = postion;
                openSysCamera(mContent);
            }
        });
        //刷新序号列表
        contentApapter.setDeleteRefresh(new XJKitchenWetPowderContentAdapter.RemoveXH() {
            @Override
            public void deleteRefresh(int postion) {
                firstColumnApapter.notifyDataSetChanged();
            }
        });
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.iv_add_table:
                addItemView();
                break;
            case R.id.iv_save:
                saveToUpdara();
                break;
        }
    }

    /**
     * 一  点击增加:分两种情况
     * 1.是没有数据的时候,  默认给用户增加一条数据,插入到数据库
     * 2.有数据的时候,  默认给用户增加与最后一条数据一样的数据
     * 二:增加数据成功后,刷新适配器adapter
     */
    private void addItemView() {
        if (rl_firstColumn != null && rl_content != null && inspectionResults != null) {
            InspectionResult result = new InspectionResult();
            if (inspectionResults.size() != 0) {
                Log.d("dong", "默认有数据==之后吧");
                //有数据的时候,拿到最后一条数据进行填充
                InspectionResult item = inspectionResults.get(inspectionResults.size() - 1);
                result.setProfession(item.getProfession());
                result.setCheckPerson(item.getCheckPerson());
                result.setCheckDate(item.getCheckDate());
                result.setDescription(item.getDescription());
                result.setImgPath(item.getImgPath());
                result.setParam1(item.getParam1());
                result.setParam2(item.getParam2());
                result.setParam3(item.getParam3());
                result.setParam4(item.getParam4());
                result.setParam5(item.getParam5());
                result.setParam6(item.getParam6());
                result.setParam7(item.getParam7());
                result.setParam8(item.getParam8());
                result.setParam9(item.getParam9());
                result.setParam10(item.getParam10());

            } else {
                result.setProfession(duty);
                result.setCheckPerson(check_name);
                result.setCheckDate(parse_check_date);
                result.setDescription("暂无");
                result.setImgPath("暂无图片");
                result.setParam1("是");
                result.setParam2("是");
                result.setParam3("是");
                result.setParam4("是");
                result.setParam5("是");
                result.setParam6("是");
                result.setParam7("是");
                result.setParam8("否");
                result.setParam9("否");
                result.setParam10("请输入");
            }
            long l = service.insertInspectionData(result, companyInfoId, systemId, parse_check_date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l == 0) {
                inspectionResults = service.getInspectionData(companyInfoId, systemId, parse_check_date);
                Log.d("dong", "inspectionResults=  " + inspectionResults.size() + "  " + inspectionResults.get(0).getParam2());
                firstColumnApapter.setNewData(inspectionResults);
                contentApapter.setNewData(inspectionResults);
            } else {
                ToastUtil.show(this, "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }

    /**
     * 流程说明:
     * 我们在点击加号的时候, 就从数据量库中插入了数据,
     * 我们在Updara的时候先查询,如果没有没有数据就说明无数据更新  , 如果有就进行更新,
     */
    private void saveToUpdara() {
        if (inspectionResults == null || inspectionResults.size() == 0) {
            Toast.makeText(this, "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }
        int itemCount = rl_content.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) rl_content.getChildAt(i);

            TextView tv_gas1 = childAt.findViewById(R.id.tv_gas1);
            TextView tv_gas2 = childAt.findViewById(R.id.tv_gas2);
            TextView tv_gas3 = childAt.findViewById(R.id.tv_gas3);
            TextView tv_gas4 = childAt.findViewById(R.id.tv_gas4);
            TextView tv_gas5 = childAt.findViewById(R.id.tv_gas5);
            TextView tv_gas6 = childAt.findViewById(R.id.tv_gas6);
            TextView tv_gas7 = childAt.findViewById(R.id.tv_gas7);
            TextView tv_gas8 = childAt.findViewById(R.id.tv_gas8);
            TextView tv_gas9 = childAt.findViewById(R.id.tv_gas9);
            TextView tv_gas10 = childAt.findViewById(R.id.tv_gas10);
            EditText et_fire10 = childAt.findViewById(R.id.et_fire10);


            InspectionResult itemObj = inspectionResults.get(i);
            itemObj.setProfession(itemObj.getProfession());
            itemObj.setCheckPerson(itemObj.getCheckPerson());
            itemObj.setCheckDate(itemObj.getCheckDate());
            itemObj.setDescription(et_fire10.getText().toString());
            itemObj.setParam1(tv_gas1.getText().toString());
            itemObj.setParam2(tv_gas2.getText().toString());
            itemObj.setParam3(tv_gas3.getText().toString());
            itemObj.setParam4(tv_gas4.getText().toString());
            itemObj.setParam5(tv_gas5.getText().toString());
            itemObj.setParam6(tv_gas6.getText().toString());
            itemObj.setParam7(tv_gas7.getText().toString());
            itemObj.setParam8(tv_gas8.getText().toString());
            itemObj.setParam9(tv_gas9.getText().toString());
            itemObj.setParam10(tv_gas10.getText().toString());

            service.update(itemObj);
        }
        Toast.makeText(this, "数据保存成功", Toast.LENGTH_SHORT).show();
    }


    private File fileNew = null;
    /**
     * 打开系统相机
     */
    public void openSysCamera(Context mContent)  {

        // intent用来启动系统自带的Camera
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            fileNew = new FileRoute(mContent).createOriImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imgUriOri = null;
        if (fileNew != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imgUriOri = Uri.fromFile(fileNew);
            } else {
                imgUriOri = FileProvider.getUriForFile(mContent, mContent.getApplicationContext().getPackageName() + ".fileProvider", fileNew);
            }
            // 将系统Camera的拍摄结果写入到文件
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri);
            startActivityForResult(cameraIntent, FileRoute.CAMERA_RESULT_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FileRoute.CAMERA_RESULT_CODE:
                //这里目前需要适配
                if (fileNew != null && imgPostion != -1 && contentApapter != null) {
                    inspectionResults.get(imgPostion).setImgPath(fileNew.getAbsolutePath());
                    contentApapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
