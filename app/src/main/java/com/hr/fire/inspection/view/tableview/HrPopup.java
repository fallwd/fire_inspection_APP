package com.hr.fire.inspection.view.tableview;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * popwindw
 */
public class HrPopup extends PopupWindow {
    private Activity activity;

    public HrPopup(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void dismiss() {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 1f;
        activity.getWindow().setAttributes(params);
        super.dismiss();
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }
}
