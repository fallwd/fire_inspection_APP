package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CarBon1Adapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.service.impl.YearCheckServiceImpl;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarbonFragment1 extends Fragment {
    View rootView;
    private static CarbonFragment1 fragment1;
    private static String mKey;
    private CarBon1Adapter adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView rc_list;
    private IntentTransmit its;
    private List<CheckType> checkTypes;

    public static CarbonFragment1 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new CarbonFragment1();
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
            rootView = inflater.inflate(R.layout.fragment_carbon1, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    /**
     * 接口说明:
     * 1.历史中的companyInfoId  ,  systemId和在公司、平台那边传过来的都是一样的ID，使用哪一个都行
     * 2.gettableNameData  这个接口填二氧化碳灭火系统的id，就能获取到他的数据表的id，然后找到药剂瓶的id就是参数二
     * 3. 用gettableNameData返回的数据ID,  填充到getItemDataEasy第三个参数
     */
    private void initData() {
        //历史中的companyInfoId  ,  systemId和在公司、平台那边传过来的都是一样的ID，使用哪一个都行
        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(its.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(its.companyInfoId, checkTypes.get(0).getId(), its.number == null ? "" : its.number, its.srt_Date);
        Log.d("dong", "itemDataList默认服务器数据== " + itemDataList.toString());
    }

    private void initView() {
        if (itemDataList.size() == 0) {
            Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
        }
        rc_list = rootView.findViewById(R.id.rc_list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new CarBon1Adapter(getActivity(), itemDataList);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(0).getId(), its);
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
                itemInfo.setVolume(item.getVolume());
                itemInfo.setWeight(item.getWeight());
                itemInfo.setGoodsWeight(item.getGoodsWeight());
                itemInfo.setProdFactory(item.getProdFactory());
                itemInfo.setProdDate(item.getProdDate());
                itemInfo.setCheckDate(item.getCheckDate());
            } else {
                //点击新增,如果没有数据,就造一条默认数据
                itemInfo.setVolume("9");
                itemInfo.setWeight("3");
                itemInfo.setGoodsWeight("50");
                itemInfo.setProdFactory("未知");
                Date date = new Date();
                itemInfo.setProdDate(date);
                itemInfo.setCheckDate(date);
            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(its.companyInfoId, checkTypes.get(0).getId(), its.number == null ? "" : its.number, its.srt_Date);
                adapter.setNewData(itemDataList);
            } else {
                ToastUtil.show(getActivity(), "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
//            adapter.addData(itemDataList.size());
//            //点击"＋", 就像数据库中插入一条数据, 点"保存"就更新所有数据
//            rc_list.post(new Runnable() {
//                @Override
//                public void run() {
//                    addData();
//                }
//            });
        }
    }

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //点击"＋", 就像数据库中插入一条数据, 点"保存"就更新所有数据
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
//        itemObj.setIsPass("是");
//        itemObj.setLabelNo("BQ0002");
//        itemObj.setSystemNumber("SD002");
//        itemObj.setProtectArea("主配电间");
//        itemObj.setCodePath("检查表图片路径:/src/YJP0002.jpg");
        long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
        if (l1 == 0) {
            Toast.makeText(getContext(), "药剂瓶数据添加成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void upData() {
        int itemCount = rc_list.getChildCount();
        //通知数据库刷新数据， 才能在调用Update();
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(its.companyInfoId, checkTypes.get(0).getId(), its.number == null ? "" : its.number, its.srt_Date);
        Log.d("dong", "upData==   " + itemCount + "   新的数据条数   " + itemDataList.size());
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
            TextView tv_9 = childAt.findViewById(R.id.tv_9);

            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setNo(et_2.getText().toString());
            itemObj.setVolume(et_3.getText().toString());
            itemObj.setWeight(et_4.getText().toString());
            itemObj.setGoodsWeight(et_5.getText().toString());
            itemObj.setProdFactory(et_6.getText().toString());
            Date date = TimeUtil.getInstance().hhmmssTodata(et_7.getText().toString());
            Date date1 = TimeUtil.getInstance().hhmmssTodata(et_8.getText().toString());
            itemObj.setProdDate(date);
            itemObj.setObserveDate(date1);
            //默认日期参数影响查询结果， 所以不能修改该参数
//            itemObj.setCheckDate(new Date());
            //这是里层检查表数据,当前页面没有这个数据,可以不传。
//            itemObj.setIsPass("是");
//            itemObj.setLabelNo("BQ0002");
//            itemObj.setSystemNumber("SD002");
//            itemObj.setProtectArea("主配电间");
//            itemObj.setCodePath("检查表图片路径:/src/YJP0002.jpg");
//            list.add(itemObj);
            ServiceFactory.getYearCheckService().update(itemObj);
        }
        Toast.makeText(getContext(), "\"药剂瓶\"数据保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            adapter = null;
        }
    }
}
