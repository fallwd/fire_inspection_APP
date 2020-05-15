package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.impl.YCCamera;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.PhotoView2;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.List;

public class xfspAdapter extends RecyclerView.Adapter {
    Context mContext;
    private List<InspectionResult> mData;

    public xfspAdapter(Context c, List<InspectionResult> inspectionResults) {
        this.mContext = c;
        this.mData = inspectionResults;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xfspitem, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myholder = (MyViewHolder) holder;
        InspectionResult result = mData.get(position);
        myholder.tv_fire1.setText(result.getParam1());
        myholder.tv_fire2.setText(result.getParam2());
        myholder.tv_fire3.setText(result.getParam3());
        myholder.tv_fire4.setText(result.getParam4());
        myholder.et_fire10.setText(result.getDescription());
        String imageUrl = mData.get(position).getImgPath();
        if (imageUrl != null && imageUrl.endsWith(".jpg")) {
            Uri uri = Uri.parse(imageUrl);
            myholder.tv_fire6.setImageURI(uri);
        }else{
            myholder.tv_fire6.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));
        }
        myholder.rl_fire1.setOnClickListener(new xfspAdapter.MyOnClickListener(myholder, position));
        myholder.rl_fire2.setOnClickListener(new xfspAdapter.MyOnClickListener(myholder, position));
        myholder.rl_fire3.setOnClickListener(new xfspAdapter.MyOnClickListener(myholder, position));
        myholder.rl_fire4.setOnClickListener(new xfspAdapter.MyOnClickListener(myholder, position));
        myholder.rl_fire6.setOnClickListener(new xfspAdapter.MyOnClickListener(myholder, position));
        myholder.tv_fire7.setOnClickListener(new xfspAdapter.MyOnClickListener(myholder, position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<InspectionResult> inspectionResults) {
        this.mData = inspectionResults;
        notifyDataSetChanged();
    }


    private HrPopup hrPopup;

    //显示对话框,用户选择是否异常的弹框
    private void showPopWind(TextView tv) {
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
        hrPopup.showAsDropDown(tv);
        rl_yes.setOnClickListener(v -> {
            tv.setText("是");
            if (hrPopup.isShowing()) {
                hrPopup.dismiss();
            }
        });
        rl_no.setOnClickListener(v -> {
            tv.setText("否");
            if (hrPopup.isShowing()) {
                hrPopup.dismiss();
            }
        });
        rl_other.setOnClickListener(v -> {
            tv.setText("其他");
            if (hrPopup.isShowing()) {
                hrPopup.dismiss();
            }
        });
    }

    //点击事件写在外层
    class MyOnClickListener implements View.OnClickListener {
        private final MyViewHolder myholder;
        private int position;

        public MyOnClickListener(MyViewHolder holder, int position) {
            this.myholder = holder;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            String param26 = null;
            for (int i = 0; i<mData.size(); i++) {
                param26 = mData.get(i).getParam26();
            }
            if (param26 != "隐患库") {
                switch (v.getId()) {
                    case R.id.rl_fire1:
                        showPopWind(myholder.tv_fire1);
                        break;
                    case R.id.rl_fire2:
                        showPopWind(myholder.tv_fire2);
                        break;
                    case R.id.rl_fire3:
                        showPopWind(myholder.tv_fire3);
                        break;
                    case R.id.rl_fire4:
                        showPopWind(myholder.tv_fire4);
                        break;
                    case R.id.rl_fire6:
                        new PhotoView2().showPopWindPic(mContext, position, mYCCamera, mData);
                        break;
                    case R.id.tv_fire7:
                        removeData(position);
                        //通知序号列表刷新数据和高度
                        mRemoveXH.deleteRefresh(position);
                        break;
                }
            }
        }
    }

    public void removeData(int position) {
        if (mData != null && mData.size() == 1) {
            Toast.makeText(mContext, "基础表格,无法删除", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mData != null && mData.size() != 0 && mData.size() > 1) {
            //1.删除数据库数据,
            InspectionResult itemInfo = mData.get(position);
            ServiceFactory.getInspectionService().delete(itemInfo);
            //2.刷新列表数据,  理论上应该是数据库删除成功后,有一个返回值,在进行刷新
            mData.remove(position);
            //删除动画
            notifyItemRemoved(position);
            //通知重新绑定某一范围内的的数据与界面
            notifyItemRangeChanged(position, mData.size() - position);//通知数据与界面重新绑定
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_fire1;
        private TextView tv_fire2;
        private TextView tv_fire3;
        private TextView tv_fire4;
        private EditText et_fire10;
        private ImageView tv_fire6;
        private TextView tv_fire7;
        private RelativeLayout rl_fire1;
        private RelativeLayout rl_fire2;
        private RelativeLayout rl_fire3;
        private RelativeLayout rl_fire4;
        private RelativeLayout rl_fire6;
        private RelativeLayout rl_fire7;

        public MyViewHolder(View view) {
            super(view);

            rl_fire1 = view.findViewById(R.id.rl_fire1);
            rl_fire2 = view.findViewById(R.id.rl_fire2);
            rl_fire3 = view.findViewById(R.id.rl_fire3);
            rl_fire4 = view.findViewById(R.id.rl_fire4);
            rl_fire6 = view.findViewById(R.id.rl_fire6);
            rl_fire7 = view.findViewById(R.id.rl_fire7);

            tv_fire1 = view.findViewById(R.id.tv_fire1);
            tv_fire2 = view.findViewById(R.id.tv_fire2);
            tv_fire3 = view.findViewById(R.id.tv_fire3);
            tv_fire4 = view.findViewById(R.id.tv_fire4);

            et_fire10 = view.findViewById(R.id.et_fire10);
            tv_fire6 = view.findViewById(R.id.tv_fire6);
            tv_fire7 = view.findViewById(R.id.tv_fire7);



        }
    }


    private xfspAdapter.RemoveXH mRemoveXH;

    //接口回调, 将点击事件传递到activity中,刷新序号
    public void setDeleteRefresh(xfspAdapter.RemoveXH xh) {
        this.mRemoveXH = xh;
    }

    public interface RemoveXH {
        void deleteRefresh(int postion);
    }

    private YCCamera mYCCamera;

    //接口回调, 将点击事件传递到activity中,打开相机
    public void setmYCCamera(YCCamera y) {
        this.mYCCamera = y;
    }
}
