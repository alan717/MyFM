package com.example.myfm.style;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.Object;
import java.lang.Integer;
import java.lang.StringBuilder;
import android.view.ViewParent;
import android.view.View;
import java.util.Iterator;
import java.lang.Object;

public class OrientationSpecificStyle extends UpdateableStyleBase {
    private int FPortraitHeight;
    private Dim fHeight;
    private int fHeightParent;
    private Dim fMarginBottom;
    private Dim fMarginLeft;
    private Dim fMarginRight;
    private Dim fMarginTop;
    private int fPortraitWidth;
    private Dim fWidth;
    private int fWidthParent;
    public static final String PROP_HEIGHT = null;
    public static final String PROP_MARGIN_BOTTOM = null;
    public static final String PROP_MARGIN_LEFT = null;
    public static final String PROP_MARGIN_RIGHT = null;
    public static final String PROP_MARGIN_TOP = null;
    public static final String PROP_WIDTH = null;

    public   OrientationSpecificStyle(String p0){
        super(p0);

        this.fWidthParent = -1;
        this.fHeightParent = -1;
        this.fWidth = new Dim();
        this.fHeight = new Dim();
        this.fMarginLeft = new Dim(0);
        this.fMarginRight = new Dim(0);
        this.fMarginTop = new Dim(0);
        this.fMarginBottom = new Dim(0);
    }
    private Dim updateSize(String p0,JSONObject p1,Dim p2) throws JSONException {
        Dim dim = new Dim();
        dim.init(p1.get(p0));
        this.logChange(p0, p2, dim, Integer.valueOf(0));
        return dim;
    }
    public int getFPortraitHeight(){
        return this.FPortraitHeight;
    }
    public int getHeight(){
        return this.fHeight.get(this.fHeightParent);
    }
    public int getMarginBottom(){
        return this.fMarginBottom.get(this.fHeightParent);
    }
    public int getMarginLeft(){
        return this.fMarginLeft.get(this.fWidthParent);
    }
    public int getMarginRight(){
        return this.fMarginRight.get(0);
    }
    public int getMarginTop(){
        return this.fMarginTop.get(this.fHeightParent);
    }
    public int getPortraitWidth(){
        return this.fPortraitWidth;
    }
    public int getWidth(){
        return this.fWidth.get(this.fWidthParent);
    }
    public void setFPortraitHeight(int p0){
        this.FPortraitHeight = p0;
    }
    public void setHeight(int p0){
        this.fHeight.set(p0);
    }
    public void setMarginBottom(int p0){
        this.fMarginBottom.set(p0);
    }
    public void setMarginLeft(int p0){
        this.fMarginLeft.set(p0);
    }
    public void setMarginRight(int p0){
        this.fMarginRight.set(p0);
    }
    public void setMarginTop(int p0){
        this.fMarginTop.set(p0);
    }
    public void setPortraitWidth(int p0){
        this.fPortraitWidth = p0;
    }
    public void setWidth(int p0){
        this.fWidth.set(p0);
    }
    public String toString(){
        String sappend = this.getGivenName()+":"+this.fWidth+"x"+this.fHeight;
        if (this.fMarginLeft == null && this.fMarginBottom == null && this.fMarginRight != null || this.fMarginTop != null) {
            sappend = sappend+" margin(t:"+this.fMarginTop+" b:"+this.fMarginBottom+" l:"+this.fMarginLeft+" r:"+this.fMarginRight+")";
        }
        return sappend;
    }
    void update(ViewParent p0){
        ViewParent vParent = p0;
        if (p0==null) {
            this.fWidthParent = -1;
            this.fHeightParent = -1;
        }else {
            this.fWidthParent = ((View)vParent).getWidth();
            this.fHeightParent = ((View)vParent).getHeight();
        }
        return;
    }
    void update(JSONObject p0) {
        String str;
        Object[] objectArray;
        int vi = 0;

        Iterator ikeys = p0.keys();
        while (ikeys.hasNext()) {
            str = (String) ikeys.next();
            try {
                if (str.equals("height")) {
                    this.fHeight = this.updateSize(str, p0, this.fHeight);
                } else if (str.equals("width")) {
                    this.fWidth = this.updateSize(str, p0, this.fWidth);
                } else if (str.equals("marginBottom")) {
                    this.fMarginBottom = this.updateSize(str, p0, this.fMarginBottom);
                } else if (str.equals("marginLeft")) {
                    this.fMarginLeft = this.updateSize(str, p0, this.fMarginLeft);
                } else if (str.equals("marginRight")) {
                    this.fMarginRight = this.updateSize(str, p0, this.fMarginRight);
                } else if (str.equals("marginTop")) {
                    this.fMarginTop = this.updateSize(str, p0, this.fMarginTop);
                } else {
                    objectArray = new Object[2];
                    objectArray[0] = "ignoring style ";
                    objectArray[1] = str;
                    this.fLog.w(objectArray);
                }

            } catch (org.json.JSONException e7) {

                throw new IllegalArgumentException(new StringBuilder().append("error processing ").append(str).toString(), e7);
            }
        }
    }
}
