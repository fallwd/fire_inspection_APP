package com.hr.fire.inspection.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.NJMhqSelectItem1;

import java.util.List;

public class NJ_Mhq_Select_Adapter1 extends BaseAdapter {
    private List<NJMhqSelectItem1> stringArrayList;
    private final Context mContext;

    public NJ_Mhq_Select_Adapter1(Context rulesActivity, List<NJMhqSelectItem1> workSelectData) {
        mContext = rulesActivity;
        this.stringArrayList = workSelectData;
    }


    @Override
    public int getCount() {
        return stringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_work, parent, false);
            holder = new ViewHolder();
            holder.rl_code = (LinearLayout) convertView.findViewById(R.id.rl_code);
            holder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            holder.iv_code = (ImageView) convertView.findViewById(R.id.iv_code);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_item.setText(stringArrayList.get(position).getTitle());
        boolean state = stringArrayList.get(position).isState();
        if (state) {
            holder.iv_code.setBackground(mContext.getDrawable(R.drawable.cb_pressed));
        } else {
            holder.iv_code.setBackground(mContext.getDrawable(R.drawable.cb_normal));
        }
        ViewHolder finalHolder = holder;
        holder.rl_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NJMhqSelectItem1 itemBean = stringArrayList.get(position);
                itemBean.setState(!itemBean.isState());
                if (itemBean.isState()) {
                    finalHolder.iv_code.setBackground(mContext.getDrawable(R.drawable.cb_pressed));
                } else {
                    finalHolder.iv_code.setBackground(mContext.getDrawable(R.drawable.cb_normal));
                }
            }
        });
        return convertView;
    }

    //更新工作表中的数据
    public List<NJMhqSelectItem1> getSelection() {
        return stringArrayList;
    }

    static class ViewHolder {
        LinearLayout rl_code;
        TextView tv_item;
        ImageView iv_code;
    }

}
