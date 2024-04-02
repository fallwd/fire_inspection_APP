package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.CarBonGoodsWeightAcitivty;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.activity.QRCodeExistenceAcitivty;
import com.hr.fire.inspection.constant.ConstantInspection;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.WorkIItemBean;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.view.tableview.HrPopup;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class NjKitchenAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ItemInfo> mData;
    private Long checkid;  //检查表的Id
    private IntentTransmit intentTransmit;   //之前页面数据的传参,如系统号\公司id...
    private HrPopup hrPopup; // 下拉框相关的引用
    private Map<Integer, List<WorkIItemBean>> mapSelection = new HashMap();
    private ViewGroup mParent;
    public NjKitchenAdapter1(Context mContext, List<ItemInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acitivty_nj_kitchen1_input, parent, false);
        ViewHolder holder = new ViewHolder(view);
        mParent = parent;
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        NjKitchenAdapter1.ViewHolder vh = (NjKitchenAdapter1.ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            ItemInfo info = mData.get(position);
            Log.e("dong", "position----:" + position);
            Log.e("dong", "info----:" + info);
            vh.et_1.setText(new StringBuffer().append(" ").append(position + 1));
//            vh.et_2.setText(new StringBuffer().append(info.getAgentsType()).append(""));
//            vh.et_3.setText(new StringBuffer().append(info.getNo()).append(""));
//            vh.et_4.setText(new StringBuffer().append(info.getVolume()).append(""));
//            vh.et_5.setText(new StringBuffer().append(info.getWeight()).append(""));
//            vh.et_6.setText(new StringBuffer().append(info.getGoodsWeight()).append(""));
//            vh.et_7.setText(new StringBuffer().append(info.getProdFactory()).append(""));


            if (info.getAgentsType() == null) {
                vh.et_2.setHint("请输入");
            } else {
                vh.et_2.setText(info.getAgentsType());
            }
            if (info.getNo() == null) {
                vh.et_3.setHint("请输入");
            } else {
                vh.et_3.setText(info.getNo());
            }
            if (info.getVolume() == null) {
                vh.et_4.setHint("请输入");
            } else {
                vh.et_4.setText(info.getVolume());
            }
            if (info.getWeight() == null) {
                vh.et_5.setHint("请输入");
            } else {
                vh.et_5.setText(info.getWeight());
            }
            if (info.getGoodsWeight() == null) {
                vh.et_6.setHint("请输入");
            } else {
                vh.et_6.setText(info.getGoodsWeight());
            }
            if (info.getProdFactory() == null) {
                vh.et_7.setHint("请输入");
            } else {
                vh.et_7.setText(info.getProdFactory());
            }

            String mProdDate = DateFormatUtils.format(info.getProdDate(),"yyyy-MM");
            String mCheckDate = DateFormatUtils.format(info.getFillingDate(),"yyyy-MM");


            vh.et_8.setText(new StringBuffer().append(mProdDate).append(""));
            vh.et_9.setText(new StringBuffer().append(mCheckDate).append(""));

            vh.et_10.setText(new StringBuffer().append(info.getTaskNumber()).append(""));
            vh.et_10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWindWork(vh.et_10,mapSelection,position);
                }
            });
            vh.et_11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkid == 0 || intentTransmit == null) {
                        Toast.makeText(mContext, "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(mContext, CarBonGoodsWeightAcitivty.class);
                    intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_ID, checkid);
                    intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_DIVICE, "药剂瓶  >  检查表");
                    intent.putExtra("item_id", mData.get(position).getId());

                    if (mData.get(position).getId() != 0) {
                        intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_DIVICE_ID, mData.get(position).getId());
                    }
                    intent.putExtra(CarBonGoodsWeightAcitivty.CHECK_SYS_DATA, intentTransmit);
                    mContext.startActivity(intent);
                }
            });

//          下拉框
            vh.et_12.setText(new StringBuffer().append(info.getIsPass()).append(""));
            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.et_12.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            final ViewHolder finalHolder = vh;
            vh.et_12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWind(finalHolder.et_12);
                }
            });
            vh.et_12.setBackground(drawable1);

