package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
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
import com.hr.fire.inspection.service.impl.YearCheckServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarbonFragment1 extends Fragment {
    View rootView;
    private static CarbonFragment1 fragment1;
    private static String mKey;
    private CarBon1Adapter adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView rc_list;

    public static CarbonFragment1 newInstance(String key, String value) {
        if (fragment1 == null) {
            fragment1 = new CarbonFragment1();
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
            rootView = inflater.inflate(R.layout.fragment_carbon1, container, false);
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
        // 调用接口测试
        String companyName = "辽东作业公司";
        String oilfieldName = "SZ36-1";
        String platformName = "SZ36-1B";
        String systemName = "高压二氧化碳系统灭火系统";
        String tableName = "药剂瓶";
        String number = "SD002";
        itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName, oilfieldName, platformName, systemName, tableName, number);
        Log.d("dong", "数据查看:" + itemDataList.size());
        Log.d("dong", "数据查看===:" + itemDataList.get(0).toString());
    }

    private void initView() {

        rc_list = rootView.findViewById(R.id.rc_list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new CarBon1Adapter(getActivity(), itemDataList);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
    }

    //动态添加条目
    public void addItemView() {
        if (adapter != null) {
            adapter.addData(itemDataList.size());
        }
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void saveData() {
        int childCount = rc_list.getChildCount();
        Log.d("dong", "点击了保存数据的方法 " + childCount);
        for (int i = 0; i < rc_list.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) rc_list.getChildAt(i);
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
            String tableName = "药剂瓶";
            String number = "SD002";
            ItemInfo itemObj = new ItemInfo();
            itemObj.setNo(et_2.getText().toString());
            itemObj.setVolume(et_3.getText().toString());
            itemObj.setWeight(et_4.getText().toString());
            itemObj.setGoodsWeight(et_5.getText().toString());
            itemObj.setProdFactory(et_5.getText().toString());
            Date prodDate = null;
            Date observeDate = null;
            Date checkDate = null;
            try {
                prodDate = format.parse("2018-08-03");
                observeDate = format.parse("2020-04-09");
                checkDate = format.parse("2020-04-09");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            itemObj.setProdDate(prodDate);
            itemObj.setObserveDate(observeDate);
            itemObj.setCheckDate(checkDate);
            itemObj.setIsPass("是否 正常");
            itemObj.setLabelNo("BQ0002");
            itemObj.setSystemNumber("SD002");
            itemObj.setProtectArea("主配电间");
            itemObj.setCodePath("检查表图片路径:/src/YJP0002.jpg");
            long l = ServiceFactory.getYearCheckService().insertItemData(itemObj, companyName, oilfieldName, platformName, systemName, tableName, number);
        }
        Toast.makeText(getContext(), "药剂瓶数据保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            adapter = null;
        }
    }
}