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
import com.hr.fire.inspection.adapter.CarBon5Adapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class CarbonFragment5 extends Fragment {
    View rootView;
    private static CarbonFragment5 fragment5;
    private static String mKey;
    private CarBon5Adapter adapter;
    private List<CheckType> checkTypes;
    private IntentTransmit it;
    private List<YearCheckResult> DataList = new ArrayList<>();
    private List<YearCheck> itemDataList = new ArrayList<>();
    private RecyclerView rc_list;

    public static CarbonFragment5 newInstance(String key, IntentTransmit value) {
        if (fragment5 == null) {
            fragment5 = new CarbonFragment5();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment5.setArguments(args);
        return fragment5;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            it = (IntentTransmit) getArguments().getSerializable(mKey);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_carbon5, container, false);
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

        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        List<YearCheckResult> DataList = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, it.companyInfoId, checkTypes.get(4).getId(), it.number == null ? "" : it.number, it.srt_Date);
        if(DataList.size()==0){
            itemDataList = ServiceFactory.getYearCheckService().getCheckDataEasy(checkTypes.get(4).getId());

        }
    }

    private void initView() {

        rc_list = rootView.findViewById(R.id.rc_list5);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new CarBon5Adapter(getActivity(), itemDataList);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
    }


    public void upData() {
//        int itemCount = rc_list2.getChildCount();
//        //通知数据库刷新数据， 才能在调用Update();
//        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(its.companyInfoId, checkTypes.get(1).getId(), its.number == null ? "" : its.number, its.srt_Date);
//
//        for (int i = 0; i < itemCount; i++) {
//            LinearLayout childAt = (LinearLayout) rc_list3.getChildAt(i);
//            TextView tv_1 = childAt.findViewById(R.id.tv_1);
//            EditText et_2 = childAt.findViewById(R.id.et_2);
//            EditText et_3 = childAt.findViewById(R.id.et_3);
//            EditText et_4 = childAt.findViewById(R.id.et_4);
//            EditText et_5 = childAt.findViewById(R.id.et_5);
//            EditText et_6 = childAt.findViewById(R.id.et_6);
//            EditText et_7 = childAt.findViewById(R.id.et_7);
//            EditText et_8 = childAt.findViewById(R.id.et_8);
//            TextView tv_9 = childAt.findViewById(R.id.tv_9);
//
//            ItemInfo itemObj = itemDataList.get(i);
//            itemObj.setNo(et_2.getText().toString());
//            itemObj.setVolume(et_3.getText().toString());
//            itemObj.setWeight(et_4.getText().toString());
////            itemObj.setGoodsWeight(et_5.getText().toString());
//            itemObj.setPressure(et_5.getText().toString());
//            itemObj.setProdFactory(et_6.getText().toString());
//            Date date = TimeUtil.getInstance().hhmmssTodata(et_7.getText().toString());
//            Date date1 = TimeUtil.getInstance().hhmmssTodata(et_8.getText().toString());
//            itemObj.setProdDate(date);
//            itemObj.setObserveDate(date1);
//            ServiceFactory.getYearCheckService().update(itemObj);
//        }
        Toast.makeText(getContext(), "\"管线管件\"数据保存成功", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
