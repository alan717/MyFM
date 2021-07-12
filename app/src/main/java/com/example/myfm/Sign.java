package com.example.myfm;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.example.myfm.command.WM;
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
    public void error(){
        WebView wWebView = this.getWebView();
        wWebView.loadDataWithBaseURL("file:///android_asset/www/index.html", templates_popup.T.error(this.fMsg), "text/html", "UTF-8", null);
        Toast toast = new Toast(this.fCtx);
        toast.setDuration(0);
        toast.setView(wWebView);
        toast.setGravity(87, 0, 0);
        toast.show();
    }
    protected void configWebView(WebView p0){
        p0.getSettings().setJavaScriptEnabled(false);
        p0.getSettings().setDatabaseEnabled(false);
        p0.getSettings().setAppCacheEnabled(false);
        p0.getSettings().setDomStorageEnabled(false);
        p0.getSettings().setSupportMultipleWindows(false);
        p0.getSettings().setSupportZoom(false);
        if (Build.VERSION.SDK_INT >= 14) {
            p0.getSettings().setTextZoom(100);
        }
        p0.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        p0.setScrollBarStyle(0);
        p0.setScrollbarFadingEnabled(true);
        p0.setHorizontalScrollBarEnabled(false);
        p0.setVerticalScrollBarEnabled(false);
        return;
    }
    protected WebView getWebView(){
        if (this.fWebView == null) {
            this.fWebView = new WebView(this.fCtx);
            this.configWebView(this.fWebView);
        }
        return this.fWebView;
    }
}
