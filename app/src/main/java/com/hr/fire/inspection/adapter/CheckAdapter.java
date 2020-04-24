package com.hr.fire.inspection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.CheckActivity;
import com.hr.fire.inspection.entity.CheckType;

import java.util.List;

public class CheckAdapter  extends BaseAdapter {
    private List<CheckType> stringArrayList;
    private final CheckActivity mContext;

    public CheckAdapter(CheckActivity rulesActivity) {
        mContext = rulesActivity;
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
        CheckAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem, parent, false);
            holder = new CheckAdapter.ViewHolder();
            holder.sayTextView = (TextView) convertView.findViewById(R.id.dong_context);
            convertView.setTag(holder);
        } else {
            holder = (CheckAdapter.ViewHolder) convertView.getTag();
        }
        holder.sayTextView.setText(stringArrayList.get(position).getName());
        return convertView;
    }

    public void setData(List<CheckType> mList) {
        stringArrayList = mList;
    }

    static class ViewHolder {
        TextView sayTextView;
    }
}
