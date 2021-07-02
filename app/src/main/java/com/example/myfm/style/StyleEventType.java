package com.example.myfm.style;

public enum StyleEventType {
    OPEN("OPEN", 0),CLOSE("CLOSE", 1),SHOW("SHOW", 2),BEFORE_HIDE("BEFORE_HIDE", 3),HIDE("HIDE", 4),ROTATE("ROTATE", 5);
    private String name;
    private int index;
    StyleEventType(String name, int i) {
        this.name = name;
        this.index = index;
    }
}
