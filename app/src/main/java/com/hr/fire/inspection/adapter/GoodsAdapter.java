package com.hr.fire.inspection.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.utils.DisplayUtil;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter {

    private ArrayList<String> stringArrayList;
    private Context mContext;
    private HrPopup hrPopup;

    public GoodsAdapter(Activity rulesActivity) {
        mContext = rulesActivity;
    }

    @Override
    public int getCount() {
//        return stringArrayList.size();
        return 5;
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
            holder.iv7 = (ImageView) convertView.findViewById(R.id.iv7);
            holder.rl7 = (RelativeLayout) convertView.findViewById(R.id.rl7);
            holder.tv8 = (TextView) convertView.findViewById(R.id.tv8);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.ll_item.setLayoutParams(layoutParams);
        holder.tv1.setText(new StringBuffer().append(position + 1));
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
        final ViewHolder finalHolder = holder;
        holder.tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWind(finalHolder.tv6);

            }
        });
        holder.iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, PhotoUploadActivity.class));
            }
        });
//        }
        return convertView;
    }


    public void setData(ArrayList<String> mList) {
        stringArrayList = mList;
    }

    //ViewHolder 复用优化, 避免重复创建对象
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
        ImageView iv7;
        TextView tv8;
    }

    //显示对话框,用户选择是否异常的弹框
    private void showPopWind(final TextView tv6) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        RelativeLayout rl_yes = PopupRootView.findViewById(R.id.rl_yes);
        RelativeLayout rl_no = PopupRootView.findViewById(R.id.rl_no);
        RelativeLayout rl_other = PopupRootView.findViewById(R.id.rl_other);
        hrPopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(true);
        hrPopup.setOutsideTouchable(true);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAsDropDown(tv6);
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("---");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
    }
}
