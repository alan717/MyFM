package com.example.myfm;

public class MainRun implements Runnable {
    MainActivity ma;

    public MainRun(MainActivity activity) {
        this.ma=activity;
    }


    @Override
    public void run() {
        MainActivity.createMain(this.ma);
    }
}
