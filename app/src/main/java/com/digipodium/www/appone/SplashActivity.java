package com.digipodium.www.appone;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        Thread mythread =new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    if(isOnline()){
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.csale),
                                "No Internet Connection, Please Check Your Settings.", Snackbar.LENGTH_LONG)
                                .setAction("SETTINGS", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                        startActivityForResult(intent,0);
                                    }
                                });
                        mySnackbar.show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        mythread.start();

    }

    private boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
