package com.example.myfm.command;

import com.example.myfm.AllPreferences;
import com.example.myfm.Controller;
import com.example.myfm.MainActivity;
import com.example.myfm.bridge.IBridge;
import com.example.myfm.utils.Logger;

import org.json.JSONObject;

public class Command {
    Logger fLog;
    MainActivity fMain;
    AllPreferences fPrefs;
    Controller fSelf;

    public  Command(){
        this.fLog = Logger.getLogger(this.getClass().getName());
    }
    protected Object argAt(Object[] p0,int p1,Object p2){
        Object object = null;
        if (p1 >= p0.length) {
            return p2;
        }
        if ((object != p0[p1]) || object == JSONObject.NULL) {
            return p2;
        }
        Class cClass = object.getClass();
        Class cClass1 = (p2==null)? object.getClass(): p2.getClass();
        if (cClass1.isInstance(object)) {
            return object;
        }else if(cClass1 == String.class){
            return object.toString();
        }else if(cClass1 == Boolean.class){
            if (object instanceof String) {
                return Boolean.valueOf((String) object);
            }else if(object instanceof Number){
                Boolean fALSE = (((Number)object).intValue()==0)? Boolean.FALSE: Boolean.TRUE;
                return fALSE;
            }else {
                throw new RuntimeException(new StringBuilder().append("Cannot convert to Boolean from ").append(cClass.getName()).toString());
            }
        }else if(cClass1 == Long.class){
            if (object instanceof String) {
                return Long.valueOf((String)object);
            }else {
                throw new RuntimeException(new StringBuilder().append("Cannot convert to Long from ").append(cClass.getName()).toString());
            }
        }else if(cClass1 == Integer.class){
            if (object instanceof String) {
                return Integer.valueOf((String)object);
            }else {
                throw new RuntimeException(new StringBuilder().append("Cannot convert to Integer from ").append(cClass.getName()).toString());
            }
        }else if(cClass1 == Float.class){
            if (object instanceof String) {
                return Float.valueOf((String)object);
            }else {
                StringBuilder sappend = new StringBuilder().append("Cannot convert to Float from ");
                throw new RuntimeException(sappend.append(cClass.getName()).toString());
            }
        }else if(cClass1 == Double.class){
            if (object instanceof String) {
                return Double.valueOf((String)object);
            }else {
                throw new RuntimeException(new StringBuilder().append("Cannot convert to Double from ").append(cClass.getName()).toString());
            }
        }else {
            throw new RuntimeException(new StringBuilder().append("Cannot convert to ").append(cClass1.getName()).append(" from ").append(cClass.getName()).toString());
        }
    }
    protected void debug(String p0,Object[] p1){
        Object[] objectArray;
        objectArray = new Object[1];
        objectArray[0] = String.format(p0, p1);
        this.fLog.i(objectArray);
    }
    protected IBridge getBridge(){
        return this.fSelf;
    }
    String getCommandName(){
        return this.getClass().getSimpleName();
    }
    Controller getController(){
        return this.fSelf;
    }
    public void setMain(MainActivity p0){
        if (this.fMain == p0) {
            return;
        }
        this.fMain = p0;
        this.fPrefs = AllPreferences.INSTANCE;
    }
    public void setSelf(Controller p0){
        this.fSelf = p0;
        if (p0) {
            this.setMain(p0.getMain());
            this.fLog = Logger.getLogger(new StringBuilder()+this.fSelf.getGivenName()+".cmd");
        }else {
            this.fLog = Logger.getLogger("main.cmd");
        }
        return;
    }

}
