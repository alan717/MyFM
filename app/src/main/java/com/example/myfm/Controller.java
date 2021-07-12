package com.example.myfm;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

import com.example.myfm.bridge.IBridge;
import com.example.myfm.command.WM;
import com.example.myfm.style.CompositeStyleChangedListener;
import com.example.myfm.style.IStyleChangedListener;
import com.example.myfm.style.WindowStyleManager;
import com.example.myfm.utils.ILog;
import com.example.myfm.utils.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Controller implements IBridge, ILog {
    private String fGivenName;
    public String CONTENT_VIEW_TAG;
    protected IBridge fBridge;
    protected View fContentView;
    private static final Map fHardwareEventNames= new ConcurrentHashMap(4);
    protected JSONObject fMainJson;
    private List<Controller> fGroup;
    protected Handler fHandler;
    protected Logger fLog;
    private Controller fOwner;
    private Controller fParent;
    private CompositeStyleChangedListener fStyleListener;
    protected MainActivity fMain;
    protected FrameLayout fContentViewFrame;
    protected WindowStyleManager fStyleManager;

    protected AllPreferences fPrefs;

    public   Controller(){
        this.CONTENT_VIEW_TAG = new StringBuilder()+"content.view."+this.getClass().getName();
        this.fMainJson = new JSONObject();
        this.fGroup = new ArrayList<Controller>();
        this.fHandler = new Handler();
        this.fLog = Logger.getLogger(new StringBuilder()+"c.anon."+this.getClass().getSimpleName());
    }
    private IBridge getBridge(){
        return this.fBridge;
    }
    public void addController(Controller p0){
        if (p0.isMemberOfAGroup()) {
            p0.fOwner.removeController(p0);
        }
        this.fGroup.add(p0);
        p0.fOwner = this;

    }
    public boolean isMemberOfAGroup(){
        boolean vb = (this.fOwner != null)? true: false;
        return vb;
    }
    public void removeController(Controller p0){
        if (p0.fOwner != this) {
            return;
        }
        this.fGroup.remove(p0);
        p0.fOwner = null;
    }

    public void addStyleChangeListener(IStyleChangedListener p0){
        this.fStyleListener.addListener(p0, 100);
    }

    @Override
    public Object eval(String p0) {
        return null;
    }

    @Override
    public void exec(String p0) {

    }

    @Override
    public void exec(String p0, Object p1) {

    }

    @Override
    public void onappevent(JSONObject p0) {

    }

    @Override
    public void onevent(JSONObject p0) {

    }

    @Override
    public void setupBridge() {

    }

    @Override
    public void dbg(String p0, Object[] p1) {

    }

    @Override
    public void err(String p0, Object[] p1) {

    }

    @Override
    public void info(String p0, Object[] p1) {

    }

    @Override
    public void warn(String p0, Object[] p1) {

    }

    public void init(String p0,MainActivity p1) {
        this.fMain = p1;
        this.setGivenName(p0);
        this.fStyleListener = new CompositeStyleChangedListener(this.fLog);
        this.fStyleListener.addListener(new ControllerListener(this), 1001);
        this.fStyleManager = new WindowStyleManager(p0);
        this.fStyleManager.setStyleChangedListener(this.fStyleListener);
    }

    public void setGivenName(String p0){
        this.fLog = Logger.getLogger(new StringBuilder()+p0+".c");
    }

    public void buildView(){
        this.fPrefs = AllPreferences.INSTANCE;
    }
    public MainActivity getMain(){
        return this.fMain;
    }

    public String getGivenName(){
        return this.fGivenName;
    }
    protected void hideGrouped(){
        Object[] objectArray;
        WM wInstance = WM.getInstance();
        Iterator<Controller> iiterator = this.fGroup.iterator();
        while (iiterator.hasNext()) {
            objectArray = new Object[1];
            objectArray[0] = iiterator.next().getGivenName();
            wInstance.hide(objectArray);
        }
    }
    public Controller getParent(){
        return this.fParent;
    }
}
