package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CarBon1Adapter;
import com.hr.fire.inspection.entity.ItemInfo;
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
    private CarBon1Adapter adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView hz_table_tbody_id;

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
            String keyParameter = (String) getArguments().get(mKey);
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
//         测试查询接口
        // companyInfoId 对应 辽东作业公司 SZ36-1 SZ36-1A--》3
        long companyInfoId = 3;
        // checkTypeId 对应 药剂瓶 2
        long checkTypeId = 2;
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

        List<ItemInfo> dataList = ServiceFactory.getYearCheckService().getItemDataEasy(companyInfoId, checkTypeId, number, checkDate);
        for(int i=0;i<dataList.size();i++){
            Log.i("getItemDataEasy",dataList.get(i).toString());
            Log.i(TAG, "11111111111111111111111getItemDataEasy=" + dataList.get(i).toString());


//            ItemInfo{
//                id=1,
//                checkTypeId=2,
//                checkType=CheckType{
//                    id=2,
//                    name='药剂瓶',
//                    type=1,
//                    parentId=1,
//                    parent=CheckType{
//                        id=1,
//                        name='高压二氧化碳系统灭火系统',
//                        type=1,
//                        parentId=0,
//                        parent=null
//                    }
//                 },
//                companyInfoId=3,
//                companyInfo=CompanyInfo{
//                    id=3,
//                    companyName='辽东作业公司',
//                    oilfieldName='SZ36-1',
//                    platformName='SZ36-1A',
//                    isNecessary=1
//                },
//                typeNo='null',
//                no='YJP0001',
//                volume='9',
//                weight='3',
//                goodsWeight='8',
//                pressure='null',
//                prodFactory='红日药业',
//                prodDate=Fri Aug 03 09:08:07 GMT+00:00 2018,
//                typeConformity='null',
//                positionConformity='null',
//                appearance='null',
//                check='null',
//                slience='null',
//                reset='null',
//                powerAlarmFunction='null',
//                alarmFunction='null',
//                effectiveness='null',
//                responseTime='null',
//                description='null',
//                setAlarm25='null',
//                setAlarm50='null',
//                testAlarm25='null',
//                testAlarm50='null',
//                observeDate=Sat Mar 09 09:08:07 GMT+00:00 2019,
//                taskNumber='null',
//                isPass='是',
//                labelNo='BQ0001',
//                imageUrl='null',
//                codePath='/src/YJP0001.jpg',
//                SystemNumber='SD001',
//                ProtectArea='主配电间',
//                checkDate=Sat Aug 03 10:10:00 GMT+00:00 2019,
//                checkResultList=[]
//            }
        }


    }

    private void initView() {

        hz_table_tbody_id = rootView.findViewById(R.id.hz_table_tbody_id);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        hz_table_tbody_id.setLayoutManager(layoutManager);
        adapter = new CarBon1Adapter(getActivity(), itemDataList);
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

    public void saveData() {
        int childCount = hz_table_tbody_id.getChildCount();
        Log.d("dong", "点击了保存数据的方法 " + childCount);
        for (int i = 0; i < hz_table_tbody_id.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) hz_table_tbody_id.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);

        }
        Toast.makeText(getActivity(), "七氟丙烷钢瓶信息采集,保存成功", Toast.LENGTH_SHORT).show();
    }

}