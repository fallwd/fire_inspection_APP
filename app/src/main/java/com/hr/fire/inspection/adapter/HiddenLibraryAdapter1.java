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
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TimeUtil;

import java.util.Date;
import java.util.List;

public class HiddenLibraryAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ItemInfo> mData;


    public HiddenLibraryAdapter1() {
    }

    public HiddenLibraryAdapter1(Context mContext, List<ItemInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hidden_library_body, parent, false);
        HiddenLibraryAdapter1.ViewHolder holder = new HiddenLibraryAdapter1.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        HiddenLibraryAdapter1.ViewHolder vh = (HiddenLibraryAdapter1.ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            ItemInfo info = mData.get(position);
            vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));
            vh.tv_2.setText(new StringBuffer().append("").append(position + 1));
            vh.tv_3.setText(new StringBuffer().append("").append(""));
            vh.tv_4.setText(new StringBuffer().append("").append("222"));
            vh.tv_5.setText(new StringBuffer().append("").append("3333"));
            vh.tv_6.setText(new StringBuffer().append("").append("444"));
            vh.tv_7.setText(new StringBuffer().append("").append("555"));
            vh.tv_8.setText(new StringBuffer().append("").append("6666"));
        }
        vh.rl_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }






    public void setNewData(List<ItemInfo> itemDataList) {
        this.mData = itemDataList;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1;
        TextView tv_2;
        TextView tv_3;
        TextView tv_4;
        TextView tv_5;
        TextView tv_6;
        TextView tv_7;
        TextView tv_8;
        RelativeLayout rl_11;

        ViewHolder(View view) {
            super(view);
            tv_1 = (TextView) view.findViewById(R.id.tv_1);
            tv_2 = (TextView) view.findViewById(R.id.tv_2);
            tv_3 = (TextView) view.findViewById(R.id.tv_3);
            tv_4 = (TextView) view.findViewById(R.id.tv_4);
            tv_5 = (TextView) view.findViewById(R.id.tv_5);
            tv_6 = (TextView) view.findViewById(R.id.tv_6);
            tv_7 = (TextView) view.findViewById(R.id.tv_7);
            tv_8 = (TextView) view.findViewById(R.id.tv_8);
            rl_11 = (RelativeLayout) view.findViewById(R.id.rl_11);
        }
    }
}
