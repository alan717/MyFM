package com.example.myfm.style;

public enum EPullType {
    REFRESH("REFRESH", 0),CLOSE("CLOSE", 1);
    private String state;
    private int i;
    EPullType(String close, int i) {
        this.state=close;
        this.i=i;
    }
}
