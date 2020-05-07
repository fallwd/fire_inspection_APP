package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.HiddenLibraryAdapter2;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.HYLogUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HiddenLibaryFragment2 extends Fragment {
    View rootView;
    private static HiddenLibaryFragment2 fragment1;
    private static String mKey;
    private HiddenLibraryAdapter2 adapter;
    private RecyclerView rc_list;
    private IntentTransmit its;
    private List<HashMap> retData;

    public static HiddenLibaryFragment2 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new HiddenLibaryFragment2();
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
            its = (IntentTransmit) getArguments().getSerializable(mKey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_hidden_library2, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            initData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        initView();
    }


    private void initData() throws ParseException {
        //参数
        DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (its != null && its.end_time != "" && its.start_time != ""){
            Date starTime = format2.parse(its.start_time);
            Date endTime = format2.parse(its.end_time);
            retData = ServiceFactory.getAnalysisService().getInspectionView(its.platformId,its.systemId,starTime, endTime);
            HYLogUtil.getInstance().d("获取隐患库巡检表格数据,数据更新查看:" + retData.size() + "  " + retData.toString());
        }else{
            retData = ServiceFactory.getAnalysisService().getInspectionView(0,0,null, null);
        }
    }
    private void initView() {
        rc_list = rootView.findViewById(R.id.rc_list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new HiddenLibraryAdapter2(getActivity(), retData);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            adapter = null;
        }
    }
}
