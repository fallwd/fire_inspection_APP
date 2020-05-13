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

public class XJKitchenWetPowderContentAdapter  extends RecyclerView.Adapter{

    Context mContext;
    private List<InspectionResult> mData;

    public XJKitchenWetPowderContentAdapter(Context c, List<InspectionResult> inspectionResults) {
        this.mContext = c;
        this.mData = inspectionResults;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xj_kitchen_wet_powder_content, parent, false);
        XJKitchenWetPowderContentAdapter.MyViewHolder holder = new XJKitchenWetPowderContentAdapter.MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        XJKitchenWetPowderContentAdapter.MyViewHolder myholder = (XJKitchenWetPowderContentAdapter.MyViewHolder) holder;
        InspectionResult result = mData.get(position);

        myholder.tv_gas1.setText(result.getParam1());
        myholder.tv_gas2.setText(result.getParam2());
        myholder.tv_gas3.setText(result.getParam3());
        myholder.tv_gas4.setText(result.getParam4());
        myholder.tv_gas5.setText(result.getParam5());
        myholder.tv_gas6.setText(result.getParam6());
        myholder.tv_gas7.setText(result.getParam7());
        myholder.tv_gas8.setText(result.getParam8());
        myholder.tv_gas9.setText(result.getParam9());

        myholder.tv_gas10.setText(result.getParam10());


        if (result.getDescription() != null) {
            myholder.et_fire10.setText(result.getDescription());
        }

        String imageUrl = mData.get(position).getImgPath();
        if (imageUrl != null && imageUrl.endsWith(".jpg")) {
            Uri uri = Uri.parse(imageUrl);
            myholder.tv_fire11.setImageURI(uri);
        }else{
            myholder.tv_fire11.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));
        }

        myholder.gas1.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas2.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas3.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas4.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas5.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas6.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas7.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas8.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas9.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.gas10.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.rl_fire11.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));
        myholder.rl_fire12.setOnClickListener(new XJKitchenWetPowderContentAdapter.MyOnClickListener(myholder, position));

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
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("其他");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
    }

    //点击事件写在外层
    class MyOnClickListener implements View.OnClickListener {
        private final XJKitchenWetPowderContentAdapter.MyViewHolder myholder;
        private int position;

        public MyOnClickListener(XJKitchenWetPowderContentAdapter.MyViewHolder holder, int position) {
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
                    case R.id.gas1:
                        showPopWind(myholder.tv_gas1);
                        break;
                    case R.id.gas2:
                        showPopWind(myholder.tv_gas2);
                        break;
                    case R.id.gas3:
                        showPopWind(myholder.tv_gas3);
                        break;
                    case R.id.gas4:
                        showPopWind(myholder.tv_gas4);
                        break;
                    case R.id.gas5:
                        showPopWind(myholder.tv_gas5);
                        break;
                    case R.id.gas6:
                        showPopWind(myholder.tv_gas6);
                        break;
                    case R.id.gas7:
                        showPopWind(myholder.tv_gas7);
                        break;
                    case R.id.gas8:
                        showPopWind(myholder.tv_gas8);
                        break;
                    case R.id.gas9:
                        showPopWind(myholder.tv_gas9);
                        break;
                    case R.id.gas10:
                        showPopWind(myholder.tv_gas10);
                        break;
                    case R.id.rl_fire11:
                        new PhotoView2().showPopWindPic(mContext, position, mYCCamera, mData);
                        break;
                    case R.id.rl_fire12:
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
        private RelativeLayout gas1;
        private RelativeLayout gas2;
        private RelativeLayout gas3;
        private RelativeLayout gas4;
        private RelativeLayout gas5;
        private RelativeLayout gas6;
        private RelativeLayout gas7;
        private RelativeLayout gas8;
        private RelativeLayout gas9;
        private RelativeLayout gas10;

        private EditText et_fire10;
        private RelativeLayout rl_fire11;
        private RelativeLayout rl_fire12;

        private TextView tv_gas1;
        private TextView tv_gas2;
        private TextView tv_gas3;
        private TextView tv_gas4;
        private TextView tv_gas5;
        private TextView tv_gas6;
        private TextView tv_gas7;
        private TextView tv_gas8;
        private TextView tv_gas9;
        private TextView tv_gas10;
        private ImageView tv_fire11;


        public MyViewHolder(View view) {
            super(view);
            gas1 = view.findViewById(R.id.gas1);
            gas2 = view.findViewById(R.id.gas2);
            gas3 = view.findViewById(R.id.gas3);
            gas4 = view.findViewById(R.id.gas4);
            gas5 = view.findViewById(R.id.gas5);
            gas6 = view.findViewById(R.id.gas6);
            gas7 = view.findViewById(R.id.gas7);
            gas8 = view.findViewById(R.id.gas8);
            gas9 = view.findViewById(R.id.gas9);
            gas10 = view.findViewById(R.id.gas10);
            tv_fire11 = view.findViewById(R.id.tv_fire11);

            tv_gas1 = view.findViewById(R.id.tv_gas1);
            tv_gas2 = view.findViewById(R.id.tv_gas2);
            tv_gas3 = view.findViewById(R.id.tv_gas3);
            tv_gas4 = view.findViewById(R.id.tv_gas4);
            tv_gas5 = view.findViewById(R.id.tv_gas5);
            tv_gas6 = view.findViewById(R.id.tv_gas6);
            tv_gas7 = view.findViewById(R.id.tv_gas7);
            tv_gas8 = view.findViewById(R.id.tv_gas8);
            tv_gas9 = view.findViewById(R.id.tv_gas9);
            tv_gas10 = view.findViewById(R.id.tv_gas10);

            et_fire10 = (EditText) view.findViewById(R.id.et_fire10);
            rl_fire11 = (RelativeLayout) view.findViewById(R.id.rl_fire11);
            rl_fire12 = (RelativeLayout) view.findViewById(R.id.rl_fire12);

        }
    }
    private XJKitchenWetPowderContentAdapter.RemoveXH mRemoveXH;
    //接口回调, 将点击事件传递到activity中,刷新序号
    public void setDeleteRefresh(XJKitchenWetPowderContentAdapter.RemoveXH xh) {
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
