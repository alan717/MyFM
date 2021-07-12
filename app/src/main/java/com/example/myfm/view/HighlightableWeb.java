package com.example.myfm.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

import com.example.myfm.utils.Logger;

public class HighlightableWeb extends WebView {
    private Activity fActivity;
    private int fDrawCount;
    private HighlightHandler fHighlightHandler;
    private Logger fLog;
    private boolean fSaveImages;

    public HighlightableWeb(Context context) {
        super(context);
        this.fSaveImages = true;
        this.fLog = Logger.getLogger("main.web");
        this.fActivity = (Activity) context;
    }
    static Logger access$000(HighlightableWeb p0){
        return p0.fLog;
    }
    static Activity access$100(HighlightableWeb p0){
        return p0.fActivity;
    }
    static HighlightHandler access$200(HighlightableWeb p0){
        return p0.fHighlightHandler;
    }
    private void init(Context p0,String p1){
    }

    void dbg(String p0,Object[] p1){
    }
    public HighlightHandler getHighlightHandler(){
        return this.fHighlightHandler;
    }
    void info(String p0,Object[] p1){
        Logger fLog;
        Object[] objectArray;
        if ((fLog = this.fLog) != null) {
            objectArray = new Object[1];
            objectArray[0] = String.format(p0, p1);
            fLog.i(objectArray);
        }
        return;
    }
    protected void onCreateContextMenu(ContextMenu p0){
        super.onCreateContextMenu(p0);
        if (!this.fSaveImages) {
            return;
        }
        WebView.HitTestResult wHitTestResu = this.getHitTestResult();
        HighlightableWebListener u1 = new HighlightableWebListener(this);
        if (wHitTestResu.getType() == 5 || wHitTestResu.getType() == 8) {
            p0.setHeaderTitle(wHitTestResu.getExtra());
            p0.add(0, 0, 0, "Save Image").setOnMenuItemClickListener(u1);
        }
        return;
    }

    public void onScrollChanged(int p0,int p1){
        super.onScrollChanged(p0, p1, p0, p1);
    }
    public void setHighlightHandler(HighlightHandler p0){
        this.fHighlightHandler = p0;
    }
    public ActionMode startActionMode(ActionMode.Callback p0){
        return super.startActionMode(new HighlightableWebCustomActionModeCallback(this, this, p0));
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public ActionMode startActionMode(ActionMode.Callback p0, int p1){
        Object customAction=null;
        if (p0 instanceof ActionMode.Callback2) {
            customAction = new HighlightableWebCustomActionModeCallback2(this, this, (ActionMode.Callback2) p0);
        }else {
            customAction = new HighlightableWebCustomActionModeCallback(this, this, p0);
        }
        return super.startActionMode((ActionMode.Callback) customAction, p1);
    }
}
