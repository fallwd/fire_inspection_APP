package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.HiddenLibraryAdapter1;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.HYLogUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HiddenLibaryFragment1 extends Fragment {
    View rootView;
    private static HiddenLibaryFragment1 fragment1;
    private static String mKey;
    private HiddenLibraryAdapter1 adapter;
    private RecyclerView rc_list;
    private IntentTransmit its;
    private List<HashMap> retData;

    public static HiddenLibaryFragment1 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new HiddenLibaryFragment1();
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
            its = (IntentTransmit) getArguments().getSerializable(mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_hidden_library1, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            its = (IntentTransmit) getArguments().getSerializable(mKey);
            Log.d("its====",""+ its);
            try {
                initData();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    private void initData() throws ParseException {
        DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (its != null && its.end_time != "" && its.start_time != ""){
            Date starTime = format2.parse(its.start_time);
            Date endTime = format2.parse(its.end_time);
            Log.d("time==========", "开始时间======"+starTime+"\n结束时间====="+endTime +"\n系统ID==="+its.systemId+"\n平台ID====="+its.platformId);
            retData = ServiceFactory.getAnalysisService().getYearCheckView(its.platformId,its.systemId,starTime, endTime);
            HYLogUtil.getInstance().d("获取隐患库年检格数据,数据更新查看:" + retData.size() + "  " + retData.toString());
        }else{
            Toast.makeText(getActivity(), "为了方便查询精确数据，建议选择时间范围", Toast.LENGTH_SHORT).show();
            retData = ServiceFactory.getAnalysisService().getYearCheckView(0,0,null, null);
        }
        initView();
    }
    private void initView() {
        if (retData.size() == 0) {
            Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
        }
        rc_list = rootView.findViewById(R.id.rc_list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new HiddenLibraryAdapter1(getActivity(), retData);
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
