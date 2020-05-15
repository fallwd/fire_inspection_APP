package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.CarBonGoodsWeightAcitivty;
import com.hr.fire.inspection.activity.HFCGoodsWeightActivity;
import com.hr.fire.inspection.activity.QRCodeExistenceAcitivty;
import com.hr.fire.inspection.constant.ConstantInspection;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.WorkIItemBean;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HFC1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ItemInfo> mData;
    private Long checkid;  //检查表的Id
    private IntentTransmit intentTransmit;   //之前页面数据的传参,如系统号\公司id...
    private Map<Integer, List<WorkIItemBean>> mapSelection = new HashMap();

    public HFC1Adapter() {

    }

    public HFC1Adapter(Context mContext, List<ItemInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mapSelection.clear();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hfc_form_input_new, parent, false);
        HFC1Adapter.ViewHolder holder = new HFC1Adapter.ViewHolder(view);
        return holder;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final HFC1Adapter.ViewHolder vh = (HFC1Adapter.ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            ItemInfo info = mData.get(position);
            vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));
//            vh.et_2.setText(new StringBuffer().append(info.getLabelNo()));
            vh.et_2.setText(new StringBuffer().append("BQ00").append(position + 1));
            vh.et_3.setText(new StringBuffer().append(info.getVolume()).append(""));
            vh.et_4.setText(new StringBuffer().append(info.getGoodsWeight()).append(""));
            vh.et_5.setText(new StringBuffer().append(info.getPressure()).append(""));
            vh.et_6.setText(new StringBuffer().append(info.getProdFactory()).append(""));

            String mProdDate = (String) TimeUtil.getInstance().dataToHHmmss(info.getProdDate());
            String mCheckDate = (String) TimeUtil.getInstance().dataToHHmmss(info.getCheckDate());
            vh.et_7.setText(new StringBuffer().append(mProdDate).append(""));
            vh.et_8.setText(new StringBuffer().append(mCheckDate).append(""));
//            vh.et_9.setText(new StringBuffer().append("").append(""));
            vh.et_10.setText(new StringBuffer().append("").append(position + 1));

            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.tv_10.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            vh.tv_10.setBackground(drawable1);

            vh.tv_10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind(vh.tv_10);
                }
            });

            vh.tv_9.setText(new StringBuffer().append("七氟丙烷钢瓶信息采集瓶").append(position + 1).append("号表"));
            String s = info.getIsPass();
            if (s == null || s.isEmpty()) {
                vh.tv_10.setText("合格");
            } else {
                vh.tv_10.setText(s);
            }
            if (info.getTaskNumber() == null || info.getTaskNumber().isEmpty()) {
                vh.tv_11.setText("请选择");
            } else {
                vh.tv_11.setText(new StringBuffer().append(info.getTaskNumber()));
            }
            if (info.getLabelNo() == null) {
                vh.et_10.setHint("请输入...");
            } else {
                vh.et_10.setText(info.getLabelNo());
            }
            //初始化工作表数据
            WorkIItemBean mWorkIItemBean = new WorkIItemBean();
            List<WorkIItemBean> workSelectData = mWorkIItemBean.getWorkSelectData(3);
            mapSelection.put(position, workSelectData);
        }
        vh.tv_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkid == 0 || intentTransmit == null) {
                    Toast.makeText(mContext, "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(mContext, CarBonGoodsWeightAcitivty.class);
                intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_ID, checkid);
                intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_DIVICE, "  七氟丙烷钢瓶>  检查表");
                intent.putExtra("item_id", mData.get(position).getId());

                if (mData.get(position).getId() != 0) {
                    intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_DIVICE_ID, mData.get(position).getId());
                }
                intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_SYS_DATA, intentTransmit);
                mContext.startActivity(intent);
            }
        });
        vh.rl_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });
        vh.tv_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWind(vh.tv_10);
            }
        });
        vh.tv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindWork(vh.tv_11, mapSelection, position);
            }
        });
        vh.tv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ConstantInspection.CHECK_DIVICE, "七氟丙烷钢瓶");
                intent.setClass(mContext, QRCodeExistenceAcitivty.class);
                mContext.startActivity(intent);
            }
        });
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

            itemInfo.setVolume("请编辑");
            itemInfo.setWeight("请编辑");
            itemInfo.setGoodsWeight("请编辑");
            itemInfo.setProdFactory("请编辑");
            Date date = new Date();
            itemInfo.setProdDate(date);
            itemInfo.setCheckDate(date);
            itemInfo.setTaskNumber("请选择");
            itemInfo.setIsPass("请选择");


            mData.add(itemInfo);
            //添加动画
            notifyItemInserted(position);
        }
        notifyDataSetChanged();
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


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1;
        EditText et_2;
        EditText et_3;
        EditText et_4;
        EditText et_5;
        EditText et_6;
        EditText et_7;
        EditText et_8;
        EditText et_10;
        TextView tv_9;
        TextView tv_10;
        RelativeLayout rl_9;
        RelativeLayout rl_11;
        TextView tv_11;
        TextView tv_12;


        ViewHolder(View view) {
            super(view);
            tv_1 = (TextView) view.findViewById(R.id.tv_1);
            et_2 = (EditText) view.findViewById(R.id.et_2);
            et_3 = (EditText) view.findViewById(R.id.et_3);
            et_4 = (EditText) view.findViewById(R.id.et_4);
            et_5 = (EditText) view.findViewById(R.id.et_5);
            et_6 = (EditText) view.findViewById(R.id.et_6);
            et_7 = (EditText) view.findViewById(R.id.et_7);
            et_8 = (EditText) view.findViewById(R.id.et_8);
            et_10 = (EditText) view.findViewById(R.id.et_10);
            tv_9 = (TextView) view.findViewById(R.id.tv_9);
            tv_10 = (TextView) view.findViewById(R.id.tv_10);
            rl_9 = (RelativeLayout) view.findViewById(R.id.rl_9);
            rl_11 = (RelativeLayout) view.findViewById(R.id.rl_11);
            tv_11 = (TextView) view.findViewById(R.id.tv_11);
            tv_12 = (TextView) view.findViewById(R.id.tv_12);
        }
    }

    private void showPopWindWork(TextView tv_11, Map<Integer, List<WorkIItemBean>> mapSelectData, int position) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_item, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        ListView list_work = PopupRootView.findViewById(R.id.list_work);
        TextView tv_canl = PopupRootView.findViewById(R.id.tv_canl);
        TextView tv_confirm = PopupRootView.findViewById(R.id.tv_confirm);

        List<WorkIItemBean> workIItemBeans = mapSelectData.get(position);
        WorkSheetAdapter workSheetAdapter = new WorkSheetAdapter(mContext, workIItemBeans);
        list_work.setAdapter(workSheetAdapter);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(false);
        hrPopup.setOutsideTouchable(false);
        hrPopup.setContentView(PopupRootView);
//        hrPopup.showAsDropDown(tv);
        tv_canl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                    List<WorkIItemBean> selection = workSheetAdapter.getSelection();
                    mapSelectData.put(position, selection);
                    //将结果赋值给tv11
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < selection.size(); i++) {
                        WorkIItemBean mBean = selection.get(i);
                        if (mBean.isState()) {
                            builder.append(1 + i).append(",");
                        }
                    }
                    tv_11.setText(builder.toString().substring(0, builder.length() - 1));
                }
            }
        });
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
    }

}