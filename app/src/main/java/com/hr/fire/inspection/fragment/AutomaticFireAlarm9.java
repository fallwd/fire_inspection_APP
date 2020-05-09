package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.AutomaticFireAlarmAdapter4;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.BaseService;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.service.impl.YearCheckServiceImpl;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AutomaticFireAlarm9 extends Fragment {
    View rootView;
    private static AutomaticFireAlarm9 fragment1;
    private static String mKey;
    private AutomaticFireAlarmAdapter4 adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView hz_table_tbody_id2;
    private IntentTransmit it;
    private List<CheckType> checkTypes;

    public static AutomaticFireAlarm9 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new AutomaticFireAlarm9();
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
            rootView = inflater.inflate(R.layout.acitivty_automatic_fire_alarm4, container, false);
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
        long checkTypeId = arr.get(8).getId();


        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(8).getId(), it.number == null ? "" : it.number, it.srt_Date);
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
        adapter = new AutomaticFireAlarmAdapter4(getActivity(), itemDataList);
        hz_table_tbody_id2.setAdapter(adapter);
        //添加动画
        hz_table_tbody_id2.setItemAnimator(new DefaultItemAnimator());
        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(8).getId(), it);
        }
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
                itemInfo.setProdFactory(item.getProdFactory());
                itemInfo.setTypeNo(item.getTypeNo());
                itemInfo.setPositionConformity(item.getPositionConformity());
                itemInfo.setAppearance(item.getAppearance());
                itemInfo.setCheck(item.getCheck());
                itemInfo.setSlience(item.getSlience());
                itemInfo.setReset(item.getReset());
                itemInfo.setPowerAlarmFunction(item.getPowerAlarmFunction());
                itemInfo.setAlarmFunction(item.getAlarmFunction());
                itemInfo.setImageUrl(item.getImageUrl());
                itemInfo.setCodePath(item.getCodePath());
                itemInfo.setDescription(item.getDescription());

            } else {
                //点击新增,如果没有数据,就造一条默认数据
                itemInfo.setNo("请添加");
                itemInfo.setDeviceType("请添加");
                itemInfo.setProdFactory("请添加");
                itemInfo.setTypeNo("请添加");
                itemInfo.setPositionConformity("请添加");
                itemInfo.setAppearance("请添加");

                itemInfo.setCheck("请添加");
                itemInfo.setSlience("请添加");
                itemInfo.setReset("请添加");
                itemInfo.setPowerAlarmFunction("请添加");
                itemInfo.setAlarmFunction("请添加");
                itemInfo.setImageUrl("请添加");
                itemInfo.setCodePath("请添加");
                itemInfo.setDescription("请添加");



//            序号 1
//            设备位号5 no
//            设备类型2  DeviceType
//            生产厂家3 prodFactory
//            型号 4 typeNo
//            位置 6 positionConformity
//            外观是否良好 7 appearance
//            自检功能是否良好 et_8  check
//            消音功能是否良好 9 slience
//            复位功能是否良好 10 reset
//            主/备电源连线故障报警功能 11 powerAlarmFunction
//            报警功能是否正常 12 alarmFunction
//            照片 13 imageUrl
//            二维码 14 codePath
//            隐患描述 15 description




            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, it.companyInfoId, checkTypes.get(8).getId(), it.number, it.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(8).getId(), it.number == null ? "" : it.number, it.srt_Date);
                adapter.setNewData(itemDataList);
            } else {
                ToastUtil.show(getActivity(), "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }


    public void upData() {
        int itemCount = hz_table_tbody_id2.getChildCount();
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(8).getId(), it.number == null ? "" : it.number, it.srt_Date);

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
            EditText et_6 = childAt.findViewById(R.id.et_6);
            TextView et_7 = childAt.findViewById(R.id.et_7);
            TextView et_8 = childAt.findViewById(R.id.et_8);
            TextView et_9 = childAt.findViewById(R.id.et_9);
            TextView et_10 = childAt.findViewById(R.id.et_10);
            TextView et_11= childAt.findViewById(R.id.et_11);
            TextView et_12 = childAt.findViewById(R.id.et_12);
            ImageView et_13 = childAt.findViewById(R.id.et_13);
            ImageView et_14 = childAt.findViewById(R.id.et_14);
            EditText et_15 = childAt.findViewById(R.id.et_15);


            //            序号 1
//            设备位号5 no
//            设备类型2  DeviceType
//            生产厂家3 prodFactory
//            型号 4 typeNo
//            位置 6 positionConformity
//            外观是否良好 7 appearance
//            自检功能是否良好 et_8  check
//            消音功能是否良好 9 slience
//            复位功能是否良好 10 reset
//            主/备电源连线故障报警功能 11 powerAlarmFunction
//            报警功能是否正常 12 alarmFunction
//            照片 13 imageUrl
//            二维码 14 codePath
//            隐患描述 15 description


            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setDeviceType(et_2.getText().toString());
            itemObj.setProdFactory(et_3.getText().toString());
            itemObj.setTypeNo(et_4.getText().toString());
            itemObj.setNo(et_5.getText().toString());

            itemObj.setPositionConformity(et_6.getText().toString());
            itemObj.setAppearance(et_7.getText().toString());
            itemObj.setCheck(et_8.getText().toString());
            itemObj.setSlience(et_9.getText().toString());
            itemObj.setReset(et_10.getText().toString());
            itemObj.setPowerAlarmFunction(et_11.getText().toString());
            itemObj.setAlarmFunction(et_12.getText().toString());
            //      et_13 照片怎么上传 怎么获取
//          itemObj.setImageUrl(et_13.get);
//          itemObj.setCodePath(et_14.getText().toString());
            itemObj.setDescription(et_15.getText().toString());
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
}