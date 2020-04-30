package com.hr.fire.inspection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.InspectionResult;

import java.util.List;

public class XJGasColumnAdapter extends RecyclerView.Adapter {
    Context mContext;
    private List<InspectionResult> mData;

    public XJGasColumnAdapter(Context c, List<InspectionResult> inspectionResults) {
        this.mContext = c;
        this.mData = inspectionResults;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xj_gas_fire_system_column, parent, false);
        XJGasColumnAdapter.MyViewHolder holder = new XJGasColumnAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        XJGasColumnAdapter.MyViewHolder myholder = (XJGasColumnAdapter.MyViewHolder) holder;
        int index = position + 1;//m默认索引是0
        myholder.tv_serial_number.setText(String.valueOf(index));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<InspectionResult> inspectionResults) {
        mData = inspectionResults;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_serial_number;

        public MyViewHolder(View view) {
            super(view);
            tv_serial_number = (TextView) view.findViewById(R.id.tv_serial_number);
        }
    }
}
