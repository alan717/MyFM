package com.example.myfm.widget;

import org.json.JSONObject;

import java.io.Serializable;

public class SessionInfo implements Serializable {
    private String fAccessToken;
    private long fExpiry;
    private String fFeedlyId;
    private String fPlan;
    private String fProvider;
    private String fRefreshToken;

    public SessionInfo(){

    }
    public   SessionInfo(String p0,String p1,String p2,long p3,String p4,String p5){

        this.fFeedlyId = p0;
        this.fRefreshToken = p1;
        this.fAccessToken = p2;
        this.fExpiry = p3;
        this.fProvider = p4;
        this.fPlan = p5;
    }
    public static SessionInfo fromJson(String p0){
        SessionInfo sessionInfo;
        if (p0==null) {
            return null;
        }
        try{
            JSONObject jSONObject = new JSONObject(p0);
            sessionInfo = new SessionInfo();
            sessionInfo.fFeedlyId = jSONObject.getString("feedlyId");
            sessionInfo.fRefreshToken = jSONObject.getString("feedlyRefreshToken");
            sessionInfo.fAccessToken = jSONObject.optString("feedlyToken");
            sessionInfo.fExpiry = jSONObject.optLong("feedlyExpirationTime");
            sessionInfo.fProvider = jSONObject.optString("provider");
            sessionInfo.fPlan = jSONObject.optString("plan");
            return sessionInfo;
        }catch(org.json.JSONException e3){
            throw new RuntimeException(e3);
        }
    }

    public String getAccessToken(){
        return this.fAccessToken;
    }
    public String getFeedlyId(){
        return this.fFeedlyId;
    }
    public String getPlan(){
        return this.fPlan;
    }
    public String getProvider(){
        return this.fProvider;
    }
    public String getRefreshToken(){
        return this.fRefreshToken;
    }
    public boolean isBusinessPlan(){
        return "business".equals(this.getPlan());
    }
    public void setAccessToken(String p0){
        this.fAccessToken = p0;
    }
    public void setExpiry(long p0){
        this.fExpiry = p0;
    }
    public void setFeedlyId(String p0){
        this.fFeedlyId = p0;
    }
    public void setProvider(String p0){
        this.fProvider = p0;
    }

    public String toJson(){
        JSONObject jSONObject;
        try{
            jSONObject = new JSONObject();
            jSONObject.put("feedlyId", this.fFeedlyId);
            jSONObject.put("feedlyRefreshToken", this.fRefreshToken);
            if (this.fAccessToken != null && this.fExpiry-System.currentTimeMillis() > 0) {
                jSONObject.put("feedlyToken", this.fAccessToken);
                jSONObject.put("feedlyExpirationTime", this.fExpiry);
            }
            if (this.fProvider != null) {
                jSONObject.put("provider", this.fProvider);
            }
            if (this.fPlan != null) {
                jSONObject.put("plan", this.fPlan);
            }
            return jSONObject.toString();
        }catch(org.json.JSONException e0){
            throw new RuntimeException(e0);
        }
    }

}
