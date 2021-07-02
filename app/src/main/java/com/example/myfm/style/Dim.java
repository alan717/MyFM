package com.example.myfm.style;

import com.example.myfm.Utils;

public class Dim {
    private float fPercent;
    private byte fType;
    private int fValue;
    static final byte TYPE_PERCENT = 0;
    static final byte TYPE_VALUE = 0;

    public Dim(){

        this.fValue = 0;
        this.fPercent = 1.00f;
        this.fType = 0;
    }
    public Dim(float p0){

        this.fValue = 0;
        this.fPercent = 1.00f;
        this.fType = 0;
        this.set(p0);
    }
    void Dim(int p0){

        this.fValue = 0;
        this.fPercent = 1.00f;
        this.fType = 0;
        this.set(p0);
    }
    int get(int p0){
        if (this.fType==0) {
            return this.fValue;
        }
        float fPercent = this.fPercent;
        if (0x3f800000-fPercent==0) {
            return 0;
        }
        if (p0 < 0) {
            return 0;
        }
        return (int)(fPercent*(float)p0);
    }
    void init(Object p0){
        int vi;
        if (p0 instanceof Number) {
            this.set(Utils.dp2pixels(((Number)p0).intValue()));
            return;
        }else if(p0 instanceof String){
            if ("auto".equals(p0)) {
                return;
            }
            if ((vi = ((String) p0).length()-1) > 0 && ((String)p0).charAt(vi) == 37) {
                this.set((Float.parseFloat(((String)p0).substring(0, vi))/0x42c80000));
            }else {
                this.set(Utils.dp2pixels(Float.parseFloat((String) p0)));
            }
            return;
        }else {
            this.set(1.00f);
        }
    }
    void set(float p0){
        this.fPercent = p0;
        this.fType = 1;
    }
    void set(int p0){
        this.fValue = p0;
        this.fType = 0;
    }
    public String toString(){
        String stringBuilde = new String();
        byte fType = this.fType;
        if (fType == 1) {
            stringBuilde = stringBuilde+"{"+(this.fPercent*0x42c80000)+"%"+"}";
        }else if(fType==0){
            stringBuilde = stringBuilde+"{"+this.fValue+"}";
        }else {
            stringBuilde = stringBuilde+"{unknown}";
        }
        return stringBuilde;
    }
}
