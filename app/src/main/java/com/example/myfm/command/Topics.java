package com.example.myfm.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Topics {
    final Map fSubscriptions;
    static String[] EMPTY_STR;

    static {
        String[] stringArray = new String[0];
        Topics.EMPTY_STR = stringArray;
    }
    public Topics(){
        this.fSubscriptions = new HashMap();
    }
    static boolean isTopic(String p0){
        boolean vb = (p0.indexOf(37) >= 0)? true: false;
        return vb;
    }
    TopicMsg create(String p0){
        return new TopicMsg(this, p0);
    }
    String[] interest(TopicMsg p0){
        Set sget = (Set) this.fSubscriptions.get(p0);
        Set sget1 = (Set) this.fSubscriptions.get(new TopicMsg(this, p0.domain));
        if (sget == null && sget1 == null) {
            return Topics.EMPTY_STR;
        }
        if (sget != null && sget1 != null) {
            HashSet hashSet = new HashSet(sget);
            hashSet.addAll(sget1);
            return (String[]) hashSet.toArray(Topics.EMPTY_STR);
        }else if(sget != null){
            return (String[]) sget.toArray(Topics.EMPTY_STR);
        }else {
            return (String[]) sget1.toArray(Topics.EMPTY_STR);
        }
    }
    String[] interest(String p0){
        return this.interest(new TopicMsg(this, p0));
    }



    void register(String p0,String p1){
        Set sget;
        HashSet hashSet = new HashSet();
        TopicMsg omsg= new TopicMsg(this, p0);
        if ((sget = (Set) this.fSubscriptions.get(omsg)) == null) {
            this.fSubscriptions.put(omsg, hashSet);
        }
        hashSet.add(p1);
        return;
    }
    void unregister(String p0,String p1){
        Set sget;
        TopicMsg omsg = new TopicMsg(this, p0);
        if ((sget = (Set) this.fSubscriptions.get(omsg)) == null) {
            return;
        }
        sget.remove(p1);
        if (sget.size()==0) {
            this.fSubscriptions.remove(omsg);
        }
    }
}
