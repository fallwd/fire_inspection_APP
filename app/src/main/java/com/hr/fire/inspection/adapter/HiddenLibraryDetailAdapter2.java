package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.utils.TimeUtil;

import java.io.File;
import java.util.List;

public class HiddenLibraryDetailAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<InspectionResult> ycr;
    public HiddenLibraryDetailAdapter2(Activity rulesActivity, List<InspectionResult> InspectionResult) {
        this.mContext = rulesActivity;
        this.ycr = InspectionResult;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_hidden_library_detail_layout_tbody, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;

        if (ycr != null && ycr.size() != 0) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            vh.ll_item.setLayoutParams(layoutParams);
            vh.tv1.setText(new StringBuffer().append(position + 1));
            InspectionResult yearCheckResult = ycr.get(position);
            Log.i("aaaa","渲染啦啦啦222222222" + yearCheckResult);
            vh.tv2.setText(yearCheckResult.getCheckType().getName());
//            vh.tv3.setText(yearCheckResult.getYearCheck().getProject());
//            vh.tv4.setText(yearCheckResult.getYearCheck().getContent());
//            vh.tv5.setText(yearCheckResult.getYearCheck().getRequirement());
//            vh.tv6.setText(yearCheckResult.getYearCheck().getStandard());
//            String mProdDate = (String) TimeUtil.getInstance().dataToHHmmss(yearCheckResult.getCheckDate());
//            vh.tv7.setText(new StringBuffer().append(mProdDate).append(""));
//            vh.tv8.setText(yearCheckResult.getIsPass());
//            String imageUrl = yearCheckResult.getImageUrl();
//            String imageUrl = "/external_path/Android/data/com.hr.fire.inspection/cache/1587462719699.jpg";
//            if (imageUrl != null && imageUrl.endsWith(".jpg")) {
//                //路径  /external_path/Android/data/com.hr.fire.inspection/cache/1587462719699.jpg
//                Uri uri = Uri.fromFile(new File(imageUrl));
//                vh.iv9.setImageURI(uri);
//            }
            vh.tv10.setText(yearCheckResult.getDescription());
        }


//        序号
//                设备类型
//        检查项目
//                检查内容
//        合格要求
//                检查依据
//        检查时间
//                是否合格
//        现场照片 rl9 iv9
//                隐患描述

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return ycr.size();
    }


    //ViewHolder 复用优化, 避免重复创建对象
    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_item;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv5;
        TextView tv6;
        TextView tv7;
        TextView tv8;
        RelativeLayout rl9;
        ImageView iv9;
        TextView tv10;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            tv3 = (TextView) itemView.findViewById(R.id.tv3);
            tv4 = (TextView) itemView.findViewById(R.id.tv4);
            tv5 = (TextView) itemView.findViewById(R.id.tv5);
            tv6 = (TextView) itemView.findViewById(R.id.tv6);
            tv7 = (TextView) itemView.findViewById(R.id.tv7);
            tv8 = (TextView) itemView.findViewById(R.id.tv8);
            rl9 = (RelativeLayout) itemView.findViewById(R.id.rl9);
            iv9 = (ImageView) itemView.findViewById(R.id.iv9);
            tv10 = (TextView) itemView.findViewById(R.id.tv10);
        }
    }
}
