package com.example.myfm.style;

import com.example.myfm.Utils;

public class EffectFrame {
    private int fHeight;
    private int fWidth;
    private int fX;
    private int fY;

    public   EffectFrame(){

    }
    public   EffectFrame(int[] p0){

        this.setX(Utils.dp2pixels(p0[0]));
        this.setY(Utils.dp2pixels(p0[1]));
        this.setWidth(Utils.dp2pixels(p0[2]));
        this.setHeight(Utils.dp2pixels(p0[3]));
    }
    public int getHeight(){
        return this.fHeight;
    }
    public int getWidth(){
        return this.fWidth;
    }
    public int getX(){
        return this.fX;
    }
    public int getY(){
        return this.fY;
    }
    public void setHeight(int p0){
        this.fHeight = p0;
    }
    public void setWidth(int p0){
        this.fWidth = p0;
    }
    public void setX(int p0){
        this.fX = p0;
    }
    public void setY(int p0){
        this.fY = p0;
    }
    public String toString(){
        Object[] objectArray;
        objectArray = new Object[4];
        objectArray[0] = Integer.valueOf(this.fX);
        objectArray[1] = Integer.valueOf(this.fY);
        objectArray[2] = Integer.valueOf(this.fWidth);
        objectArray[3] = Integer.valueOf(this.fHeight);
        return String.format("(%d, %d) %dx%d", objectArray);
    }

}
