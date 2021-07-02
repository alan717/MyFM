package com.example.myfm.utils;

import java.util.Map;
import android.util.Log;

public class Logger {
    private String fLogTag;
    private String fUserTag;
    public static boolean  sEnabled = false;;
    private static final Map<String,Logger> sLoggers = null;
      public Logger(String p0){
          this.fLogTag = p0;
    }
    public static Logger getLogger(String p0){
        Logger lget;
        Object[] objectArray;
        synchronized(sLoggers) {
            try {
                int vi = 0;
                int vi1 = 23;
                String ssubstring = (p0.length() > vi1) ? p0.substring(vi, vi1) : p0;
                if ((lget = Logger.sLoggers.get(ssubstring)) == null) {
                    lget = new Logger(ssubstring);
                    Logger.sLoggers.put(ssubstring, lget);
                }
                if (ssubstring != p0) {
                    objectArray = new Object[2];
                    objectArray[vi] = "log tag truncated (only 23 chars allowed), ";
                    objectArray[1] = p0;
                    lget.w(objectArray);
                }

                return lget;
            } catch (Exception e5) {
                throw e5;
            }
        }
    }

    private Throwable throwable(Object[] p0){
        if ((p0!=null) && p0.length > 0 && p0[(p0.length-1)] instanceof Throwable) {
            return (Throwable) p0[(p0.length-1)];
        }
        return null;
    }
    private String buildMessage(int p0,Object[] p1){
        if (p1==null) {
            return "null";
        }
        int vi = 0;
        if (p1.length == 1) {
            return String.valueOf(p1[vi]);
        }
        StringBuilder stringBuilde = new StringBuilder();
        for (; vi < p0; vi = vi+1) {
            stringBuilde = stringBuilde.append(p1[vi]);
        }
        return stringBuilde.toString();
    }

    public void d(Object[] p0){
        Throwable tthrowable;
        if (Logger.sEnabled) {
            if ((tthrowable = this.throwable(p0)) != null) {
                Log.d(this.fLogTag, this.buildMessage((p0.length-1), p0), tthrowable);
            }else {
                Log.d(this.fLogTag, this.buildMessage(p0.length, p0));
            }
        }
        return;
    }
    public void e(Object[] p0){
        Throwable tthrowable;
        if (Logger.sEnabled) {
            if ((tthrowable = this.throwable(p0)) != null) {
                Log.e(this.fLogTag, this.buildMessage((p0.length-1), p0), tthrowable);
            }else {
                Log.e(this.fLogTag, this.buildMessage(p0.length, p0));
            }
        }
        return;
    }
    public void i(Object[] p0){
        Throwable tthrowable;
        if (Logger.sEnabled) {
            if ((tthrowable = this.throwable(p0)) != null) {
                Log.i(this.fLogTag, this.buildMessage((p0.length-1), p0), tthrowable);
            }else {
                Log.i(this.fLogTag, this.buildMessage(p0.length, p0));
            }
        }
        return;
    }
    public void v(Object[] p0){
        Throwable tthrowable;
        if (Logger.sEnabled) {
            if ((tthrowable = this.throwable(p0)) != null) {
                Log.v(this.fLogTag, this.buildMessage((p0.length-1), p0), tthrowable);
            }else {
                Log.v(this.fLogTag, this.buildMessage(p0.length, p0));
            }
        }
        return;
    }
    public void w(Object[] p0){
        Throwable tthrowable;
        if (Logger.sEnabled) {
            if ((tthrowable = this.throwable(p0)) != null) {
                Log.w(this.fLogTag, this.buildMessage((p0.length-1), p0), tthrowable);
            }else {
                Log.w(this.fLogTag, this.buildMessage(p0.length, p0));
            }
        }
        return;
    }

}
