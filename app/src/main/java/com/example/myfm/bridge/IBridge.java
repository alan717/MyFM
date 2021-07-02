package com.example.myfm.bridge;

import org.json.JSONObject;
import java.lang.String;

public interface IBridge {
    public Object eval(String p0);
    public void exec(String p0);
    public void exec(String p0,Object p1);
    public void onappevent(JSONObject p0);
    public void onevent(JSONObject p0);
    public void setupBridge();
}
