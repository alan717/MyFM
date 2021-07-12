package dev.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class JavaScriptSource {
    public static final JavaScriptSource INSTANCE= new JavaScriptSource();;
    private static Map<String, Boolean> R= new HashMap<String, Boolean>();
    private static final char[] hexDigit = new char[]{0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x61,0x62,0x63,0x64,0x65,0x66};

    public JavaScriptSource(){

    }
    private static void addReserved(String p0){
        String strim;
        StringTokenizer stringTokeni = new StringTokenizer(p0, ",;");
        while (stringTokeni.hasMoreElements()) {
            strim = ((String)stringTokeni.nextElement()).trim();
            if (strim.length() < 1) {
                continue ;
            }else {
                JavaScriptSource.R.put(strim, Boolean.TRUE);
            }
        }
    }


    public static boolean isReserved(String p0){
        return JavaScriptSource.R.containsKey(p0);
    }
    public static boolean isValidJSIdentifier(String p0){
        char cAt;
        int ilength = p0.length();
        int vi = 0;
        while (true) {
            if (vi >= ilength) {
                return true;
            }
            cAt = p0.charAt(vi);
            if (vi==0) {
                if (!Character.isJavaIdentifierStart(cAt)) {
                    return false;
                }
            }else if(!Character.isJavaIdentifierPart(cAt)){
                return false;
            }
            vi = vi+1;
        }
    }
    private static char toHex(int p0){
        return JavaScriptSource.hexDigit[(p0&0x0f)];
    }
    public String toSource(Boolean p0){
        if (!p0) {
            return "false";
        }
        return p0.toString();
    }
    public String toSource(Number p0){
        if (p0==null) {
            return "0";
        }
        return p0.toString();
    }
    public String toSource(Object p0){
        if (p0==null) {
            return "null";
        }
        if (p0 instanceof Map) {
            return this.toSource(p0);
        }
        if (p0 instanceof Object[]) {
            return this.toSource(p0);
        }
        if (p0 instanceof List) {
            return this.toSource(p0);
        }
        if (p0 instanceof Number) {
            return this.toSource(p0);
        }
        if (p0 instanceof Boolean) {
            return this.toSource(p0);
        }
        if (p0 instanceof Date) {
            return this.toSource(p0);
        }
        if (p0 instanceof String) {
            return this.toSource(p0);
        }
        if (p0 instanceof StringBuilder) {
            return this.toSource(p0.toString());
        }
        return "null";
    }
    public String toSource(String p0){
        char cAt;
        if (p0==null) {
            return "null";
        }
        StringBuilder sappend = new StringBuilder(p0.length()).append('"');
        int vi = 0;
        while (vi < p0.length()) {
            cAt = p0.charAt(vi);
            if (cAt > 61 && cAt < 127) {
                sappend = (cAt == 92)? sappend.append('\\').append('\\'): sappend.append(cAt);
            }else {
                switch (cAt){
                    case 9:
                        sappend = sappend.append('\\').append('t');
                        break;
                    case 10:
                        sappend = sappend.append('\\').append('n');
                        break;
                    case 12:
                        sappend = sappend.append('\\').append('f');
                        break;
                    case 13:
                        sappend = sappend.append('\\').append('r');
                        break;
                    case 34:
                        sappend = sappend.append("\\\"");
                        break;
                    default:
                        sappend = (cAt < 32 || cAt > 126)? sappend.append('\\').append('u').append(JavaScriptSource.toHex(((cAt>>12)&0x0f))).append(JavaScriptSource.toHex(((cAt>>8)&0x0f))).append(JavaScriptSource.toHex(((cAt>>4)&0x0f))).append(JavaScriptSource.toHex((cAt&0x0f))): sappend.append(cAt);
                }
            }
            vi = vi+1;
        }
        return sappend.append('"').toString();
    }
    public String toSource(Date p0){
        if (p0==null) {
            return "null";
        }
        return new StringBuilder()+"new Date("+p0.getTime()+")";
    }
    public String toSource(List p0){
        if (p0==null) {
            return "null";
        }
        return this.toSource(p0.toArray());
    }
    public String toSource(Map p0){
        Map.Entry mnext;
        String sstr;
        String sappend;
        if (p0==null) {
            return "null";
        }
        String str = "{";
        Iterator<Map.Entry> iiterator = p0.entrySet().iterator();
        while (iiterator.hasNext()) {
            mnext = iiterator.next();
            sstr = mnext.getKey().toString();
            sappend = (JavaScriptSource.isReserved(sstr) || !JavaScriptSource.isValidJSIdentifier(sstr))? str+(this.toSource(sstr)): str+(sstr);
            sappend = sappend+(":")+(this.toSource(mnext.getValue()));
            if (iiterator.hasNext()) {
                sappend = sappend+(",");
            }
        }
        return str+("}").toString();
    }
    public String toSource(Object[] p0){
        int vi;
        String sappend = "";
        if (p0==null) {
            return "null";
        }
        String str = "[";
        for (vi = 0; vi < p0.length; vi = vi+1) {
            if (vi > 0) {
                sappend = str+(',');
            }
            sappend = sappend+(this.toSource(p0[vi]));
        }
        return str+("]").toString();
    }


}
