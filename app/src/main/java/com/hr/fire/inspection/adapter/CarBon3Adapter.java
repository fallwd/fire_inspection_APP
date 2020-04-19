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
<<<<<<< HEAD
=======
import android.widget.LinearLayout;
>>>>>>> c7c6ea5001ed1c26c21778236285b03bd6ea443d
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.CarBonGoodsWeightAcitivty;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.List;

public class CarBon3Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<YearCheck> mData;
    private HrPopup hrPopup; // 下拉框相关的引用


    public CarBon3Adapter(Context mContext, List<YearCheck> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carbon_item3_input, parent, false);
        CarBon3Adapter.ViewHolder holder = new CarBon3Adapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;
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
=======
            YearCheck yearCheck = mData.get(position);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            vh.ll_item.setLayoutParams(layoutParams);
            vh.tv1.setText(new StringBuffer().append(position + 1));

            vh.tv2.setText(yearCheck.getProject());
            vh.tv3.setText(yearCheck.getContent());
            vh.tv4.setText(yearCheck.getRequirement());
            vh.tv5.setText(yearCheck.getStandard());
//            vh.tv6.setText(ycr.get(position).getIsPass());
//            vh.ev8.setText(ycr.get(position).getDescription());
>>>>>>> c7c6ea5001ed1c26c21778236285b03bd6ea443d

            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
<<<<<<< HEAD
            vh.et_6.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            final CarBon3Adapter.ViewHolder finalHolder = vh;
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
=======
            vh.tv6.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            vh.tv6.setBackground(drawable1);

            vh.tv7.setVisibility(View.GONE);
            vh.rl7.setVisibility(View.VISIBLE);
            vh.tv8.setVisibility(View.GONE);
            vh.ev8.setVisibility(View.VISIBLE);

            vh.tv6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind(vh.tv6);
                }
            });
            vh.iv7.setOnClickListener(new View.OnClickListener() {
>>>>>>> c7c6ea5001ed1c26c21778236285b03bd6ea443d
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, PhotoUploadActivity.class));
                }
            });

        }

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
        return mData.size();
    }

<<<<<<< HEAD

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

=======
    class ViewHolder extends RecyclerView.ViewHolder {
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

        ViewHolder(View view) {
            super(view);
            ll_item = (LinearLayout) view.findViewById(R.id.ll_item);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
            tv4 = (TextView) view.findViewById(R.id.tv4);
            tv5 = (TextView) view.findViewById(R.id.tv5);
            tv6 = (TextView) view.findViewById(R.id.tv6);
            tv7 = (TextView) view.findViewById(R.id.tv7);
            rl7 = (RelativeLayout) view.findViewById(R.id.rl7);
            iv7 = (ImageView) view.findViewById(R.id.iv7);
            tv8 = (TextView) view.findViewById(R.id.tv8);
            ev8 = (EditText) view.findViewById(R.id.ev8);
>>>>>>> c7c6ea5001ed1c26c21778236285b03bd6ea443d
        }
    }

    private HrPopup hrPopup;

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