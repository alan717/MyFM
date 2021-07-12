package com.example.myfm;

import android.os.Build;

public class HackPredicates {
    final String brand;
    public static final HackPredicates INSTANCE= new HackPredicates();
    public   HackPredicates(){
        this.brand = (Build.BRAND != null)? Build.BRAND.toLowerCase(): "unknown";
    }
    public boolean isAtLeastAPI(int p0){
        return Build.VERSION.SDK_INT >= p0;
    }
}
