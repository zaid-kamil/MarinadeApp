package com.digipodium.www.appone;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.aadira.library.Enums.SigningMethod;
import com.aadira.library.Main.WCBuilder;
import com.aadira.library.Main.WooCommerce;

/**
 * Created by Asit on 26-12-2017.
 */

/* The base activity will implement everyother activity*/
public class BaseActivity extends AppCompatActivity {

    public static final String LOG_TAG = "themarinade";
    public static final String BASE_PACKAGE = "com.digipodium.www.appone";
    public static final String WEBSITE = "http://www.themarinade.in";

    private ProgressDialog dialog;

    public void showProgress() {
        dialog = new ProgressDialog(getBaseContext());
        dialog.setMessage("Loading...");
        dialog.setIcon(R.drawable.ic_launcher_foreground);
    }

    public void updateDialogMessage(String message) {
        if (dialog != null && dialog.isShowing()) {
            dialog.setMessage(message);
        }
    }

    public void hideProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
