package com.example.myfm.style;

import android.view.View;

public class Visibility  implements IVisibility {

    final View target;
    private boolean check(int p0){
        View target = this.target;
        boolean vb = false;
        if (target == null) {
            return vb;
        }
        if (target.getVisibility() == p0) {
            vb = true;
        }
        return vb;
    }
    public static IVisibility create(View p0){
        return new XPosVisibility(p0);
    }
    private void set(int p0){
        View target;
        if ((target = this.target) != null && target.getVisibility() != p0) {
            this.target.setVisibility(p0);
        }
        return;
    }
    private   Visibility(View p0){

        this.target = p0;
    }
    @Override
    public void gone() {
        this.set(8);
    }

    @Override
    public void hide() {
        this.set(4);
    }

    @Override
    public boolean isHidden() {
        return this.check(8) || this.check(4);
    }

    @Override
    public boolean isVisible() {
        return this.check(0);

    }

    @Override
    public void show() {
        this.set(0);
    }

    @Override
    public String state() {
        View target;
        int iVisibility;
        if ((target = this.target) == null) {
            return "no-view";
        }
        if ((iVisibility = target.getVisibility())==0) {
            return "visible";
        }
        if (iVisibility == 4) {
            return "invisible";
        }
        if (iVisibility != 8) {
            return "unknown";
        }
        return "gone";

    }
}
