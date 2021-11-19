package com.example.myfm.bridge;

import android.os.Handler;
import android.webkit.WebView;

import com.example.myfm.utils.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Bridge implements IBridge	{

    private int fBits;
    final Handler fHandler;
    final Logger fLog;
    private boolean fMainGate;
    private final String fName;
    private final List fQ;
    private String fSyncResponse;
    private final WebView fWebView;
    private CountDownLatch latch;
    private static final String BOOKMARKLET_END;
    private static final String BOOKMARKLET_START;
    public static final int BRIDGE_INJECT;
    public static final int BRIDGE_RUN_MAIN;
    private static final String BRIDGE_VAR;
    private static final String JS_TRY_END;
    private static final String JS_TRY_START;
    private static final String MAIN;
    static int MAX_JS_EXEC_TIMES;
    static int MAX_LOG_JS;
    private static final String ON_APP_EVENT;
    private static final String ON_EVENT;

    public   Bridge(WebView p0,String p1){

        this.fHandler = new Handler();
        this.fQ = new ArrayList();
        this.fMainGate = true;
        this.fBits = 0;
        this.fSyncResponse = null;
        this.fWebView = p0;
        this.fName = p1;
        this.fLog = Logger.getLogger(new StringBuilder()+this.fName+".br");
        p0.addJavascriptInterface(new Bridgebridge(this), "$bridge");
    }
    static WebView access$000(Bridge p0){
        return p0.fWebView;
    }
    static String access$102(Bridge p0,String p1){
        p0.fSyncResponse = p1;
        return p1;
    }
    static CountDownLatch access$200(Bridge p0){
        return p0.latch;
    }
    static String access$300(Bridge p0){
        return p0.fName;
    }
    private String awaitResponse(){
        try{
            this.latch.await((long)Bridge.MAX_JS_EXEC_TIMES, TimeUnit.SECONDS);
            if (!this.latch.getCount()) {
                Object[] objectArray = new Object[3];
                objectArray[0] = "awaitResponse\(\) --> Received --> ";
                objectArray[1] = Integer.valueOf(this.fSyncResponse.length());
                objectArray[2] = " chars";
                this.fLog.i(objectArray);
            }else {
                objectArray = new Object[3];
                objectArray[0] = "awaitResponse\(\) -- NOT received --> ";
                objectArray[1] = Integer.valueOf(Bridge.MAX_JS_EXEC_TIMES);
                objectArray[2] = "sec PASSED";
                this.fLog.i(objectArray);
            }
            return this.fSyncResponse;
        }catch(java.lang.InterruptedException e-1){
        }
    }
    private String buildCall(String p0,Object[] p1){
        i oi;
        String sstr;
        int ilength;
        int vi2;
        char cAt;
        StringBuilder sappend = new StringBuilder().append(p0).append("\(");
        int len = p1.length;
        int vi = 0;
        int vi1 = 0;
        while (vi < len) {
            oi = p1[vi];
            if (vi1) {
                sappend = sappend.append(",");
            }
            sstr = oi.toString();
            ilength = sstr.length();
            vi2 = 0;
            while (vi2 < ilength) {
                cAt = sstr.charAt(vi2);
                sappend = (this.isValidJSCharacter(cAt))? sappend.append(cAt): sappend.append(' ');
                vi2 = vi2+1;
            }
            vi = vi+1;
            sstr = 1;
        }
        return sappend.append("\)").toString();
    }
    private Object eval0(String p0){
        String sResponse;
        _monitor_enter(this);
        try{
            this.resetResponseAndLatch();
            this.jsExec(new StringBuilder()+"try{$bridge.reply\(JSON.stringify\("+p0+"\)\)}catch\(X\){$bridge.reply\(JSON.stringify\(X\)\)}");
            if ((sResponse = this.awaitResponse()) != null) {
                _monitor_exit(this);
                return Json.parseNext(sResponse);
            }else {
                _monitor_exit(this);
                return null;
            }
        }catch(Exception e3){
            _monitor_exit(this);
            throw e3;
        }
    }
    private boolean isUIThread(){
        boolean vb = (Thread.currentThread() == Looper.getMainLooper().getThread())? true: false;
        return vb;
    }
    private boolean isValidJSCharacter(char p0){
        if (p0 == 10 || p0 == 9) {
            return true;
        }
        if (Character.isWhitespace(p0)) {
            return false;
        }
        return true;
    }
    private void jsExec(String p0){
        this.logJsExpr(p0);
        if (HackPredicates.INSTANCE.isKitKat()) {
            this.jsExecPostKitKat(p0);
        }else {
            this.jsExecPreKitKat(p0);
        }
        return;
    }
    private void jsExecPostKitKat(String p0){
        if (this.isUIThread()) {
            this.fWebView.evaluateJavascript(p0, null);
        }else {
            this.fHandler.post(new Bridge$1(this, p0));
        }
        return;
    }
    private void jsExecPreKitKat(String p0){
        this.jsExecPreKitKatLoadUrl("javascript:{"+p0+"}");
    }
    private void jsExecPreKitKatLoadUrl(String p0){
        if (this.isUIThread()) {
            this.fWebView.loadUrl(p0);
        }else {
            this.fHandler.post(new Bridge$2(this, p0));
        }
        return;
    }
    private void logJsExpr(String p0){
        Object[] objectArray;
        Object[] objectArray1;
        int mAX_LOG_JS = Bridge.MAX_LOG_JS;
        if (p0.length() > mAX_LOG_JS) {
            objectArray = new Object[2];
            objectArray[0] = p0.substring(0, mAX_LOG_JS);
            objectArray[1] = " ....<snip> ";
            this.fLog.i(objectArray);
        }else {
            objectArray1 = new Object[1];
            objectArray1[0] = p0;
            this.fLog.i(objectArray1);
        }
        return;
    }
    private void resetResponseAndLatch(){
        this.fSyncResponse = null;
        this.latch = new CountDownLatch(1);
    }
    public void clearBits(){
        this.fBits = 0;
    }
    public Object eval(String p0){
        return this.eval0(p0);
    }
    public void exec(String p0){
        int vi1;
        Object[] objectArray;
        _monitor_enter(this);
        try{
            if (!this.fMainGate) {
                this.jsExec(p0);
                _monitor_exit(this);
                return;
            }else {
                int vi = 0;
                if (p0.startsWith("MAIN")) {
                    this.pushUserSettingsNow();
                    this.jsExec(p0);
                    if (this.fQ.size() > 0) {
                        String[] stringArray = new String[this.fQ.size()];
                        String[] sArray = this.fQ.toArray(stringArray);
                        this.fQ.clear();
                        int len = sArray.length;
                        for (vi1 = 0; vi1 < len; vi1 = vi1+1) {
                            this.jsExec(sArray[vi1]);
                        }
                    }
                    this.fMainGate = vi;
                    _monitor_exit(this);
                    return;
                }else {
                    objectArray = new Object[3];
                    objectArray[vi] = "call to ";
                    objectArray[1] = p0;
                    objectArray[2] = " delayed until MAIN is called.";
                    this.fLog.i(objectArray);
                    this.fQ.add(p0);
                    _monitor_exit(this);
                    return;
                }
            }
        }catch(Exception e5){
            _monitor_exit(this);
            throw e5;
        }
    }
    public void exec(String p0,Object p1){
        Object[] objectArray=null;
        if (p0==null) {
            this.exec(p1);
        }else if("MAIN".equals(p0)){
            objectArray = new Object[1];
            objectArray[0] = p1;
            this.exec(this.buildCall(p0, objectArray));
        }else {
            objectArray = new Object[1];
            objectArray[0] = p1;
            this.exec(this.buildCall(p0, objectArray));
        }
        return;
    }
    JSONObject getMainJSON(){
        return null;
    }
    public boolean hasBitsSet(int p0){
        boolean vb = ((p0&this.fBits))? true: false;
        return vb;
    }
    Object js2java(String p0,String p1,String p2){
        Object[] objectArray;
        objectArray = new Object[7];
        objectArray[0] = "js2java - no IMPL for ";
        objectArray[1] = p0;
        objectArray[2] = ".";
        objectArray[3] = p1;
        objectArray[4] = "(";
        objectArray[5] = p2;
        objectArray[6] = ")";
        this.fLog.w(objectArray);
        return null;
    }
    public void onappevent(JSONObject p0){
        Object[] objectArray;
        objectArray = new Object[1];
        objectArray[0] = p0;
        this.exec(this.buildCall("ONAPPEVENT", objectArray));
    }
    public void onevent(JSONObject p0){
        Object[] objectArray;
        objectArray = new Object[1];
        objectArray[0] = p0;
        this.exec(this.buildCall("ONEVENT", objectArray));
    }
    void pushUserSettingsNow(){
        Object[] objectArray;
        objectArray = new Object[1];
        objectArray[0] = this.userSettings();
        this.jsExec(this.buildCall("ONAPPEVENT", objectArray));
    }
    public void setBits(int p0){
        this.fBits = p0|this.fBits;
    }
    public void setMainGate(boolean p0){
        this.fMainGate = p0;
    }
    public void setupBridge(){
        int vi = 2;
        if (this.hasBitsSet(vi)) {
            this.unsetBits(vi);
            this.exec(null, templates.T.jsBridge());
        }
        vi = 1;
        if (this.hasBitsSet(vi)) {
            this.unsetBits(vi);
            this.exec("MAIN", this.getMainJSON());
        }
        return;
    }
    public void unsetBits(int p0){
        this.fBits = (~p0)&this.fBits;
    }
    JSONObject userSettings(){
        JSONObject jSONObject = new JSONObject();
        Json.put(jSONObject, "type", "userSettings");
        this.userSettings(jSONObject);
        return jSONObject;
    }
    void userSettings(JSONObject p0){
    }

}
