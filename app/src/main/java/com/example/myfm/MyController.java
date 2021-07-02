package com.example.myfm;

import android.view.View;
import android.view.ViewGroup;

import com.example.myfm.style.IStyleChangedListener;
import com.example.myfm.style.StyleEventType;
import com.example.myfm.style.WindowStyleManager;

public class MyController implements IStyleChangedListener {
    final Controller ctl;
    protected WindowStyleManager fStyleManager;
    public MyController(Controller p0){
        this.ctl=p0;
    }
    @Override
    public void onStyleChangeComplete(StyleEventType p0, View p1) {
        ViewGroup vParent;
        Object[] objectArray1;
        if (p0 == StyleEventType.SHOW) {
            if (this.ctl.isMemberOfAGroup()) {
                return;
            }
        }else {
            int vi = 1;
            int vi1 = 0;
            if (p0 == StyleEventType.CLOSE) {
                int vi2 = 2;
                int vi3 = 3;
                if (this.ctl.isMemberOfAGroup()) {
                    return;
                }else if(this.ctl.fContentViewFrame == null){
                    return;
                }else if((vParent = this.ctl.fContentViewFrame.getParent()) != null){
                    this.ctl.fHandler.postDelayed(new MyControllerRun(this, vParent), 100);
                }else {
                    objectArray = new Object[vi3];
                    objectArray[vi1] = "view for \"";
                    objectArray[vi] = this.this$0.getGivenName();
                    objectArray[vi2] = "\" is already detached!";
                    this.ctl.fLog.w(objectArray);
                }
            }else if(p0 == StyleEventType.HIDE){
                if (this.ctl.fContentViewFrame.getParent() != null) {
                    this.ctl.hideGrouped();
                }else {
                    objectArray1 = new Object[vi];
                    objectArray1[vi1] = "view is not attached!";
                    this.ctl.fLog.w(objectArray1);
                }
            }
        }
        return;

    }
}
