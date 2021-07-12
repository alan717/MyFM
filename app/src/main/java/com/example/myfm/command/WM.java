package com.example.myfm.command;

import com.example.myfm.Controller;
import com.example.myfm.utils.Json;

import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WM extends Command{
    final Topics topics;
    static Map<String,Class> FACTORY= new HashMap<String, Class>();;
    static final Map<String,Controller> controllerMap = new HashMap<String, Controller>();;
    static final WM singleton = new WM(new Topics());;

    public   WM(){
        this.topics = WM.singleton.topics;
    }
    private   WM(Topics p0){
        this.topics = p0;
    }
    public static Collection allControllers(){
        return WM.controllerMap.values();
    }
    public static Set controllerNames(){
        return WM.controllerMap.keySet();
    }
    public static void destroy(){
        String[] stringArray;
        Iterator<String> iiterator = new ArrayList(WM.controllerNames()).iterator();
        while (iiterator.hasNext()) {
            stringArray = new String[1];
            stringArray[0] = iiterator.next();
            WM.singleton.close(stringArray);
        }
    }
    static String factoryKey(URI p0){
        String sHost;
        String sappend = new StringBuilder()+p0.getScheme()+"://";
        sappend = ((sHost = p0.getHost()) != null)? sappend+sHost: sappend+"*";
        return sappend+"/";
    }
    public static Controller getByName(String p0){
        return WM.controllerMap.get(p0);
    }
    private Controller getControllerByName(String p0){
        if ("self".equals(p0)) {
            return this.fSelf;
        }
        if (!"parent".equals(p0)) {
            return WM.getByName(p0);
        }
        Controller controller = (this.fSelf == null)? null: this.fSelf.getParent();
        return controller;
    }
    public static WM getInstance(){
        return WM.singleton;
    }
    public static Controller getMain(){
        Controller cByName;
        if ((cByName = WM.getByName("main")) != null) {
            return cByName;
        }
        throw new RuntimeException("main must be present");
    }
    public static Controller newInstance(URI p0){
        Class cget;
        if ((cget = (Class) WM.FACTORY.get(WM.factoryKey(p0))) == null) {
            cget = (Class) WM.FACTORY.get(new StringBuilder()+p0.getScheme()+"://*/");
        }
        try{
            Controller cInstance = cget.newInstance();
            cInstance.setURI(p0);
            return cInstance;
        }catch(java.lang.Exception e2){
            throw new RuntimeException(e2);
        }
    }
    private void postMessageTo(String p0, String p1, JSONObject p2){
        String sstr;
        Object[] objectArray;
        Object[] objectArray1;
        Object[] objectArray2;
        int vi = this;
        JSONObject jSONObject = p2;
        Controller cControllerB = this.getControllerByName(p0);
        int vi1 = 2;
        int vi2 = 1;
        int vi3 = 3;
        if (cControllerB != null) {
            if ((sstr = jSONObject.optString("__target")) != null) {
                Json.put(jSONObject, "__target", vi.resolveLogicalToAbsolute(sstr));
            }
            String str = null;
            sstr = jSONObject.optString("type", str);
            String sstr1 = jSONObject.optString("target", str);
            str = jSONObject.optString("__sender", str);
            int vi4 = 6;
            if (sstr1 == null) {
                objectArray = new Object[vi4];
                objectArray[0] = "postMessage: ";
                objectArray[vi2] = str;
                objectArray[vi1] = " --> ";
                objectArray[vi3] = p0;
                objectArray[4] = " type=";
                objectArray[5] = sstr;
                vi.fLog.i(objectArray);
            }else {
                objectArray1 = new Object[8];
                objectArray1[0] = "postMessage: ";
                objectArray1[vi2] = str;
                objectArray1[vi1] = " --> ";
                objectArray1[vi3] = p0;
                objectArray1[4] = " type=";
                objectArray1[5] = sstr;
                objectArray1[vi4] = " target=";
                objectArray1[7] = sstr1;
                vi.fLog.i(objectArray1);
            }
            if ("app".equals(p1)) {
                cControllerB.onappevent(jSONObject);
            }else {
                cControllerB.onevent(jSONObject);
            }
        }else {
            objectArray2 = new Object[vi3];
            objectArray2[0] = "window \"";
            objectArray2[vi2] = p0;
            objectArray2[vi1] = "\" does not exist - message not posted.";
            vi.fLog.w(objectArray2);
        }
        return;
    }
    public static final void registerProtocol(URI p0,Class p1){
        WM.FACTORY.put(WM.factoryKey(p0), p1);
    }
    public static Controller removeByName(Controller p0){
        return WM.controllerMap.remove(p0.getGivenName());
    }
    public static void setByName(Controller p0){
        WM.controllerMap.put(p0.getGivenName(), p0);
    }
    public void attach(Object[] p0){
        Controller cByName;
        Object[] objectArray;
        String sAt = this.argAt(p0, 0, null);
        if ((cByName = WM.getByName(sAt)) != null) {
            cByName.attachView();
        }
        objectArray = new Object[1];
        objectArray[0] = new StringBuilder()+"name \""+sAt+"\" is not \'created\' - cannot find window with this name.";
        this.fLog.e(objectArray);
        return;
    }
    public void captureHardwareEvent(Object[] p0){
        String sAt1;
        Controller cControllerB;
        Object object = null;
        int vi = 0;
        String sAt = this.argAt(p0, vi, object);
        if ((sAt1 = this.argAt(p0, 1, object)) == null || sAt == null) {
            return;
        }
        if ((cControllerB = this.getControllerByName(sAt)) == null) {
            return;
        }
        String[] sList = Utils.parseStringList(sAt1);
        int len = sList.length;
        for (; vi < len; vi = vi+1) {
            cControllerB.registerHardwareEvent(sList[vi]);
        }
        return;
    }
    public void close(Object[] p0){
        Object[] objectArray;
        Object[] objectArray1;
        int vi = 0;
        String sAt = this.argAt(p0, vi, null);
        JSONObject jAt = this.argAt(p0, 1, null);
        Controller cControllerB = this.getControllerByName(sAt);
        int vi1 = 2;
        if (cControllerB != null) {
            objectArray = new Object[vi1];
            objectArray[vi] = "closing window \"";
            objectArray[1] = sAt;
            this.fLog.i(objectArray);
            cControllerB.finish(jAt);
            WM.removeByName(cControllerB);
        }else {
            objectArray1 = new Object[3];
            objectArray1[vi] = "window \"";
            objectArray1[1] = sAt;
            objectArray1[vi1] = "\" does not exist - close ignored.";
            this.fLog.w(objectArray1);
        }
        return;
    }
    public void create(Object[] p0){
        Object[] objectArray;
        int vi = 0;
        String sAt = this.argAt(p0, vi, null);
        String sAt1 = this.argAt(p0, 1, null);
        JSONObject jAt = this.argAt(p0, 2, null);
        JSONObject jAt1 = this.argAt(p0, 3, null);
        if (this.isReserved(sAt)) {
            objectArray = new Object[3];
            objectArray[vi] = "name \"";
            objectArray[1] = sAt;
            objectArray[2] = "\" is reserved - cannot open window with this name.";
            this.fLog.e(objectArray);
            return;
        }else if((cByName = WM.getByName(sAt)) == null){
            Controller cByName = this.createUsing(sAt1);
            if (this.fSelf == null) {
                cByName.init(sAt, this.fMain);
                this.setSelf(cByName);
            }else {
                cByName.init(sAt, this.fSelf.getMain());
                cByName.setParent(this.fSelf);
            }
            cByName.addStyleChangeListener(new WM$WMStyleListener(this, cByName));
            WM.setByName(cByName);
        }
        cByName.setJson(jAt1);
        cByName.setMainJson(jAt);
        if (cByName instanceof WebController) {
            cByName.setBootstrapContent(sAt1);
        }
        cByName.buildView();
        return;
    }
    Controller createUsing(String p0){
        int iOf = p0.indexOf("://");
        if (iOf < 6 && iOf >= 0) {
            return WM.newInstance(URI.create(p0));
        }
        return new WebController();
    }
    public void group(Object[] p0){
        Object[] objectArray;
        Controller cByName;
        String str;
        Controller cByName1;
        Object[] objectArray1;
        int vi = 0;
        String sAt = this.argAt(p0, vi, null);
        JSONArray jAt = this.argAt(p0, 1, null);
        JSONObject jAt1 = this.argAt(p0, 2, null);
        if (this.isReserved(sAt)) {
            objectArray = new Object[3];
            objectArray[vi] = "name \"";
            objectArray[1] = sAt;
            objectArray[2] = "\" is reserved - cannot \'group\' window with this name.";
            this.fLog.e(objectArray);
            return;
        }else if((cByName = WM.getByName(sAt)) == null){
            cByName = this.createUsing("ctrl://group");
            if (this.fSelf == null) {
                cByName.init(sAt, this.fMain);
                this.setSelf(cByName);
            }else {
                cByName.init(sAt, this.fSelf.getMain());
                cByName.setParent(this.fSelf);
            }
            cByName.addStyleChangeListener(new WM$WMStyleListener(this, cByName));
            WM.setByName(cByName);
        }
        cByName.setJson(jAt1);
        cByName.buildView();
        cByName.attachView();
        String[] sArray = Json.toJavaArray(jAt);
        int len = sArray.length;
        int vi1 = 0;
        while (vi1 < len) {
            str = sArray[vi1];
            if ((cByName1 = WM.getByName(str)) == null) {
                objectArray1 = new Object[5];
                objectArray1[vi] = "WM.group[";
                objectArray1[1] = sAt;
                objectArray1[2] = "] missing controller \"";
                objectArray1[3] = str;
                objectArray1[4] = "\"";
                this.fLog.w(objectArray1);
            }else {
                cByName.addController(cByName1);
                cByName1.attachView();
                cByName1.buildView();
            }
            vi1 = vi1+1;
        }
    }
    public void hide(Object[] p0){
        Controller cControllerB;
        Object[] objectArray;
        int vi = 0;
        String sAt = this.argAt(p0, vi, null);
        JSONObject jAt = this.argAt(p0, 1, null);
        if ((cControllerB = this.getControllerByName(sAt)) != null) {
            if (cControllerB.isViewVisible()) {
                cControllerB.hide(jAt);
            }
        }else {
            objectArray = new Object[3];
            objectArray[vi] = "window \"";
            objectArray[1] = sAt;
            objectArray[2] = "\" does not exist - hide\(\) ignored.";
            this.fLog.w(objectArray);
        }
        return;
    }
    protected boolean isReserved(String p0){
        boolean vb = ("self".equals(p0) || "parent".equals(p0))? true: false;
        return vb;
    }
    public void markAsReady(Object[] p0){
        Object[] objectArray;
        objectArray = new Object[2];
        objectArray[0] = "markAsReady called for: ";
        objectArray[1] = this.fSelf.getGivenName();
        this.fLog.i(objectArray);
        this.fSelf.setupBridge();
    }
    public void open(Object[] p0){
        this.create(p0);
        this.attach(p0);
    }
    public void postMessage(Object[] p0){
        int vi = 0;
        Object object = null;
        String sAt = this.argAt(p0, vi, object);
        JSONObject jAt = this.argAt(p0, 1, object);
        Json.put(jAt, "__sender", this.fSelf.getGivenName());
        if (Topics.isTopic(sAt)) {
            Topics$msg tcreate = WM.singleton.topics.create(sAt);
            Json.put(jAt, "$domain", tcreate.domain);
            Json.put(jAt, "type", tcreate.id);
            String[] sinterest = WM.singleton.topics.interest(tcreate);
            int len = sinterest.length;
            for (; vi < len; vi = vi+1) {
                this.postMessageTo(sinterest[vi], tcreate.domain, jAt);
            }
        }else {
            this.postMessageTo(sAt, object, jAt);
        }
        return;
    }
    public void refreshDone(Object[] p0){
        this.fSelf.refreshDone();
    }
    public void registerTopics(Object[] p0){
        JSONArray jAt;
        int ilength;
        int vi = 0;
        if ((jAt = this.argAt(p0, vi, null)) == null) {
            return;
        }
        String sGivenName = this.fSelf.getGivenName();
        for (ilength = jAt.length(); vi < ilength; vi = vi+1) {
            WM.singleton.topics.register(jAt.optString(vi), sGivenName);
        }
    }
    public void releaseHardwareEvent(Object[] p0){
        String sAt1;
        Controller cControllerB;
        Object object = null;
        int vi = 0;
        String sAt = this.argAt(p0, vi, object);
        if ((sAt1 = this.argAt(p0, 1, object)) == null || sAt == null) {
            return;
        }
        if ((cControllerB = this.getControllerByName(sAt)) == null) {
            return;
        }
        String[] sList = Utils.parseStringList(sAt1);
        int len = sList.length;
        for (; vi < len; vi = vi+1) {
            cControllerB.releaseHardwareEvent(sList[vi]);
        }
        return;
    }
    protected String resolveLogicalToAbsolute(String p0){
        if ("self".equals(p0)) {
            return this.fSelf.getGivenName();
        }
        if ("parent".equals(p0)) {
            return this.fSelf.getParent().getGivenName();
        }
        return p0;
    }
    public void share(Object[] p0){
        Object[] objectArray;
        Object object = null;
        int vi = 0;
        try{
            JSONObject jAt = this.argAt(p0, vi, object);
            Json.put(jAt, "__sender", this.fSelf.getGivenName());
            String sget = jAt.get("title");
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", sget);
            intent.putExtra("android.intent.extra.TEXT", new StringBuilder()+sget+" "+jAt.get("url"));
            this.fMain.startActivity(Intent.createChooser(intent, "Share ..."));
        }catch(org.json.JSONException e6){
            objectArray = new Object[1];
            objectArray[vi] = new StringBuilder()+"share failed: "+e6.getMessage();
            this.fLog.w(objectArray);
        }
        return;
    }
    public void show(Object[] p0){
        Controller cControllerB;
        Object[] objectArray;
        int vi = 0;
        String sAt = this.argAt(p0, vi, null);
        if ((cControllerB = this.getControllerByName(sAt)) != null) {
            if (!cControllerB.isViewVisible()) {
                cControllerB.show();
            }
        }else {
            objectArray = new Object[3];
            objectArray[vi] = "window \"";
            objectArray[1] = sAt;
            objectArray[2] = "\" does not exist - show\(\) ignored.";
            this.fLog.w(objectArray);
        }
        return;
    }
    public void unregisterTopics(Object[] p0){
        JSONArray jAt;
        int ilength;
        int vi = 0;
        if ((jAt = this.argAt(p0, vi, null)) == null) {
            return;
        }
        String sGivenName = this.fSelf.getGivenName();
        for (ilength = jAt.length(); vi < ilength; vi = vi+1) {
            WM.singleton.topics.unregister(jAt.optString(vi), sGivenName);
        }
    }
    public void updateStyle(Object[] p0){
        Controller cControllerB;
        Object[] objectArray;
        int vi = 0;
        String sAt = this.argAt(p0, vi, null);
        JSONObject jAt = this.argAt(p0, 1, null);
        if ((cControllerB = this.getControllerByName(sAt)) != null) {
            cControllerB.updateStyle(jAt);
        }else {
            objectArray = new Object[3];
            objectArray[vi] = "window \"";
            objectArray[1] = sAt;
            objectArray[2] = "\" does not exist - updateStyle\(\) ignored.";
            this.fLog.w(objectArray);
        }
        return;
    }

}
