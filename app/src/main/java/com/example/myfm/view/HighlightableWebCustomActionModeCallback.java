package com.example.myfm.view;

import android.annotation.SuppressLint;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

public class HighlightableWebCustomActionModeCallback implements ActionMode.Callback {
    private ActionMode.Callback mOrigCallback;
    private HighlightableWeb mWebView;
    final HighlightableWeb this$0;

    public HighlightableWebCustomActionModeCallback(HighlightableWeb this$0, HighlightableWeb mWebView, ActionMode.Callback mOrigCallback ) {
        this.mOrigCallback = mOrigCallback;
        this.mWebView = mWebView;
        this.this$0 = this$0;
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (this.mWebView.getHighlightHandler() != null) {
            actionMode.getMenuInflater().inflate(0x7f0b0000, menu);
        }
        return this.mOrigCallback.onCreateActionMode(actionMode, menu);
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return this.mOrigCallback.onPrepareActionMode(actionMode, menu);
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getItemId() != 2131230830) {
            return this.mOrigCallback.onActionItemClicked(actionMode, menuItem);
        }
        HighlightableWeb.access$200(this.this$0).onHighlight();
        this.mWebView.getHighlightHandler().onHighlight();
        actionMode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        this.mOrigCallback.onDestroyActionMode(actionMode);
    }
}
