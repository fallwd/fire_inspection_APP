package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.List;

public class XJDelugeValveContentApapter extends RecyclerView.Adapter {
    Context mContext;
    private List<InspectionResult> mData;

    public XJDelugeValveContentApapter(Context c, List<InspectionResult> inspectionResults) {
        this.mContext = c;
        this.mData = inspectionResults;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xj_deluge_valve_content_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myholder = (MyViewHolder) holder;
        InspectionResult result = mData.get(position);
        myholder.et_fire1.setText(result.getParam1());
        myholder.et_fire2.setText(result.getParam2());
        myholder.et_fire3.setText(result.getParam3());
        myholder.tv_fire4.setText(result.getParam4());
        myholder.tv_fire5.setText(result.getParam5());
        myholder.tv_fire6.setText(result.getParam6());
        myholder.tv_fire7.setText(result.getParam7());
        myholder.tv_fire8.setText(result.getParam8());
        myholder.tv_fire9.setText(result.getParam9());
        myholder.tv_fire10.setText(result.getParam10());
        myholder.tv_fire11.setText(result.getParam11());
        myholder.tv_fire12.setText(result.getParam12());
        myholder.tv_fire13.setText(result.getParam13());
        myholder.tv_fire14.setText(result.getParam14());
        if (result.getParam15() != null) {
            myholder.et_fire15.setText(result.getParam15());
//            myholder.tv_fire10.setText(result.getParam10());
        }

        myholder.rl_fire4.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire5.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire6.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire7.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire8.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire9.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire10.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire11.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire12.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire13.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire14.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire16.setOnClickListener(new MyOnClickListener(myholder, position));
        myholder.rl_fire17.setOnClickListener(new MyOnClickListener(myholder, position));

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
        private final MyViewHolder myholder;
        private int position;

        public MyOnClickListener(MyViewHolder holder, int position) {
            this.myholder = holder;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.rl_fire1:
//                    showPopWind(myholder.tv_fire1);
//                    break;
                case R.id.rl_fire4:
                    showPopWind(myholder.tv_fire4);
                    break;
                case R.id.rl_fire5:
                    showPopWind(myholder.tv_fire5);
                    break;
                case R.id.rl_fire6:
                    showPopWind(myholder.tv_fire6);
                    break;
                case R.id.rl_fire7:
                    showPopWind(myholder.tv_fire7);
                    break;
                case R.id.rl_fire8:
                    showPopWind(myholder.tv_fire8);
                    break;
                case R.id.rl_fire9:
                    showPopWind(myholder.tv_fire9);
                    break;
                case R.id.rl_fire10:
                    showPopWind(myholder.tv_fire10);
                    break;
                case R.id.rl_fire11:
                    showPopWind(myholder.tv_fire11);
                    break;
                case R.id.rl_fire12:
                    showPopWind(myholder.tv_fire12);
                    break;
                case R.id.rl_fire13:
                    showPopWind(myholder.tv_fire13);
                    break;
                case R.id.rl_fire14:
                    showPopWind(myholder.tv_fire14);
                    break;
                case R.id.rl_fire16:
                    mYCCamera.startCamera(position);
                    break;
                case R.id.rl_fire17:
                    removeData(position);
                    break;
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
        private EditText et_fire1;
        private EditText et_fire2;
        private EditText et_fire3;
        private RelativeLayout rl_fire4;
        private RelativeLayout rl_fire5;
        private RelativeLayout rl_fire6;
        private RelativeLayout rl_fire7;
        private RelativeLayout rl_fire8;
        private RelativeLayout rl_fire9;
        private RelativeLayout rl_fire10;
        private RelativeLayout rl_fire11;
        private RelativeLayout rl_fire12;
        private RelativeLayout rl_fire13;
        private RelativeLayout rl_fire14;
        private EditText et_fire15;
        private RelativeLayout rl_fire16;
        private RelativeLayout rl_fire17;

        private TextView tv_fire1;
        private TextView tv_fire2;
        private TextView tv_fire3;
        private TextView tv_fire4;
        private TextView tv_fire5;
        private TextView tv_fire6;
        private TextView tv_fire7;
        private TextView tv_fire8;
        private TextView tv_fire9;
        private TextView tv_fire10;
        private TextView tv_fire11;
        private TextView tv_fire12;
        private TextView tv_fire13;
        private TextView tv_fire14;
        private TextView tv_fire15;
        private TextView tv_fire16;

        public MyViewHolder(View view) {
            super(view);
            et_fire1 = (EditText) view.findViewById(R.id.et_fire1);
            et_fire2 = (EditText) view.findViewById(R.id.et_fire2);
            et_fire3 = (EditText) view.findViewById(R.id.et_fire3);
            rl_fire4 = (RelativeLayout) view.findViewById(R.id.rl_fire4);
            rl_fire5 = (RelativeLayout) view.findViewById(R.id.rl_fire5);
            rl_fire6 = (RelativeLayout) view.findViewById(R.id.rl_fire6);
            rl_fire7 = (RelativeLayout) view.findViewById(R.id.rl_fire7);
            rl_fire8 = (RelativeLayout) view.findViewById(R.id.rl_fire8);
            rl_fire9 = (RelativeLayout) view.findViewById(R.id.rl_fire9);
            rl_fire10 = (RelativeLayout) view.findViewById(R.id.rl_fire10);
            rl_fire11 = (RelativeLayout) view.findViewById(R.id.rl_fire11);
            rl_fire12 = (RelativeLayout) view.findViewById(R.id.rl_fire12);
            rl_fire13 = (RelativeLayout) view.findViewById(R.id.rl_fire13);
            rl_fire14 = (RelativeLayout) view.findViewById(R.id.rl_fire14);
            et_fire15 = (EditText) view.findViewById(R.id.et_fire15);
            rl_fire16 = (RelativeLayout) view.findViewById(R.id.rl_fire16);
            rl_fire17 = (RelativeLayout) view.findViewById(R.id.rl_fire17);

            tv_fire1 = (TextView) view.findViewById(R.id.tv_fire1);
            tv_fire2 = (TextView) view.findViewById(R.id.tv_fire2);
            tv_fire3 = (TextView) view.findViewById(R.id.tv_fire3);
            tv_fire4 = (TextView) view.findViewById(R.id.tv_fire4);
            tv_fire5 = (TextView) view.findViewById(R.id.tv_fire5);
            tv_fire6 = (TextView) view.findViewById(R.id.tv_fire6);
            tv_fire7 = (TextView) view.findViewById(R.id.tv_fire7);
            tv_fire8 = (TextView) view.findViewById(R.id.tv_fire8);
            tv_fire9 = (TextView) view.findViewById(R.id.tv_fire9);
            tv_fire10 = (TextView) view.findViewById(R.id.tv_fire10);
            tv_fire11 = (TextView) view.findViewById(R.id.tv_fire11);
            tv_fire12 = (TextView) view.findViewById(R.id.tv_fire12);
            tv_fire13 = (TextView) view.findViewById(R.id.tv_fire13);
            tv_fire14 = (TextView) view.findViewById(R.id.tv_fire14);
            tv_fire15 = (TextView) view.findViewById(R.id.tv_fire15);
            tv_fire16 = (TextView) view.findViewById(R.id.tv_fire16);
        }
    }

    private YCCamera mYCCamera;

    //接口回调, 将点击事件传递到activity中,打开相机
    public void setmYCCamera(YCCamera y) {
        this.mYCCamera = y;
    }

    public interface YCCamera {
        void startCamera(int postion);
    }
}
