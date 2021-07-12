package com.example.myfm;

import android.view.ViewGroup;

public class ControllerRun implements Runnable {
    final ControllerListener this$1;
    final ViewGroup val$vg;

    public ControllerRun(ControllerListener p0,ViewGroup p1){
        this.this$1 = p0;
        this.val$vg = p1;
     }
    public void run(){
        Object[] objectArray;
        objectArray = new Object[3];
        objectArray[0] = "detaching content view for \"";
        objectArray[1] = this.this$1.this$0.getGivenName();
        objectArray[2] = "\"";
        this.this$1.this$0.fLog.i(objectArray);
        this.val$vg.removeView(this.this$1.this$0.fContentViewFrame);
    }
}
