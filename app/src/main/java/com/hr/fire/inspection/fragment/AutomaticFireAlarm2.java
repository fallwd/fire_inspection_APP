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
import com.hr.fire.inspection.adapter.AutomaticFireAlarmAdapter;
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

public class AutomaticFireAlarm2 extends Fragment {
    View rootView;
    private static AutomaticFireAlarm2 fragment1;
    private static String mKey;
    private AutomaticFireAlarmAdapter adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView hz_table_tbody_id;
    private IntentTransmit it;
    private List<CheckType> checkTypes;

    public static AutomaticFireAlarm2 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new AutomaticFireAlarm2();
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
            Log.e("dong", "f1传参====" + it.toString());
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
        long checkTypeId = arr.get(1).getId();


        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);
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
            adapter.setCheckId(checkTypes.get(1).getId(), it);
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
                itemInfo.setTypeNo(item.getTypeNo());
                itemInfo.setAppearance(item.getAppearance());
                itemInfo.setResponseTime(item.getResponseTime());
                itemInfo.setIsPass(item.getIsPass());
                itemInfo.setDescription(item.getDescription());
                itemInfo.setProdFactory(item.getProdFactory());
            } else {
                //点击新增,如果没有数据,就造一条默认数据
                itemInfo.setNo("请编辑");
                itemInfo.setDeviceType("请编辑");
                itemInfo.setTypeNo("请编辑");
                itemInfo.setResponseTime("请编辑");
                itemInfo.setIsPass("请编辑");
                itemInfo.setDescription("请编辑");
                itemInfo.setProdFactory("请编辑");
                itemInfo.setAppearance("请编辑");
            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, it.companyInfoId, checkTypes.get(1).getId(), it.number, it.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);
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
        int childCount = hz_table_tbody_id.getChildCount();
        if (childCount == 0) {
            return;
        }
        //这些数据需要从上层传参过来
        ItemInfo itemObj = new ItemInfo();
        LinearLayout childAt = (LinearLayout) hz_table_tbody_id.getChildAt(childCount - 1);
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


        itemObj.setDeviceType(et_2.getText().toString());
        itemObj.setProdFactory(et_3.getText().toString());
        itemObj.setTypeNo(et_4.getText().toString());
        itemObj.setNo(et_5.getText().toString());
        itemObj.setAppearance(et_6.getText().toString());
        itemObj.setResponseTime(et_7.getText().toString());
        itemObj.setIsPass(et_8.getText().toString());
        itemObj.setDescription(tv_10.getText().toString());
        itemObj.setCheckDate(new Date());

        long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj, it.companyInfoId, checkTypes.get(1).getId(), it.number, it.srt_Date);
        if (l1 == 0) {
            Toast.makeText(getContext(), "数据保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void upData() {
        int itemCount = hz_table_tbody_id.getChildCount();
        //通知数据库刷新数据， 才能在调用Update();
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);

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
            Log.d("dong", "itemObj222222保存==   "+itemObj);
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

}