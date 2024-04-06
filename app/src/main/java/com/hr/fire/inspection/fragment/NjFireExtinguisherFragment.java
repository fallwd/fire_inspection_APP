package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.AutomaticFireAlarmAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NjFireExtinguisherFragment extends Fragment {
    View rootView;
    private static NjFireExtinguisherFragment fragment1;
    private static String mKey;
    private AutomaticFireAlarmAdapter adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView hz_table_tbody_id;
    private IntentTransmit it;
    private List<CheckType> checkTypes;

    public static NjFireExtinguisherFragment newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new NjFireExtinguisherFragment();
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
            it = (IntentTransmit) getArguments().getSerializable(mKey);
            Log.e("dong", "f1传参====" + it.toString());
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
        List<CheckType> arr = ServiceFactory.getYearCheckService().gettableNameData(1);
        long checkTypeId = arr.get(0).getId();


        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(0).getId(), it.number == null ? "" : it.number, it.srt_Date);
        HYLogUtil.getInstance().d("设备表信息,数据查看:" + itemDataList.size() + "  " + itemDataList.toString());
        // 一级表插入数据insertItemData


    }

    private void initView() {

        if (itemDataList.size() == 0) {
            Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
        }
        hz_table_tbody_id = rootView.findViewById(R.id.hz_table_tbody_id);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        hz_table_tbody_id.setLayoutManager(layoutManager);
        adapter = new AutomaticFireAlarmAdapter(getActivity(), itemDataList);
        hz_table_tbody_id.setAdapter(adapter);
        //添加动画
        hz_table_tbody_id.setItemAnimator(new DefaultItemAnimator());
        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(0).getId(), it);
        }
    }

//    //动态添加条目
//    public void addItemView() {
//        if (adapter != null) {
//            adapter.addData(itemDataList.size());
//            //点击"＋", 就像数据库中插入一条数据, 点"保存"就更新所有数据
//            addData();
//        }
//    }

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //点击"＋", 就像数据库中插入一条数据, 点"保存"就更新所有数据
    public void addData() {
        int childCount = hz_table_tbody_id.getChildCount();
        if (childCount == 0) {
            return;
        }
        //这些数据需要从上层传参过来
        ItemInfo itemObj = new ItemInfo();
        LinearLayout childAt = (LinearLayout) hz_table_tbody_id.getChildAt(childCount - 1);
        TextView tv_1 = childAt.findViewById(R.id.tv_1);
        EditText et_2 = childAt.findViewById(R.id.et_2);
        EditText et_3 = childAt.findViewById(R.id.et_3);
        EditText et_4 = childAt.findViewById(R.id.et_4);
        EditText et_5 = childAt.findViewById(R.id.et_5);
        EditText et_6 = childAt.findViewById(R.id.et_6);
        EditText et_7 = childAt.findViewById(R.id.et_7);
        TextView et_8 = childAt.findViewById(R.id.et_8);
        ImageView tv_9 = childAt.findViewById(R.id.tv_9);
        itemObj.setNo(et_2.getText().toString());
        itemObj.setVolume(et_3.getText().toString());
        itemObj.setWeight(et_4.getText().toString());
        itemObj.setGoodsWeight(et_5.getText().toString());
        itemObj.setProdFactory(et_6.getText().toString());
        Date date = TimeUtil.getInstance().hhmmssTodata(et_7.getText().toString());
        Date date1 = TimeUtil.getInstance().hhmmssTodata(et_8.getText().toString());
        itemObj.setProdDate(date);
        itemObj.setObserveDate(date1);
        itemObj.setCheckDate(new Date());
        itemObj.setIsPass("是");
        itemObj.setLabelNo("BQ0002");
        itemObj.setSystemNumber("SD002");
        itemObj.setProtectArea("主配电间");
        itemObj.setCodePath("检查表图片路径:/src/YJP0002.jpg");
        itemObj.setUuid(UUID.randomUUID().toString().replace("-",""));
        Log.d("dong", "一直遍历吗兄弟?" + date1 + "  " + et_5.getText().toString());
//        }
        long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj, 201, 1, "HZ001", it.srt_Date,"");
        if (l1 == 0) {
            Toast.makeText(getContext(), "药剂瓶数据保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void upData() {
        int itemCount = hz_table_tbody_id.getChildCount();
        List<ItemInfo> list = new ArrayList();
        for (int i = 0; i < itemCount; i++) {
            ItemInfo itemObj = new ItemInfo();
            LinearLayout childAt = (LinearLayout) hz_table_tbody_id.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            EditText et_6 = childAt.findViewById(R.id.et_6);
            EditText et_7 = childAt.findViewById(R.id.et_7);
            EditText et_8 = childAt.findViewById(R.id.et_8);
            TextView tv_9 = childAt.findViewById(R.id.tv_9);
            itemObj.setNo(et_2.getText().toString());
            itemObj.setVolume(et_3.getText().toString());
            itemObj.setWeight(et_4.getText().toString());
            itemObj.setGoodsWeight(et_5.getText().toString());
            itemObj.setProdFactory(et_6.getText().toString());
            Date date = TimeUtil.getInstance().hhmmssTodata(et_7.getText().toString());
            Date date1 = TimeUtil.getInstance().hhmmssTodata(et_8.getText().toString());
            itemObj.setProdDate(date);
            itemObj.setObserveDate(date1);
            itemObj.setCheckDate(new Date());
            itemObj.setIsPass("是");
            itemObj.setLabelNo("BQ0002");
            itemObj.setSystemNumber("SD002");
            itemObj.setProtectArea("主配电间");
            itemObj.setCodePath("检查表图片路径:/src/YJP0002.jpg");
            itemObj.setUuid(UUID.randomUUID().toString().replace("-",""));
            list.add(itemObj);
        }
        Log.d("dong", "itemCount" + itemCount + "   " + hz_table_tbody_id.getChildCount() + "   list  + " + list.size());
//        Log.i(t)
        ServiceFactory.getYearCheckService().update(list);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            adapter = null;
        }
    }
}