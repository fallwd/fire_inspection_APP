package com.hr.fire.inspection.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.impl.YCCCameraForVideo;
import com.hr.fire.inspection.impl.YCCPhotoAlbum;
import com.hr.fire.inspection.utils.PhotoView;
import com.hr.fire.inspection.utils.StringBitmap;
import com.hr.fire.inspection.view.tableview.HrPopup;
import com.hr.fire.inspection.impl.YCCamera;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GoodsRecycAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private HrPopup hrPopup;
    private final List<YearCheck> dataEasy;//检查的条目数据
    private List<YearCheckResult> ycr;  //用户之前的填写记录

    private ViewGroup mParent;

    public GoodsRecycAdapter(Activity rulesActivity, List<YearCheck> checkDataEasy, List<YearCheckResult> yearCheckResults) {
        this.mContext = rulesActivity;
        this.dataEasy = checkDataEasy;
        this.ycr = yearCheckResults;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods_check, parent, false);
        ViewHolder holder = new ViewHolder(view);
        mParent = parent;
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;

        if (dataEasy != null && dataEasy.size() != 0) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            vh.ll_item.setLayoutParams(layoutParams);
            vh.tv1.setText(new StringBuffer().append(position + 1));
            YearCheck yearCheck = dataEasy.get(position);
            vh.tv2.setText(yearCheck.getProject());
            vh.tv3.setText(yearCheck.getContent());
            vh.tv4.setText(yearCheck.getRequirement());
            vh.tv5.setText(yearCheck.getStandard());
            vh.tv6.setText(ycr.get(position).getIsPass());

            if (ycr.get(position).getDescription() == null) {
                vh.ev8.setHint("无描述");
            } else {
                vh.ev8.setText(ycr.get(position).getDescription());
            }
//            vh.ev8.setText(ycr.get(position).getDescription());


            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.tv6.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            vh.tv6.setBackground(drawable1);

            vh.tv7.setVisibility(View.GONE);
            vh.rl7.setVisibility(View.VISIBLE);

            vh.tv8.setVisibility(View.GONE);
            vh.ev8.setVisibility(View.VISIBLE);
            vh.tv6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind(vh.tv6);
                }
            });

            String imageUrl = ycr.get(position).getImageUrl();
            Log.i("aaa","ImageURL1111"+imageUrl);
            if (imageUrl != null /*&& imageUrl.endsWith(".jpg")*/) {
                Uri uri = Uri.parse(imageUrl);
                vh.iv7.setImageURI(uri);
            } else {
                vh.iv7.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));
            }

            vh.iv7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    new PhotoView().showPopWindPic2(mContext, position, mYCCamera, doOpenCameraForVideo, ycr);
                    new PhotoView().showPopWindPicAlbum(mContext, position,mYCCPhotoAlbum, mYCCamera, doOpenCameraForVideo, ycr);
                }
            });

        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataEasy.size();
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
        RelativeLayout rl7;
        ImageView iv7;
        TextView tv8;
        EditText ev8;

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
            rl7 = (RelativeLayout) itemView.findViewById(R.id.rl7);
            iv7 = (ImageView) itemView.findViewById(R.id.iv7);
            tv8 = (TextView) itemView.findViewById(R.id.tv8);
            ev8 = (EditText) itemView.findViewById(R.id.ev8);
        }
    }

    //显示对话框,用户选择是否异常的弹框
    private void showPopWind(final TextView tv6) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        RelativeLayout rl_yes = PopupRootView.findViewById(R.id.rl_yes);
        RelativeLayout rl_no = PopupRootView.findViewById(R.id.rl_no);
        RelativeLayout rl_other = PopupRootView.findViewById(R.id.rl_other);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(true);
        hrPopup.setOutsideTouchable(true);
        hrPopup.setContentView(PopupRootView);
//        hrPopup.showAsDropDown(tv6);
        hrPopup.showAtLocation(mParent, Gravity.BOTTOM,0,0);
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("---");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
    }

    private YCCamera mYCCamera;
    private YCCPhotoAlbum mYCCPhotoAlbum;

    //接口回调, 将点击事件传递到activity中,打开相机
    public void setmYCCamera(YCCamera y) {
        this.mYCCamera = y;
    }
    public void setYCCPhotoAlbum(YCCPhotoAlbum y) {
        this.mYCCPhotoAlbum = y;
    }

    private YCCCameraForVideo doOpenCameraForVideo;
    //接口回调, 将点击事件传递到activity中,打开相机录像
    public void setdoOpenCameraForVideo(YCCCameraForVideo y) {
        this.doOpenCameraForVideo = y;
    }

}
