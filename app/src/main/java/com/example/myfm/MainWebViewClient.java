package com.example.myfm;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainWebViewClient extends WebViewClient {
    final MainController this$0;

    public MainWebViewClient(MainController p0){
        this.this$0 = p0;
     }
    public boolean shouldOverrideUrlLoading(WebView p0, String p1){
        Object[] objectArray;
        objectArray = new Object[2];
        objectArray[0] = "shouldOverrideUrlLoading \(url\): ";
        objectArray[1] = p1;
        this.this$0.fLog.i(objectArray);
        MainController.access$000(this.this$0, p1);
        return true;
    }
}
