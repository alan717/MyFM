package com.example.myfm.bridge;

public class Bridgebridge {

    final Bridge this$0;

    public Bridgebridge(Bridge p0){
        this.this$0 = p0;
     }
    public void postMessage(String p0,String p1,String p2){
        String str;
        Object[] objectArray=null;
        if (p0==null || p1==null) {
            return;
        }
        if (p2==null) {
            str = "[]";
        }
        int vi = 5;
        int vi1 = 4;
        int vi2 = 3;
        int vi3 = 2;
        int vi4 = 7;
        if (str.length() > Bridge.MAX_LOG_JS) {
            objectArray = new Object[vi4];
            objectArray[0] = p0;
            objectArray[1] = ".";
            objectArray[vi3] = p1;
            objectArray[vi2] = "(";
            objectArray[vi1] = str.substring(0, Bridge.MAX_LOG_JS);
            objectArray[vi] = "...";
            objectArray[6] = new StringBuilder().append(") - self=").append(Bridge.access$300(this.this$0)).toString();
            this.this$0.fLog.w(objectArray);
        }else {
            objectArray = new Object[vi4];
            objectArray[0] = p0;
            objectArray[1] = ".";
            objectArray[vi3] = p1;
            objectArray[vi2] = "(";
            objectArray[vi1] = str;
            objectArray[vi] = ") - self=";
            objectArray[6] = Bridge.access$300(this.this$0);
            this.this$0.fLog.w(objectArray);
        }
        this.this$0.js2java(p0, p1, str);
        return;
    }
    public void reply(String p0){
        Bridge.access$102(this.this$0, p0);
        Bridge.access$200(this.this$0).countDown();
    }

}
