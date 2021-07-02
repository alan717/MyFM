package com.example.myfm;

public class MainController  extends Controller{


    public void init(String p0,MainActivity p1){
        super.init(p0, p1);
        this.fStyleManager.getWindowStyle().setGravity(119);
        this.fStyleManager.getWindowStyle().setHardwareAccelerated(true);
        this.fStyleManager.getWindowStyle().setBringToTop(false);
    }
}
