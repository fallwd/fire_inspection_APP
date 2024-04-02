package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.DryPowderFireSystemAdapter3;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.helper.TakePhotoHelper;
import com.hr.fire.inspection.impl.YCCCameraForVideo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.FileRoute;
import com.hr.fire.inspection.impl.YCCamera;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DryPowderFireSystemFragment3  extends Fragment {
    View rootView;
    private static DryPowderFireSystemFragment3 fragment3;
    private static String mKey;
    public IntentTransmit its;
    private DryPowderFireSystemAdapter3 adapter;
    private RecyclerView rc_list;
    private List<YearCheck> checkDataEasy;
    private List<YearCheckResult> yearCheckResults;
    private int imgPostion = -1;   //用户点击拍照, 所对应的位置
    private int videoPostion = -1;   //用户点击录像, 所对应的位置

    public static DryPowderFireSystemFragment3 newInstance(String key, IntentTransmit value) {
        if (fragment3 == null) {
            fragment3 = new DryPowderFireSystemFragment3();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment3.setArguments(args);
        return fragment3;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // 建立当前页面的IntentTransmit，为了不影响其他页面的its
            its = new IntentTransmit();
            IntentTransmit it = (IntentTransmit) getArguments().getSerializable(mKey);
            its.srt_Date = it.srt_Date;
            its.systemId = it.systemId;
            its.companyInfoId = it.companyInfoId;
            its.id = it.id;
            its.platformId = it.platformId;
            its.type = it.type;
            its.start_time = it.start_time;
            its.end_time = it.end_time;
            its.name = it.name;
            its.number = it.number;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_hfc3, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }

    private void initData() {
        //根据二氧化碳系统的ID,拿到二氧化碳下面的检查表数据
        List<CheckType> checkTypes = ServiceFactory.getYearCheckService().gettableNameData(its.systemId);
        //在二氧化碳的子表数据中,拿到管线管件对应的id, 通过id去查询管线管件需要检查的内容
        checkDataEasy = ServiceFactory.getYearCheckService().getCheckDataEasy(checkTypes.get(2).getId());
        //获取用户需要填写的数据,如果没有数据,就需要插入的默认数据（流程4）。如果有数据就
        yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, its.companyInfoId, checkTypes.get(2).getId(), its.number, its.srt_Date);
        if (its.name != null || its.name == "基于历史数据新建") {
            if (yearCheckResults != null && yearCheckResults.size() != 0) {
                //点击新增,有数据,就拿到最后一条数据新增,创建一个新的对象
                for (int i = 0; i<yearCheckResults.size(); i++) {
                    YearCheckResult ycr = new YearCheckResult();
                    ycr.setIsPass(" -- ");
//                    ycr.setDescription("无描述");
                    ycr.setImageUrl("暂无图片");
                    ycr.setSystemNumber(its.number);
                    ycr.setProtectArea(its.ProtectArea); // 保护位号
                    ycr.setCheckDate(its.srt_Date);  //检查日期
                    ycr.setUuid(UUID.randomUUID().toString().replace("-",""));  // 数据导入时候做去重判断
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    long nowTime = new Date().getTime();
                    String d = format.format(nowTime);
                    try {
                        its.srt_Date = format.parse(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, 0, checkDataEasy.get(i).getId(), its.companyInfoId,
                            checkTypes.get(2).getId(), its.number, its.srt_Date,its.ProtectArea);
                }
            }
            yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, its.companyInfoId, checkTypes.get(2).getId(), its.number, its.srt_Date);
        } else {
            if (yearCheckResults == null || yearCheckResults.size() == 0) {
                for (int i = 0; i < checkDataEasy.size(); i++) {
                    //3.进入系统就给用户默认插入两条数据, 用户点击保存时,就Updata数据库
                    YearCheckResult ycr = new YearCheckResult();
                    ycr.setIsPass(" -- ");
//                ycr.setImageUrl("暂无图片");  //可以在iv7中获取
//                    ycr.setDescription("无描述");
                    ycr.setSystemNumber(its.number);
                    ycr.setProtectArea(its.ProtectArea); // 保护位号
                    ycr.setCheckDate(its.srt_Date);  //检查日期
                    ycr.setUuid(UUID.randomUUID().toString().replace("-",""));  // 数据导入时候做去重判断
                    ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, 0, checkDataEasy.get(i).getId(), its.companyInfoId,
                            checkTypes.get(2).getId(), its.number, its.srt_Date,its.ProtectArea);
                    yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, its.companyInfoId, checkTypes.get(2).getId(), its.number, its.srt_Date);
                }
            }
        }
        initView();
    }

    private void initView() {
        rc_list = rootView.findViewById(R.id.rc_list3);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new DryPowderFireSystemAdapter3(getActivity(), checkDataEasy, yearCheckResults);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
        // 拍照回显
        adapter.setmYCCamera(new YCCamera() {
            @Override
            public void startCamera(int postion) {
                imgPostion = postion;
                openSysCamera();
            }
        });
        adapter.setAlbumListener(postion -> {
            imgPostion = postion;
            TakePhotoHelper.openPhotoAlbum(DryPowderFireSystemFragment3.this);
        });
        adapter.setdoOpenCameraForVideo(new YCCCameraForVideo() {
            @Override
            public void startCamera(int postion) {
                videoPostion = postion;
                openVideo();
            }
        });
    }

    public void saveData() {
        int childCount = rc_list.getChildCount();
//                Log.d("dong", "childCount==- " + childCount + "    数据条目 " + yearCheckResults.size());
        //两边的数据条数是一样的.
        if (yearCheckResults.size() == childCount) {
            for (int i = 0; i < childCount; i++) {
                LinearLayout childAt = (LinearLayout) rc_list.getChildAt(i);
                TextView tv6 = childAt.findViewById(R.id.tv6);
                //图片参数
                TextView tv7 = childAt.findViewById(R.id.tv7);
                ImageView iv7 = childAt.findViewById(R.id.iv7);
                EditText ev8 = childAt.findViewById(R.id.ev8);

                YearCheckResult yearCheckResult = yearCheckResults.get(i);
                yearCheckResult.setIsPass(tv6.getText().toString().isEmpty() ? " -- " : tv6.getText().toString());
//                yearCheckResult.setImageUrl("暂无图片链接");  //可以在iv7中获取
                yearCheckResult.setDescription(ev8.getText().toString().isEmpty() ? null : ev8.getText().toString());
                yearCheckResult.setSystemNumber(its.number);
                yearCheckResult.setProtectArea(its.ProtectArea); // 保护位号
                yearCheckResult.setCheckDate(its.srt_Date);  //检查日期
                yearCheckResult.setUuid(UUID.randomUUID().toString().replace("-",""));  // 数据导入时候做去重判断
                ServiceFactory.getYearCheckService().update(yearCheckResult);
            }
        }
        Toast.makeText(getContext(), "\"管线管件\"数据保存成功", Toast.LENGTH_SHORT).show();
    }

    // 拍照回调后触发update接口 使是否合格选项不被清空
    public void photoCallbackUpdate() {
        int childCount = rc_list.getChildCount();
        if (yearCheckResults.size() == childCount) {
            for (int i = 0; i < childCount; i++) {
                LinearLayout childAt = (LinearLayout) rc_list.getChildAt(i);
                TextView tv6 = childAt.findViewById(R.id.tv6);
                //图片参数
                TextView tv7 = childAt.findViewById(R.id.tv7);
                ImageView iv7 = childAt.findViewById(R.id.iv7);
                EditText ev8 = childAt.findViewById(R.id.ev8);

                YearCheckResult yearCheckResult = yearCheckResults.get(i);
                yearCheckResult.setIsPass(tv6.getText().toString().isEmpty() ? " -- " : tv6.getText().toString());
//                yearCheckResult.setImageUrl("暂无图片链接");  //可以在iv7中获取
                yearCheckResult.setDescription(ev8.getText().toString().isEmpty() ? null : ev8.getText().toString());
                yearCheckResult.setSystemNumber(its.number);
                yearCheckResult.setProtectArea(its.ProtectArea); // 保护位号
                yearCheckResult.setCheckDate(its.srt_Date);  //检查日期
                yearCheckResult.setUuid(UUID.randomUUID().toString().replace("-",""));  // 数据导入时候做去重判断
                ServiceFactory.getYearCheckService().update(yearCheckResult);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FileRoute.CAMERA_RESULT_CODE:
                //这里目前需要适配
                if (fileNew.getAbsolutePath() != null && imgPostion != -1 && adapter != null) {
                    yearCheckResults.get(imgPostion).setImageUrl(fileNew.getAbsolutePath());
                    photoCallbackUpdate(); // 拍照回调前先提交下填写的数据，不然会被清空
                    adapter.notifyItemChanged(imgPostion);
                }
                break;
            case 0:
                if (videoNew.getAbsolutePath() != null && videoPostion != -1 && adapter != null) {
                    yearCheckResults.get(videoPostion).setVideoUrl(videoNew.getAbsolutePath());
                    photoCallbackUpdate(); // 拍照回调前先提交下填写的数据，不然会被清空
                    adapter.notifyItemChanged(videoPostion);
                }
                Toast.makeText(this.getContext(), "录像数据保存成功，请点击拍照图标进行录像观看", Toast.LENGTH_SHORT).show();
                break;
            case FileRoute.PHOTO_ALBUM_RESULT_CODE:
                if ( imgPostion != -1 && adapter != null) {
                    Uri uri = data.getData();
                    yearCheckResults.get(imgPostion).setImageUrl(uri.toString());
                    adapter.notifyItemChanged(imgPostion);
                }
                break;
        }
    }

    /**
     * 打开系统相机
     */
    private File fileNew = null;
    private void openSysCamera() {
        // intent用来启动系统自带的Camera
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            fileNew = new FileRoute(getActivity()).createOriImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imgUriOri = null;
        if (fileNew != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imgUriOri = Uri.fromFile(fileNew);
            } else {
                imgUriOri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".fileProvider", fileNew);
            }
            // 将系统Camera的拍摄结果写入到文件
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri);
            startActivityForResult(cameraIntent, FileRoute.CAMERA_RESULT_CODE);
        }
    }

    private File videoNew = null;
    private void openVideo() {
        // intent用来启动系统自带的Camera
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            videoNew = new FileRoute(getActivity()).createOriVideoFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imgUriOri = null;
        if (videoNew != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imgUriOri = Uri.fromFile(videoNew);
            } else {
                imgUriOri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".fileProvider", videoNew);
            }
            // 将系统Camera的拍摄结果写入到文件
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri);
            cameraIntent.setAction("android.media.action.VIDEO_CAPTURE");
            cameraIntent.addCategory("android.intent.category.DEFAULT");
            startActivityForResult(cameraIntent, 0);
        }
    }
}
