package com.example.myfm;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;

import org.json.JSONObject;

import dev.common.util.JavaScriptSource;

public class Sign  {
    final Context fCtx;
    JSONObject fJsonObj;
    final String fMsg;
    WebView fWebView;
    static int NOTIFICATION_ID;
    public   Sign(Activity p0, int p1){

        this.fCtx = p0.getApplicationContext();
        this.fMsg = p0.getResources().getString(p1);
    }
    public   Sign(Activity p0,String p1){

        this.fCtx = p0.getApplicationContext();
        this.fMsg = p1;
    }
    public   Sign(Context p0,String p1){

        this.fCtx = p0;
        this.fMsg = p1;
    }

    static void exec0(String p0,String p1){
        Controller cByName;
        String sappend = new StringBuilder()+p0+"("+ JavaScriptSource.INSTANCE.toSource(p1)+")";
        if ((cByName = WM.getByName("main")) == null) {
            return;
        }
        cByName.exec(sappend);
    }
    private void postSign(String p0){
    }
    public static void showError(String p0){
        Sign.exec0("$B.core.wm.error", p0);
    }
    public static void showMessage(String p0){
        Sign.exec0("$B.core.wm.message", p0);
    }
    public static void showSign(String p0){
        Sign.exec0("SIGN", p0);
    }
    public static void showWarning(String p0){
        Sign.exec0("$B.core.wm.warning", p0);
    }

}
