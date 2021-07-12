package com.example.myfm;

import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import java.util.Set;

public class AllPreferencesEditor implements SharedPreferences.Editor{

    final AllPreferences  this$0;

    public AllPreferencesEditor(AllPreferences this$0) {
        this.this$0 = this$0;
    }

    @Override
    public SharedPreferences.Editor putString(String s, @Nullable String s1) {
        AllPreferences.access$000(this.this$0).putString(s, s1);
        return AllPreferences.access$100(this.this$0);
    }

    @Override
    public SharedPreferences.Editor putStringSet(String s, @Nullable Set<String> set) {
        AllPreferences.access$000(this.this$0).putStringSet(s, set);
        return AllPreferences.access$100(this.this$0);
    }

    @Override
    public SharedPreferences.Editor putInt(String s, int i) {
        AllPreferences.access$000(this.this$0).putInt(s, i);
        return AllPreferences.access$100(this.this$0);
    }

    @Override
    public SharedPreferences.Editor putLong(String s, long l) {
        AllPreferences.access$000(this.this$0).putLong(s, l);
        return AllPreferences.access$100(this.this$0);
    }

    @Override
    public SharedPreferences.Editor putFloat(String s, float v) {
        AllPreferences.access$000(this.this$0).putFloat(s, v);
        return AllPreferences.access$100(this.this$0);
    }

    @Override
    public SharedPreferences.Editor putBoolean(String s, boolean b) {
        AllPreferences.access$000(this.this$0).putBoolean(s, b);
        return AllPreferences.access$100(this.this$0);
    }

    @Override
    public SharedPreferences.Editor remove(String s) {
        AllPreferences.access$000(this.this$0).remove(s);
        return AllPreferences.access$100(this.this$0);
    }

    @Override
    public SharedPreferences.Editor clear() {

        return AllPreferences.access$000(this.this$0).clear();

    }

    @Override
    public boolean commit() {
        return AllPreferences.access$000(this.this$0).commit();
    }

    @Override
    public void apply() {
        if (HackPredicates.INSTANCE.isAtLeastAPI(9)) {
            AllPreferences.access$000(this.this$0).apply();
        }else {
            AllPreferences.access$000(this.this$0).commit();
        }
    }
}
