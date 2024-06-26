package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hr.fire.inspection.R;

import java.util.ArrayList;

public class PlatformAdapter extends BaseAdapter {

    private ArrayList<String> stringArrayList;
    private Activity mContext;
    private View.OnClickListener listener;
    public PlatformAdapter(View.OnClickListener listener, Activity rulesActivity) {
        mContext = rulesActivity;
        this.listener = listener;
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
        PlatformAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_platform, parent, false);
            holder = new PlatformAdapter.ViewHolder();
            holder.platform_name = (TextView) convertView.findViewById(R.id.platform_name);
            holder.edit_btn = (ImageView) convertView.findViewById(R.id.edit_btn);
            holder.del_btn = (ImageView) convertView.findViewById(R.id.del_btn);
            convertView.setTag(holder);
        } else {
            holder = (PlatformAdapter.ViewHolder) convertView.getTag();
        }
        holder.platform_name.setText(stringArrayList.get(position));
        holder.del_btn.setOnClickListener(listener);
        holder.edit_btn.setOnClickListener(listener);
        //通过setTag 将被点击控件所在条目的位置传递出去
        holder.del_btn.setTag(position);
        holder.edit_btn.setTag(position);
        return convertView;
    }

    public void setData(ArrayList<String> mList) {
        stringArrayList = mList;
    }

    static class ViewHolder {
        TextView platform_name ;
        ImageView edit_btn;
        ImageView del_btn;
    }
}
