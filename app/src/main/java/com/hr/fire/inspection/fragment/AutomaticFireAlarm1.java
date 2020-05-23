package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.AutomaticFireAlarmAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.impl.YCCamera;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.FileRoute;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.ToastUtil;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class AutomaticFireAlarm1 extends Fragment {
    View rootView;
    private static AutomaticFireAlarm1 fragment1;
    private static String mKey;
    private AutomaticFireAlarmAdapter adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView hz_table_tbody_id;
    private IntentTransmit it;
    private int imgPostion = -1;   //用户点击拍照, 所对应的位置
    private List<CheckType> checkTypes;

    public static AutomaticFireAlarm1 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new AutomaticFireAlarm1();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment1.setArguments(args);
        return fragment1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            it = (IntentTransmit) getArguments().getSerializable(mKey);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.acitivty_automatic_fire_alarm1, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        List<CheckType> arr = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        long checkTypeId = arr.get(0).getId();


        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(0).getId(), it.number == null ? "" : it.number, it.srt_Date);
        HYLogUtil.getInstance().d("设备表信息,数据查看:" + itemDataList.size() + "  " + itemDataList.toString());
        // 一级表插入数据insertItemData


    }

    private void initView() {

        if (itemDataList.size() == 0) {
            Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
        }
        hz_table_tbody_id = rootView.findViewById(R.id.hz_table_tbody_id);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        hz_table_tbody_id.setLayoutManager(layoutManager);
        adapter = new AutomaticFireAlarmAdapter(getActivity(), itemDataList);
        hz_table_tbody_id.setAdapter(adapter);
        //添加动画
        hz_table_tbody_id.setItemAnimator(new DefaultItemAnimator());
        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(0).getId(), it);
        }
        adapter.setmYCCamera(new YCCamera() {
            @Override
            public void startCamera(int postion) {
                imgPostion = postion;
                openSysCamera();
            }
        });

    }

    //动态添加条目
    public void addItemView() {
        if (adapter != null) {
            ItemInfo itemInfo = new ItemInfo();
            if (itemDataList != null && itemDataList.size() != 0) {
                //点击新增,有数据,就拿到最后一条数据新增,创建一个新的对象
                ItemInfo item = itemDataList.get(itemDataList.size() - 1);
                //如果直接新增会导致后台id冲重复\冲突
                itemInfo.setNo(item.getNo());
                itemInfo.setDeviceType(item.getDeviceType());
                itemInfo.setTypeNo(item.getTypeNo());
                itemInfo.setAppearance(item.getAppearance());
                itemInfo.setResponseTime(item.getResponseTime());
                itemInfo.setIsPass(item.getIsPass());
                itemInfo.setDescription(item.getDescription());
                itemInfo.setProdFactory(item.getProdFactory());
                itemInfo.setImageUrl(item.getImageUrl());
                itemInfo.setUuid(UUID.randomUUID().toString().replace("-",""));
            } else {
                //点击新增,如果没有数据,就造一条默认数据
                itemInfo.setNo("请编辑");
                itemInfo.setDeviceType("请编辑");
                itemInfo.setTypeNo("请编辑");
                itemInfo.setResponseTime("请编辑");
                itemInfo.setIsPass("请选择");
                itemInfo.setDescription("请编辑");
                itemInfo.setProdFactory("请编辑");
                itemInfo.setAppearance("请编辑");
                itemInfo.setUuid(UUID.randomUUID().toString().replace("-",""));
            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, it.companyInfoId, checkTypes.get(0).getId(), it.number, it.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(0).getId(), it.number == null ? "" : it.number, it.srt_Date);
                adapter.setNewData(itemDataList);
            } else {
                ToastUtil.show(getActivity(), "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }


    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public void upData() {
        int itemCount = hz_table_tbody_id.getChildCount();
        //通知数据库刷新数据， 才能在调用Update();
        Log.d("dong", "it.companyInfoId111==   " + it.companyInfoId + "   checkTypes.get(0).getId()   " + checkTypes.get(0).getId() + "ITTTTTTTTTT" + it);


        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(0).getId(), it.number == null ? "" : it.number, it.srt_Date);

        Log.d("dong", "upData==   " + itemCount + "   新的数据条数   " + itemDataList.size());
        if (itemCount == 0 || itemDataList.size() == 0 || itemDataList.size() != itemCount) {
            Toast.makeText(getActivity(), "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) hz_table_tbody_id.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            EditText et_6 = childAt.findViewById(R.id.et_6);
            EditText et_7 = childAt.findViewById(R.id.et_7);
            TextView et_8 = childAt.findViewById(R.id.et_8);
            ImageView tv_9 = childAt.findViewById(R.id.tv_9);
            EditText tv_10 = childAt.findViewById(R.id.tv_10);

            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setDeviceType(et_2.getText().toString());
            itemObj.setProdFactory(et_3.getText().toString());
            itemObj.setTypeNo(et_4.getText().toString());
            itemObj.setNo(et_5.getText().toString());
            itemObj.setAppearance(et_6.getText().toString());
            itemObj.setResponseTime(et_7.getText().toString());
            itemObj.setIsPass(et_8.getText().toString());
            itemObj.setDescription(tv_10.getText().toString());

//            itemObj.setCheckDate(new Date());
//            序号  tv_1  瓶号 et_2     生产厂家et_6  生产时间et_7  合格et_8  拍照rl_9  隐患描述tv_10
            Log.d("dong", "itemObj222222保存==   " + itemObj);
            ServiceFactory.getYearCheckService().update(itemObj);
            Toast.makeText(getContext(), "数据保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            adapter = null;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FileRoute.CAMERA_RESULT_CODE:
                //这里目前需要适配
                if (fileNew.getAbsolutePath() != null && imgPostion != -1 && adapter != null) {
                    itemDataList.get(imgPostion).setImageUrl(fileNew.getAbsolutePath());
                    adapter.notifyItemChanged(imgPostion);
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
            fileNew = new FileRoute(getActivity()).createOriImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imgUriOri = null;
        if (fileNew != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imgUriOri = Uri.fromFile(fileNew);
            } else {
                imgUriOri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".fileProvider", fileNew);
            }
            // 将系统Camera的拍摄结果写入到文件
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri);
            startActivityForResult(cameraIntent, FileRoute.CAMERA_RESULT_CODE);
        }
    }
}