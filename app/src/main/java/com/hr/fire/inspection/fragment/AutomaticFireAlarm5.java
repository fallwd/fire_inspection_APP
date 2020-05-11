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
import com.hr.fire.inspection.adapter.AutomaticFireAlarmAdapter2;
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


public class AutomaticFireAlarm5 extends Fragment {
    View rootView;
    private static AutomaticFireAlarm5 fragment1;
    private static String mKey;
    private AutomaticFireAlarmAdapter2 adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView hz_table_tbody_id2;
    private IntentTransmit it;
    private int imgPostion = -1;   //用户点击拍照, 所对应的位置
    private List<CheckType> checkTypes;

    public static AutomaticFireAlarm5 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new AutomaticFireAlarm5();
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
            rootView = inflater.inflate(R.layout.acitivty_automatic_fire_alarm2, container, false);
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
        long checkTypeId = arr.get(4).getId();


        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(4).getId(), it.number == null ? "" : it.number, it.srt_Date);
        HYLogUtil.getInstance().d("设备表信息,数据查看:" + itemDataList.size() + "  " + itemDataList.toString());
        // 一级表插入数据insertItemData


    }

    private void initView() {

        if (itemDataList.size() == 0) {
            Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
        }
        hz_table_tbody_id2 = rootView.findViewById(R.id.hz_table_tbody_id2);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        hz_table_tbody_id2.setLayoutManager(layoutManager);
        adapter = new AutomaticFireAlarmAdapter2(getActivity(), itemDataList);
        hz_table_tbody_id2.setAdapter(adapter);
        //添加动画
        hz_table_tbody_id2.setItemAnimator(new DefaultItemAnimator());
        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(4).getId(), it);
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
                itemInfo.setSetAlarm25(item.getSetAlarm25());
                itemInfo.setSetAlarm50(item.getSetAlarm50());
                itemInfo.setTestAlarm25(item.getTestAlarm25());
                itemInfo.setTestAlarm50(item.getTestAlarm50());
                itemInfo.setResponseTime(item.getResponseTime());
                itemInfo.setIsPass(item.getIsPass());
                itemInfo.setDescription(item.getDescription());
                itemInfo.setProdFactory(item.getProdFactory());
            } else {
                //点击新增,如果没有数据,就造一条默认数据
                itemInfo.setNo("请添加");
                itemInfo.setDeviceType("请添加");
                itemInfo.setTypeNo("请添加");
                itemInfo.setAppearance("请添加");
                itemInfo.setSetAlarm25("请添加");
                itemInfo.setSetAlarm50("请添加");
                itemInfo.setTestAlarm25("请添加");
                itemInfo.setTestAlarm50("请添加");
                itemInfo.setResponseTime("请添加");
                itemInfo.setIsPass("请选择");
                itemInfo.setDescription("请添加");
                itemInfo.setProdFactory("请添加");
            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, it.companyInfoId, checkTypes.get(4).getId(), it.number, it.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(4).getId(), it.number == null ? "" : it.number, it.srt_Date);
                adapter.setNewData(itemDataList);
            } else {
                ToastUtil.show(getActivity(), "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }


    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //点击"＋", 就像数据库中插入一条数据, 点"保存"就更新所有数据
    public void addData() {
        int childCount = hz_table_tbody_id2.getChildCount();
        if (childCount == 0) {
            return;
        }
        //这些数据需要从上层传参过来
        ItemInfo itemObj = new ItemInfo();
        LinearLayout childAt = (LinearLayout) hz_table_tbody_id2.getChildAt(childCount - 1);
        TextView tv_1 = childAt.findViewById(R.id.tv_1);
        EditText et_2 = childAt.findViewById(R.id.et_2);
        EditText et_3 = childAt.findViewById(R.id.et_3);
        EditText et_4 = childAt.findViewById(R.id.et_4);
        EditText et_5 = childAt.findViewById(R.id.et_5);
        TextView et_6 = childAt.findViewById(R.id.et_6);
        EditText et_7 = childAt.findViewById(R.id.et_7);
        EditText et_8 = childAt.findViewById(R.id.et_8);
        EditText et_9 = childAt.findViewById(R.id.et_9);
        EditText et_10 = childAt.findViewById(R.id.et_10);
        EditText et_11= childAt.findViewById(R.id.et_11);
        TextView et_12 = childAt.findViewById(R.id.et_12);
        ImageView et_13 = childAt.findViewById(R.id.et_13);
        EditText et_14 = childAt.findViewById(R.id.et_14);



        itemObj.setDeviceType(et_2.getText().toString());
        itemObj.setProdFactory(et_3.getText().toString());
        itemObj.setTypeNo(et_4.getText().toString());
        itemObj.setNo(et_5.getText().toString());
        itemObj.setAppearance(et_6.getText().toString());
        itemObj.setSetAlarm25(et_7.getText().toString());
        itemObj.setSetAlarm50(et_8.getText().toString());
        itemObj.setTestAlarm25(et_9.getText().toString());
        itemObj.setTestAlarm50(et_10.getText().toString());
        itemObj.setResponseTime(et_11.getText().toString());
        itemObj.setIsPass(et_12.getText().toString());
//        照片怎么上传 怎么获取
        itemObj.setDescription(et_14.getText().toString());
        itemObj.setCheckDate(new Date());

        long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj, it.companyInfoId, checkTypes.get(4).getId(), it.number, it.srt_Date);
        if (l1 == 0) {
            Toast.makeText(getContext(), "药剂瓶数据保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void upData() {
        int itemCount = hz_table_tbody_id2.getChildCount();
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(4).getId(), it.number == null ? "" : it.number, it.srt_Date);

        Log.d("dong", "upData==   " + itemCount + "   新的数据条数   " + itemDataList.size());
        if (itemCount == 0 || itemDataList.size() == 0 || itemDataList.size() != itemCount) {
            Toast.makeText(getActivity(), "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) hz_table_tbody_id2.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            TextView et_6 = childAt.findViewById(R.id.et_6);
            EditText et_7 = childAt.findViewById(R.id.et_7);
            EditText et_8 = childAt.findViewById(R.id.et_8);
            EditText et_9 = childAt.findViewById(R.id.et_9);
            EditText et_10 = childAt.findViewById(R.id.et_10);
            EditText et_11= childAt.findViewById(R.id.et_11);
            TextView et_12 = childAt.findViewById(R.id.et_12);
            ImageView et_13 = childAt.findViewById(R.id.et_13);
            EditText et_14 = childAt.findViewById(R.id.et_14);

            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setDeviceType(et_2.getText().toString());
            itemObj.setProdFactory(et_3.getText().toString());
            itemObj.setTypeNo(et_4.getText().toString());
            itemObj.setNo(et_5.getText().toString());
            itemObj.setAppearance(et_6.getText().toString());
            itemObj.setSetAlarm25(et_7.getText().toString());
            itemObj.setSetAlarm50(et_8.getText().toString());
            itemObj.setTestAlarm25(et_9.getText().toString());
            itemObj.setTestAlarm50(et_10.getText().toString());
            itemObj.setResponseTime(et_11.getText().toString());
            itemObj.setIsPass(et_12.getText().toString());
    //      et_13 照片怎么上传 怎么获取
            itemObj.setDescription(et_14.getText().toString());
            Log.d("dong", "itemObj222222保存==   "+itemObj);
            ServiceFactory.getYearCheckService().update(itemObj);
            Toast.makeText(getActivity(), "数据保存成功", Toast.LENGTH_SHORT).show();
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