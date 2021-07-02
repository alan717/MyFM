package com.example.myfm.view;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.myfm.style.WindowStyle;
import com.example.myfm.utils.Logger;

public class ModalView extends View implements View.OnTouchListener {
    final Handler fHandler;
    final Logger fLog;
    final String fName;
    private boolean fTouchEnabled;
    final WindowStyle fWindowStyle;

    public ModalView(Context p0, WindowStyle p1, String p2){
        super(p0);
        this.fHandler = new Handler();
        this.fName = p2;
        this.fWindowStyle = p1;
        this.fLog = Logger.getLogger(new StringBuilder()+"modal."+p2);
        this.setVisibility(INVISIBLE);
        this.setOnTouchListener(this);
        this.setAlpha(0);
    }
    static boolean access$002(ModalView p0,boolean p1){
        p0.fTouchEnabled = p1;
        return p1;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return false;
    }
    public void fadeIn(long p0,View p1){
        this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        this.setBackgroundColor(this.fWindowStyle.getModalBackgroundColor().intValue());
        this.setVisibility(0);
        p1.getParent().addView(this);
        p1.bringToFront();
        this.animate().setDuration(p0).alpha((float)this.fWindowStyle.getModalAlpha()).setListener(new ModalView$1(this));
    }
}
