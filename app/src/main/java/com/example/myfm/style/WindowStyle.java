package com.example.myfm.style;

import org.json.JSONObject;

public class WindowStyle extends UpdateableStyleBase {
    private Integer fBackgroundColor;
    private Integer fBorderColor;
    private double fBorderRadius;
    private int fBorderWidth;
    private boolean fCloseGestureEnabled;
    private VisibilityAnimationType fEffect;
    private int fEffectDuration;
    private EffectFrame fEffectFrame;
    private int fGravity;
    private boolean fHardwareAccelerated;
    private boolean fHasShadow;
    private boolean fHideGestureEnabled;
    private OrientationSpecificStyle fLandscapeStyle;
    private boolean fLongPressDisabled;
    private double fModalAlpha;
    private Integer fModalBackgroundColor;
    private String fModalTap;
    private OrientationSpecificStyle fPortraitStyle;
    private int fScaleStartHeight;
    private int fScaleStartWidth;
    private double fShadowOffset;
    private double fShadowRadius;
    private PullStyle fTopPull;
    private boolean fTopPullEnabled;
    private int fZIndex;
    private boolean fbBounces;
    private boolean fbBringToTop;
    private boolean fbClips;
    private boolean fbFocusable;
    private boolean fbModal;
    private boolean fbScrollsToTop;
    static String[] MODAL_TAP_VALUES={"close","hide"};
    public static final String PROP_BACKGROUND_COLOR = null;
    public static final String PROP_BORDER_COLOR = null;
    public static final String PROP_BORDER_RADIUS = null;
    public static final String PROP_BORDER_WIDTH = null;
    public static final String PROP_BOUNCES = null;
    public static final String PROP_BRING_TO_TOP = null;
    public static final String PROP_CLIPS = null;
    public static final String PROP_CLOSE_GESTURE_ENABLED = null;
    public static final String PROP_DISPLAY_DURATION = null;
    public static final String PROP_EFFECT = null;
    public static final String PROP_EFFECT_DURATION = null;
    public static final String PROP_EFFECT_FRAME = null;
    public static final String PROP_FOCUSABLE = null;
    public static final String PROP_GRAVITY = null;
    public static final String PROP_HARDWARE_ACCELERATED = null;
    public static final String PROP_HEIGHT = null;
    public static final String PROP_HIDE_GESTURE_ENABLED = null;
    public static final String PROP_LANDSCAPE = null;
    public static final String PROP_LONG_PRESS_DISABLED = null;
    public static final String PROP_MARGIN_BOTTOM = null;
    public static final String PROP_MARGIN_LEFT = null;
    public static final String PROP_MARGIN_RIGHT = null;
    public static final String PROP_MARGIN_TOP = null;
    public static final String PROP_MODAL = null;
    public static final String PROP_MODAL_ALPHA = null;
    public static final String PROP_MODAL_BACKGROUND_COLOR = null;
    public static final String PROP_MODAL_TAP;
    public static final String PROP_PORTRAIT;
    public static final String PROP_SCALE_START_HEIGHT;
    public static final String PROP_SCALE_START_WIDTH;
    public static final String PROP_SCROLLS_TO_TOP;
    public static final String PROP_SHADOW_COLOR;
    public static final String PROP_SHADOW_OFFSET;
    public static final String PROP_SHADOW_RADIUS;
    public static final String PROP_TOP_PULL;
    public static final String PROP_TOP_PULL_ENABLED;
    public static final String PROP_WIDTH;
    public static final String PROP_ZINDEX;
    public static int VAL_BOUNCES_HORIZONTAL=1;
    public static int VAL_BOUNCES_VERTICAL=2;
    public static final String VAL_COLOR_NONE;
    public static final int VAL_SHADOW_THICKNESS;




