package com.hr.fire.inspection.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;

import java.util.List;

public class CarBon1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mData;
    private int itemType;  //获取item的条目类型

    public CarBon1Adapter() {
    }

    public CarBon1Adapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("DONG", "==" + itemType);
        View view;
        if (itemType == 0) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.carbon_item1, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.carbon_item1_input, parent, false);
        }
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        itemType = position;
        return super.getItemViewType(position);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
//            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
//            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

}
