package com.example.myfm.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONTokener;


public class Json {
    private static final String[] EMPTY = new String[0];


    public   Json(){

    }
    public static JSONObject clone(JSONObject p0){
        return Json.merge(new JSONObject(), p0, true);
    }
    public static String get(JSONObject p0,String p1){
        return p0.optString(p1, null);
    }
    private static void handle(Throwable p0){
    }
    public static Object jsonGet(Object p0,Object[] p1){
        Object oi;
        Object oopt;
        int len = p1.length;
        int vi = 0;
        while (vi < len) {
            oi = p1[vi];
            if (p0 == null) {
                break ;
            }else if(oi instanceof String){
                oopt = (p0 instanceof JSONObject)? ((JSONObject)p0).opt((String) oi): null;
            }else if(oi instanceof Number){
                oopt = (p0 instanceof JSONArray)? ((JSONArray)p0).opt(((Number)oi).intValue()): null;
            }
            vi = vi+1;
        }
        return p0;
    }
    public static JSONObject merge(JSONObject p0,JSONObject p1,boolean p2){
        String snext;
        Iterator ikeys = p1.keys();
        while (ikeys.hasNext()) {
            snext = (String) ikeys.next();
            if (p0.has(snext) && !p2) {
                continue ;
            }else {
                Json.put(p0, snext, p1.opt(snext));
            }
        }
        return p0;
    }
    public static JSONObject parse(String p0){
        try{
            return new JSONObject(p0);
        }catch(org.json.JSONException e1){
            Json.handle(e1);
            return null;
        }
    }
    public static Object parseNext(String p0){
        try{
            return new JSONTokener(p0).nextValue();
        }catch(org.json.JSONException e1){
            Json.handle(e1);
            return null;
        }
    }
    public static void push(JSONObject p0, Map p1){
        Map.Entry mnext;
        try{
            Iterator iiterator = p1.entrySet().iterator();
            while (iiterator.hasNext()) {
                mnext = (Map.Entry) iiterator.next();
                p0.put((String) mnext.getKey(), mnext.getValue());
            }
        }catch(org.json.JSONException e2){
            Json.handle(e2);
        }
        return;
    }
    public static void put(JSONObject p0,String p1,int p2){
        try{
            p0.put(p1, p2);
        }catch(org.json.JSONException e0){
            Json.handle(e0);
        }
        return;
    }
    public static void put(JSONObject p0,String p1,Object p2){
        try{
            p0.put(p1, p2);
        }catch(org.json.JSONException e0){
            Json.handle(e0);
        }
        return;
    }
    public static void put(JSONObject p0,String p1,String p2){
        try{
            p0.put(p1, p2);
        }catch(org.json.JSONException e0){
            Json.handle(e0);
        }
        return;
    }
    public static void put(JSONObject p0,String p1,boolean p2){
        int vi = (p2)? 1: 0;
        try{
            p0.put(p1, vi);
        }catch(org.json.JSONException e0){
            Json.handle(e0);
        }
        return;
    }
    public static String[] toJavaArray(JSONArray p0){
        int vi;
        String[] eMPTY = Json.EMPTY;
        if (p0!=null) {
            return eMPTY;
        }
        eMPTY = new String[p0.length()];
        for (vi = 0; vi < eMPTY.length; vi = vi+1) {
            eMPTY[vi] = p0.optString(vi);
        }
        return eMPTY;
    }
    public static String[] toJavaArray(JSONObject p0,String p1){
        return Json.toJavaArray(p0.optJSONArray(p1));
    }
    public static String toQuery(JSONObject p0){
        String snext;
        String sstr;
        String stringBuilde = new String();
        Iterator ikeys = p0.keys();
        while (ikeys.hasNext()) {
            snext = (String) ikeys.next();
            sstr = p0.optString(snext);
            try{
                stringBuilde = stringBuilde+snext+"="+ URLEncoder.encode(sstr, "UTF-8")+"&";
            }catch(IOException e){

            }
        }
        return stringBuilde;
    }
    public static String toSource(JSONArray p0){
        int vi = 2;
        try{
            return p0.toString(vi);
        }catch(org.json.JSONException e1){
            Json.handle(e1);
            return "[]";
        }
    }
    public static String toSource(JSONObject p0){
        int vi = 2;
        try{
            return p0.toString(vi);
        }catch(org.json.JSONException e1){
            Json.handle(e1);
            return "{}";
        }
    }

}
