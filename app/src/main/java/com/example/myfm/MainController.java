package com.example.myfm;

import android.content.Intent;
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
            this.fWebView = new HighlightableWebController(this, this.fMain);
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
    static void access$000(MainController p0,String p1){
        p0.openURL(p1);
    }

    private void openURL(String p0){
        Intent iIntent;
        if ((iIntent = Utils.toPlayStoreIntent(p0)) != null) {
            this.fMain.startActivity(iIntent);
            this.fMain.overridePendingTransition(2130772009, 2130772009);
            return;
        }else if(this.fPrefs.useChromeCustomTabs() && p0 && p0.indexOf("youtube.com") == -1){
                this.openCustomTab(p0);
        }else {
            iIntent = new Intent("android.intent.action.VIEW");
            iIntent.setData(Uri.parse(p0));
            this.fMain.startActivity(iIntent);
        }
        return;
    }
    private void openCustomTab(String p0){
        String sPackageName;
        try{
            CustomTabsIntent$Builder builder = new CustomTabsIntent$Builder();
            builder.setToolbarColor(Color.parseColor("#FCFCFC"));
            builder.setShowTitle(true);
            builder.setActionButton(BitmapFactory.decodeResource(this.fMain.getResources(), 2131165269), "Share", this.createPendingIntent());
            builder.enableUrlBarHiding();
            builder.setCloseButtonIcon(BitmapFactory.decodeResource(this.fMain.getResources(), 2131165333));
            builder.setStartAnimations(this.fMain, 2130772007, 2130772011);
            builder.setExitAnimations(this.fMain, 2130772011, 2130772010);
            if ((sPackageName = CustomTabsHelper.getPackageNameToUse(this.fMain)) == null) {
                sPackageName = "com.android.chrome";
            }
            CustomTabsIntent cbuild = builder.build();
            cbuild.intent.setPackage(sPackageName);
            cbuild.launchUrl(this.fMain, Uri.parse(p0));
        }catch(android.content.ActivityNotFoundException e-1){
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(p0));
            this.fMain.startActivity(intent);
        }
        return;
    }
}
