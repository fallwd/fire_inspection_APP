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


import com.hr.fire.inspection.adapter.CarBon3Adapter;

import com.hr.fire.inspection.R;
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

public class CarbonFragment3 extends Fragment {
    View rootView;
    private static CarbonFragment3 fragment3;
    private static String mKey;
    private IntentTransmit it;
    private List<CheckType> checkTypes;
    private CarBon3Adapter adapter;
    private List<YearCheckResult> DataList = new ArrayList<>();
    private List<YearCheck> itemDataList = new ArrayList<>();
    private RecyclerView rc_list;

    public static CarbonFragment3 newInstance(String key, IntentTransmit value) {
        if (fragment3 == null) {
            fragment3 = new CarbonFragment3();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment3.setArguments(args);
        return fragment3;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            it = (IntentTransmit) getArguments().getSerializable(mKey);
//            String keyParameter = (String) getArguments().get(mKey);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_carbon3, container, false);
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
//        // 调用接口测试
//        long companyInfoId = 3;
//        long checkTypeId = 7;
//        String number = "SD002";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date checkDate = null;
//
//        ItemInfo Obj =  new ItemInfo();
//
//
//        try {
//            checkDate = format.parse("2019-08-03 10:10");
////            checkDate = format.parse("2019-07-03 09:10");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        List<YearCheckResult> DataList = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, companyInfoId,checkTypeId,number,checkDate);
//        if(DataList.size()==0){
//           itemDataList = ServiceFactory.getYearCheckService().getCheckDataEasy(7);
//            Log.d("dong", "数据查看:" + itemDataList.size());
//            Log.d("dong", "数据查看===:" + itemDataList.get(0).toString());
//        }

        // 调用接口测试
        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        List<YearCheckResult> DataList = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, it.companyInfoId, checkTypes.get(2).getId(), it.number == null ? "" : it.number, it.srt_Date);
        if(DataList.size()==0){
           itemDataList = ServiceFactory.getYearCheckService().getCheckDataEasy(checkTypes.get(2).getId());
            Log.d("dong", "数据查看:" + itemDataList.size());
//            Log.d("dong", "数据查看===:" + itemDataList.get(0).toString());
        }

    }

    private void initView() {

        rc_list = rootView.findViewById(R.id.rc_list3);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new CarBon3Adapter(getActivity(), itemDataList);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
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
        Log.d("dong", "onDestroyView: ");
//        boolean mIsFirstLoad = true;
//        boolean mIsPrepare = false;
//        boolean mIsVisible = false;
//        if (rootView != null) {
//            ((ViewGroup) rootView.getParent()).removeView(rootView);
//        }
    }

}
