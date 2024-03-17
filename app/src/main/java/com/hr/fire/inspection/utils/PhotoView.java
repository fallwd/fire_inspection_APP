package com.hr.fire.inspection.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.impl.YCCCameraForVideo;
import com.hr.fire.inspection.impl.YCCPhotoAlbum;
import com.hr.fire.inspection.impl.YCCamera;
import com.hr.fire.inspection.view.tableview.HrPopup;


import java.io.File;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * 年检, 巡检页面拍照、查看大图的弹框
 */
public class PhotoView {

    private boolean isDismiss = false;
    private HrPopup hrPopup;

    /**
     * 参数说明:
     * 注意注意ycr  有两种数据,一种是YearCheckResult对象，一种是ItemInfo对象，
     * 根据适配器其中的数据类型决定调用那个方法
     * 如果是：  List<YearCheckResult> ，就调用 showPopWindPic（）方法
     * 如果是：  List<ItemInfo> ，就调用  showPopWindPicInfo（）  方法
     *
     * @param mContext  上下文
     * @param postion   位置
     * @param mYCCamera 拍照回调的接口
     * @param ycr       注意注意:原始数据:这里有两种数据,根据
     */
    public void showPopWindPic(Context mContext, int postion, YCCamera mYCCamera, List<YearCheckResult> ycr) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_pic_see, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        Button bt_photograph = PopupRootView.findViewById(R.id.bt_photograph);
        Button bt_see = PopupRootView.findViewById(R.id.bt_see);
        Button bt_cancel = PopupRootView.findViewById(R.id.bt_cancel);
        ImageView iv_pic_see = PopupRootView.findViewById(R.id.iv_pic_see);
        LinearLayout ll_pop_item = PopupRootView.findViewById(R.id.ll_pop_item);
        RelativeLayout rl_pop_item = PopupRootView.findViewById(R.id.rl_pop_item);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(false);
        hrPopup.setOutsideTouchable(false);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
        bt_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                mYCCamera.startCamera(postion);
            }
        });

        bt_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = ycr.get(postion).getImageUrl();
                if (imageUrl != null && imageUrl.endsWith(".jpg")) {
                    isDismiss = true;
                    ll_pop_item.setVisibility(View.GONE);
                    iv_pic_see.setVisibility(View.VISIBLE);
                    Uri uri = Uri.parse(imageUrl);
                    iv_pic_see.setImageURI(uri);
                } else {
                    ToastUtil.show(mContext, "没有采集图片", Toast.LENGTH_SHORT);
                }
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_pop_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDismiss) {
                    if (hrPopup != null && hrPopup.isShowing()) {
                        hrPopup.dismiss();
                    }
                    isDismiss = false;
                }
            }
        });
    }

    /**
     * 照相机、相册选择照片
     * @param mContext
     * @param postion
     * @param mYCCamera
     * @param doOpenCameraForVideo
     * @param ycr
     */
    public void showPopWindPicAlbum(Context mContext, int postion, YCCPhotoAlbum photoAlbum, YCCamera mYCCamera, YCCCameraForVideo doOpenCameraForVideo, List<YearCheckResult> ycr) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_pic_with_album, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        Button bt_photograph = PopupRootView.findViewById(R.id.bt_photograph);
        Button bt_photo_album = PopupRootView.findViewById(R.id.bt_photo_album);
        Button bt_see = PopupRootView.findViewById(R.id.bt_see);
        Button bt_video = PopupRootView.findViewById(R.id.bt_video);
        Button bt_cancel = PopupRootView.findViewById(R.id.bt_cancel);
        ImageView iv_pic_see = PopupRootView.findViewById(R.id.iv_pic_see);
        Button bt_seevideo = PopupRootView.findViewById(R.id.bt_seevideo);
        VideoView video_view = PopupRootView.findViewById(R.id.video_view);
        LinearLayout ll_pop_item = PopupRootView.findViewById(R.id.ll_pop_item);
        RelativeLayout rl_pop_item = PopupRootView.findViewById(R.id.rl_pop_item);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(false);
        hrPopup.setOutsideTouchable(false);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
        bt_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                mYCCamera.startCamera(postion);
            }
        });
        bt_photo_album.setVisibility(photoAlbum != null?View.VISIBLE:View.GONE);
        bt_photo_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                if(photoAlbum != null){
                    photoAlbum.openAlbum(postion);
                }
            }
        });
        bt_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                doOpenCameraForVideo.startCamera(postion);
            }
        });

        bt_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = ycr.get(postion).getImageUrl();
                if (!TextUtils.isEmpty(imageUrl) /*&& imageUrl.endsWith(".jpg")*/) {
                    isDismiss = true;
                    ll_pop_item.setVisibility(View.GONE);
                    iv_pic_see.setVisibility(View.VISIBLE);
                    Uri uri = Uri.parse(imageUrl);
                    iv_pic_see.setImageURI(uri);
                } else {
                    ToastUtil.show(mContext, "没有采集图片", Toast.LENGTH_SHORT);
                }
            }
        });

        bt_seevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = ycr.get(postion).getVideoUrl();
                if (videoUrl != null && videoUrl.endsWith(".mp4")) {
                    try {
                        isDismiss = true;
                        ll_pop_item.setVisibility(View.GONE);
                        video_view.setVisibility(View.VISIBLE);
                        MediaController mediacontroller = new MediaController(mContext);
                        mediacontroller.setAnchorView(video_view);
                        // Get the URL from String videoUrl
                        Uri video = Uri.parse(videoUrl);
                        video_view.setMediaController(mediacontroller);
                        Log.i(TAG, "录像录像录像地址"+video);
                        video_view.setVideoURI(video);

                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }

                    video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            video_view.start();
                        }
                    });
                } else {
                    ToastUtil.show(mContext, "没有获取录像视频", Toast.LENGTH_SHORT);
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_pop_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDismiss) {
                    if (hrPopup != null && hrPopup.isShowing()) {
                        hrPopup.dismiss();
                    }
                    isDismiss = false;
                }
            }
        });
    }


    public void showPopWindPic2(Context mContext, int postion, YCCamera mYCCamera, YCCCameraForVideo doOpenCameraForVideo, List<YearCheckResult> ycr) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_pic_see, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        Button bt_photograph = PopupRootView.findViewById(R.id.bt_photograph);
        Button bt_photo_album = PopupRootView.findViewById(R.id.bt_photo_album);
        Button bt_see = PopupRootView.findViewById(R.id.bt_see);
        Button bt_video = PopupRootView.findViewById(R.id.bt_video);
        Button bt_cancel = PopupRootView.findViewById(R.id.bt_cancel);
        ImageView iv_pic_see = PopupRootView.findViewById(R.id.iv_pic_see);
        Button bt_seevideo = PopupRootView.findViewById(R.id.bt_seevideo);
        VideoView video_view = PopupRootView.findViewById(R.id.video_view);
        LinearLayout ll_pop_item = PopupRootView.findViewById(R.id.ll_pop_item);
        RelativeLayout rl_pop_item = PopupRootView.findViewById(R.id.rl_pop_item);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(false);
        hrPopup.setOutsideTouchable(false);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
        bt_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                mYCCamera.startCamera(postion);
            }
        });
        bt_photo_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                mYCCamera.startCamera(postion);
            }
        });
        bt_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                doOpenCameraForVideo.startCamera(postion);
            }
        });

        bt_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = ycr.get(postion).getImageUrl();
                if (imageUrl != null && imageUrl.endsWith(".jpg")) {
                    isDismiss = true;
                    ll_pop_item.setVisibility(View.GONE);
                    iv_pic_see.setVisibility(View.VISIBLE);
                    Uri uri = Uri.parse(imageUrl);
                    iv_pic_see.setImageURI(uri);
                } else {
                    ToastUtil.show(mContext, "没有采集图片", Toast.LENGTH_SHORT);
                }
            }
        });

        bt_seevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = ycr.get(postion).getVideoUrl();
                if (videoUrl != null && videoUrl.endsWith(".mp4")) {
                    try {
                        isDismiss = true;
                        ll_pop_item.setVisibility(View.GONE);
                        video_view.setVisibility(View.VISIBLE);
                        MediaController mediacontroller = new MediaController(mContext);
                        mediacontroller.setAnchorView(video_view);
                        // Get the URL from String videoUrl
                        Uri video = Uri.parse(videoUrl);
                        video_view.setMediaController(mediacontroller);
                        Log.i(TAG, "录像录像录像地址"+video);
                        video_view.setVideoURI(video);

                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }

                    video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            video_view.start();
                        }
                    });
                } else {
                    ToastUtil.show(mContext, "没有获取录像视频", Toast.LENGTH_SHORT);
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_pop_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDismiss) {
                    if (hrPopup != null && hrPopup.isShowing()) {
                        hrPopup.dismiss();
                    }
                    isDismiss = false;
                }
            }
        });
    }


    public void showHiddenLibaryDetail(Context mContext, int postion, List<YearCheckResult> ycr) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.hidden_library_detail_see1, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        Button bt_see = PopupRootView.findViewById(R.id.bt_see);
        Button bt_cancel = PopupRootView.findViewById(R.id.bt_cancel);
        ImageView iv_pic_see = PopupRootView.findViewById(R.id.iv_pic_see);
        Button bt_seevideo = PopupRootView.findViewById(R.id.bt_seevideo);
        VideoView video_view = PopupRootView.findViewById(R.id.video_view);
        LinearLayout ll_pop_item = PopupRootView.findViewById(R.id.ll_pop_item);
        RelativeLayout rl_pop_item = PopupRootView.findViewById(R.id.rl_pop_item);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(false);
        hrPopup.setOutsideTouchable(false);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);

        bt_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = ycr.get(postion).getImageUrl();
                if (imageUrl != null && imageUrl.endsWith(".jpg")) {
                    isDismiss = true;
                    ll_pop_item.setVisibility(View.GONE);
                    iv_pic_see.setVisibility(View.VISIBLE);
                    Uri uri = Uri.parse(imageUrl);
                    iv_pic_see.setImageURI(uri);
                } else {
                    ToastUtil.show(mContext, "没有获取图片", Toast.LENGTH_SHORT);
                }
            }
        });
        bt_seevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = ycr.get(postion).getVideoUrl();
                if (videoUrl != null && videoUrl.endsWith(".mp4")) {
                    try {
                        isDismiss = true;
                        ll_pop_item.setVisibility(View.GONE);
                        video_view.setVisibility(View.VISIBLE);
                        MediaController mediacontroller = new MediaController(mContext);
                        mediacontroller.setAnchorView(video_view);
                        // Get the URL from String videoUrl
                        Uri video = Uri.parse(videoUrl);
                        video_view.setMediaController(mediacontroller);
                        Log.i(TAG, "录像录像录像地址"+video);
                        video_view.setVideoURI(video);

                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }

                    video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            video_view.start();
                        }
                    });
                } else {
                    ToastUtil.show(mContext, "没有获取录像视频", Toast.LENGTH_SHORT);
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_pop_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDismiss) {
                    if (hrPopup != null && hrPopup.isShowing()) {
                        hrPopup.dismiss();
                    }
                    isDismiss = false;
                }
            }
        });
    }


    public void showPopWindPicInfo(Context mContext, int postion, YCCamera mYCCamera, YCCCameraForVideo doOpenCameraForVideo, List<ItemInfo> mData) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_pic_see, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        Button bt_photograph = PopupRootView.findViewById(R.id.bt_photograph);
        Button bt_see = PopupRootView.findViewById(R.id.bt_see);
        Button bt_cancel = PopupRootView.findViewById(R.id.bt_cancel);
        Button bt_video = PopupRootView.findViewById(R.id.bt_video);
        Button bt_seevideo = PopupRootView.findViewById(R.id.bt_seevideo);
        VideoView video_view = PopupRootView.findViewById(R.id.video_view);
        ImageView iv_pic_see = PopupRootView.findViewById(R.id.iv_pic_see);
        LinearLayout ll_pop_item = PopupRootView.findViewById(R.id.ll_pop_item);
        RelativeLayout rl_pop_item = PopupRootView.findViewById(R.id.rl_pop_item);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(false);
        hrPopup.setOutsideTouchable(false);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
        bt_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                mYCCamera.startCamera(postion);
            }
        });


        bt_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                doOpenCameraForVideo.startCamera(postion);
            }
        });



        bt_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = mData.get(postion).getImageUrl();
                if (imageUrl != null && imageUrl.endsWith(".jpg")) {
                    isDismiss = true;
                    ll_pop_item.setVisibility(View.GONE);
                    iv_pic_see.setVisibility(View.VISIBLE);
                    Uri uri = Uri.parse(imageUrl);
                    iv_pic_see.setImageURI(uri);
                } else {
                    ToastUtil.show(mContext, "没有采集图片", Toast.LENGTH_SHORT);
                }
            }
        });


        bt_seevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = mData.get(postion).getVideoUrl();
                if (videoUrl != null && videoUrl.endsWith(".mp4")) {
                    try {
                        isDismiss = true;
                        ll_pop_item.setVisibility(View.GONE);
                        video_view.setVisibility(View.VISIBLE);
                        MediaController mediacontroller = new MediaController(mContext);
                        mediacontroller.setAnchorView(video_view);
                        // Get the URL from String videoUrl
                        Uri video = Uri.parse(videoUrl);
                        video_view.setMediaController(mediacontroller);
                        video_view.setVideoURI(video);

                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }

                    video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            video_view.start();
                        }
                    });
                } else {
                    ToastUtil.show(mContext, "没有获取录像视频", Toast.LENGTH_SHORT);
                }
            }
        });



        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_pop_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDismiss) {
                    if (hrPopup != null && hrPopup.isShowing()) {
                        hrPopup.dismiss();
                    }
                    isDismiss = false;
                }
            }
        });
    }
    public void showPopWindPicInfoAlbum(Context mContext, int postion,YCCPhotoAlbum albumListener, YCCamera mYCCamera, YCCCameraForVideo doOpenCameraForVideo, List<ItemInfo> mData) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_pic_with_album, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        Button bt_photograph = PopupRootView.findViewById(R.id.bt_photograph);
        Button bt_photo_album = PopupRootView.findViewById(R.id.bt_photo_album);
        Button bt_see = PopupRootView.findViewById(R.id.bt_see);
        Button bt_cancel = PopupRootView.findViewById(R.id.bt_cancel);
        Button bt_video = PopupRootView.findViewById(R.id.bt_video);
        Button bt_seevideo = PopupRootView.findViewById(R.id.bt_seevideo);
        VideoView video_view = PopupRootView.findViewById(R.id.video_view);
        ImageView iv_pic_see = PopupRootView.findViewById(R.id.iv_pic_see);
        LinearLayout ll_pop_item = PopupRootView.findViewById(R.id.ll_pop_item);
        RelativeLayout rl_pop_item = PopupRootView.findViewById(R.id.rl_pop_item);
        hrPopup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(false);
        hrPopup.setOutsideTouchable(false);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAtLocation(hrPopup.getContentView(), Gravity.CENTER, 0, 0);
        bt_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                mYCCamera.startCamera(postion);
            }
        });
        bt_photo_album.setVisibility(albumListener != null?View.VISIBLE:View.GONE);
        bt_photo_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                if(albumListener != null){
                    albumListener.openAlbum(postion);
                }
            }
        });

        bt_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup != null || hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
                doOpenCameraForVideo.startCamera(postion);
            }
        });



        bt_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = mData.get(postion).getImageUrl();
                if (!TextUtils.isEmpty(imageUrl)/* && imageUrl.endsWith(".jpg")*/) {
                    isDismiss = true;
                    ll_pop_item.setVisibility(View.GONE);
                    iv_pic_see.setVisibility(View.VISIBLE);
                    Uri uri = Uri.parse(imageUrl);
                    iv_pic_see.setImageURI(uri);
                } else {
                    ToastUtil.show(mContext, "没有采集图片", Toast.LENGTH_SHORT);
                }
            }
        });


        bt_seevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = mData.get(postion).getVideoUrl();
                if (videoUrl != null && videoUrl.endsWith(".mp4")) {
                    try {
                        isDismiss = true;
                        ll_pop_item.setVisibility(View.GONE);
                        video_view.setVisibility(View.VISIBLE);
                        MediaController mediacontroller = new MediaController(mContext);
                        mediacontroller.setAnchorView(video_view);
                        // Get the URL from String videoUrl
                        Uri video = Uri.parse(videoUrl);
                        video_view.setMediaController(mediacontroller);
                        video_view.setVideoURI(video);

                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }

                    video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            video_view.start();
                        }
                    });
                } else {
                    ToastUtil.show(mContext, "没有获取录像视频", Toast.LENGTH_SHORT);
                }
            }
        });



        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_pop_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDismiss) {
                    if (hrPopup != null && hrPopup.isShowing()) {
                        hrPopup.dismiss();
                    }
                    isDismiss = false;
                }
            }
        });
    }

}
