package com.digipodium.www.appone.utils;

import android.content.Context;
import android.content.SharedPreferences;



public class Prefs {

    private final SharedPreferences pref;
    private Context context;

    public Prefs(Context context, String prefFile) {
        this.context = context;
        pref = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPref() {
        return pref;
    }

    public SharedPreferences.Editor getEditor(){
        return pref.edit();
    }


}
