package com.hr.fire.inspection.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;

import java.util.HashMap;
import java.util.List;

public class HiddenLibraryAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<HashMap> mData;


    public HiddenLibraryAdapter1(Context mContext, List<HashMap> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_drypower1_input, parent, false);
//        HiddenLibraryAdapter1.ViewHolder holder = new HiddenLibraryAdapter1.ViewHolder(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }




    @Override

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        HiddenLibraryAdapter1.ViewHolder vh = (HiddenLibraryAdapter1.ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            HashMap info = mData.get(position);
            Log.i("hahah","infoinfoinfoinfo" + info);
            vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));
            vh.tv_2.setText(new StringBuffer().append(info.get("system")).append(""));
            vh.tv_4.setText(new StringBuffer().append(info.get("company")).append(""));
            vh.tv_5.setText(new StringBuffer().append(info.get("systemNumber")).append(""));
            vh.tv_6.setText(new StringBuffer().append(info.get("protectArea")).append(""));
            vh.tv_7.setText(new StringBuffer().append(info.get("checkDate")).append(""));
            vh.tv_8.setText(new StringBuffer().append("").append("否"));
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






    public void setNewData(List<HashMap> retData) {
        this.mData = retData;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1;
        TextView tv_2;
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
            tv_4 = (TextView) view.findViewById(R.id.tv_4);
            tv_5 = (TextView) view.findViewById(R.id.tv_5);
            tv_6 = (TextView) view.findViewById(R.id.tv_6);
            tv_7 = (TextView) view.findViewById(R.id.tv_7);
            tv_8 = (TextView) view.findViewById(R.id.tv_8);
            rl_11 = (RelativeLayout) view.findViewById(R.id.rl_11);
        }
    }
}
