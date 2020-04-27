package com.hr.fire.inspection.activity;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class MyJavascriptInterface {
    private Context context;

    public MyJavascriptInterface(Context context) {
        this.context = context;
    }

    /**
     * 网页使用的js，方法无参数
     */
    @JavascriptInterface
    public void startFunction() {
        Log.e("startFunction", "----无参");
    }

    /**
     * 网页使用的js，方法有参数，且参数名为data
     *
     * @param data 网页js里的参数名
     */
    @JavascriptInterface
    public void startFunction(String data) {
        //  这里接收到参数  需要去查数据库

    }
}
