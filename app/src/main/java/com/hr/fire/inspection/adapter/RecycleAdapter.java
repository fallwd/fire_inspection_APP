package com.hr.fire.inspection.adapter;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter {

    private List dataList;
    private LayoutInflater layoutInflater;

    public RecycleAdapter(Context context, List DataList){
        this.dataList = DataList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_left, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;

        private TextView tv_content;

        MyViewHolder(View itemView) {
            super(itemView);
//            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
//            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
