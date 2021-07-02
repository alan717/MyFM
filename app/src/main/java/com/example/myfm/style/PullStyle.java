package com.example.myfm.style;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class PullStyle extends UpdateableStyleBase {
    private String fActiveText;
    private String fArmText;
    private EPullType fPullType;
    private String fText;
    private int fYOffset;
    public static final String PROP_ACTIVE_TEXT = null;
    public static final String PROP_ARM_TEXT = null;
    public static final String PROP_TEXT = null;
    public static final String PROP_TYPE = null;
    public static final String PROP_Y_OFFSET = null;

    public   PullStyle(String p0){
        super(p0);
    }
    private String updateString(String p0, JSONObject p1, String p2) throws JSONException {
        String sstr = this.getString(p0, p1);
        this.logChange(p0, p2, sstr);
        return sstr;
    }
    public String getActiveText(){
        return this.fActiveText;
    }
    public String getArmText(){
        return this.fArmText;
    }
    public EPullType getPullType(){
        return this.fPullType;
    }
    public String getText(){
        return this.fText;
    }
    public int getYOffset(){
        return this.fYOffset;
    }
    public void setActiveText(String p0){
        this.fActiveText = p0;
    }
    public void setArmText(String p0){
        this.fArmText = p0;
    }
    public void setPullType(EPullType p0){
        this.fPullType = p0;
    }
    public void setText(String p0){
        this.fText = p0;
    }
    public String toString(){
        return this.getGivenName()+":"+this.fPullType;
    }
    void update(JSONObject p0){
        String str = null;
        String str1;
        Object[] objectArray;
        int vi = 0;
        try{
            Iterator ikeys = p0.keys();
            int vi1 = vi;
     
                while (ikeys.hasNext()) {
                    str = (String) ikeys.next();
                    if (str.equals("activeText")) {
                        this.fActiveText = this.updateString(str, p0, this.fActiveText);
                    } else if (str.equals("armText")) {
                        this.fArmText = this.updateString(str, p0, this.fArmText);
                    } else if (str.equals("text")) {
                        this.fText = this.updateString(str, p0, this.fText);
                    } else if (str.equals("yOffset")) {
                        this.fYOffset = this.asNumber("yOffset", p0.get(str)).intValue();
                    } else if (str.equals("type")) {
                        str1 = (this.fPullType == null) ? (String) "0" : this.fPullType.toString().toLowerCase();
                        this.fPullType = EPullType.valueOf(this.updateString(str, p0, str1).toUpperCase());
                    } else {
                        objectArray = new Object[2];
                        objectArray[0] = "ignoring style ";
                        objectArray[1] = str;
                        this.fLog.w(objectArray);
                    }
                }
            }catch(org.json.JSONException e8){

                   throw new IllegalArgumentException(new StringBuilder().append("error processing ").append(str).toString(), e8);
            }

    }

}
