package com.hr.fire.inspection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.Function;

import java.util.List;

public class GridRecordAdapter extends RecyclerView.Adapter<GridRecordAdapter.MyViewHolder> {
    private Context context;
    private List<Function> data;
    private int calculateCount;

    private OnItemClickListener listener;

    public GridRecordAdapter(Context context, List<Function> data, int calculateCount) {
        this.context = context;
        this.data = data;
        this.calculateCount = calculateCount;
    }


    @NonNull
    @Override
    public GridRecordAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_func_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridRecordAdapter.MyViewHolder holder, final int position) {
        holder.tvText.setText(data.get(position).getName());
        holder.itemView.setTag(data.get(position));
        holder.rl_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return calculateCount;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int tag);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;
        private ImageView imgPic;
        private RelativeLayout rl_view;

        public MyViewHolder(View view) {
            super(view);
            tvText = (TextView) view.findViewById(R.id.tv_text);
            imgPic = (ImageView) view.findViewById(R.id.img_pic);
            rl_view = (RelativeLayout) view.findViewById(R.id.rl_view);
        }
    }
}
