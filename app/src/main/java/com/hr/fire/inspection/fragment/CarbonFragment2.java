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
import com.hr.fire.inspection.adapter.CarBon1Adapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarbonFragment2 extends Fragment {
    View rootView;
    private static CarbonFragment2 fragment2;
    private static String mKey;
    private CarBon1Adapter adapter;
    private IntentTransmit it;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private List<CheckType> checkTypes;
    private RecyclerView rc_list2;

    public static CarbonFragment2 newInstance(String key, IntentTransmit value) {
        if (fragment2 == null) {
            fragment2 = new CarbonFragment2();
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
            rootView = inflater.inflate(R.layout.fragment_carbon2, container, false);
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
        Log.d("dong", "数据查看:" + itemDataList.size() );
    }

    private void initView() {

        rc_list2 = rootView.findViewById(R.id.rc_list2);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list2.setLayoutManager(layoutManager);
        adapter = new CarBon1Adapter(getActivity(), itemDataList);

        rc_list2.setAdapter(adapter);
        //添加动画
        rc_list2.setItemAnimator(new DefaultItemAnimator());
    }

    //动态添加条目
    public void addItemView() {
        if (adapter != null && itemDataList != null) {
            adapter.addData(itemDataList.size());
            addData();
        }
    }


    public void addData() {
        int childCount = rc_list2.getChildCount();
        //这些数据需要从上层传参过来
        ItemInfo itemObj = new ItemInfo();
        LinearLayout childAt = (LinearLayout) rc_list2.getChildAt(childCount - 1);
        TextView tv_1 = childAt.findViewById(R.id.tv_1);
        EditText et_2 = childAt.findViewById(R.id.et_2);
        EditText et_3 = childAt.findViewById(R.id.et_3);
        EditText et_4 = childAt.findViewById(R.id.et_4);
        EditText et_5 = childAt.findViewById(R.id.et_5);
        EditText et_6 = childAt.findViewById(R.id.et_6);
        EditText et_7 = childAt.findViewById(R.id.et_7);
        EditText et_8 = childAt.findViewById(R.id.et_8);
        itemObj.setNo(et_2.getText().toString());
        itemObj.setVolume(et_3.getText().toString());
        itemObj.setWeight(et_4.getText().toString());
        itemObj.setPressure(et_5.getText().toString());
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
        Log.d("dong", "一直遍历吗兄弟?" + date1 + "  " + et_5.getText().toString());
//        }
        long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj, it.companyInfoId, checkTypes.get(1).getId(), it.number, it.srt_Date);
        if (l1 == 0) {
            Toast.makeText(getContext(), "氮气瓶瓶数据保存成功", Toast.LENGTH_SHORT).show();
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
