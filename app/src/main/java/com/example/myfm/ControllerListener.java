package com.example.myfm;

import android.view.View;
import android.view.ViewGroup;

import com.example.myfm.style.IStyleChangedListener;
import com.example.myfm.style.StyleEventType;

public class ControllerListener implements IStyleChangedListener {
    final Controller this$0;

    public ControllerListener(Controller p0){
        this.this$0 = p0;

    }
    public void onStyleChangeComplete(StyleEventType p0, View p1){
        Object[] objectArray;
        ViewGroup vParent;
        Object[] objectArray1;
        if (p0 == StyleEventType.SHOW) {
            if (this.this$0.isMemberOfAGroup()) {
                return;
            }
        }else {
            int vi = 1;
            int vi1 = 0;
            if (p0 == StyleEventType.CLOSE) {
                int vi2 = 2;
                int vi3 = 3;
                if (this.this$0.isMemberOfAGroup()) {
                    objectArray = new Object[vi3];
                    objectArray[vi1] = "CLOSE -> view \"";
                    objectArray[vi] = this.this$0.getGivenName();
                    objectArray[vi2] = "\" is part of group, not detaching now";
                    this.this$0.fLog.i(objectArray);
                    return;
                }else if(this.this$0.fContentViewFrame == null){
                    return;
                }else if((vParent = (ViewGroup) this.this$0.fContentViewFrame.getParent()) != null){
                    this.this$0.fHandler.postDelayed(new ControllerRun(this, vParent), 100);
                }else {
                    objectArray = new Object[vi3];
                    objectArray[vi1] = "view for \"";
                    objectArray[vi] = this.this$0.getGivenName();
                    objectArray[vi2] = "\" is already detached!";
                    this.this$0.fLog.w(objectArray);
                }
            }else if(p0 == StyleEventType.HIDE){
                if (this.this$0.fContentViewFrame.getParent() != null) {
                    this.this$0.hideGrouped();
                }else {
                    objectArray1 = new Object[vi];
                    objectArray1[vi1] = "view is not attached!";
                    this.this$0.fLog.w(objectArray1);
                }
            }
        }

    }

}
