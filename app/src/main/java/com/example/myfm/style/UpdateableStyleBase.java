package com.example.myfm.style;

import com.example.myfm.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateableStyleBase {
    private final String fGivenName;
    protected Logger fLog;



    public   UpdateableStyleBase(String p0){

        this.fGivenName = p0;
        this.fLog = Logger.getLogger(new StringBuilder()+p0+".s");
    }
    protected Number asNumber(String p0,Object p1){
        if (p1 instanceof Number) {
            return (Number) p1;
        }
        if (p1 instanceof String) {
            return Double.valueOf(Double.parseDouble((String) p1));
        }
        throw new IllegalArgumentException(new StringBuilder()+"expected double for "+p0+" but encountered "+p1);
    }
    protected Boolean boolean0(String p0,JSONObject p1){
        Object oopt = p1.opt(p0);
        if (oopt instanceof Boolean) {
            return (Boolean) oopt;
        }
        if (oopt instanceof String) {
            return Boolean.valueOf(Boolean.parseBoolean((String) oopt));
        }
        if (!(oopt instanceof Number)) {
            throw new IllegalArgumentException(new StringBuilder().append("expected boolean for ").append(p0).append(" but encountered ").append(oopt).toString());
        }
        Boolean tRUE = (((Number) oopt).intValue()==1)? Boolean.TRUE: Boolean.FALSE;
        return tRUE;
    }
    public String getGivenName(){
        return this.fGivenName;
    }
    protected JSONObject getJson(String p0,JSONObject p1) throws JSONException {
        Object oget = p1.get(p0);
        if (oget instanceof JSONObject) {
            return (JSONObject) oget;
        }
        throw new IllegalArgumentException(new StringBuilder()+"expected json object for "+p0+" but encountered "+oget.getClass());
    }
    protected String getString(String p0,JSONObject p1) throws JSONException {
        Object oget = p1.get(p0);
        if (oget instanceof String) {
            return (String) oget;
        }
        throw new IllegalArgumentException(new StringBuilder()+"expected string for "+p0+" but encountered "+oget.getClass());
    }
    protected void logChange(String p0,Object p1,Object p2){
        this.logChange(p0, p1, p2, null);
    }
    protected void logChange(String p0,Object p1,Object p2,Object p3){
        Object[] objectArray;
        Object[] objectArray1;
        Object[] objectArray2;
        if (p1 == p2) {
            return;
        }
        if (p1==null || p3!=null&& p1.equals(p3)) {
            objectArray = new Object[3];
            objectArray[0] = p0;
            objectArray[1] = ": ";
            objectArray[2] = p2;
            this.fLog.d(objectArray);
        }else if(p2!=null){
            objectArray1 = new Object[2];
            objectArray1[0] = "clearing ";
            objectArray1[1] = p0;
            this.fLog.d(objectArray1);
        }else if(!p1.equals(p2)){
            objectArray2 = new Object[5];
            objectArray2[0] = p0;
            objectArray2[1] = ": ";
            objectArray2[2] = p1;
            objectArray2[3] = " -> ";
            objectArray2[4] = p2;
            this.fLog.d(objectArray2);
        }
        return;
    }
    protected Number number(String p0, JSONObject p1){
        return this.asNumber(p0, p1.opt(p0));
    }

}
