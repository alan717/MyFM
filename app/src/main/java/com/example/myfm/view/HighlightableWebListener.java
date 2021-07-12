package com.example.myfm.view;

import android.view.MenuItem;
import android.webkit.WebView;

import com.example.myfm.Sign;

import java.net.URL;
import java.util.Date;

public class HighlightableWebListener implements MenuItem.OnMenuItemClickListener	{

        final HighlightableWeb this$0;

         public HighlightableWebListener(HighlightableWeb p0){
        this.this$0 = p0;

    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Object[] objectArray;
        WebView.HitTestResult wHitTestResu = this.this$0.getHitTestResult();
        try{
            int vi = 1;
            String sFile = new URL(wHitTestResu.getExtra()).getFile();
            int iOf = sFile.lastIndexOf("/");
            int vi1 = 2;
            if (iOf >= 0 && iOf < (sFile.length()-vi1)) {
                sFile = sFile.substring((iOf+vi));
            }
            if ((iOf = sFile.indexOf(63)) > 0) {
                sFile = sFile.substring(0, iOf);
                objectArray = new Object[vi1];
                objectArray[0] = "excluding url params from f, renaming to ";
                objectArray[vi] = sFile;
                HighlightableWeb.access$000(this.this$0).i(objectArray);
            }
            if (sFile.equals("resize")) {
                sFile = new StringBuilder().append("image-").append(new Date().getTime()).toString();
            }
            Void[] voidArray = new Void[0];
            new HighlightableWebSaveImageClass(this.this$0, sFile, wHitTestResu.getExtra()).execute(voidArray);
        }catch(java.net.MalformedURLException e){
            new Sign(this.this$0.getContext(), new StringBuilder().append("Invalid URL ").append(wHitTestResu.getExtra()).toString()).error();
        }
        return v0;

    }
}