    public void WindowStyle(String p0){
        super(p0);
        this.fScaleStartWidth = 0;
        this.fScaleStartHeight = -1;
        this.fbClips = false;
        this.fHardwareAccelerated = true;
        this.fbFocusable = false;
        this.fBorderRadius = 0;
        this.fBorderWidth = 0;
        this.fHideGestureEnabled = false;
        this.fCloseGestureEnabled = false;
        this.fLongPressDisabled = false;
        this.fbBringToTop = true;
        this.fbBounces = true;
        this.fbScrollsToTop = false;
        this.fTopPullEnabled = false;
        this.fbModal = false;
        this.fModalAlpha = 0.00f;
        this.fModalTap = WindowStyle.MODAL_TAP_VALUES[0];
        this.fModalBackgroundColor = Integer.valueOf(0);
        this.fShadowRadius = 0;
        this.fShadowOffset = 0;
        this.fEffectDuration = 500;
        this.fLandscapeStyle = new OrientationSpecificStyle(new StringBuilder()+p0+".l");
        this.fPortraitStyle = new OrientationSpecificStyle(new StringBuilder()+p0+".p");
    }
    private int[] intArray(String p0, JSONObject p1, int p2, int p3){
        int vi;
        Object[] objectArray;
        Object[] objectArray1;
        Object[] objectArray2;
        Object oget = p1.get(p0);
        if (oget instanceof JSONArray) {
            int ilength = oget.length();
            if (ilength >= p2 && ilength <= p3) {
                int[] ointArray = new int[ilength];
                for (vi = 0; vi < ilength; vi = vi+1) {
                    objectArray = new Object[2];
                    objectArray[0] = p0;
                    objectArray[1] = Integer.valueOf(vi);
                    ointArray[vi] = this.asNumber(String.format("%s[%d]", objectArray), oget.get(vi)).intValue();
                }
                return ointArray;
            }else {
                objectArray1 = new Object[4];
                objectArray1[0] = Integer.valueOf(ilength);
                objectArray1[1] = p0;
                objectArray1[2] = Integer.valueOf(p2);
                objectArray1[3] = Integer.valueOf(p3);
                throw new IllegalArgumentException(String.format("invalid array size %d for property %s. %d <= len <= %d required", objectArray1));
            }
        }else {
            objectArray2 = new Object[2];
            objectArray2[0] = p0;
            objectArray2[1] = oget.getClass();
            throw new IllegalArgumentException(String.format("exepected JSONArray for %s but encountered %s", objectArray2));
        }
    }
    private Integer parseWebColor(String p0){
        Object[] objectArray;
        Integer integer = null;
        if (!p0 || p0.equals("none")) {
            return integer;
        }
        int vi = 0;
        if (p0.equals("transparent")) {
            return Integer.valueOf(vi);
        }
        int vi1 = 1;
        int vi2 = p0.length()-vi1;
        if (p0.charAt(vi) != 35) {
            objectArray = new Object[vi1];
            objectArray[vi] = new StringBuilder().append("not gonna parse color: ").append(p0).toString();
            this.fLog.w(objectArray);
            return integer;
        }else {
            int vi3 = 16;
            int iInt = Integer.parseInt(p0.substring(vi1), vi3);
            int vi4 = 255;
            if (vi2 == 3) {
                vi = (iInt&0x0f)<<4;
                iInt = ((iInt>>8)&0x0f)<<4;
                vi2 = ((iInt>>4)&0x0f)<<4;
            }else if(vi2 == 6){
                vi = iInt&0x00ff;
                vi2 = (iInt>>8)&vi4;
                iInt = (iInt>>vi3)&vi4;
            }else if(vi2 == 8){
                vi = (iInt>>8)&0x00ff;
                vi2 = (iInt>>16)&vi4;
                iInt = (iInt>>24)&vi4;
                vi4 = iInt&0x00ff;
            }else {
                iInt = 0;
                vi2 = 0;
            }
            return Integer.valueOf(Color.argb(vi4, iInt, vi2, vi));
        }
    }
    private Boolean updateBoolean(String p0,JSONObject p1,Boolean p2,Boolean p3){
        Boolean bboolean0 = this.boolean0(p0, p1);
        this.logChange(p0, p2, bboolean0, p3);
        return bboolean0;
    }
    private Integer updateColor(String p0,JSONObject p1,Integer p2,Integer p3){
        Integer iColor = this.parseWebColor(this.getString(p0, p1));
        this.logChange(p0, p2, iColor, p3);
        return iColor;
    }
    private void updateGravity(String p0,JSONObject p1){
        int iGravity = Utils.parseGravity(this.getString(p0, p1));
        this.logChange(p0, Integer.valueOf(this.fGravity), Integer.valueOf(iGravity));
        this.fGravity = iGravity;
    }
    private Number updateNumber(String p0,JSONObject p1,Number p2,Number p3){
        Number nnumber = this.number(p0, p1);
        this.logChange(p0, p2, nnumber, p3);
        return nnumber;
    }
    private void updateShadow(String p0,JSONObject p1){
        this.logChange(p0, Boolean.valueOf(this.fHasShadow), Boolean.valueOf(true), Boolean.valueOf(false));
        this.fHasShadow = this.getString(p0, p1).equals("none")^1;
    }
    private String updateValue(String p0,JSONObject p1,String p2,String[] p3){
        String sstr = this.getString(p0, p1);
        int len = p3.length;
        int vi = 0;
        while (true) {
            if (vi >= len) {
                return p3[0];
            }
            if (p3[vi].equalsIgnoreCase(sstr)) {
                this.logChange(p0, p2, sstr, p3[0]);
                return sstr;
            }else {
                vi = vi+1;
            }
        }
    }
    private void updateVisibilityAnimation(String p0,JSONObject p1){
        this.fEffect = VisibilityAnimationType.fromStyle(this.getString(p0, p1));
    }
    String color(int p0){
        int ilength;
        StringBuilder sappend = new StringBuilder()+"#";
        for (ilength = Integer.toHexString(p0).length(); ilength < 8; ilength = ilength+1) {
            sappend = sappend+"0";
        }
        return sappend;
    }
    public Integer getBorderColor(){
        return this.fBorderColor;
    }
    public double getBorderRadius(){
        return this.fBorderRadius;
    }
    public int getBorderWidth(){
        return this.fBorderWidth;
    }
    public boolean getCloseGestureEnabled(){
        return this.fCloseGestureEnabled;
    }
    public VisibilityAnimationType getEffect(){
        return this.fEffect;
    }
    public int getEffectDuration(){
        return this.fEffectDuration;
    }
    public EffectFrame getEffectFrame(){
        return this.fEffectFrame;
    }
    public int getGravity(){
        return this.fGravity;
    }
    public boolean getHideGestureEnabled(){
        return this.fHideGestureEnabled;
    }
    public OrientationSpecificStyle getLandscapeStyle(){
        return this.fLandscapeStyle;
    }
    public boolean getLongPressDisabled(){
        return this.fLongPressDisabled;
    }
    public double getModalAlpha(){
        return this.fModalAlpha;
    }
    public Integer getModalBackgroundColor(){
        return this.fModalBackgroundColor;
    }
    public String getModalTap(){
        return this.fModalTap;
    }
    public OrientationSpecificStyle getPortraitStyle(){
        return this.fPortraitStyle;
    }
    public int getScaleStartHeight(){
        return this.fScaleStartHeight;
    }
    public int getScaleStartWidth(){
        return this.fScaleStartWidth;
    }
    public boolean getTopPullEnabled(){
        return this.fTopPullEnabled;
    }
    public PullStyle getTopPullStyle(){
        return this.fTopPull;
    }
    public int getZIndex(){
        return this.fZIndex;
    }
    public boolean hasBorder(){
        boolean vb = (this.fBorderColor != null)? true: false;
        return vb;
    }
    public boolean hasShadow(){
        return this.fHasShadow;
    }
    public boolean isBringToTop(){
        return this.fbBringToTop;
    }
    public boolean isHardwareAccelerated(){
        return this.fHardwareAccelerated;
    }
    public boolean isModal(){
        return this.fbModal;
    }
    public void setBorderColor(Integer p0){
        this.fBorderColor = p0;
    }
    public void setBorderRadius(double p0){
        this.fBorderRadius = p0;
    }
    public void setBorderWidth(int p0){
        this.fBorderWidth = p0;
    }
    public void setBringToTop(boolean p0){
        this.fbBringToTop = p0;
    }
    public void setEffect(VisibilityAnimationType p0){
        this.fEffect = p0;
    }
    public void setEffectDuration(int p0){
        this.fEffectDuration = p0;
    }
    public void setEffectFrame(EffectFrame p0){
        this.fEffectFrame = p0;
    }
    public void setGravity(int p0){
        this.fGravity = p0;
    }
    public void setHardwareAccelerated(boolean p0){
        this.fHardwareAccelerated = p0;
    }
    public void setHideGestureEnabled(boolean p0){
        this.fHideGestureEnabled = p0;
    }
    public void setLongPressDisabled(boolean p0){
        this.fLongPressDisabled = p0;
    }
    public void setModal(boolean p0){
        this.fbModal = p0;
    }
    public void setModalAlpha(double p0){
        this.fModalAlpha = p0;
    }
    public void setModalBackgroundColor(Integer p0){
        this.fModalBackgroundColor = p0;
    }
    public void setModalTap(String p0){
        this.fModalTap = p0;
    }
    public void setPortraitStyle(OrientationSpecificStyle p0){
        this.fPortraitStyle = p0;
    }
    public void setScaleStartHeight(int p0){
        this.fScaleStartHeight = p0;
    }
    public void setScaleStartWidth(int p0){
        this.fScaleStartWidth = p0;
    }
    public void setTopPullEnabled(boolean p0){
        this.fTopPullEnabled = p0;
    }
    public void setTopPullStyle(PullStyle p0){
        this.fTopPull = p0;
    }
    public void setZIndex(int p0){
        this.fZIndex = p0;
    }
    public String toString(){
        StringBuilder sappend = this.getGivenName().append(":\n").append("\t").append(this.fLandscapeStyle).append("\n").append("\t").append(this.fPortraitStyle).append("\n");
        if (this.fScaleStartHeight >= 0) {
            sappend = sappend.append("\tscaleStartHeight:").append(this.fScaleStartHeight).append("\n");
        }
        if (this.fScaleStartWidth >= 0) {
            sappend = sappend.append("\tscaleStartWidth:").append(this.fScaleStartWidth).append("\n");
        }
        sappend = sappend.append("\tshadow set:").append(this.fHasShadow).append("\n").append("\tshadowRadius:").append(this.fShadowRadius).append("\n").append("\tshadowOffset:").append(this.fShadowOffset).append("\n").append("\thardware accelerated:").append(this.fHardwareAccelerated).append("\n");
        sappend = (this.fBorderColor != null)? sappend.append("\tborder:").append(this.color(this.fBorderColor.intValue())).append(" ").append(this.fBorderWidth).append("dp radius:").append(this.fBorderRadius).append("\n"): sappend.append("\tborder: none\n");
        sappend = sappend.append("\tzIndex:").append(this.fZIndex).append("\n").append("\tbringToTop:").append(this.fbBringToTop).append("\n").append("\tbounces:").append(this.fbBounces).append("\n").append("\tscrollsToTop:").append(this.fbScrollsToTop).append("\n").append("\tgravity:").append(Utils.gravityAsString(this.fGravity)).append("\n").append("\thideGestureEnabled:").append(this.fHideGestureEnabled).append("\n").append("\tcloseGestureEnabled:");
        sappend = sappend.append(this.fCloseGestureEnabled).append("\n").append("\tlongPressDisabled:").append(this.fLongPressDisabled).append("\t").append("modal").append(": ").append(this.fbModal).append("\n").append("\t").append("modalBackgroundColor").append(": ").append(this.color(this.fModalBackgroundColor.intValue())).append("\n").append("\t").append("modalAlpha").append(": ").append(this.fModalAlpha);
        return sappend.append("\n").append("\t").append("modalTap").append(": ").append(this.fModalTap).append("\n").append("\t").append("topPull").append(": ").append(this.fTopPull).append("\n").append("\t").append("topPullEnabled").append(": ").append(this.fTopPullEnabled).append("\n").toString();
    }
    public void update(JSONObject p0){
        String str;
        int vi1;
        boolean bValue;
        float vf;
        double vd;
        EffectFrame effectFrame;
        Object[] objectArray;
        Object[] objectArray1;
        Integer integer = null;
        try{
            Iterator ikeys = p0.keys();
            boolean vb = true;
            JSONObject jSONObject = integer;
            int vi = 0;
            try{
                while (ikeys.hasNext()) {
                    str = ikeys.next();
                    try{
                        if (str.equals("modal")) {
                            this.fbModal = this.updateBoolean("modal", p0, Boolean.valueOf(this.fbModal), Boolean.valueOf(false)).booleanValue();
                        }else if(str.equals("modalAlpha")){
                            this.fModalAlpha = this.updateNumber("modalAlpha", p0, Double.valueOf(this.fModalAlpha), Double.valueOf(0.00f)).doubleValue();
                        }else if(str.equals("modalBackgroundColor")){
                            this.fModalBackgroundColor = this.updateColor(str, p0, this.fModalBackgroundColor, Integer.valueOf(0));
                        }else if(str.equals("modalTap")){
                            this.fModalTap = this.updateValue(str, p0, this.fModalTap, WindowStyle.MODAL_TAP_VALUES);
                        }else if(str.equals("clips")){
                            bValue = this.updateBoolean("clips", p0, Boolean.valueOf(this.fbClips), Boolean.valueOf(false)).booleanValue();
                            this.fbClips = bValue;
                        }else if(str.equals("focusable")){
                            this.fbFocusable = this.updateBoolean("focusable", p0, Boolean.valueOf(this.fbFocusable), Boolean.valueOf(false)).booleanValue();
                        }else {
                            vf = 1000.00f;
                            if (str.equals("displayDuration")) {
                                this.fEffectDuration = (int)(this.updateNumber("displayDuration", p0, Integer.valueOf(this.fEffectDuration), Integer.valueOf(0)).floatValue()*vf);
                            }else if(str.equals("borderColor")){
                                this.fBorderColor = this.updateColor(str, p0, this.fBorderColor, integer);
                            }else if(str.equals("backgroundColor")){
                                this.fBackgroundColor = this.updateColor(str, p0, this.fBackgroundColor, integer);
                            }else if(str.equals("bringToTop")){
                                this.fbBringToTop = this.updateBoolean("bringToTop", p0, Boolean.valueOf(this.fbBringToTop), Boolean.valueOf(vb)).booleanValue();
                            }else if((bValue = str.equals("bounces"))){
                                this.fbBounces = this.updateBoolean("bounces", p0, Boolean.valueOf(this.fbBounces), Boolean.valueOf(vb)).booleanValue();
                            }else if(str.equals("scrollsToTop")){
                                this.fbScrollsToTop = this.updateBoolean("scrollsToTop", p0, Boolean.valueOf(this.fbScrollsToTop), Boolean.valueOf(false)).booleanValue();
                            }else {
                                vd = 0;
                                if (str.equals("shadowOffset")) {
                                    this.fShadowOffset = this.updateNumber("shadowOffset", p0, Double.valueOf(this.fShadowOffset), Double.valueOf(vd)).doubleValue();
                                }else if(str.equals("shadowRadius")){
                                    this.fShadowRadius = this.updateNumber("shadowRadius", p0, Double.valueOf(this.fShadowRadius), Double.valueOf(vd)).doubleValue();
                                }else if((bValue = str.equals("borderRadius"))){
                                    this.fBorderRadius = this.updateNumber(str, p0, Double.valueOf(this.fBorderRadius), Integer.valueOf(0)).doubleValue();
                                }else if(str.equals("borderWidth")){
                                    this.fBorderWidth = Utils.dp2pixels(this.updateNumber(str, p0, Integer.valueOf(this.fBorderWidth), Integer.valueOf(0)).floatValue());
                                }else if(str.equals("effectName")){
                                    this.updateVisibilityAnimation(str, p0);
                                }else if(str.equals("effectDuration")){
                                    this.fEffectDuration = (int)(this.updateNumber(str, p0, Integer.valueOf(this.fEffectDuration), Integer.valueOf(0)).floatValue()*vf);
                                }else {
                                    vf = -1;
                                    if (str.equals("scaleStartHeight")) {
                                        this.fScaleStartHeight = Utils.dp2pixels(this.updateNumber(str, p0, Integer.valueOf(this.fScaleStartHeight), Integer.valueOf(0)).floatValue());
                                        this.fScaleStartWidth = vf;
                                    }else if(str.equals("scaleStartWidth")){
                                        this.fScaleStartWidth = Utils.dp2pixels(this.updateNumber(str, p0, Integer.valueOf(this.fScaleStartWidth), Integer.valueOf(0)).floatValue());
                                        this.fScaleStartHeight = vf;
                                    }else if(str.equals("frame1")){
                                        effectFrame = new EffectFrame(this.intArray(str, p0, 4, 2147483647));
                                        this.logChange(str, this.fEffectFrame, effectFrame);
                                        this.fEffectFrame = effectFrame;
                                    }else if(str.equals("gravity")){
                                        this.updateGravity(str, p0);
                                    }else if(str.equals("landscape")){
                                        this.fLandscapeStyle.update(this.getJson(str, p0));
                                        vi = 1;
                                    }else if(str.equals("portrait")){
                                        this.fPortraitStyle.update(this.getJson(str, p0));
                                        vi = 1;
                                    }else if(str.equals("shadowColor")){
                                        this.updateShadow(str, p0);
                                    }else if(str.equals("android$hardwareAccelerated")){
                                        this.fHardwareAccelerated = this.updateBoolean(str, p0, Boolean.valueOf(this.fHardwareAccelerated), Boolean.valueOf(vb)).booleanValue();
                                    }else if(str.equals("zIndex")){
                                        this.fZIndex = this.updateNumber(str, p0, Integer.valueOf(this.fZIndex), Integer.valueOf(0)).intValue();
                                    }else if(str.equals("hideGestureEnabled")){
                                        this.fHideGestureEnabled = this.updateBoolean(str, p0, Boolean.valueOf(this.fHideGestureEnabled), Boolean.valueOf(false)).booleanValue();
                                    }else if(str.equals("closeGestureEnabled")){
                                        bValue = this.updateBoolean(str, p0, Boolean.valueOf(this.fCloseGestureEnabled), Boolean.valueOf(false)).booleanValue();
                                        this.fCloseGestureEnabled = bValue;
                                    }else if(str.equals("longPressDisabled")){
                                        this.fLongPressDisabled = this.updateBoolean(str, p0, Boolean.valueOf(this.fLongPressDisabled), Boolean.valueOf(false)).booleanValue();
                                    }else if(str.equals("topPull")){
                                        if (this.fTopPull == null) {
                                            this.fTopPull = new PullStyle(new StringBuilder().append(this.getGivenName()).append(".pull").toString());
                                        }
                                        this.fTopPull.update(p0.getJSONObject(str));
                                    }else if(str.equals("topPullEnabled")){
                                        this.fTopPullEnabled = this.updateBoolean(str, p0, Boolean.valueOf(this.fTopPullEnabled), Boolean.valueOf(false)).booleanValue();
                                    }else if(!str.equals("height") && !str.equals("width") && !str.equals("marginBottom") && !str.equals("marginLeft") && str.equals("marginRight") || str.equals("marginTop")){
                                        if (jSONObject == null) {
                                            jSONObject = new JSONObject();
                                        }
                                        jSONObject.put(str, p0.get(str));
                                        vi1 = str;
                                    }else {
                                        objectArray = new Object[2];
                                        objectArray[0] = "ignoring style ";
                                        objectArray[vb] = str;
                                        this.fLog.w(objectArray);
                                    }
                                }
                            }
                        }
                        vi1 = str;
                    }catch(org.json.JSONException e14){
                    }
                    label_0451 :
                    throw new IllegalArgumentException(new StringBuilder().append("error processing ").append(str).toString(), e14);
                }
                if (vi && jSONObject != null) {
                    objectArray1 = new Object[vb];
                    objectArray1[0] = "global h/w and landscape/portrait dimensions specified...global settings will be used in case of conflicts";
                    this.fLog.w(objectArray1);
                }
                if (jSONObject != null) {
                    this.fPortraitStyle.update(jSONObject);
                    this.fLandscapeStyle.update(jSONObject);
                }
                if (!Utils.gravityIsValid(this.fGravity)) {
                    objectArray1 = new Object[vb];
                    objectArray1[0] = new StringBuilder().append("invalid gravity setting: ").append(this.fGravity).append(", using CENTER").toString();
                    this.fLog.w(objectArray1);
                    this.fGravity = 17;
                }
                return;
            }catch(org.json.JSONException e14){
                str = jSONObject;
             goto label_0451 ;
            }
        }catch(org.json.JSONException e14){
            str = integer;
          goto label_0451 ;
        }
    }


}
