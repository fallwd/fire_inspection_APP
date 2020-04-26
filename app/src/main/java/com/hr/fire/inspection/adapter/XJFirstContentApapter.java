package com.hr.fire.inspection.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;

public class XJFirstContentApapter extends RecyclerView.Adapter {
    Context mContext;

    public XJFirstContentApapter(Context c) {
        this.mContext = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xj_item_fire_content_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myholder = (MyViewHolder) holder;
        myholder.rl_fire1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dong", "点击了我==" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_fire1;
        private EditText et_fire2;
        private EditText et_fire3;
        private RelativeLayout rl_fire4;
        private RelativeLayout rl_fire5;
        private RelativeLayout rl_fire6;
        private RelativeLayout rl_fire7;
        private RelativeLayout rl_fire8;
        private RelativeLayout rl_fire9;
        private EditText et_fire10;
        private RelativeLayout rl_fire11;

        public MyViewHolder(View view) {
            super(view);
            rl_fire1 = (RelativeLayout) view.findViewById(R.id.rl_fire1);
            et_fire2 = (EditText) view.findViewById(R.id.et_fire2);
            et_fire3 = (EditText) view.findViewById(R.id.et_fire3);
            rl_fire4 = (RelativeLayout) view.findViewById(R.id.rl_fire4);
            rl_fire5 = (RelativeLayout) view.findViewById(R.id.rl_fire5);
            rl_fire6 = (RelativeLayout) view.findViewById(R.id.rl_fire6);
            rl_fire7 = (RelativeLayout) view.findViewById(R.id.rl_fire7);
            rl_fire8 = (RelativeLayout) view.findViewById(R.id.rl_fire8);
            rl_fire9 = (RelativeLayout) view.findViewById(R.id.rl_fire9);
            et_fire10 = (EditText) view.findViewById(R.id.et_fire10);
            rl_fire11 = (RelativeLayout) view.findViewById(R.id.rl_fire11);
        }
    }
}
