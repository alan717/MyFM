package com.example.myfm.style;

import android.view.View;

public class LayerStyle {
    final View target;

    public LayerStyle(View p0){
        this.target = p0;
    }
    public static LayerStyle forView(View p0){
        return new LayerStyle(p0);
    }
    public void update(){
    }
}
