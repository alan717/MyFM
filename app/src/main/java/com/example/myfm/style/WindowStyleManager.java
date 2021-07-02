package com.example.myfm.style;

import android.os.Handler;
import android.view.View;
import android.webkit.WebView;

import com.example.myfm.utils.Logger;

public class WindowStyleManager {
    private StyleEventType fCurrentState;
    private final String fGivenName;
    private Handler fHandler;
    private IStyleChangedListener fListener;
    private final Logger fLog;
    private ModalView fModalView;
    private FeedlyPreferences fPrefs;
    private View fView;
    private IVisibility fVis;
    private WebView fWebView;
    private WindowStyle fWindowStyle;
    public static final float TEMPO;
    public   WindowStyleManager(String p0){
        Object();
        this.fCurrentState = StyleEventType.HIDE;
        this.fView = null;
        this.fVis = null;
        this.fWebView = null;
        this.fGivenName = p0;
        String sstr = new StringBuilder()+this.fGivenName+".sm";
        this.fLog = Logger.getLogger(sstr);
        this.fWindowStyle = new WindowStyle(sstr);
        this.fHandler = new Handler();
        this.fPrefs = FeedlyPreferences.INSTANCE;
    }
}
