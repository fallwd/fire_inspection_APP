package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AutomaticFireAlarm1 extends Fragment {
    View rootView;
    private static AutomaticFireAlarm1 fragment1;
    private static String mKey;
    private AutomaticFireAlarmAdapter adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView hz_table_tbody_id;
    private IntentTransmit it;

    public static AutomaticFireAlarm1 newInstance(String key, String value) {
        if (fragment1 == null) {
            fragment1 = new AutomaticFireAlarm1();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putString(key, value);
        fragment1.setArguments(args);
        return fragment1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            it = {platform_id=201, companyInfoId=3, systemId=1, srt_Date='2019-08-03 10:10'}
            Log.e("dong", "f1传参================" + it);
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


        List<CheckType> arr = ServiceFactory.getYearCheckService().gettableNameData(1);
        long checkTypeId = arr.get(0).getId();



//         测试查询接口
        // companyInfoId 对应 辽东作业公司 SZ36-1 SZ36-1A--》3
        long companyInfoId = 3;
        // checkTypeId 对应 药剂瓶 2
        // number 区号 SD002
        String number = "SD001";
        // checkDate 检查日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date checkDate = null;
        try {
            checkDate = format.parse("2019-08-03 10:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(companyInfoId, checkTypeId, number, checkDate);
        for(int i=0;i<itemDataList.size();i++){
//            Log.i("getItemDataEasy",itemDataList.get(i).toString());
            Log.i(TAG, "11111111111111111111111getItemDataEasy=" + itemDataList.get(i).toString());
        }


    }

    private void initView() {

        hz_table_tbody_id = rootView.findViewById(R.id.hz_table_tbody_id);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        hz_table_tbody_id.setLayoutManager(layoutManager);
        adapter = new AutomaticFireAlarmAdapter(getActivity(), itemDataList);
        hz_table_tbody_id.setAdapter(adapter);
        //添加动画
        hz_table_tbody_id.setItemAnimator(new DefaultItemAnimator());
    }

    //动态添加条目
    public void addItemView() {
        if (adapter != null) {
            adapter.addData(itemDataList.size());
        }
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void saveData() {
        int childCount = hz_table_tbody_id.getChildCount();
        Log.d("dong", "点击了保存数据的方法 " + childCount);
        for (int i = 0; i < hz_table_tbody_id.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) hz_table_tbody_id.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);
        }

        Toast.makeText(getActivity(), "七氟丙烷钢瓶信息采集,保存成功", Toast.LENGTH_SHORT).show();


        for (int i = 0; i < hz_table_tbody_id.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) hz_table_tbody_id.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            EditText et_6 = childAt.findViewById(R.id.et_6);
            EditText et_7 = childAt.findViewById(R.id.et_7);
            EditText et_8 = childAt.findViewById(R.id.et_8);
            TextView tv_9 = childAt.findViewById(R.id.tv_9);
            //这些数据需要从上层传参过来
            String companyName = "辽东作业公司";
            String oilfieldName = "SZ36-1";
            String platformName = "SZ36-1D";
            String systemName = "高压二氧化碳系统灭火系统";
            String tableName = "感烟探测器";

            ItemInfo itemData = new ItemInfo();
            itemData.setNo(et_2.getText().toString());
            itemData.setVolume(et_3.getText().toString());
            itemData.setWeight(et_4.getText().toString());
            itemData.setGoodsWeight(et_5.getText().toString());
            itemData.setProdFactory(et_5.getText().toString());
            Date prodDate = null;
            Date observeDate = null;
            Date checkDate = null;
            try {
                prodDate = format.parse("2019-08-03 10:10");
                observeDate = format.parse("2020-04-09");
                checkDate = format.parse("2020-04-09");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            itemData.setProdDate(prodDate);
            itemData.setObserveDate(observeDate);
            itemData.setCheckDate(checkDate);
            itemData.setIsPass("是否 正常");
            itemData.setLabelNo("BQ0002");
            itemData.setSystemNumber("SD002");
            itemData.setProtectArea("主配电间");
            itemData.setCodePath("检查表图片路径:/src/YJP0002.jpg");


            long companyInfoId = 3;
            long checkTypeId=2;
            String number = "SD001";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date checkDate1 = null;
            try {
                checkDate1 = format.parse("2019-08-03 10:10");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.i(TAG, "itemData--------------------"+itemData);
            Log.i(TAG, "itemData--------------------"+checkDate);
//            long insertItemDataEasy(ItemInfo itemData, long companyInfoId,  long checkTypeId, String number, Date checkDate);
//            BaseService.update(itemData);
        }
    }

}