package com.hr.fire.inspection.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.CarBonGoodsWeightAcitivty;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import java.util.List;

public class CarBon1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ItemInfo> mData;


    public CarBon1Adapter() {
    }

    public CarBon1Adapter(Context mContext, List<ItemInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carbon_item1_input, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder vh = (ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            ItemInfo info = mData.get(position);
            vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));
            vh.et_2.setText(new StringBuffer().append("YJP00").append(position + 1));
            vh.et_3.setText(new StringBuffer().append(info.getVolume()).append(""));
            vh.et_4.setText(new StringBuffer().append(info.getWeight()).append(""));
            vh.et_5.setText(new StringBuffer().append(info.getGoodsWeight()).append(""));
            vh.et_6.setText(new StringBuffer().append(info.getProdFactory()).append(""));
            vh.et_7.setText(new StringBuffer().append(info.getProdDate()).append(""));
            vh.et_8.setText(new StringBuffer().append(info.getCheckDate()).append(""));
            vh.tv_9.setText(new StringBuffer().append("药剂瓶").append(position + 1).append("号表"));
        }
        vh.rl_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CarBonGoodsWeightAcitivty.class);
                intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_ID, "ItemInfo,这里需要将ID传入");
                mContext.startActivity(intent);
            }
        });
        vh.rl_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData.size() < 1) {
            //当数据为空时,也需要返回两条列表给用户
            return 1;
        } else {
            return mData.size();
        }
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


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1;
        EditText et_2;
        EditText et_3;
        EditText et_4;
        EditText et_5;
        EditText et_6;
        EditText et_7;
        EditText et_8;
        TextView tv_9;
        TextView tv_10;
        RelativeLayout rl_9;
        RelativeLayout rl_11;

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
            tv_9 = (TextView) view.findViewById(R.id.tv_9);
            tv_10 = (TextView) view.findViewById(R.id.tv_10);
            rl_9 = (RelativeLayout) view.findViewById(R.id.rl_9);
            rl_11 = (RelativeLayout) view.findViewById(R.id.rl_11);
        }
    }

}
