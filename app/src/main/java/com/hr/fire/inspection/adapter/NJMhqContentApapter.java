package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.NJMhqSelectItem1;
import com.hr.fire.inspection.entity.NJMhqSelectItem2;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.view.tableview.HrPopup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NJMhqContentApapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ItemInfo> mData;
    private Map<Integer, List<NJMhqSelectItem1>> mapSelection1 = new HashMap();
    private Map<Integer, List<NJMhqSelectItem2>> mapSelection2 = new HashMap();
    public NJMhqContentApapter(Context mContext, List<ItemInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mapSelection1.clear();
        mapSelection2.clear();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.nj_mhq_content_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NJMhqContentApapter.MyViewHolder myholder = (NJMhqContentApapter.MyViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            ItemInfo info = mData.get(position);
            myholder.et_fire1.setText(info.getTypeNo());
            myholder.tv_fire2.setText(info.getDeviceType());
            myholder.tv_fire3.setText(info.getLevel());
            myholder.tv_fire4.setText(info.getTaskNumber());
            myholder.et_fire5.setText(info.getProdFactory());
            String mCheckDate = (String) TimeUtil.getInstance().dataToHHmmss(info.getProdDate());
            myholder.et_fire6.setText(mCheckDate);
            myholder.tv_fire7.setText(info.getTypeConformity());
            myholder.tv_fire8.setText(info.getPositionConformity());
            myholder.tv_fire9.setText(info.getAppearance());
            myholder.tv_fire10.setText(info.getIsPressure());
            myholder.tv_fire11.setText(info.getEffectiveness());
            String mCheckDate2 = (String) TimeUtil.getInstance().dataToHHmmss(info.getObserveDate());
            myholder.et_fire12.setText(mCheckDate2);
            myholder.tv_fire13.setText(info.getIsPass());
            myholder.et_fire14.setText(info.getLabelNo());
            myholder.tv_fire15.setText(info.getImageUrl());
            myholder.et_fire16.setText(info.getDescription());
            myholder.tv_fire17.setText(info.getCodePath());

            //初始化灭火等级
            NJMhqSelectItem1 mWorkIItemBean1 = new NJMhqSelectItem1();
            List<NJMhqSelectItem1> workSelectData = mWorkIItemBean1.getWorkSelectData();
            mapSelection1.put(position, workSelectData);
            //初始化工作代号
            NJMhqSelectItem2 mWorkIItemBean2 = new NJMhqSelectItem2();
            List<NJMhqSelectItem2> workSelectData2 = mWorkIItemBean2.getWorkSelectData();
            mapSelection2.put(position, workSelectData2);

            myholder.rl_fire2.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire3.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire4.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire7.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire8.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire9.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire10.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire11.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire13.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire17.setOnClickListener(new MyOnClickListener(myholder, position));
            myholder.rl_fire18.setOnClickListener(new MyOnClickListener(myholder, position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<ItemInfo> itemDataList) {
        this.mData = itemDataList;
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
    //显示对话框,用户选择是否异常的弹框
    private void showPopWind2(TextView tv) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.nj_select2, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        RelativeLayout rl_yes = PopupRootView.findViewById(R.id.rl_yes);
        RelativeLayout rl_no = PopupRootView.findViewById(R.id.rl_no);
        RelativeLayout rl_other = PopupRootView.findViewById(R.id.rl_other);
        RelativeLayout ff = PopupRootView.findViewById(R.id.ff);
        RelativeLayout jj = PopupRootView.findViewById(R.id.jj);
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
                tv.setText("S");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("P");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("T");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("F");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        jj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("J");
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
                case R.id.rl_fire2:
                    showPopWind2(myholder.tv_fire2);
                    break;
                case R.id.rl_fire3:
                    showPopWindWork1(myholder.tv_fire3, mapSelection1, position);
                    break;
                case R.id.rl_fire4:
                    showPopWindWork2(myholder.tv_fire4, mapSelection2, position);
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
                case R.id.rl_fire13:
                    showPopWind(myholder.tv_fire13);
                    break;

                case R.id.rl_fire17:
                    showPopWind(myholder.tv_fire17);
                    break;
                case R.id.rl_fire18:
                    removeData(position);
                    //通知序号列表刷新数据和高度
                    mRemoveXH.deleteRefresh(position);
                    break;
            }
        }
    }
//    灭火级别下拉多选
    private void showPopWindWork1(TextView tv_fire3, Map<Integer, List<NJMhqSelectItem1>> mapSelectData, int position) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_item, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        ListView list_work = PopupRootView.findViewById(R.id.list_work);
        TextView tv_canl = PopupRootView.findViewById(R.id.tv_canl);
        TextView tv_confirm = PopupRootView.findViewById(R.id.tv_confirm);

        List<NJMhqSelectItem1> workIItemBeans = mapSelectData.get(position);
        NJ_Mhq_Select_Adapter1 workSheetAdapter = new NJ_Mhq_Select_Adapter1(mContext, workIItemBeans);
        list_work.setAdapter(workSheetAdapter);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(false);
        hrPopup.setOutsideTouchable(false);
        hrPopup.setContentView(PopupRootView);
//        hrPopup.showAsDropDown(tv);
        tv_canl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                    List<NJMhqSelectItem1> selection = workSheetAdapter.getSelection();
                    mapSelectData.put(position, selection);
                    //将结果赋值给tv11
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < selection.size(); i++) {
                        NJMhqSelectItem1 mBean = selection.get(i);
                        if (mBean.isState()) {
                            builder.append(1 + i).append(",");
                        }
                    }
                    tv_fire3.setText(builder.toString().substring(0, builder.length() - 1));
                }
            }
        });
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
    }
