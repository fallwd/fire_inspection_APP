package com.hr.fire.inspection.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.List;

public class GoodsAdapter extends BaseAdapter {

    private Context mContext;
    private HrPopup hrPopup;
    private final List<YearCheck> dataEasy;//检查的条目数据
    private List<YearCheckResult> ycr;  //用户之前的填写记录
    private ViewGroup mParent;

    public GoodsAdapter(Activity rulesActivity, List<YearCheck> checkDataEasy, List<YearCheckResult> yearCheckResults) {
        this.mContext = rulesActivity;
        this.dataEasy = checkDataEasy;
        this.ycr = yearCheckResults;
    }

    @Override
    public int getCount() {
        return dataEasy.size();
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
        mParent = parent;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods_check, parent, false);
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
            holder.iv7 = (ImageView) convertView.findViewById(R.id.iv7);
            holder.tv8 = (TextView) convertView.findViewById(R.id.tv8);
            holder.ev8 = (EditText) convertView.findViewById(R.id.ev8);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.ll_item.setLayoutParams(layoutParams);
        holder.tv1.setText(new StringBuffer().append(position + 1));
        YearCheck yearCheck = dataEasy.get(position);
        holder.tv2.setText(yearCheck.getProject());
        holder.tv3.setText(yearCheck.getContent());
        holder.tv4.setText(yearCheck.getRequirement());
        holder.tv5.setText(yearCheck.getStandard());
        holder.tv6.setText(ycr.get(position).getIsPass());
        holder.ev8.setText(ycr.get(position).getDescription());

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

        return convertView;
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
        EditText ev8;
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
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(true);
        hrPopup.setOutsideTouchable(true);
        hrPopup.setContentView(PopupRootView);
//        hrPopup.showAsDropDown(tv6);
        hrPopup.showAtLocation(mParent, Gravity.BOTTOM,0,0);
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
