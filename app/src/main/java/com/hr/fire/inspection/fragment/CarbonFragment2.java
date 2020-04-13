package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CarBon1Adapter;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class CarbonFragment2 extends Fragment {
    View rootView;
    private static CarbonFragment2 fragment2;
    private static String mKey;
    private CarBon1Adapter adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();


    public static CarbonFragment2 newInstance(String key, String value) {
        if (fragment2 == null) {
            fragment2 = new CarbonFragment2();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putString(key, value);
        fragment2.setArguments(args);
        return fragment2;
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
            rootView = inflater.inflate(R.layout.fragment_carbon2, container, false);
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
        String tableName = "氮气瓶";
        String number = "SD002";
        itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName, oilfieldName, platformName, systemName, tableName, number);
        Log.d("dong", "数据查看:" + itemDataList.size());
        Log.d("dong", "数据查看===:" + itemDataList.get(0).toString());
    }

    private void initView() {

        RecyclerView rc_list2 = rootView.findViewById(R.id.rc_list2);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list2.setLayoutManager(layoutManager);
        adapter = new CarBon1Adapter(getActivity(), itemDataList);

        rc_list2.setAdapter(adapter);
        //添加动画
        rc_list2.setItemAnimator(new DefaultItemAnimator());
    }
    //动态添加条目
    public void addItemView() {
        if (adapter != null && itemDataList != null) {
            adapter.addData(itemDataList.size());
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
