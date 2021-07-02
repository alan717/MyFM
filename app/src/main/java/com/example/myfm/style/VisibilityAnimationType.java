package com.example.myfm.style;

public enum VisibilityAnimationType {
    APPEAR("APPEAR", 0, "appear"),FADE("FADE", 1, "fadein"),SLIDE_UP("SLIDE_UP", 2, "slideup"),SLIDE_DOWN("SLIDE_DOWN", 3, "slidedown"),
    SLIDE_RIGHT("SLIDE_RIGHT", 4, "slideright");
    ;

    VisibilityAnimationType(String appear, int i, String appear1) {
        this.fStyleNames=appear;
        this.index=i;
        this.stylename=appear1;
    }
    private  String fStyleNames;
    private int index;
    private String stylename;
}
