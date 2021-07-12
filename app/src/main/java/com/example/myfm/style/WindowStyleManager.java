package com.example.myfm.style;

import android.os.Handler;
import android.view.View;
import android.webkit.WebView;

import com.example.myfm.AllPreferences;
import com.example.myfm.MainController;
import com.example.myfm.command.WM;
import com.example.myfm.utils.Logger;
import com.example.myfm.view.ModalView;

import org.json.JSONObject;

public class WindowStyleManager {
    private StyleEventType fCurrentState;
    private final String fGivenName;
    private Handler fHandler;
    private IStyleChangedListener fListener;
    private final Logger fLog;
    private ModalView fModalView;
    private AllPreferences fPrefs;
    private View fView;
    private IVisibility fVis;
    private WebView fWebView;
    private WindowStyle fWindowStyle;


    public WindowStyleManager(String p0){
        this.fCurrentState = StyleEventType.HIDE;
        this.fView = null;
        this.fVis = null;
        this.fWebView = null;
        this.fGivenName = p0;
        String sstr = new StringBuilder()+this.fGivenName+".sm";
        this.fLog = Logger.getLogger(sstr);
        this.fWindowStyle = new WindowStyle(sstr);
        this.fHandler = new Handler();
        this.fPrefs = AllPreferences.INSTANCE;
    }


    public void setStyleChangedListener(IStyleChangedListener p0){
        this.fListener = p0;
    }
    static WindowStyle access$000(WindowStyleManager p0){
        return p0.fWindowStyle;
    }
    static IStyleChangedListener access$100(WindowStyleManager p0){
        return p0.fListener;
    }
    static View access$200(WindowStyleManager p0){
        return p0.fView;
    }
    static IVisibility access$300(WindowStyleManager p0){
        return p0.fVis;
    }

