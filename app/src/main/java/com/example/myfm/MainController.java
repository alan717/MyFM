package com.example.myfm;

import android.webkit.WebView;

public class MainController  extends Controller{
    private WebView fWebView;

    public void init(String p0,MainActivity p1){
        super.init(p0, p1);
        this.fStyleManager.getWindowStyle().setGravity(119);
        this.fStyleManager.getWindowStyle().setHardwareAccelerated(true);
        this.fStyleManager.getWindowStyle().setBringToTop(false);
    }




    public void buildView(){
        Object[] objectArray;
        super.buildView();
        if (this.fWebView == null) {
            this.fWebView = new MainController$1(this, this.fMain);
            int vi = 0;
            this.fWebView.setScrollBarStyle(vi);
            this.fWebView.setTag(this.getGivenName());
            this.configWebView(this.fWebView);
            this.fWebView.getSettings().setUseWideViewPort(true);
            TwoWayBridge twoWayBridge = new TwoWayBridge(this, this.fWebView);
            this.fWebBridge = twoWayBridge;
            this.fBridge = twoWayBridge;
            this.fWebBridge.setBits(1);
            this.fWebViewClient = new MainController$2(this);
            this.fWebChromeClient = new ChromeClient(this.fWebView, this.fMain);
            this.fWebView.setWebViewClient(this.fWebViewClient);
            this.fWebView.setWebChromeClient(this.fWebChromeClient);
            this.setContentView(this.fWebView);
            FeedlyPreferences fPrefs = this.fPrefs;
            try{
                fPrefs.registerListener(this);
                this.clearAllWebViewCaches();
            }catch(java.lang.Throwable e0){
                objectArray = new Object[2];
                objectArray[vi] = "failed to clear all web view caches";
                objectArray[1] = e0;
                this.fLog.i(objectArray);
            }
            WebView fWebView = this.fWebView;
            if (fWebView instanceof HighlightableWeb) {
                fWebView.setHighlightHandler(new MainController$3(this));
            }
        }
        return;
    }

    public WebView getWebView(){
        return this.fWebView;
    }

}
