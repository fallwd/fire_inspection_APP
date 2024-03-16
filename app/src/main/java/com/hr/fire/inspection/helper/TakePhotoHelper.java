package com.hr.fire.inspection.helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import com.hr.fire.inspection.utils.FileRoute;

import androidx.fragment.app.Fragment;

public class TakePhotoHelper {
   public static void openPhotoAlbum(Fragment fragment) {
      // 打开相机相册
      // 在Activity Action里面有一个“ACTION_GET_CONTENT”字符串常量，
      // 该常量让用户选择特定类型的数据，并返回该数据的URI.我们利用该常量，
      // 然后设置类型为“image/*”，就可获得Android手机内的所有image。*/
//      Intent intent = new Intent();
//      /* 开启Pictures画面Type设定为image */
//      intent.setType("image/*");
//      /* 使用Intent.ACTION_GET_CONTENT这个Action */
//      intent.setAction(Intent.ACTION_GET_CONTENT);
      String action;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
         action = Intent.ACTION_OPEN_DOCUMENT;
      } else {
         action = Intent.ACTION_PICK;
      }
      Intent intent = new Intent(action, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      /* 取得相片后返回本画面 */
      fragment.startActivityForResult(intent, FileRoute.PHOTO_ALBUM_RESULT_CODE);
   }
   public static void openPhotoAlbum(Activity activity) {
      // 打开相机相册
      // 在Activity Action里面有一个“ACTION_GET_CONTENT”字符串常量，
      // 该常量让用户选择特定类型的数据，并返回该数据的URI.我们利用该常量，
      // 然后设置类型为“image/*”，就可获得Android手机内的所有image。*/
//      Intent intent = new Intent();
//      /* 开启Pictures画面Type设定为image */
//      intent.setType("image/*");
//      /* 使用Intent.ACTION_GET_CONTENT这个Action */
//      intent.setAction(Intent.ACTION_GET_CONTENT);
      String action;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
         action = Intent.ACTION_OPEN_DOCUMENT;
      } else {
         action = Intent.ACTION_PICK;
      }
      Intent intent = new Intent(action, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      /* 取得相片后返回本画面 */
      activity.startActivityForResult(intent, FileRoute.PHOTO_ALBUM_RESULT_CODE);
   }
}
