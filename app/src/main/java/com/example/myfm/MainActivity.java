package com.example.myfm;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.os.Build;

public class MainActivity extends Activity {
    int fMagicCount;
    private final Handler fHandler=new Handler();
    boolean fSystemWindowInsetResolved= false;
    MainController fMainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window wWindow = this.getWindow();
        int vi = 2;
        wWindow.requestFeature(Window.DECOR_CAPTION_SHADE_DARK);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        int flag = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        wWindow.getDecorView().setSystemUiVisibility(flag);
        if (Build.VERSION.SDK_INT >= 24) {
            this.getWindow().setNavigationBarColor(this.getApplicationContext().getColor(R.color.primary));
        }
        wWindow.setBackgroundDrawable(null);
        this.setContentView(R.layout.splash);

    }
    static  Handler getHandler(MainActivity main){
        return main.fHandler;
    }
    static void createMain(MainActivity p0){
        p0.createMain();
    }

    @SuppressLint("ResourceType")
    public ViewGroup top(){
        return this.findViewById(R.layout.splash);
    }
    private void createMain(){
        int fMagicCount;
        if ((fMagicCount = this.fMagicCount) < 0) {
            return;
        }
        this.fMagicCount = fMagicCount-1;
        fMagicCount = this.getDisplayWidth();
        int iDisplayHeig = this.getDisplayHeight();
        if (fMagicCount > 0 && iDisplayHeig > 0 && this.fSystemWindowInsetResolved) {
            this.doCreateMain(fMagicCount, iDisplayHeig);
        }else {
            this.fHandler.postDelayed(new MainRun(this), 20);
        }
        return;
    }
    int getDisplayWidth(){
        return this.top().getWidth();
    }
    int getDisplayHeight(){
        return this.top().getHeight();
    }
    private void doCreateMain(int p0,int p1){
        this.getIntent();
        this.fMainController = new MainController();
        this.fMainController.init("main", this);
        this.fMainController.buildView();
        this.fMainController.attachView();
        this.fMainController.fContentView.setMinimumHeight(p1);
        this.fMainController.fContentView.setMinimumWidth(p0);

    }
}