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
import com.hr.fire.inspection.adapter.CarBon4Adapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarbonFragment4 extends Fragment {
    View rootView;
    private static CarbonFragment4 fragment4;
    private static String mKey;
    private CarBon4Adapter adapter;
    private IntentTransmit its;

    private RecyclerView rc_list;
    private List<YearCheck> checkDataEasy;
    private List<YearCheckResult> yearCheckResults;

    public static CarbonFragment4 newInstance(String key, IntentTransmit value) {
        if (fragment4 == null) {
            fragment4 = new CarbonFragment4();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment4.setArguments(args);
        return fragment4;
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
            rootView = inflater.inflate(R.layout.fragment_carbon4, container, false);
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
        checkDataEasy = ServiceFactory.getYearCheckService().getCheckDataEasy(its.systemId);

        //3.获取用户需要填写的数据,如果没有数据,就需要插入的默认数据（流程4）。如果有数据就
//        yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
        if (yearCheckResults == null || yearCheckResults.size() == 0) {
            for (int i = 0; i < checkDataEasy.size(); i++) {
//                Log.d("dong", "第一次加载数据 = ");
                //3.进入系统就给用户默认插入两条数据, 用户点击保存时,就Updata数据库
                YearCheckResult ycr = new YearCheckResult();
                ycr.setIsPass(" -- ");
                ycr.setImageUrl("暂无");  //可以在iv7中获取
                ycr.setDescription("无描述");
                ycr.setSystemNumber(its.number);
                ycr.setProtectArea(" "); // 保护位号
                ycr.setCheckDate(its.srt_Date);  //检查日期
//                ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, 0, checkDataEasy.get(i).getId(), its.companyInfoId, check_id, its.number, its.srt_Date);
//                yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
            }
        }
        initView();
    }

    private void initView() {

        rc_list = rootView.findViewById(R.id.rc_list4);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
//        adapter = new CarBon4Adapter(getActivity(), itemDataList);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
