package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.hr.fire.inspection.adapter.NJMhqCloumnAdapter;
import com.hr.fire.inspection.adapter.NJMhqContentApapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.impl.YCCamera;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.service.impl.InspectionServiceImpl;
import com.hr.fire.inspection.utils.FileRoute;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;
import com.hr.fire.inspection.view.tableview.HListViewScrollView;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//巡检: 灭火器页面
public class NJFireExtinguisherActivity extends AppCompatActivity implements View.OnClickListener {
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
    private long platform_id;
    private String str_title;
    private Date check_date; // 检查时间

    Date srt_Date = null;
    private String srt_date;
    private String sys_number;
    private InspectionServiceImpl service;
    private NJMhqCloumnAdapter firstColumnApapter;
    private NJMhqContentApapter contentApapter;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final int TAKE_PHOTO = 1;//拍照
    private int imgPostion = -1;

    private List<CheckType> checkTypes;
    private List<ItemInfo> itemDataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nj_mhq_activity);
        getIntentData();
        initData();
        initView();
        initAdapter();
        setSlide();
    }

    private void getIntentData() {
        //历史中的companyInfoId  ,  systemId和再公司、平台那边传过来的都是一样的ID，使用哪一个都行
        Intent intent = getIntent();
        systemId = intent.getLongExtra("systemId", -1);
        companyInfoId = intent.getLongExtra("platform_id", -1);
        Date srt_Date1 = (Date) intent.getSerializableExtra("srt_Date");  //传过来的时间
        srt_Date = srt_Date1;
        str_title = intent.getStringExtra("str_title"); //系统名称 :高压二氧化碳灭火系统
        sys_number = intent.getStringExtra("sys_number"); //传过来的名称
        Log.d("dong", "systemId=" + systemId+ "companyInfoId="+companyInfoId+ "srt_Date="+ srt_Date + "str_title="+str_title+"sys_number= "+sys_number);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long nowTime = srt_Date.getTime();
        String d = format.format(nowTime);
        try {
            srt_Date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        check_date = srt_Date;
    }

    private void initData() {

        List<CheckType> arr = ServiceFactory.getYearCheckService().gettableNameData(systemId);
        long checkTypeId = arr.get(0).getId();


        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(systemId);
        Log.d("dong", "checkTypeId == -----   " + checkTypeId+ "checkTypes===" +checkTypes);
        if (checkTypes == null) {
            Toast.makeText(this, "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(companyInfoId, checkTypes.get(0).getId(), sys_number == null ? "" : sys_number, srt_Date);

        HYLogUtil.getInstance().d("设备表信息,数据查看:" + itemDataList.size() + "  " + itemDataList.toString());
        // 一级表插入数据insertItemData

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


        //显示顶部展示系统位号、保护区域、检查时间的LinearLayout
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.VISIBLE);
        // 系统位号文字显示
        LinearLayout sys_numberCode = (LinearLayout) this.findViewById(R.id.sys_number);
        sys_numberCode.setVisibility(View.GONE);
        // 保护区域文字显示
        LinearLayout protect_areaCode = (LinearLayout) this.findViewById(R.id.protect_area);
        protect_areaCode.setVisibility(View.GONE);
        // 检查时间文字显示
        LinearLayout check_dateCode = (LinearLayout) this.findViewById(R.id.check_date);
        check_dateCode.setVisibility(View.VISIBLE);
        TextView check_date_text = (TextView) this.findViewById(R.id.check_date_text);
        if (check_date == null) {
            check_date_text.setText("检查时间为空");
        } else {
            String mProdDate = DateFormatUtils.format(check_date,"yyyy-MM-dd");
            check_date_text.setText(mProdDate);
        }

    }

    @SuppressLint("WrongConstant")
    private void initAdapter() {
        //创建线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_firstColumn.setLayoutManager(mLayoutManager);
        firstColumnApapter = new NJMhqCloumnAdapter(this, itemDataList);
        rl_firstColumn.setAdapter(firstColumnApapter);


        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager2.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_content.setLayoutManager(mLayoutManager2);
        contentApapter = new NJMhqContentApapter(this, itemDataList);
        rl_content.setAdapter(contentApapter);
        contentApapter.setmYCCamera(new YCCamera() {
            @Override
            public void startCamera(int postion) {
                imgPostion = postion;
                openSysCamera();
            }
        });
        //刷新序号列表
        contentApapter.setDeleteRefresh(new NJMhqContentApapter.RemoveXH() {
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
        if (contentApapter != null) {
            ItemInfo itemInfo = new ItemInfo();
            if (itemDataList != null && itemDataList.size() != 0) {
                //点击新增,有数据,就拿到最后一条数据新增,创建一个新的对象
                ItemInfo item = itemDataList.get(itemDataList.size() - 1);
//                Log.d("dong", "item有数据时" + item);
                itemInfo.setTypeNo(item.getTypeNo());
                itemInfo.setDeviceType(item.getDeviceType());
                itemInfo.setLevel(item.getLevel());
                itemInfo.setTaskNumber(item.getTaskNumber());
                itemInfo.setProdFactory(item.getProdFactory());
                itemInfo.setProdDate(item.getProdDate());
                itemInfo.setTypeConformity(item.getTypeConformity());
                itemInfo.setPositionConformity(item.getPositionConformity());
                itemInfo.setAppearance(item.getAppearance());
                itemInfo.setIsPressure(item.getIsPressure());
                itemInfo.setEffectiveness(item.getEffectiveness());
                itemInfo.setObserveDate(item.getObserveDate());
                itemInfo.setIsPass(item.getIsPass());
                itemInfo.setLabelNo(item.getLabelNo());
                itemInfo.setImageUrl(item.getImageUrl());
                itemInfo.setDescription(item.getDescription());
                itemInfo.setCodePath(item.getCodePath());



//    型号 typeNo
//    灭火剂类型  deviceType
//    灭火级别	 level
//    工作代号  taskNumber
//    生产厂家	prodFactory
//    生产日期	  date prodDate
//    型号符合性	 typeConformity
//    位置符合性	 positionConformity
//    外观是否良好	 appearance
//    压力/重量是否合格	 isPressure
//    药剂有效性	 effectiveness
//    维修日期	Date observeDate
//    检测结果   isPass
//    检验标签	labelNo
//    照片 imageUrl
//    隐患描述  description
//    二维码 codePath


            } else {
                //没有数据造一段默认数据
                itemInfo.setTypeNo("请输入");
                itemInfo.setDeviceType("请选择");
                itemInfo.setLevel("请选择");
                itemInfo.setTaskNumber("请选择");
                itemInfo.setProdFactory("请输入");
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                long nowTime = date.getTime();
                String d = format.format(nowTime);
                try {
                    date = format.parse(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                itemInfo.setProdDate(date);
                itemInfo.setTypeConformity("请选择");
                itemInfo.setPositionConformity("请选择");
                itemInfo.setAppearance("请选择");
                itemInfo.setIsPressure("请选择");
                itemInfo.setEffectiveness("请选择");
//                itemInfo.setObserveDate(date);  // 甲方要求默认为空
                itemInfo.setIsPass("请选择");
                itemInfo.setLabelNo("请输入");
                itemInfo.setDescription("请输入");
            }

//            Log.d("dong", "itemInfo增加" + itemInfo);
//            Log.d("dong", "itemInfo增加" + companyInfoId + checkTypes.get(0).getId() + sys_number + srt_Date );

            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, companyInfoId, checkTypes.get(0).getId(), sys_number, srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(companyInfoId, checkTypes.get(0).getId(), sys_number == null ? "" : sys_number, srt_Date);
//                Log.d("dong", "itemDataList增加我要去传给adapter渲染啦啦" + itemDataList);
                contentApapter.setNewData(itemDataList);
                firstColumnApapter.setNewData(itemDataList);
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
        int itemCount = rl_content.getChildCount();

        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(companyInfoId, checkTypes.get(0).getId(), sys_number == null ? "" : sys_number, srt_Date);
//        Log.d("dong", "upData==   " + itemCount + "   新的数据条数   " + itemDataList.size());
        if (itemCount == 0 || itemDataList.size() == 0 || itemDataList.size() != itemCount) {
            Toast.makeText(this, "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) rl_content.getChildAt(i);

            EditText et_fire1 = childAt.findViewById(R.id.et_fire1);
            TextView tv_fire2 = childAt.findViewById(R.id.tv_fire2);
            TextView tv_fire3 = childAt.findViewById(R.id.tv_fire3);
            TextView tv_fire4 = childAt.findViewById(R.id.tv_fire4);
            EditText et_fire5 = childAt.findViewById(R.id.et_fire5);
            EditText et_fire6 = childAt.findViewById(R.id.et_fire6);
            TextView tv_fire7 = childAt.findViewById(R.id.tv_fire7);
            TextView tv_fire8 = childAt.findViewById(R.id.tv_fire8);
            TextView tv_fire9 = childAt.findViewById(R.id.tv_fire9);
            TextView tv_fire10 = childAt.findViewById(R.id.tv_fire10);
            TextView tv_fire11 = childAt.findViewById(R.id.tv_fire11);
            EditText et_fire12 = childAt.findViewById(R.id.et_fire12);
            TextView tv_fire13 = childAt.findViewById(R.id.tv_fire13);
            TextView tv_fire15 = childAt.findViewById(R.id.tv_fire15);
            EditText et_fire16 = childAt.findViewById(R.id.et_fire16);
            TextView et_fire14 = childAt.findViewById(R.id.et_fire14);
            ImageView tv_fire17 = childAt.findViewById(R.id.tv_fire17);


            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setTypeNo(et_fire1.getText().toString());
            itemObj.setDeviceType(tv_fire2.getText().toString());
            itemObj.setLevel(tv_fire3.getText().toString());
            itemObj.setTaskNumber(tv_fire4.getText().toString());
            itemObj.setProdFactory(et_fire5.getText().toString());
            Date date1 = TimeUtil.parse(et_fire6.getText().toString(),"yyyy-MM");
            itemObj.setProdDate(date1);
            itemObj.setTypeConformity(tv_fire7.getText().toString());
            itemObj.setPositionConformity(tv_fire8.getText().toString());
            itemObj.setAppearance(tv_fire9.getText().toString());
            itemObj.setIsPressure(tv_fire10.getText().toString());
            itemObj.setEffectiveness(tv_fire11.getText().toString());
            Date date2 = TimeUtil.parse(et_fire12.getText().toString(),"yyyy-MM");
            itemObj.setObserveDate(date2);
            itemObj.setIsPass(tv_fire13.getText().toString());
            itemObj.setLabelNo(et_fire14.getText().toString());
//            itemObj.setImageUrl(tv_fire15.getText().toString());
            itemObj.setDescription(et_fire16.getText().toString());
//            itemObj.setCodePath(tv_fire17.getText().toString());  // 二维码路径？？？
            Log.i("AAA","itemObj"+itemObj);
            ServiceFactory.getYearCheckService().update(itemObj);
        }
        Toast.makeText(this, "数据保存成功", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FileRoute.CAMERA_RESULT_CODE:
                //这里目前需要适配
                if (fileNew.getAbsolutePath() != null && imgPostion != -1 && contentApapter != null) {
                    itemDataList.get(imgPostion).setImageUrl(fileNew.getAbsolutePath());
//                    contentApapter.notifyItemChanged(imgPostion);

                    contentApapter.notifyDataSetChanged();
                }
                break;
        }
    }

    /**
     * 打开系统相机
     */
    private File fileNew = null;

    private void openSysCamera() {
        // intent用来启动系统自带的Camera
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            fileNew = new FileRoute(this).createOriImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imgUriOri = null;
        if (fileNew != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imgUriOri = Uri.fromFile(fileNew);
            } else {
                imgUriOri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".fileProvider", fileNew);
            }
            // 将系统Camera的拍摄结果写入到文件
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri);
            startActivityForResult(cameraIntent, FileRoute.CAMERA_RESULT_CODE);
        }
    }
}
