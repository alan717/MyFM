package com.example.myfm.view;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class HighlightableWebCustomActionModeCallback2 extends ActionMode.Callback2 {
    private ActionMode.Callback2 mOrigCallback;
    private HighlightableWeb mWebView;
    final HighlightableWeb this$0;

    public HighlightableWebCustomActionModeCallback2(HighlightableWeb p0,HighlightableWeb p1,ActionMode.Callback2 p2){
        this.this$0 = p0;
        this.mOrigCallback = p2;
        this.mWebView = p1;
    }
    public boolean onActionItemClicked(ActionMode p0, MenuItem p1){
        if (p1.getItemId() != 2131230830) {
            return this.mOrigCallback.onActionItemClicked(p0, p1);
        }
        Log.d("Higlight", "Highlight pressed");
        this.mWebView.getHighlightHandler().onHighlight();
        return true;
    }
    @SuppressLint("ResourceType")
    public boolean onCreateActionMode(ActionMode p0, Menu p1){
        if (this.mWebView.getHighlightHandler() != null) {
            p0.getMenuInflater().inflate(0x7f0b0000, p1);
        }
        return this.mOrigCallback.onCreateActionMode(p0, p1);
    }
    public void onDestroyActionMode(ActionMode p0){
        this.mOrigCallback.onDestroyActionMode(p0);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onGetContentRect(ActionMode p0, View p1, Rect p2){
        this.mOrigCallback.onGetContentRect(p0, p1, p2);
    }
    public boolean onPrepareActionMode(ActionMode p0,Menu p1){
        return this.mOrigCallback.onPrepareActionMode(p0, p1);
    }
}
