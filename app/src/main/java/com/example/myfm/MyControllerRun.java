package com.example.myfm;

import android.view.ViewGroup;



public class MyControllerRun  implements Runnable {
    final MyController this$1;
    final ViewGroup val$vg;



    public MyControllerRun(MyController p0, ViewGroup p1){
        this.this$1 = p0;
        this.val$vg = p1;
    }
    @Override
    public void run() {
        this.val$vg.removeView(this.this$1.ctl.fContentViewFrame);
    }
}