    private void applyStyling(boolean p0,boolean p1){
        int vi1;
        ViewGroup$LayoutParams vLayoutParam;
        WebView fWebView;
        ViewGroup$LayoutParams vLayoutParam1;
        WebView fWebView1;
        ViewGroup$LayoutParams vLayoutParam2;
        OrientationSpecificStyle oPortraitSty = (p0)? this.fWindowStyle.getPortraitStyle(): this.fWindowStyle.getLandscapeStyle();
        oPortraitSty.update(this.fView.getParent());
        int vi = 0;
        if (oPortraitSty.getHeight() > 0 && oPortraitSty.getWidth() > 0 && this.fWindowStyle.hasBorder()) {
            this.fView.setBackgroundColor(this.fWindowStyle.getBorderColor().intValue());
            vi1 = this.fWindowStyle.getBorderWidth()+vi;
            int vi2 = 0;
        }else {
            vi1 = 0;
            vi2 = 1;
        }
        if ((vLayoutParam = this.fView.getLayoutParams()) != null) {
            if (this.fView.getLayoutParams() instanceof FrameLayout$LayoutParams) {
                int vi3 = vLayoutParam;
                int iGravity = this.fWindowStyle.getGravity();
                if (vi3.topMargin != oPortraitSty.getMarginTop()) {
                    vi3.topMargin = oPortraitSty.getMarginTop();
                }
                if (vi3.bottomMargin != oPortraitSty.getMarginBottom()) {
                    vi3.bottomMargin = oPortraitSty.getMarginBottom();
                }
                if (vi3.leftMargin != oPortraitSty.getMarginLeft()) {
                    vi3.leftMargin = oPortraitSty.getMarginLeft();
                }
                if (vi3.rightMargin != oPortraitSty.getMarginRight()) {
                    vi3.rightMargin = oPortraitSty.getMarginRight();
                }
                this.fView.setPadding(vi, vi1, vi, vi1);
                if ((iGravity&0x30) == 48) {
                    vi3.topMargin = oPortraitSty.getMarginTop()-vi1;
                }else if((iGravity&0x50) == 80){
                    vi3.bottomMargin = oPortraitSty.getMarginBottom()-vi1;
                }
                vi3.gravity = iGravity;
            }
            if (oPortraitSty.getHeight() <= 0) {
                vLayoutParam.height = -1;
                if ((fWebView = this.fWebView) != null && fWebView.getLayoutParams() != null) {
                    vLayoutParam1 = this.fWebView.getLayoutParams();
                    vLayoutParam1.height = vLayoutParam.height;
                }
            }else {
                vLayoutParam.height = oPortraitSty.getHeight()+(vi1*2);
                if ((fWebView = this.fWebView) != null && fWebView.getLayoutParams() != null) {
                    vLayoutParam1 = this.fWebView.getLayoutParams();
                    vLayoutParam1.height = oPortraitSty.getHeight();
                }
            }
            if (oPortraitSty.getWidth() <= 0) {
                vLayoutParam.width = -1;
                if ((fWebView1 = this.fWebView) != null && fWebView1.getLayoutParams() != null) {
                    vLayoutParam2 = this.fWebView.getLayoutParams();
                    vLayoutParam2.width = vLayoutParam.width;
                }
            }else {
                vLayoutParam.width = oPortraitSty.getWidth();
                if ((fWebView = this.fWebView) != null && fWebView.getLayoutParams() != null) {
                    vLayoutParam1 = this.fWebView.getLayoutParams();
                    vLayoutParam1.width = oPortraitSty.getWidth();
                }
            }
        }
        if (p1 || vi2) {
            View fView = this.fView;
            if (fView instanceof FrameLayout) {
                fView.recomputeViewAttributes(fView);
            }
            this.fView.requestLayout();
        }
        return;
    }
    private ValueAnimator expandAnimator(int p0,int p1,View p2){
        int[] ointArray;
        ointArray = new int[2];
        ointArray[0] = p0;
        ointArray[1] = p1;
        ValueAnimator vInt = ValueAnimator.ofInt(ointArray);
        vInt.addUpdateListener(new WindowStyleManager$4(this, p2, p1, p0));
        return vInt;
    }
    private ValueAnimator opacityAnimator(float p0,float p1,View p2){
        float[] ofloatArray;
        ofloatArray = new float[2];
        ofloatArray[0] = p0;
        ofloatArray[1] = p1;
        ValueAnimator vFloat = ValueAnimator.ofFloat(ofloatArray);
        vFloat.addUpdateListener(new WindowStyleManager$3(this, p2));
        return vFloat;
    }
    private void styleHide(StyleEventType p0,int p1,int p2){
        IStyleChangedListener fListener;
        StyleEventType[] styleEventTy;
        ModalView fModalView;
        ModalView fModalView1;
        StyleEventType[] styleEventTy1;
        if ((fListener = this.fListener) != null) {
            fListener.onStyleChangeComplete(StyleEventType.BEFORE_HIDE, this.fView);
        }
        int vi = 0;
        int vi1 = 1;
        if (p0 == StyleEventType.CLOSE && this.fCurrentState == StyleEventType.SHOW) {
            styleEventTy = new StyleEventType[2];
            styleEventTy[vi] = StyleEventType.HIDE;
            styleEventTy[vi1] = StyleEventType.CLOSE;
        }else {
            styleEventTy1 = new StyleEventType[vi1];
            styleEventTy1[vi] = p0;
            styleEventTy = styleEventTy1;
        }
        if (this.fWindowStyle.getEffect() != null && this.fWindowStyle.getEffect() == VisibilityAnimationType.APPEAR || this.fWindowStyle.getEffectDuration() <= 0) {
            if ((fModalView = this.fModalView) != null) {
                fModalView.fadeOut(0);
            }
            if (this.fListener != null) {
                int len = styleEventTy.length;
                for (; vi < len; vi = vi+1) {
                    this.fListener.onStyleChangeComplete(styleEventTy[vi], this.fView);
                }
            }
            this.fVis.hide();
        }else {
            int iEffectDurat = this.fWindowStyle.getEffectDuration();
            vi = this.fView.getLayoutParams().height;
            vi1 = -1;
            if (vi != vi1) {
                int vi2 = vi;
            }
            vi = this.fView.getLayoutParams().width;
            if (vi == vi1) {
                vi = p1;
            }
            VisibilityAnimationType fADE = VisibilityAnimationType.FADE;
            if (this.fWindowStyle.getEffect() != null) {
                fADE = this.fWindowStyle.getEffect();
            }
            WindowStyleManager$5 u5 = new WindowStyleManager$5(this, styleEventTy);
            if ((fModalView1 = this.fModalView) != null) {
                fModalView1.fadeOut((long)iEffectDurat);
            }
            switch (WindowStyleManager$6.$SwitchMap$com$devhd$feedly$style$VisibilityAnimationType[fADE.ordinal()]){
                case 1:
                    this.fView.animate().setDuration((long)iEffectDurat).alpha(0).setListener(u5);
                    break;
                case 2:
                    this.fView.animate().setDuration((long)iEffectDurat).y((float)(-p2)).setListener(u5);
                    break;
                case 3:
                    this.fView.animate().setDuration((long)iEffectDurat).y((float)p2).setListener(u5);
                    break;
                case 4:
                    this.fView.animate().setDuration((long)iEffectDurat).x((float)p1).setListener(u5);
                    break;
                case 5:
                    this.fView.animate().setDuration((long)iEffectDurat).x((float)(-vi)).setListener(u5);
                    break;
                default:
            }
        }
        this.fModalView = null;
        return;
    }
    public void associate(View p0,WebView p1){
        if (this.fView != p0) {
            this.fView = p0;
            this.fVis = Visibility.create(p0);
        }
        this.fWebView = p1;
        return;
    }
    public String getGivenName(){
        return this.fGivenName;
    }
    protected WebView getMainWebView(){
        MainController mByName;
        WebView wWebView = ((mByName = (MainController) WM.getByName("main")) != null)? mByName.getWebView(): null;
        return wWebView;
    }
    public WindowStyle getWindowStyle(){
        return this.fWindowStyle;
    }
    public void updateStyle(JSONObject p0){
        Object[] objectArray;
        objectArray = new Object[1];
        objectArray[0] = "updating style";
        this.fLog.i(objectArray);
        this.fWindowStyle.update(p0);
    }
    protected WebView getWebView(){
        return null;
    }
}
