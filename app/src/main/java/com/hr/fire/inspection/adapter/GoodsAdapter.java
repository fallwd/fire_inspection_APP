package com.hr.fire.inspection.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.utils.DisplayUtil;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter {

    private ArrayList<String> stringArrayList;
    private Context mContext;

    public GoodsAdapter(Activity rulesActivity) {
        mContext = rulesActivity;
    }

    @Override
    public int getCount() {
//        return stringArrayList.size();
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods_form, parent, false);
            holder = new ViewHolder();
            holder.ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);
            holder.tv1 = (TextView) convertView.findViewById(R.id.tv1);
            holder.tv2 = (TextView) convertView.findViewById(R.id.tv2);
            holder.tv3 = (TextView) convertView.findViewById(R.id.tv3);
            holder.tv4 = (TextView) convertView.findViewById(R.id.tv4);
            holder.tv5 = (TextView) convertView.findViewById(R.id.tv5);
            holder.tv6 = (TextView) convertView.findViewById(R.id.tv6);
            holder.tv7 = (TextView) convertView.findViewById(R.id.tv7);
            holder.rl7 = (RelativeLayout) convertView.findViewById(R.id.rl7);
            holder.tv8 = (TextView) convertView.findViewById(R.id.tv8);
            holder.ev8 = (EditText) convertView.findViewById(R.id.ev8);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 110);
            holder.ll_item.setLayoutParams(layoutParams);
            holder.tv1.setText("序号");
            holder.tv2.setText("检查项目");
            holder.tv3.setText("检查内容");
            holder.tv4.setText("合格需求");
            holder.tv5.setText("检查标准");
            holder.tv6.setCompoundDrawables(null, null, null, null);
            holder.tv6.setBackground(null);
            holder.tv6.setText("是否合格");
            holder.tv7.setText("现场照片");
            holder.tv7.setVisibility(View.VISIBLE);
            holder.rl7.setVisibility(View.GONE);
            holder.tv8.setVisibility(View.VISIBLE);
            holder.ev8.setVisibility(View.GONE);

        } else {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.ll_item.setLayoutParams(layoutParams);
            holder.tv1.setText(new StringBuffer().append(position));
            holder.tv2.setText("瓶体外观检查");
            holder.tv3.setText("所有存储容器应进行目视检查,看是否有破损生锈或安装的硬件松动的迹象");
            holder.tv4.setText("储瓶无碰撞变形及其他机械性损伤,表面无锈蚀,保护图层完好,涂层颜色为红色");
            holder.tv5.setText("中国船级社《船用消防系统检测机构服务指南》4.10.2");
            holder.tv6.setText("是  ");
            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tv6.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            holder.tv6.setBackground(drawable1);

            holder.tv7.setVisibility(View.GONE);
            holder.rl7.setVisibility(View.VISIBLE);
            holder.tv8.setVisibility(View.GONE);
            holder.ev8.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public void setData(ArrayList<String> mList) {
        stringArrayList = mList;
    }

    static class ViewHolder {
        LinearLayout ll_item;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv5;
        TextView tv6;
        TextView tv7;
        RelativeLayout rl7;
        TextView tv8;
        EditText ev8;
    }

}
