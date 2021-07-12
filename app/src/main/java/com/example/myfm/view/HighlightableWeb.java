package com.example.myfm.view;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.webkit.WebView;

import com.example.myfm.utils.Logger;

public class HighlightableWeb extends WebView {
    private Activity fActivity;
    private int fDrawCount;
    private HighlightHandler fHighlightHandler;
    private Logger fLog;
    private boolean fSaveImages;
    private static final boolean DEBUG;
    private static final int ID_SAVEIMAGE;
    public HighlightableWeb(Context context) {
        super(context);
        this.fSaveImages = true;
        this.fLog = Logger.getLogger("main.web");
        this.fActivity = context;
    }
    static Logger access$000(HighlightableWeb p0){
        return p0.fLog;
    }
    protected void onCreateContextMenu(ContextMenu p0){
        super.onCreateContextMenu(p0);
        if (!this.fSaveImages) {
            return;
        }
        WebView.HitTestResult wHitTestResu = this.getHitTestResult();
        HighlightableWeb$1 u1 = new HighlightableWeb$1(this);
        if (wHitTestResu.getType() == 5 || wHitTestResu.getType() == 8) {
            p0.setHeaderTitle(wHitTestResu.getExtra());
            p0.add(0, 0, 0, "Save Image").setOnMenuItemClickListener(u1);
        }
        return;
    }
}
