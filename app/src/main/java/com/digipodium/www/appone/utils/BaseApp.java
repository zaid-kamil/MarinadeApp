package com.digipodium.www.appone.utils;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.aadira.library.Enums.SigningMethod;
import com.aadira.library.Interfaces.ListCallback;
import com.aadira.library.Main.WCBuilder;
import com.aadira.library.Main.WooCommerce;
import com.digipodium.www.appone.R;

import java.util.List;

import retrofit.RetrofitError;

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
        builder.setWc_key(getString(R.string.client_key));
        builder.setWc_secret(getString(R.string.client_secret));
        WooCommerce.getInstance().initialize(builder);


    }
}
