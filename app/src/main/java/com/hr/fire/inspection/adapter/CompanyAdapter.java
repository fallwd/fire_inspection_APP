package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.RulesActivity;

import java.util.ArrayList;

public class CompanyAdapter extends BaseAdapter {

    private ArrayList<String> stringArrayList;
    private Activity mContext;
    private View.OnClickListener listener;


    public CompanyAdapter(View.OnClickListener listener, Activity rulesActivity) {
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
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem, parent, false);
            //这里改布局
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_company, parent, false);
            holder = new ViewHolder();
            holder.company_name = (TextView) convertView.findViewById(R.id.company_name);
            holder.edit_btn = (ImageView) convertView.findViewById(R.id.edit_btn);
            holder.del_btn = (ImageView) convertView.findViewById(R.id.del_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.company_name.setText(stringArrayList.get(position));
        //给要被点击的控件加入点击监听，具体事件在创建adapter的地方实现
        holder.del_btn.setOnClickListener(listener);
        holder.edit_btn.setOnClickListener(listener);
        //通过setTag 将被点击控件所在条目的位置传递出去
        holder.del_btn.setTag(position);
        holder.edit_btn.setTag(position);


        //点击事件这里设置
//        holder.company_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext,"点击了我",Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext,"您点击了编辑",Toast.LENGTH_SHORT).show();
//            }
//        });
        return convertView;
    }

    public void setData(ArrayList<String> mList) {
        stringArrayList = mList;
    }

    static class ViewHolder {
        TextView company_name;
        ImageView edit_btn;
        ImageView del_btn;
    }
}
