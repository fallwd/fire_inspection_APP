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
import com.hr.fire.inspection.adapter.HFC2Adapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HFCFragment2 extends Fragment {
    View rootView;
    private static HFCFragment2 fragment2;
    private static String mKey;
    private HFC2Adapter adapter;
    private IntentTransmit it;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView rc_list;
    private List<CheckType> checkTypes;

    public static HFCFragment2 newInstance(String key, IntentTransmit value) {
        if (fragment2 == null) {
            fragment2 = new HFCFragment2();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment2.setArguments(args);
        return fragment2;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            String keyParameter = (String) getArguments().get(mKey);
            it = (IntentTransmit) getArguments().getSerializable(mKey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_hfc2, container, false);
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
        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);
        HYLogUtil.getInstance().d("设备表信息,数据查看:" + itemDataList.size() + "  " + itemDataList.toString());
        // 一级表插入数据insertItemData
    }

    private void initView() {
        rc_list = rootView.findViewById(R.id.rc_list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new HFC2Adapter(getActivity(), itemDataList);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());

        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(1).getId(), it);
        }
    }

    public void addData() {
        int childCount = rc_list.getChildCount();
        if (childCount == 0) {
            return;
        }
        //这些数据需要从上层传参过来
        ItemInfo itemObj = new ItemInfo();
        LinearLayout childAt = (LinearLayout) rc_list.getChildAt(childCount - 1);
        TextView tv_1 = childAt.findViewById(R.id.tv_1);
        EditText et_2 = childAt.findViewById(R.id.et_2);
        EditText et_3 = childAt.findViewById(R.id.et_3);
        EditText et_4 = childAt.findViewById(R.id.et_4);
        EditText et_5 = childAt.findViewById(R.id.et_5);
        EditText et_6 = childAt.findViewById(R.id.et_6);
        EditText et_7 = childAt.findViewById(R.id.et_7);
        EditText et_8 = childAt.findViewById(R.id.et_8);
        EditText et_9 = childAt.findViewById(R.id.et_9);

        itemObj.setNo(et_2.getText().toString());
        itemObj.setVolume(et_3.getText().toString());
        itemObj.setWeight(et_4.getText().toString());
        itemObj.setPressure(et_5.getText().toString());
        itemObj.setProdFactory(et_6.getText().toString());

        Date date = TimeUtil.getInstance().hhmmssTodata(et_7.getText().toString());
        Date date1 = TimeUtil.getInstance().hhmmssTodata(et_8.getText().toString());
        itemObj.setTaskNumber(et_9.getText().toString());
        itemObj.setProdDate(date);
        itemObj.setObserveDate(date1);
//        itemObj.setCheckDate(new Date());
        itemObj.setIsPass("是");
        itemObj.setLabelNo("BQ0002");

        itemObj.setCodePath("检查表图片路径:/src/YJP0002.jpg");
        Log.d("dong", "一直遍历吗兄弟?" + date1 + "  " + et_5.getText().toString());
//        }
        long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj, it.companyInfoId, checkTypes.get(1).getId(), it.number, it.srt_Date);
        if (l1 == 0) {
            Toast.makeText(getContext(), "数据保存成功", Toast.LENGTH_SHORT).show();
        }
    }


    //动态添加条目
    public void addItemView() {
        if (adapter != null) {
            adapter.addData(itemDataList.size());
            rc_list.post(new Runnable() {
                @Override
                public void run() {
                    addData();
                }
            });
        }
    }

    public void saveData() {
        int itemCount = rc_list.getChildCount();

        //通知数据库刷新数据， 才能在调用Update();
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);

        if (itemCount == 0 || itemDataList.size() == 0 || itemDataList.size() != itemCount) {
            Toast.makeText(getActivity(), "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }


        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) rc_list.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            EditText et_6 = childAt.findViewById(R.id.et_6);
            EditText et_7 = childAt.findViewById(R.id.et_7);
            EditText et_8 = childAt.findViewById(R.id.et_8);
            EditText et_9 = childAt.findViewById(R.id.et_9);

            //这些数据需要从上层传参过来
            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setNo(et_2.getText().toString());
            itemObj.setVolume(et_3.getText().toString());
            itemObj.setWeight(et_4.getText().toString());
            itemObj.setPressure(et_5.getText().toString());
            itemObj.setProdFactory(et_6.getText().toString());

            Date date = TimeUtil.getInstance().hhmmssTodata(et_7.getText().toString());
            Date date1 = TimeUtil.getInstance().hhmmssTodata(et_8.getText().toString());
            itemObj.setTaskNumber(et_9.getText().toString());
            itemObj.setProdDate(date);
            itemObj.setObserveDate(date1);
//            itemObj.setCheckDate(new Date());
            itemObj.setIsPass("是");
            itemObj.setLabelNo("BQ0002");
            ServiceFactory.getYearCheckService().update(itemObj);
        }
        Toast.makeText(getActivity(), "氮气驱动瓶信息采集,保存成功", Toast.LENGTH_SHORT).show();
    }
}