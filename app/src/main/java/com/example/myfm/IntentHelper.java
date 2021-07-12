package com.example.myfm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.Iterator;

public class IntentHelper {
    final Context fMain;
    public static String TWITTER_APP_ID;

    public IntentHelper(Context fMain) {
        this.fMain = fMain;
    }
    public Intent getShareIntentByPackageId(String p0){
        ResolveInfo rnext;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", "This is a Test.");
        intent.setType("text/plain");
        Iterator<ResolveInfo> iiterator = this.fMain.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).iterator();
        while (true) {
            if (!iiterator.hasNext()) {
                return null;
            }
            rnext = iiterator.next();
            if (rnext.activityInfo.packageName.startsWith(p0)) {
                intent.setClassName(rnext.activityInfo.packageName, rnext.activityInfo.name);
                return intent;
            }
        }
    }
    public Intent getTwitterShareIntent(){
        return this.getShareIntentByPackageId(IntentHelper.TWITTER_APP_ID);
    }
    protected boolean isAppInstalled(String p0){
        boolean vb = false;

        try{
            if (this.fMain.getPackageManager().getPackageInfo(p0, 0) != null) {
                vb = true;
            }
            return vb;
        }catch(java.lang.Exception e){
            return vb;
        }
    }
    protected boolean isShareAvailableByPackageId(String p0){
        boolean vb = (this.getShareIntentByPackageId(p0) != null)? true: false;
        return vb;
    }
    public boolean isTwitterAppInstalled(){
        return this.isShareAvailableByPackageId(IntentHelper.TWITTER_APP_ID);
    }
}
