package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.CarBonGoodsWeightAcitivty;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.impl.YCCCameraForVideo;
import com.hr.fire.inspection.impl.YCCamera;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.PhotoView;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AutomaticFireAlarmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ItemInfo> mData;
    private Long checkid;  //检查表的Id
    private IntentTransmit intentTransmit;   //之前页面数据的传参,如系统号\公司id...
    private HrPopup hrPopup; // 下拉框相关的引用

    public AutomaticFireAlarmAdapter(Context mContext, List<ItemInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acitivty_automatic_fire_alarm1_input, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder vh = (ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            ItemInfo info = mData.get(position);
            vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));


            if (info.getDeviceType() == null) {
                vh.et_2.setHint("请输入");
            } else {
                vh.et_2.setText(info.getDeviceType());
            }

            if (info.getProdFactory() == null) {
                vh.et_3.setHint("请输入");
            } else {
                vh.et_3.setText(info.getProdFactory());
            }

            if (info.getTypeNo() == null) {
                vh.et_4.setHint("请输入");
            } else {
                vh.et_4.setText(info.getTypeNo());
            }

            if (info.getNo() == null) {
                vh.et_5.setHint("请输入");
            } else {
                vh.et_5.setText(info.getNo());
            }

            if (info.getAppearance() == null) {
                vh.et_6.setHint("请输入");
            } else {
                vh.et_6.setText(info.getAppearance());
            }

            if (info.getResponseTime() == null) {
                vh.et_7.setHint("请输入");
            } else {
                vh.et_7.setText(info.getResponseTime());
            }

            if (info.getDescription() == null) {
                vh.tv_10.setHint("请输入");
            } else {
                vh.tv_10.setText(info.getDescription());
            }


//            vh.et_2.setText(new StringBuffer().append(info.getDeviceType()).append(""));
//            vh.et_3.setText(new StringBuffer().append(info.getProdFactory()).append(""));
//            vh.et_4.setText(new StringBuffer().append(info.getTypeNo()).append(""));
//            vh.et_5.setText(new StringBuffer().append(info.getNo()).append(""));
//            vh.et_6.setText(new StringBuffer().append(info.getAppearance()).append(""));
//            vh.et_7.setText(new StringBuffer().append(info.getResponseTime()).append(""));
//            vh.tv_10.setText(new StringBuffer().append(info.getDescription()).append(""));

//          下拉框
            vh.et_8.setText(new StringBuffer().append(info.getIsPass()).append(""));
            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.et_8.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);

            final ViewHolder finalHolder = vh;
            vh.et_8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind(finalHolder.et_8);
                }
            });
            vh.et_8.setBackground(drawable1);
//          照相机的图片  需要把对应的xml转换为textview
            String imageUrl = info.getImageUrl();
            if (imageUrl != null && imageUrl.endsWith(".jpg")) {
                Uri uri = Uri.parse(imageUrl);
                vh.tv_9.setImageURI(uri);
            } else {
                    vh.tv_9.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));
            }

            String videoUrl = info.getVideoUrl();
            if (videoUrl != null && videoUrl.endsWith(".mp4")) {
                vh.tv_9.setImageDrawable(mContext.getDrawable(R.mipmap.video_icon));
            } else {
                vh.tv_9.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));
            }

            vh.tv_9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PhotoView().showPopWindPicInfo(mContext, position, mYCCamera, doOpenCameraForVideo, mData);
                }
            });
        }

        vh.rl_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });
    }

    //显示对话框,用户选择是否异常的弹框
    private void showPopWind(final TextView et_8) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        RelativeLayout rl_yes = PopupRootView.findViewById(R.id.rl_yes);
        RelativeLayout rl_no = PopupRootView.findViewById(R.id.rl_no);
        RelativeLayout rl_other = PopupRootView.findViewById(R.id.rl_other);
        hrPopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(true);
        hrPopup.setOutsideTouchable(true);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAsDropDown(et_8);
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_8.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_8.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_8.setText("---");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    //  删除数据
    public void removeData(int position) {
        if (mData != null && mData.size() == 1) {
            Toast.makeText(mContext, "基础表格,无法删除", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mData != null && mData.size() != 0 && mData.size() > 1) {
            //1.删除数据库数据,
            ItemInfo itemInfo = mData.get(position);
            ServiceFactory.getYearCheckService().delete(itemInfo);
            //2.刷新列表数据,  理论上应该是数据库删除成功后,有一个返回值,在进行刷新
            mData.remove(position);
            //删除动画
            notifyItemRemoved(position);
            //通知重新绑定某一范围内的的数据与界面
            notifyItemRangeChanged(position, mData.size() - position);//通知数据与界面重新绑定
        }
    }


    /**
     * @param id
     * @param it
     */
    public void setCheckId(Long id, IntentTransmit it) {
        checkid = id;
        intentTransmit = it;
    }

    public void setNewData(List<ItemInfo> itemDataList) {
        this.mData = itemDataList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1;
        EditText et_2;
        EditText et_3;
        EditText et_4;
        EditText et_5;
        EditText et_6;
        EditText et_7;
        TextView et_8;
        ImageView tv_9;
        TextView tv_10;
        RelativeLayout rl_9;
        RelativeLayout rl_11;

        ViewHolder(View view) {
            super(view);
            tv_1 = (TextView) view.findViewById(R.id.tv_1);
            et_2 = (EditText) view.findViewById(R.id.et_2);
            et_3 = (EditText) view.findViewById(R.id.et_3);
            et_4 = (EditText) view.findViewById(R.id.et_4);
            et_5 = (EditText) view.findViewById(R.id.et_5);
            et_6 = (EditText) view.findViewById(R.id.et_6);
            et_7 = (EditText) view.findViewById(R.id.et_7);
            et_8 = (TextView) view.findViewById(R.id.et_8);
            tv_9 = (ImageView) view.findViewById(R.id.tv_9);
            tv_10 = (TextView) view.findViewById(R.id.tv_10);
            rl_9 = (RelativeLayout) view.findViewById(R.id.rl_9);
            rl_11 = (RelativeLayout) view.findViewById(R.id.rl_11);
        }
    }

    private YCCamera mYCCamera;

    //接口回调, 将点击事件传递到activity中,打开相机
    public void setmYCCamera(YCCamera y) {
        this.mYCCamera = y;
    }

    private YCCCameraForVideo doOpenCameraForVideo;
    //接口回调, 将点击事件传递到activity中,打开相机录像
    public void setdoOpenCameraForVideo(YCCCameraForVideo y) {
        this.doOpenCameraForVideo = y;
    }

}