//            vh.et_13.setText(new StringBuffer().append(info.getLabelNo()));
            if (info.getLabelNo() == null) {
                vh.et_13.setHint("请输入");
            } else {
                vh.et_13.setText(info.getLabelNo());
            }
            vh.et_14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mData.get(position).getNo() == null) {
                        Toast.makeText(mContext, "二维码是跟据瓶号生成的，请填写瓶号,填写完成后请点击保存按钮", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra(ConstantInspection.CHECK_DIVICE, "药剂瓶信息");
                        intent.setClass(mContext, QRCodeExistenceAcitivty.class);
                        // 调用生成函数，处理扫描后显示的数据
                        ItemInfo itemInfo = mData.get(position);
                        Bitmap dCode = create2DCode(itemInfo.toEnCodeString());
                        intent.putExtra("titleValue", mData.get(position).getNo()); // 传某个设备的具体名称
                        byte buf[] = new byte[1024 * 1024];
                        buf = Bitmap2Bytes(dCode);
                        intent.putExtra("photo_bmp", buf);
                        mContext.startActivity(intent);
                    }
                }
            });

            //初始化工作表数据
            WorkIItemBean mWorkIItemBean = new WorkIItemBean();
            List<WorkIItemBean> workSelectData = mWorkIItemBean.getWorkSelectData(5);
            mapSelection.put(position, workSelectData);

        }


        vh.et_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });
    }

    // 调用生成二维码事件
    public static Bitmap create2DCode(String string) {
        return Create2DCode(string, 0, 0);
    }
    /**
     * @param str 用字符串生成二维码
     */
    public static Bitmap Create2DCode(String str, int codeWidth, int codeHeight) {
        // 用于设置QR二维码参数
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别——这里选择最高H级别
        qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        qrParam.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //判断用户指定的二维码大小
        if (codeWidth < 100 || codeHeight < 100 || codeWidth > 1200 || codeHeight > 1200) {
            codeWidth = 400;
            codeHeight = 400;
        }
        //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, codeWidth, codeHeight,qrParam);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        //二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }else{//这个else要加上去，否者保存的二维码全黑
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
    // Bitmap传参转码处理
    private byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    //显示对话框,用户选择是否异常的弹框
    private void showPopWind(final TextView et_12) {
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
//        hrPopup.showAsDropDown(et_12);
        hrPopup.showAtLocation(mParent, Gravity.BOTTOM,0,0);
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

    private void showPopWindWork(TextView tv_11, Map<Integer, List<WorkIItemBean>> mapSelectData, int position) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_item, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        ListView list_work = PopupRootView.findViewById(R.id.list_work);
        TextView tv_canl = PopupRootView.findViewById(R.id.tv_canl);
        TextView tv_confirm = PopupRootView.findViewById(R.id.tv_confirm);

        List<WorkIItemBean> workIItemBeans = mapSelectData.get(position);
        WorkSheetAdapter workSheetAdapter = new WorkSheetAdapter(mContext, workIItemBeans);
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
                    List<WorkIItemBean> selection = workSheetAdapter.getSelection();
                    mapSelectData.put(position, selection);
                    //将结果赋值给tv11
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < selection.size(); i++) {
                        WorkIItemBean mBean = selection.get(i);
                        if (mBean.isState()) {
                            builder.append(1 + i).append(",");
                        }
                    }
                    if (builder.length() == 0) {
                        tv_11.setText("请选择");
                    } else {
                        tv_11.setText(builder.toString().substring(0, builder.length() - 1));
                    }
                }
            }
        });
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
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
        TextView et_1;
        EditText et_2;
        EditText et_3;
        EditText et_4;
        EditText et_5;

        EditText et_6;
        EditText et_7;
        EditText et_8;
        EditText et_9;
        TextView et_10;

        TextView et_11;
        TextView et_12;
        EditText et_13;
        ImageView et_14;
        RelativeLayout et_15;


        ViewHolder(View view) {
            super(view);
            et_1 = (TextView) view.findViewById(R.id.et_1);
            et_2 = (EditText) view.findViewById(R.id.et_2);
            et_3 = (EditText) view.findViewById(R.id.et_3);
            et_4 = (EditText) view.findViewById(R.id.et_4);
            et_5 = (EditText) view.findViewById(R.id.et_5);

            et_6 = (EditText) view.findViewById(R.id.et_6);
            et_7 = (EditText) view.findViewById(R.id.et_7);
            et_8 = (EditText) view.findViewById(R.id.et_8);
            et_9 = (EditText) view.findViewById(R.id.et_9);
            et_10 = (TextView) view.findViewById(R.id.et_10);

            et_11 = (TextView) view.findViewById(R.id.et_11);
            et_12 = (TextView) view.findViewById(R.id.et_12);
            et_13 = (EditText) view.findViewById(R.id.et_13);
            et_14 = (ImageView) view.findViewById(R.id.et_14);
            et_15 = (RelativeLayout) view.findViewById(R.id.et_15);
        }
    }
}
