package com.hr.fire.inspection.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class TextSpannableUtil {
    /**
     * text    表示text的文字数据
     * rColor  String类型,表示颜色参数,   如: #E51C23
     * startIndex   表示需要变色的文字,开始位置
     * end  表示需要变色的文字,结束位置
     */

    public static SpannableString showTextColor(String text, String rColor, int startIndex, int end) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(rColor)), startIndex, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }
}
