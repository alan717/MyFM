package com.example.myfm;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfm.utils.Json;
import com.example.myfm.utils.Logger;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    static String[] EMPTY_STRING;
    static DisplayMetrics dm;
    private static Logger sLog;

    static {
        Utils.sLog = Logger.getLogger("utils");
        String[] stringArray = new String[0];
        Utils.EMPTY_STRING = stringArray;
    }
    public void Utils(){

    }
    public static Frame applyFrame(Frame p0, JSONObject p1){
        return Utils.applyFrame(p0, p1, "");
    }
    public static Frame applyFrame(Frame p0,JSONObject p1,String p2){
        Frame frame;
        int iInt;
        int iInt1;
        frame = new Frame();
        if ((iInt = p1.optInt(new StringBuilder().append(p2).append("top").toString(), -1)) >= 0) {
            frame.top = (float)(int)((float)iInt*Utils.dm.density);
        }
        if ((iInt = p1.optInt(new StringBuilder().append(p2).append("left").toString(), -1)) >= 0) {
            frame.left = (float)(int)((float)iInt*Utils.dm.density);
        }
        if ((iInt = p1.optInt(new StringBuilder().append(p2).append("height").toString(), -1)) >= 0) {
            frame.height = (float)(int)((float)iInt*Utils.dm.density);
        }
        if ((iInt1 = p1.optInt(new StringBuilder().append(p2).append("width").toString(), -1)) >= 0) {
            frame.width = (float)(int)((float)iInt1*Utils.dm.density);
        }
        if (!frame.isValid()) {
            frame = null;
        }
        return frame;
    }
    public static void assignFrame(Frame p0,Frame p1){
        p0.width = p1.width;
        p0.height = p1.height;
        p0.top = p1.top;
        p0.left = p1.left;
    }
    public static void assignFrame(JSONObject p0,Frame p1){
        Json.put(p0, "top", Float.valueOf(p1.top));
        Json.put(p0, "left", Float.valueOf(p1.left));
        Json.put(p0, "width", Float.valueOf(p1.width));
        Json.put(p0, "height", Float.valueOf(p1.height));
    }
    public static int dp2pixels(float p0){
        return Math.round((p0*Utils.dm.density));
    }
    public static int dp2pixels(int p0){
        return Math.round(((float)p0*Utils.dm.density));
    }
    public static void dump(ViewGroup p0, int p1){
        String str;
        int vi1;
        View vChildAt;
        int iChildCount = p0.getChildCount();
        int vi = 0;
        while (vi < iChildCount) {
            str = new StringBuilder().append("[").append(p1).append("]  ").toString();
            for (vi1 = 0; vi1 < p1; vi1 = vi1+1) {
                str = new StringBuilder().append(str).append("     ").toString();
            }
            vChildAt = p0.getChildAt(vi);
            Log.w("VIEW", new StringBuilder().append(str).append(p0.getChildAt(vi)).append("; tag=").append(vChildAt.getTag()).append("; visibility=").append(vChildAt.getVisibility()).toString());
            if (vChildAt instanceof ViewGroup) {
                Utils.dump((ViewGroup) vChildAt, (p1+1));
            }
            vi = vi+1;
        }
    }
    public static void dumpBuild(Appendable p0){
        Utils.dumpObj("android_build_version_", Build.VERSION.class, p0);
        Utils.dumpObj("android_build_", Build.class, p0);
    }
    static void dumpObj(String p0,Class p1,Appendable p2){
        Field field;
        String sName;
        int iModifiers;
        int vi1;
        Object object;
        int vi2;
        Object oget;
        Appendable aappend;
        Object[] objectArray;
        Object[] objectArray1;
        Field[] fDeclaredFie = p1.getDeclaredFields();
        int len = fDeclaredFie.length;
        int vi = 0;
        while (vi < len) {
            field = fDeclaredFie[vi];
            sName = field.getName();
            iModifiers = field.getModifiers();
            if ((iModifiers&0x08) == 8) {
                vi1 = 1;
                if ((iModifiers&0x01) == vi1) {
                    object = null;
                    vi2 = 2;
                    try{
                        oget = field.get(object);
                        if (p2 != null) {
                            aappend = p2.append(p0).append(sName.toLowerCase()).append("=").append(oget.toString()).append("\n");
                        }else {
                            objectArray = new Object[vi2];
                            objectArray[0] = "version: ";
                            objectArray[vi1] = new StringBuilder().append(p0).append(sName.toLowerCase()).append("=").append(oget).toString();
                            Utils.sLog.i(objectArray);
                        }
                    }catch(java.lang.Throwable e3){
                        if (p2!=null) {
                            try{
                                aappend = p2.append(p0).append(sName.toLowerCase()).append("=").append(new StringBuilder().append("? <exception> ").append(e3).toString()).append("\n");
                            }catch(java.io.IOException e){
                            }
                        }else {
                            objectArray = new Object[vi2];
                            objectArray[0] = "version: ";
                            objectArray[vi1] = new StringBuilder().append(p0).append(sName.toLowerCase()).append("=?").toString();
                            Utils.sLog.i(objectArray);
                            objectArray1 = new Object[vi2];
                            objectArray1[0] = "version";
                            objectArray1[vi1] = e3;
                            Utils.sLog.w(objectArray1);
                        }
                    }
                }
            }
            vi = vi+1;
        }
    }
    public static String facebookExpires(long p0){
        if (p0==0) {
            return "never";
        }
        if (p0-1000 < 0) {
            return "forced-logout";
        }
        return new Date(p0).toString();
    }
    public static String getDpiKind(){
        StringBuilder stringBuilde = new StringBuilder();
        int densityDpi = Utils.dm.densityDpi;
        if (densityDpi != 120) {
            if (densityDpi != 160) {
                if (densityDpi != 240) {
                    stringBuilde = (densityDpi != 320)? stringBuilde.append("default"): stringBuilde.append("xlarge");
                }else {
                    stringBuilde = stringBuilde.append("large");
                }
            }else {
                stringBuilde = stringBuilde.append("medium");
            }
        }else {
            stringBuilde = stringBuilde.append("low");
        }
        return stringBuilde.toString();
    }
    public static double getScreenDiagonalSize(){
        double vd = 0.00f;
        return ((double)Math.round((Math.sqrt((float)((((float)Utils.dm.heightPixels/Utils.dm.ydpi)*((float)Utils.dm.heightPixels/Utils.dm.ydpi))+(((float)Utils.dm.widthPixels/Utils.dm.xdpi)*((float)Utils.dm.widthPixels/Utils.dm.xdpi))))*vd))/vd);
    }
    public static int getScreenWidth(){
        return Utils.dm.widthPixels;
    }
    public static long getTotalSystemMemory(Context p0){
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) p0.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);//activity
        return memoryInfo.totalMem;
    }
    public static String gravityAsString(int p0){
        StringBuilder stringBuilde = new StringBuilder();
        if ((p0&0x30) == 48) {
            stringBuilde = stringBuilde.append("top|");
        }else if((p0&0x50) == 80){
            stringBuilde = stringBuilde.append("bottom|");
        }else if((p0&0x10) == 16){
            stringBuilde = stringBuilde.append("vertical|");
        }else if((p0&0x11) == 17){
            stringBuilde = stringBuilde.append("center|");
        }
        if ((p0&0x03) == 3) {
            stringBuilde = stringBuilde.append("left");
        }else if((p0&0x05) == 5){
            stringBuilde = stringBuilde.append("right");
        }else if((p0&0x01) == 1){
            stringBuilde = stringBuilde.append("horizontal");
        }else if((p0&17) == 17){
            stringBuilde = stringBuilde.append("center");
        }
        return stringBuilde.toString();
    }
    public static boolean gravityIsValid(int p0){
        boolean vb = false;
        if ((p0&0x71)==0) {
            return vb;
        }
        if ((p0&0x17)==0) {
            return vb;
        }
        if ((p0|119) == 119) {
            vb = true;
        }
        return vb;
    }
    public static Integer idFromName(Class p0,String p1){
        Object object = null;
        try{
            return Integer.valueOf(p0.getField(p1).getInt(object));
        }catch(java.lang.Throwable e){
            return 0;
        }
    }
    public static boolean isNotEmpty(String p0){
        if (p0==null) {
            return false;
        }
        if (p0.length()==0 || p0.trim().length()!=0) {
            return false;
        }
        return true;
    }
    public static boolean isUIThread(){
        boolean vb = Thread.currentThread() == Looper.getMainLooper().getThread();
        return vb;
    }
    public static boolean isaURL(String p0){
        boolean vb = false;
        if (p0==null) {
            return vb;
        }
        int iOf = p0.indexOf("://");
        if (iOf < 6 && iOf >= 0) {
            vb = true;
        }
        return vb;
    }
    public static int parseGravity(String p0){
        String str;
        String[] ssplit = p0.split("\\|");
        int len = ssplit.length;
        int vi = 0;
        int vi1 = 0;
        while (true) {
            if (vi >= len) {
                return vi1;
            }
            str = ssplit[vi];
            if ("bottom".equalsIgnoreCase(str)) {
                vi1 = vi1|0x50;
            }else if("top".equalsIgnoreCase(str)){
                vi1 = vi1|0x30;
            }else if("left".equalsIgnoreCase(str)){
                vi1 = vi1|0x03;
            }else if("right".equalsIgnoreCase(str)){
                vi1 = vi1|0x05;
            }else if("center".equalsIgnoreCase(str)){
                vi1 = vi1|0x11;
            }else if("horizontal".equalsIgnoreCase(str)){
                vi1 = vi1|0x01;
            }else if("vertical".equalsIgnoreCase(str)){
                vi1 = vi1|0x10;
            }else {
                throw new IllegalArgumentException(new StringBuilder().append("invalid gravity value ").append(str).toString());
            }
            vi = vi+1;
        }
    }
    public static int parseInt(String p0,int p1,int p2){
        try{
            return Integer.parseInt(p0, p1);
        }catch(java.lang.NumberFormatException e){
            return p2;
        }
    }
    public static long parseLong(String p0,int p1,long p2){
        try{
            return Long.parseLong(p0, p1);
        }catch(java.lang.NumberFormatException e){
            return p2;
        }
    }
    public static String[] parseStringList(String p0){
        if (p0==null) {
            return Utils.EMPTY_STRING;
        }
        return p0.split("(\\s+|,|;)");
    }
    public static Map parseWebForm(String p0){
        String[] ssplit1;
        String[] ssplit = p0.split("&");
        HashMap hashMap = new HashMap();
        int vi = 0;
        while (vi < ssplit.length) {
            if ((ssplit1 = ssplit[vi].split("=")) != null && ssplit1.length >= 2) {
                hashMap.put(ssplit1[0], URLDecoder.decode(ssplit1[1]));
            }
            vi = vi+1;
        }
        return hashMap;
    }
    public static int pixelsToDp(float p0){
        return (int)Math.ceil((float)((p0*0x3f800000)/Utils.dm.density));
    }
    public static void setDisplayMetrics(DisplayMetrics p0){
        Utils.dm = p0;
    }
    public static Intent toPlayStoreIntent(String p0){
        String str;
        String sappend = null;
        Intent intent = null;
        try{
            Uri uparse = Uri.parse(p0);
            String sPath = uparse.getPath();
            if (uparse.getHost().equals("play.google.com")) {
                if (sPath.startsWith("/store/apps/details")) {
                    str = "market://" + sPath.substring(12);
                }else if(sPath.startsWith("/store/search")){
                    str = "market://" + sPath.substring(7);
                }else {
                    str = null;
                }
                if (str != null) {
                    if (uparse.getQuery() != null) {
                        sappend = str+'?'+uparse.getEncodedQuery();
                    }
                    if (uparse.getFragment() != null) {
                        sappend = sappend+"#"+uparse.getEncodedFragment();
                    }
                    return new Intent("android.intent.action.VIEW", Uri.parse(sappend.toString()));
                }
            }
            return intent;
        }catch(Exception e){
            return intent;
        }
    }

}
