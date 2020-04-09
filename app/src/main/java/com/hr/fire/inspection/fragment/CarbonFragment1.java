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

import java.util.ArrayList;
import java.util.List;

public class CarbonFragment1 extends Fragment {
    View rootView;
    private static CarbonFragment1 fragment1;
    private static String mKey;
    private CarBon1Adapter adapter;

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
        initView();
    }

    private List list = new ArrayList<>();

    private void initView() {
        list.clear();
        //等待后台数据.
        list.add("哈哈");
        list.add("测试数据,默认先来两天数据");

        RecyclerView rc_list = rootView.findViewById(R.id.rc_list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new CarBon1Adapter(getActivity(), list);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
    }

    //动态添加条目
    public void addItemView() {
        if (adapter != null) {
            adapter.addData(list.size());
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