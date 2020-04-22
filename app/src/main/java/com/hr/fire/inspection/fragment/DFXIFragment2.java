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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.DFXIAdapter2;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DFXIFragment2 extends Fragment {
    View rootView;
    private static DFXIFragment2 fragment1;
    private static String mKey;
    private DFXIAdapter2 adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView table_tbody_id;
    private IntentTransmit it;
    private List<CheckType> checkTypes;

    public static DFXIFragment2 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new DFXIFragment2();
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
            rootView = inflater.inflate(R.layout.acitivty_dfxi2, container, false);
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
        List<CheckType> arr = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        long checkTypeId = arr.get(1).getId();


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

        if (itemDataList.size() == 0) {
            Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
        }
        table_tbody_id = rootView.findViewById(R.id.table_tbody_id);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        table_tbody_id.setLayoutManager(layoutManager);
        adapter = new DFXIAdapter2(getActivity(), itemDataList);
        table_tbody_id.setAdapter(adapter);
        //添加动画
        table_tbody_id.setItemAnimator(new DefaultItemAnimator());
        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(1).getId(), it);
        }
    }

    //动态添加条目
    public void addItemView() {
        if (adapter != null) {
            ItemInfo itemInfo = new ItemInfo();
            if (itemDataList != null && itemDataList.size() != 0) {
                //点击新增,有数据,就拿到最后一条数据新增,创建一个新的对象
                ItemInfo item = itemDataList.get(itemDataList.size() - 1);
                //如果直接新增会导致后台id冲重复\冲突
                itemInfo.setNo(item.getNo());
                itemInfo.setVolume(item.getVolume());
                itemInfo.setWeight(item.getWeight());
                itemInfo.setPressure(item.getPressure());
                itemInfo.setProdFactory(item.getProdFactory());
                itemInfo.setProdDate(item.getProdDate());
                itemInfo.setObserveDate(item.getObserveDate());
                itemInfo.setTaskNumber(item.getTaskNumber());
                itemInfo.setIsPass(item.getIsPass());
                itemInfo.setLabelNo(item.getLabelNo());
            } else {
                //点击新增,如果没有数据,就造一条默认数据
                itemInfo.setNo("请编辑");
                itemInfo.setVolume("请编辑");
                itemInfo.setWeight("请编辑");
                itemInfo.setPressure("请编辑");
                itemInfo.setProdFactory("请编辑");
                Date date = new Date();
                itemInfo.setProdDate(date);
                itemInfo.setObserveDate(date);
                itemInfo.setTaskNumber("请编辑");
                itemInfo.setIsPass("请编辑");
                itemInfo.setLabelNo("请编辑");


                //            <!--序号 1 -->  t
                //            <!--瓶号	 2--> e   no
                //            <!--容积/L	 3--> e   volume
                //            <!--瓶重/kg	4-->e   weight
                //            <!--压力/MPa	 5-->e   pressure
                //            <!--生产厂家	 6-->e   prodFactory
                //            <!--生产日期	 7-->e   prodDate
                //            <!--水压试验日期	 8-->e  observeDate
                //            <!--工作代号	 9-->  e   taskNumber
                //            <!--检查表	10--> t
                //            <!--是否合格	 11--> t   isPass
                //            <!--标签号	 12--> e  labelNo
                //            <!--操作 14-->  i



            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, it.companyInfoId, checkTypes.get(1).getId(), it.number, it.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);
                adapter.setNewData(itemDataList);
            } else {
                ToastUtil.show(getActivity(), "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }



    public void upData() {
        int itemCount = table_tbody_id.getChildCount();
        //通知数据库刷新数据， 才能在调用Update();
        Log.d("dong", "it.companyInfoId111==   " + it.companyInfoId + "   checkTypes.get(1).getId()   " + checkTypes.get(1).getId()+ "ITTTTTTTTTT" + it);


        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);

        Log.d("dong", "upData==   " + itemCount + "   新的数据条数   " + itemDataList.size());
        if (itemCount == 0 || itemDataList.size() == 0 || itemDataList.size() != itemCount) {
            Toast.makeText(getActivity(), "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) table_tbody_id.getChildAt(i);
            TextView et_1 = childAt.findViewById(R.id.et_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            EditText et_6 = childAt.findViewById(R.id.et_6);
            EditText et_7 = childAt.findViewById(R.id.et_7);
            EditText et_8 = childAt.findViewById(R.id.et_8);
            EditText et_9 = childAt.findViewById(R.id.et_9);
            TextView et_10 = childAt.findViewById(R.id.et_10);
            TextView et_11 = childAt.findViewById(R.id.et_11);
            EditText et_12 = childAt.findViewById(R.id.et_12);
            RelativeLayout et_14 = childAt.findViewById(R.id.et_14);

            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setNo(et_2.getText().toString());
            itemObj.setVolume(et_3.getText().toString());
            itemObj.setWeight(et_4.getText().toString());
            itemObj.setPressure(et_5.getText().toString());
            itemObj.setProdFactory(et_6.getText().toString());
            Date date = TimeUtil.getInstance().hhmmssTodata(et_7.getText().toString());
            Date date1 = TimeUtil.getInstance().hhmmssTodata(et_8.getText().toString());
            itemObj.setProdDate(date);
            itemObj.setObserveDate(date1);
            itemObj.setTaskNumber(et_9.getText().toString());
            itemObj.setIsPass(et_11.getText().toString());
            itemObj.setLabelNo(et_12.getText().toString());
            Log.d("dong", "itemObj222222保存==   "+itemObj);
            ServiceFactory.getYearCheckService().update(itemObj);
            Toast.makeText(getContext(), "数据保存成功", Toast.LENGTH_SHORT).show();
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