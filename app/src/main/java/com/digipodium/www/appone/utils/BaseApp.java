package com.digipodium.www.appone.utils;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.aadira.library.Enums.SigningMethod;
import com.aadira.library.Main.WCBuilder;
import com.aadira.library.Main.WooCommerce;

/**
 * Created by Asit on 26-12-2017.
 */

public class BaseApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        WCBuilder builder = new WCBuilder();
        builder.setIsHttps(true);
        builder.setBaseUrl("www.themarinade.in");
        builder.setSigning_method(SigningMethod.HMACSHA1);
        builder.setWc_key("ck_371b5c8afcf334fc203f5b51d88de29f7dd299c6");
        builder.setWc_secret("cs_0442f5b69fbff29b069545640a5f5880178acd23");
        WooCommerce.getInstance().initialize(builder);


        System.out.println(WooCommerce.getInstance().toString());
    }
}
