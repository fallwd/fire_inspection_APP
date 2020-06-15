package com.hr.fire.inspection.adapter;

import android.annotation.TargetApi;
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

public class AutomaticFireAlarmAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ItemInfo> mData;
    private Long checkid;  //检查表的Id
    private IntentTransmit intentTransmit;   //之前页面数据的传参,如系统号\公司id...
    private HrPopup hrPopup; // 下拉框相关的引用

    public AutomaticFireAlarmAdapter2(Context mContext, List<ItemInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acitivty_automatic_fire_alarm2_input, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        AutomaticFireAlarmAdapter2.ViewHolder vh = (AutomaticFireAlarmAdapter2.ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
//            TextView tv_1; 序号
//            EditText et_2;设备类型
//            EditText et_3;生产厂家
//            EditText et_4;型号
//            EditText et_5;编号
//            EditText et_6;外观是否良好
//            EditText et_7;设定高报值/25%LEL
//            EditText et_8;设定高高报值/50%LEL
//            EditText et_9;测试高报值/25%LEL
//            EditText et_10;测试高高报值/50%LEL

//            EditText et_11;响应时间
//            TextView et_12;是否合格
//            ImageView et_13;照片
//            EditText et_14;隐患描述
//            RelativeLayout et_15; del
//            private String setAlarm25;//设置高报值25LEL
//
//            private String setAlarm50;//设置高报值50LEL
//
//            private String testAlarm25;//测试高报值25LEL
//
//            private String testAlarm50;//测试高报值50LEL

            ItemInfo info = mData.get(position);
            vh.tv_1.setText(new StringBuffer().append(" ").append(position + 1));
//            vh.et_2.setText(new StringBuffer().append(info.getDeviceType()).append(""));
//            vh.et_3.setText(new StringBuffer().append(info.getProdFactory()).append(""));
//            vh.et_4.setText(new StringBuffer().append(info.getTypeNo()).append(""));
//            vh.et_5.setText(new StringBuffer().append(info.getNo()).append(""));



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

//          下拉框
            vh.et_6.setText(new StringBuffer().append(info.getAppearance()).append(""));
            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.et_6.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            final ViewHolder finalHolder = vh;
            vh.et_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind6(finalHolder.et_6);
                }
            });
            vh.et_6.setBackground(drawable1);

//            vh.et_7.setText(new StringBuffer().append(info.getSetAlarm25()).append(""));
//            vh.et_8.setText(new StringBuffer().append(info.getSetAlarm50()).append(""));
//            vh.et_9.setText(new StringBuffer().append(info.getTestAlarm25()).append(""));
//            vh.et_10.setText(new StringBuffer().append(info.getTestAlarm50()).append(""));
//            vh.et_11.setText(new StringBuffer().append(info.getResponseTime()).append(""));


            if (info.getSetAlarm25() == null) {
                vh.et_7.setHint("请输入");
            } else {
                vh.et_7.setText(info.getSetAlarm25());
            }
            if (info.getSetAlarm50() == null) {
                vh.et_8.setHint("请输入");
            } else {
                vh.et_8.setText(info.getSetAlarm50());
            }
            if (info.getTestAlarm25() == null) {
                vh.et_9.setHint("请输入");
            } else {
                vh.et_9.setText(info.getTestAlarm25());
            }
            if (info.getTestAlarm50() == null) {
                vh.et_10.setHint("请输入");
            } else {
                vh.et_10.setText(info.getTestAlarm50());
            }
            if (info.getResponseTime() == null) {
                vh.et_11.setHint("请输入");
            } else {
                vh.et_11.setText(info.getResponseTime());
            }


//          下拉框
            vh.et_12.setText(new StringBuffer().append(info.getIsPass()).append(""));
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.et_12.setCompoundDrawables(null, null, drawable, null);
            vh.et_12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind12(finalHolder.et_12);
                }
            });
            vh.et_12.setBackground(drawable1);
            //         照相机的图片  需要把对应的xml转换为textview

            String imageUrl = info.getImageUrl();
            if (imageUrl != null && imageUrl.endsWith(".jpg")) {
                Uri uri = Uri.parse(imageUrl);
                vh.et_13.setImageURI(uri);
            } else {
                vh.et_13.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));

            }

            String videoUrl = info.getVideoUrl();
            if (videoUrl != null && videoUrl.endsWith(".mp4")) {
                vh.et_13.setImageDrawable(mContext.getDrawable(R.mipmap.video_icon));
            } else {
                vh.et_13.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));
            }

            vh.et_13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mYCCamera.startCamera(position);
                    new PhotoView().showPopWindPicInfo(mContext, position, mYCCamera, doOpenCameraForVideo, mData);
                }
            });
//            vh.et_14.setText(new StringBuffer().append(info.getDescription()).append(""));
            if (info.getDescription() == null) {
                vh.et_14.setHint("请输入");
            } else {
                vh.et_14.setText(info.getDescription());
            }
        }

        vh.et_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });
    }

    //显示对话框,用户选择是否异常的弹框
    private void showPopWind6(final TextView et_6) {
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
        hrPopup.showAsDropDown(et_6);
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_6.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_6.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_6.setText("---");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
    }

    //显示对话框,用户选择是否异常的弹框
    private void showPopWind12(final TextView et_12) {
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
        hrPopup.showAsDropDown(et_12);
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_12.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_12.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_12.setText("---");
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
        TextView et_6;
        EditText et_7;
        EditText et_8;
        EditText et_9;
        EditText et_10;
        EditText et_11;
        TextView et_12;
        ImageView et_13;
        EditText et_14;
        RelativeLayout et_15;


        ViewHolder(View view) {
            super(view);
            tv_1 = (TextView) view.findViewById(R.id.tv_1);
            et_2 = (EditText) view.findViewById(R.id.et_2);
            et_3 = (EditText) view.findViewById(R.id.et_3);
            et_4 = (EditText) view.findViewById(R.id.et_4);
            et_5 = (EditText) view.findViewById(R.id.et_5);
            et_6 = (TextView) view.findViewById(R.id.et_6);
            et_7 = (EditText) view.findViewById(R.id.et_7);
            et_8 = (EditText) view.findViewById(R.id.et_8);
            et_9 = (EditText) view.findViewById(R.id.et_9);
            et_10 = (EditText) view.findViewById(R.id.et_10);
            et_11 = (EditText) view.findViewById(R.id.et_11);
            et_12 = (TextView) view.findViewById(R.id.et_12);
            et_13 = (ImageView) view.findViewById(R.id.et_13);
            et_14 = (EditText) view.findViewById(R.id.et_14);
            et_15 = (RelativeLayout) view.findViewById(R.id.et_15);
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
