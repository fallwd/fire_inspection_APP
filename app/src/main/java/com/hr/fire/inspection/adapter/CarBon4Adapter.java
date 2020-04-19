package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.List;

public class CarBon4Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<YearCheck> mData;
    private HrPopup hrPopup; // 下拉框相关的引用

    public CarBon4Adapter() {
    }

    public CarBon4Adapter(Context mContext, List<YearCheck> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carbon_item3_input, parent, false);
        CarBon4Adapter.ViewHolder holder = new CarBon4Adapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        CarBon4Adapter.ViewHolder vh = (CarBon4Adapter.ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
<<<<<<< HEAD
            YearCheck info = mData.get(position);
            vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));
            vh.et_2.setText(new StringBuffer().append(info.getProject()).append(""));
            vh.et_3.setText(new StringBuffer().append(info.getContent()).append(""));
            vh.et_4.setText(new StringBuffer().append(info.getRequirement()).append(""));
            vh.et_5.setText(new StringBuffer().append(info.getStandard()).append(""));

            vh.et_8.setText(new StringBuffer().append("").append(""));
            vh.et_6.setText(new StringBuffer().append("---").append(""));

            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.et_6.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            final CarBon4Adapter.ViewHolder finalHolder = vh;
            vh.et_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind(finalHolder.et_6);
                }
            });
            vh.et_6.setBackground(drawable1);

            vh.iv_7.setVisibility(View.GONE);
            vh.iv_7.setVisibility(View.VISIBLE);
            vh.iv_7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, PhotoUploadActivity.class));
                }
            });
=======
//            YearCheck info = mData.get(position);
//            vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));
//            vh.et_2.setText(new StringBuffer().append(info.getProject()).append(""));
//            vh.et_3.setText(new StringBuffer().append(info.getContent()).append(""));
//            vh.et_4.setText(new StringBuffer().append(info.getRequirement()).append(""));
//            vh.et_5.setText(new StringBuffer().append(info.getStandard()).append(""));
//            vh.et_6.setText(new StringBuffer().append("").append(""));
//            vh.et_7.setText(new StringBuffer().append("").append(""));
//            vh.et_8.setText(new StringBuffer().append("").append(""));
>>>>>>> c7c6ea5001ed1c26c21778236285b03bd6ea443d
        }
//        vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));
    }

    private void showPopWind(final TextView et_8) {
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
        hrPopup.showAsDropDown(et_8);
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_8.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_8.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_8.setText("---");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
//        if (mData.size() < 1) {
//            //当数据为空时,也需要返回两条列表给用户
//            return 1;
//        } else {
        return mData.size();  //可能事是因为没有数据
        //1.mData这个集合数据是动态获取的， 如果安卓  mData.size(), 现在这个应该是没有数据的，我默认写了5条
        //2. 目前样式没有对齐， 后面样式我来统一调整吧
//            return 15;  //写多少就是多少条数据
//        }
    }

    //  添加数据
    public void addData(int position) {
//      在list中添加数据，并通知条目加入一条
        if (mData != null && mData.size() != 0) {
            //添加最后一条数据
            mData.add(mData.get(mData.size() - 1));
            //添加动画
            notifyItemInserted(position);
        }
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1;
        EditText et_2;
        EditText et_3;
        EditText et_4;
        EditText et_5;
        EditText et_6;
        EditText et_8;
        ImageView iv_7;

        ViewHolder(View view) {
            super(view);
            tv_1 = (TextView) view.findViewById(R.id.tv_1);
            et_2 = (EditText) view.findViewById(R.id.et_2);
            et_3 = (EditText) view.findViewById(R.id.et_3);
            et_4 = (EditText) view.findViewById(R.id.et_4);
            et_5 = (EditText) view.findViewById(R.id.et_5);
            et_6 = (EditText) view.findViewById(R.id.et_6);
            iv_7 = (ImageView) view.findViewById(R.id.iv_7);
            et_8 = (EditText) view.findViewById(R.id.et_8);
        }
    }
}


