package com.hr.fire.inspection.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.view.tableview.OnTableClick;
import com.hr.fire.inspection.view.tableview.TableView;

public class InspectionActivity extends BaseActivity {
    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_inspenction;
    }

    private TableView tableView;

    @Override
    public void initView(View view) {
        tableView = findViewById(R.id.tabview);
        String[] mlistHead = {"序号", "瓶号", "容积/L", "瓶量/kg", "药剂量/kg", "生产厂家", "生产时间", "水压试验日期", "检查表", "是否合格"};
        String[] mlistContent = {"序号", "瓶号", "容积/L", "瓶量/kg", "药剂量/kg", "生产厂家", "生产时间", "水压试验日期", "检查表", "是否合格"};
        tableView.setTable(new OnTableClick() {
            @Override
            public void onTableClickListener(int row, int col) {
                Log.e("dong", "ch-" + row + " col = " + col);
            }
        });
        tableView.setTableHead(mlistHead);
        tableView.setTableContent(mlistContent);
        int childCount = tableView.getChildCount();
        Log.e("dong", "ch-" + childCount);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
