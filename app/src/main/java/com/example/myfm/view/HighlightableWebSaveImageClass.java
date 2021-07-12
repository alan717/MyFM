package com.example.myfm.view;

import android.os.AsyncTask;

public class HighlightableWebSaveImageClass extends AsyncTask {
    private final String fFileName;
    private final String fUrl;
    final HighlightableWeb this$0;
    public static final int REQUEST_CODE_EXTERNAL_STORAGE;


    public HighlightableWebSaveImageClass(String fFileName, String fUrl, HighlightableWeb this$0) {
        super();
        this.fFileName = fFileName;
        this.fUrl = fUrl;
        this.this$0 = this$0;
    }
    protected Object doInBackground(Object[] p0){
        return this.doInBackground(p0);
    }
    protected void onPostExecute(Object p0){
        this.onPostExecute(p0);
    }
    protected void onPostExecute(String p0){
        if (p0==null) {
            return;
        }
        if (p0.startsWith("S")) {
            new Sign(this.this$0.getContext(), new StringBuilder()+"Saved to "+p0.substring(1)).message();
        }else {
            new Sign(this.this$0.getContext(), p0).error();
        }
        return;
    }
}
