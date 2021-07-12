package com.example.myfm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.util.DisplayMetrics;

import com.example.myfm.utils.Logger;
import com.example.myfm.widget.SessionInfo;

import java.util.ArrayList;
import java.util.Iterator;

public class AllPreferences implements SharedPreferences.OnSharedPreferenceChangeListener{
    private SharedPreferences.Editor fBatchEditor;
    private SharedPreferences.Editor fEditor;
    private IntentHelper fIntentHelper;
    public static final AllPreferences INSTANCE = new AllPreferences();;
    final Logger fLog;
    private MainActivity fMain;
    private SharedPreferences fPrefs;
    private boolean fbSilentUpdates;
    private String fUserAgent;
    final ArrayList<SharedPreferences.OnSharedPreferenceChangeListener> listeners;
    private static Context sCtx;
    private static DisplayMetrics sDm;
    private static PackageInfo sInfo;
    public static int sInsetBottom;
    public static int sInsetLeft;
    public static int sInsetRight;
    public static int sInsetTop;

    static String NO;
    static String SECRET;
    static String TOKEN;
    static String TWITTER;
    static String YES;

    public AllPreferences(){
        this.fLog = Logger.getLogger("preferences");
        this.fbSilentUpdates = false;
        this.fIntentHelper = null;
        this.fUserAgent = null;
        this.fEditor = null;
        this.fBatchEditor = null;
        this.listeners = new ArrayList();
        this.fBatchEditor = new AllPreferencesEditor(this);
    }
    static SharedPreferences.Editor access$000(AllPreferences p0){
        return p0.fEditor;
    }
    static SharedPreferences.Editor access$100(AllPreferences p0){
        return p0.fBatchEditor;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (this.fbSilentUpdates) {
            return;
        }
        Iterator<SharedPreferences.OnSharedPreferenceChangeListener> iiterator = this.listeners.iterator();
        while (iiterator.hasNext()) {
            iiterator.next().onSharedPreferenceChanged(sharedPreferences, s);
        }
    }


    private SharedPreferences.Editor editor(){
        if (this.fEditor == null) {
            return this.fPrefs.edit();
        }
        return this.fBatchEditor;
    }
    public static void setContext(Context p0){
        AllPreferences.sCtx = p0;
    }
    public static void setDisplayMetrics(DisplayMetrics p0){
        AllPreferences.sDm = p0;
    }
    public static void setPackageInfo(PackageInfo p0){
        AllPreferences.sInfo = p0;
    }
    public static void setWindowInsets(int p0,int p1,int p2,int p3){
        AllPreferences.sInsetTop = p0;
        AllPreferences.sInsetLeft = p1;
        AllPreferences.sInsetRight = p2;
        AllPreferences.sInsetBottom = p3;
    }

    private String valueOf(boolean p0){
        String yES = (p0)? AllPreferences.YES: AllPreferences.NO;
        return yES;
    }
    public void clearSession(){
        this.setSession(null);
    }
    public void setSession(SessionInfo p0){
        SharedPreferences.Editor seditor = this.editor();
        if (p0==null) {
            seditor.remove("feedly_session");
        }else {
            seditor.putString("feedly_session", p0.toJson());
        }
        seditor.commit();
    }

}
