package com.example.myfm.style;

import android.view.View;

public class XPosVisibility implements IVisibility {
    int DX;
    int DY;
    final View target;

    public XPosVisibility(View p0){
         this.DX = -200000;
        this.DY = -200000;
        this.target = p0;
    }
    private boolean checkIf(int p0){
        View target = this.target;
        boolean vb = false;
        if (target == null) {
            return vb;
        }
        if (Math.abs(target.getTranslationX())-(float)Math.abs(p0)==0) {
            vb = true;
        }
        return vb;
    }
    public void gone(){
        this.hide();
    }
    public void hide(){
        View target;
        if ((target = this.target) != null) {
            target.setTranslationX((float)(-this.DX));
            this.target.setTranslationY((float)(-this.DY));
        }
        return;
    }
    public boolean isHidden(){
        return this.checkIf(this.DX);
    }
    public boolean isVisible(){
        return this.checkIf(0);
    }
    public void show(){
        View target;
        if ((target = this.target) != null) {
            target.setTranslationX(0);
            this.target.setTranslationY(0);
        }
        return;
    }
    public String state(){
        View target;
        if ((target = this.target) == null) {
            return "no-view";
        }
        if ((int)target.getTranslationX() <= this.DX) {
            return "hidden-(off-screen)";
        }
        return "visible-(in-screen)";
    }
}
