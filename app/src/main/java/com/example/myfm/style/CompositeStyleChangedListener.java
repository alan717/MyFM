package com.example.myfm.style;

import android.view.View;

import com.example.myfm.utils.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class CompositeStyleChangedListener  implements IStyleChangedListener {
    private List<StyleChangedWrapper> fListeners;
    private Logger fLog;

    public CompositeStyleChangedListener(Logger p0){

        this.fListeners = new ArrayList<StyleChangedWrapper>();
        this.fLog = p0;
    }
    public void addListener(IStyleChangedListener p0,int p1){
         StyleChangedWrapper cnext;
         StyleChangedWrapper styleChanged;
         Iterator<StyleChangedWrapper> iiterator = this.fListeners.iterator();
            while (true) {
                if (iiterator.hasNext()) {
                    cnext = iiterator.next();
                    if (StyleChangedWrapper.access$000(cnext) == p0) {
                    StyleChangedWrapper.access$102(cnext, p1);
                        Collections.sort(this.fListeners);
                    return;
                }
            }else {
                styleChanged = new StyleChangedWrapper(this, 0);
                StyleChangedWrapper.access$002(styleChanged, p0);
                StyleChangedWrapper.access$102(styleChanged, p1);
                this.fListeners.add(styleChanged);
                Collections.sort(this.fListeners);
            }
        }
    }
    @Override
    public void onStyleChangeComplete(StyleEventType p0, View p1) {
        Object[] objectArray;
        objectArray = new Object[2];
        objectArray[0] = "style changed: ";
        objectArray[1] = p0;

        Iterator<StyleChangedWrapper> iiterator = this.fListeners.iterator();
        while (iiterator.hasNext()) {
            StyleChangedWrapper.access$000(iiterator.next()).onStyleChangeComplete(p0, p1);
        }
    }
    public void removeListener(IStyleChangedListener p0){
        StyleChangedWrapper cnext;
        Iterator<StyleChangedWrapper> iiterator = this.fListeners.iterator();
        while (iiterator.hasNext()) {
            cnext = iiterator.next();
            if (StyleChangedWrapper.access$000(cnext) == p0) {
                this.fListeners.remove(cnext);
                break ;
            }
        }
        return;

    }
    static class StyleChangedWrapper implements Comparable<StyleChangedWrapper>{
        private IStyleChangedListener listener;
        private int priority;
        final CompositeStyleChangedListener m;

        StyleChangedWrapper(CompositeStyleChangedListener p0, int i) {
            this.m = p0;
            this.priority=i;
        }



    static IStyleChangedListener access$000( StyleChangedWrapper p0){
        return p0.listener;
    }
    static IStyleChangedListener access$002( StyleChangedWrapper p0,IStyleChangedListener p1){
        p0.listener = p1;
        return p1;
    }
    static int access$102( StyleChangedWrapper p0,int p1){
        p0.priority = p1;
        return p1;
    }

        @Override
        public int compareTo(StyleChangedWrapper styleChangedWrapper) {
            return (this.priority-styleChangedWrapper.priority);
        }
    }
}
