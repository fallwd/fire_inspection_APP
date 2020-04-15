package com.hr.fire.inspection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.FireReportActivity;

import java.util.ArrayList;

public class FireReportItemAdapter extends BaseAdapter {

    private ArrayList<String> stringArrayList;
    private final FireReportActivity mContext;


    public FireReportItemAdapter(FireReportActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return stringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_fire_report_item, parent, false);
            holder = new ViewHolder();
            holder.sayTextView = (TextView) convertView.findViewById(R.id.dong_context);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sayTextView.setText(stringArrayList.get(position));
        return convertView;
    }

    public void setData(ArrayList<String> mList) {
        stringArrayList = mList;
    }

    static class ViewHolder {
        TextView sayTextView;
    }

}
