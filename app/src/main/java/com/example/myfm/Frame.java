package com.example.myfm;

public class Frame {
    public float contentHeight;
    public float contentWidth;
    public float height;
    public float left;
    public float top;
    public float width;

    public   Frame(){

    }
    public   Frame(int p0,int p1,int p2,int p3){
         this.top = (float)p0;
        this.left = (float)p1;
        this.width = (float)p2;
        this.height = (float)p3;
    }
    public boolean isValid(){
        boolean vb = 0 - this.height > 0 && 0 - this.width > 0;
        return vb;
    }
    public String toString(){
        return new StringBuilder()+"{Frame: top="+this.top+", left="+this.left+", width="+this.width+", height="+this.height+", contentWidth="+this.contentWidth+", contentHeight="+this.contentHeight+"}";
    }

}
