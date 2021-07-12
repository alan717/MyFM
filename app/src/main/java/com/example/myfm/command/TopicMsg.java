package com.example.myfm.command;

public class TopicMsg {

    final String domain;
    final String id;
    final Topics this$0;

    public TopicMsg(Topics p0,String p1){
        int iOf;
        this.this$0 = p0;
         if ((iOf = p1.indexOf(37)) < 0) {
            this.domain = p1;
            this.id = "*";
        }else {
            this.domain = p1.substring((iOf+1));
            String str = (iOf==0)? "*": p1.substring(0, iOf);
            this.id = str;
        }
    }
    public boolean equals(Object p0){
        if (!(p0 instanceof TopicMsg)) {
            return false;
        }
        if (this.domain.equals(((TopicMsg) p0).domain)) {
            return this.id.equals(((TopicMsg) p0).id);
        }
        return false;
    }
    public int hashCode(){
        return (this.domain.hashCode()^this.id.hashCode());
    }

}
