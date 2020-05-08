package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.NjKitchenChecklistAcitivty;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.activity.QRCodeExistenceAcitivty;
import com.hr.fire.inspection.constant.ConstantInspection;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.Date;
import java.util.List;

public class NjKitchenAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ItemInfo> mData;
    private Long checkid;  //检查表的Id
    private IntentTransmit intentTransmit;   //之前页面数据的传参,如系统号\公司id...
    private HrPopup hrPopup; // 下拉框相关的引用

    public NjKitchenAdapter1(Context mContext, List<ItemInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acitivty_nj_kitchen1_input, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        NjKitchenAdapter1.ViewHolder vh = (NjKitchenAdapter1.ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            ItemInfo info = mData.get(position);
            Log.e("dong", "position----:" + position);
            Log.e("dong", "info----:" + info);
            vh.et_1.setText(new StringBuffer().append(" ").append(position + 1));
            vh.et_2.setText(new StringBuffer().append(info.getAgentsType()).append(""));
            vh.et_3.setText(new StringBuffer().append(info.getNo()).append(""));
            vh.et_4.setText(new StringBuffer().append(info.getVolume()).append(""));
            vh.et_5.setText(new StringBuffer().append(info.getWeight()).append(""));
            vh.et_6.setText(new StringBuffer().append(info.getGoodsWeight()).append(""));
            vh.et_7.setText(new StringBuffer().append(info.getProdFactory()).append(""));


            String mProdDate = (String) TimeUtil.getInstance().dataToHHmmss(info.getProdDate());
            String mCheckDate = (String) TimeUtil.getInstance().dataToHHmmss(info.getFillingDate());

            vh.et_8.setText(new StringBuffer().append(mProdDate).append(""));
            vh.et_9.setText(new StringBuffer().append(mCheckDate).append(""));

            vh.et_10.setText(new StringBuffer().append(info.getTaskNumber()).append(""));

            vh.et_11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkid == 0 || intentTransmit == null) {
                        Toast.makeText(mContext, "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(mContext, NjKitchenChecklistAcitivty.class);
                    intent.putExtra(NjKitchenChecklistAcitivty.CHECK_ID, checkid);
                    intent.putExtra(NjKitchenChecklistAcitivty.CHECK_DIVICE, "药剂瓶  >  检查表");
                    intent.putExtra("item_id", mData.get(position).getId());

                    if (mData.get(position).getId() != 0) {
                        intent.putExtra(NjKitchenChecklistAcitivty.CHECK_DIVICE_ID, mData.get(position).getId());
                    }
                    intent.putExtra(NjKitchenChecklistAcitivty.CHECK_SYS_DATA, intentTransmit);
                    mContext.startActivity(intent);
                }
            });

//          下拉框
            vh.et_12.setText(new StringBuffer().append(info.getIsPass()).append(""));
            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.et_12.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            final ViewHolder finalHolder = vh;
            vh.et_12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind(finalHolder.et_12);
                }
            });
            vh.et_12.setBackground(drawable1);

            vh.et_13.setText(new StringBuffer().append(info.getLabelNo()).append(""));
            vh.et_14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(ConstantInspection.CHECK_DIVICE, "驱动瓶信息");
                    intent.setClass(mContext, QRCodeExistenceAcitivty.class);
                    mContext.startActivity(intent);
                }
            });

        }


        vh.et_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });
    }

    //显示对话框,用户选择是否异常的弹框
    private void showPopWind(final TextView et_12) {
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
        hrPopup.showAsDropDown(et_12);
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_12.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_12.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_12.setText("---");
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


    //  添加数据
    public void addData(int position) {
//      在list中添加数据，并通知条目加入一条
        if (mData != null && mData.size() != 0) {
            //添加最后一条数据
            mData.add(mData.get(mData.size() - 1));
            //添加动画
            notifyItemInserted(position);
        } else {
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setAgentsType("请编辑");   // 介质类型
            itemInfo.setNo("请编辑");
            itemInfo.setVolume("请编辑");
            itemInfo.setWeight("请编辑");
            itemInfo.setGoodsWeight("请编辑");
            itemInfo.setProdFactory("请编辑");
            Date date = new Date();
            itemInfo.setProdDate(date);
            itemInfo.setFillingDate(date);    //灌装日期
            itemInfo.setTaskNumber("请编辑");
            itemInfo.setIsPass("请编辑");
            itemInfo.setLabelNo("请编辑");
            itemInfo.setCodePath("请编辑");
            mData.add(itemInfo);
            //添加动画
//            notifyItemInserted(position);
        }
    }

    //  删除数据
    public void removeData(int position) {
        if (mData != null && mData.size() == 1) {
            Toast.makeText(mContext, "基础表格,无法删除", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mData != null && mData.size() != 0 && mData.size() > 1) {
            //1.删除数据库数据,
            ItemInfo itemInfo = mData.get(position);
            ServiceFactory.getYearCheckService().delete(itemInfo);
            //2.刷新列表数据,  理论上应该是数据库删除成功后,有一个返回值,在进行刷新
            mData.remove(position);
            //删除动画
            notifyItemRemoved(position);
            //通知重新绑定某一范围内的的数据与界面
            notifyItemRangeChanged(position, mData.size() - position);//通知数据与界面重新绑定
        }
    }


    /**
     * @param id
     * @param it
     */
    public void setCheckId(Long id, IntentTransmit it) {
        checkid = id;
        intentTransmit = it;
    }

    public void setNewData(List<ItemInfo> itemDataList) {
        this.mData = itemDataList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView et_1;
        EditText et_2;
        EditText et_3;
        EditText et_4;
        EditText et_5;

        EditText et_6;
        EditText et_7;
        EditText et_8;
        EditText et_9;
        TextView et_10;

        TextView et_11;
        TextView et_12;
        EditText et_13;
        ImageView et_14;
        RelativeLayout et_15;


        ViewHolder(View view) {
            super(view);
            et_1 = (TextView) view.findViewById(R.id.et_1);
            et_2 = (EditText) view.findViewById(R.id.et_2);
            et_3 = (EditText) view.findViewById(R.id.et_3);
            et_4 = (EditText) view.findViewById(R.id.et_4);
            et_5 = (EditText) view.findViewById(R.id.et_5);

            et_6 = (EditText) view.findViewById(R.id.et_6);
            et_7 = (EditText) view.findViewById(R.id.et_7);
            et_8 = (EditText) view.findViewById(R.id.et_8);
            et_9 = (EditText) view.findViewById(R.id.et_9);
            et_10 = (TextView) view.findViewById(R.id.et_10);

            et_11 = (TextView) view.findViewById(R.id.et_11);
            et_12 = (TextView) view.findViewById(R.id.et_12);
            et_13 = (EditText) view.findViewById(R.id.et_13);
            et_14 = (ImageView) view.findViewById(R.id.et_14);
            et_15 = (RelativeLayout) view.findViewById(R.id.et_15);
        }
    }
}
