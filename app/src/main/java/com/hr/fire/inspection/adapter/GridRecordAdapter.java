package com.hr.fire.inspection.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.Function;

import java.util.ArrayList;
import java.util.List;

public class GridRecordAdapter extends RecyclerView.Adapter<GridRecordAdapter.MyViewHolder> {
    private Context context;
    private List<Function> data;
    private int calculateCount;

    private OnItemClickListener listener;
    private ArrayList<Boolean> hotSelecte;

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final GridRecordAdapter.MyViewHolder holder, final int position) {
        holder.tvText.setText(data.get(position).getName());
        holder.itemView.setTag(data.get(position));
        if (hotSelecte != null && hotSelecte.size() != 0) {
            Boolean aBoolean = hotSelecte.get(position);
            if (aBoolean) {
                holder.cb_button.setVisibility(View.VISIBLE);
                holder.cb_button.setChecked(aBoolean);
                holder.rl_bg.setBackground(context.getDrawable(R.drawable.listview_border_nomargin));

            } else {
                holder.cb_button.setVisibility(View.GONE);
                holder.rl_bg.setBackground(null);
            }
        }
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

    public void setCheckState(ArrayList<Boolean> hotSelecte) {
        this.hotSelecte = hotSelecte;
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
        private RelativeLayout rl_bg;
        private CheckBox cb_button;

        public MyViewHolder(View view) {
            super(view);
            tvText = (TextView) view.findViewById(R.id.tv_text);
            imgPic = (ImageView) view.findViewById(R.id.img_pic);
            rl_view = (RelativeLayout) view.findViewById(R.id.rl_view);
            rl_bg = (RelativeLayout) view.findViewById(R.id.rl_bg);
            cb_button = (CheckBox) view.findViewById(R.id.cb_button);
        }
    }
}