//    工作代号下拉多选
private void showPopWindWork2(TextView tv_fire4, Map<Integer, List<NJMhqSelectItem2>> mapSelectData, int position) {
    View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_item, null);
    if (hrPopup == null) {
        hrPopup = new HrPopup((Activity) mContext);
    }
    ListView list_work = PopupRootView.findViewById(R.id.list_work);
    TextView tv_canl = PopupRootView.findViewById(R.id.tv_canl);
    TextView tv_confirm = PopupRootView.findViewById(R.id.tv_confirm);

    List<NJMhqSelectItem2> workIItemBeans = mapSelectData.get(position);
    NJ_Mhq_Select_Adapter2 workSheetAdapter = new NJ_Mhq_Select_Adapter2(mContext, workIItemBeans);
    list_work.setAdapter(workSheetAdapter);
    hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    hrPopup.setBackgroundDrawable(new BitmapDrawable());
    hrPopup.setFocusable(false);
    hrPopup.setOutsideTouchable(false);
    hrPopup.setContentView(PopupRootView);
//        hrPopup.showAsDropDown(tv);
    tv_canl.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hrPopup.isShowing()) {
                hrPopup.dismiss();
            }
        }
    });
    tv_confirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hrPopup.isShowing()) {
                hrPopup.dismiss();
                List<NJMhqSelectItem2> selection = workSheetAdapter.getSelection();
                mapSelectData.put(position, selection);
                //将结果赋值给tv11
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < selection.size(); i++) {
                    NJMhqSelectItem2 mBean = selection.get(i);
                    if (mBean.isState()) {
                        builder.append(1 + i).append(",");
                    }
                }
                tv_fire4.setText(builder.toString().substring(0, builder.length() - 1));
            }
        }
    });
    hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
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


    class MyViewHolder extends RecyclerView.ViewHolder {
        private EditText et_fire1;
        private RelativeLayout rl_fire2;
        private RelativeLayout rl_fire3;
        private RelativeLayout rl_fire4;
        private EditText et_fire5;
        private EditText et_fire6;
        private RelativeLayout rl_fire7;
        private RelativeLayout rl_fire8;
        private RelativeLayout rl_fire9;
        private RelativeLayout rl_fire10;
        private RelativeLayout rl_fire11;
        private EditText et_fire12;
        private RelativeLayout rl_fire13;
        private EditText et_fire14;
        private RelativeLayout rl_fire15;
        private EditText et_fire16;
        private RelativeLayout rl_fire17;
        private RelativeLayout rl_fire18;



        private TextView tv_fire1;
        private TextView tv_fire2;
        private TextView tv_fire3;
        private TextView tv_fire4;
        private TextView tv_fire5;
        private TextView tv_fire7;
        private TextView tv_fire8;
        private TextView tv_fire9;
        private TextView tv_fire10;
        private TextView tv_fire11;
        private TextView tv_fire13;
        private TextView tv_fire14;
        private TextView tv_fire15;
        private TextView tv_fire16;
        private TextView tv_fire17;
        private TextView tv_fire18;

        public MyViewHolder(View view) {
            super(view);
            et_fire1 = (EditText) view.findViewById(R.id.et_fire1);
            rl_fire2 = (RelativeLayout) view.findViewById(R.id.rl_fire2);
            rl_fire3 = (RelativeLayout) view.findViewById(R.id.rl_fire3);
            rl_fire4 = (RelativeLayout) view.findViewById(R.id.rl_fire4);
            et_fire5 = (EditText) view.findViewById(R.id.et_fire5);
            et_fire6 = (EditText) view.findViewById(R.id.et_fire6);
            rl_fire7 = (RelativeLayout) view.findViewById(R.id.rl_fire7);
            rl_fire8 = (RelativeLayout) view.findViewById(R.id.rl_fire8);
            rl_fire9 = (RelativeLayout) view.findViewById(R.id.rl_fire9);
            rl_fire10 = (RelativeLayout) view.findViewById(R.id.rl_fire10);
            rl_fire11 = (RelativeLayout) view.findViewById(R.id.rl_fire11);
            et_fire12 = (EditText) view.findViewById(R.id.et_fire12);
            rl_fire13 = (RelativeLayout) view.findViewById(R.id.rl_fire13);
            et_fire14 = (EditText) view.findViewById(R.id.et_fire14);
            rl_fire15 = (RelativeLayout) view.findViewById(R.id.rl_fire15);
            et_fire16 = (EditText) view.findViewById(R.id.et_fire16);
            rl_fire17 = (RelativeLayout) view.findViewById(R.id.rl_fire17);
            rl_fire18 = (RelativeLayout) view.findViewById(R.id.rl_fire18);



            tv_fire1 = (TextView) view.findViewById(R.id.tv_fire1);
            tv_fire2 = (TextView) view.findViewById(R.id.tv_fire2);
            tv_fire3 = (TextView) view.findViewById(R.id.tv_fire3);
            tv_fire4 = (TextView) view.findViewById(R.id.tv_fire4);
            tv_fire5 = (TextView) view.findViewById(R.id.tv_fire5);
            tv_fire7 = (TextView) view.findViewById(R.id.tv_fire7);
            tv_fire8 = (TextView) view.findViewById(R.id.tv_fire8);
            tv_fire9 = (TextView) view.findViewById(R.id.tv_fire9);
            tv_fire10 = (TextView) view.findViewById(R.id.tv_fire10);
            tv_fire11 = (TextView) view.findViewById(R.id.tv_fire11);
            tv_fire13 = (TextView) view.findViewById(R.id.tv_fire13);
            tv_fire14 = (TextView) view.findViewById(R.id.tv_fire14);
            tv_fire15 = (TextView) view.findViewById(R.id.tv_fire15);
            tv_fire16 = (TextView) view.findViewById(R.id.tv_fire16);
            tv_fire17 = (TextView) view.findViewById(R.id.tv_fire17);
            tv_fire18 = (TextView) view.findViewById(R.id.tv_fire18);

        }
    }

    private YCCamera mYCCamera;
    private NJMhqContentApapter.RemoveXH mRemoveXH;
    //接口回调, 将点击事件传递到activity中,打开相机
    public void setmYCCamera(NJMhqContentApapter.YCCamera y) {
        this.mYCCamera = y;
    }

    //接口回调, 将点击事件传递到activity中,刷新序号
    public void setDeleteRefresh(NJMhqContentApapter.RemoveXH xh) {
        this.mRemoveXH = xh;
    }


    public interface YCCamera {
        void startCamera(int postion);
    }

    public interface RemoveXH {
        void deleteRefresh(int postion);
    }
}
