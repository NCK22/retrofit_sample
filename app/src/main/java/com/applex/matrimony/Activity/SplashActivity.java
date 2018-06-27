package com.applex.matrimony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

public class SplashActivity extends AppCompatActivity {

    SPCustProfile spCustProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        spCustProfile=new SPCustProfile(this);
        Thread splash= new Thread(){
            public void run(){
                try{
                    Log.e("Thread","started");
                    sleep(3000);

                    if(spCustProfile.getIsLogin().equalsIgnoreCase("true"))
                            startActivity(new Intent(SplashActivity.this,TabViewParentActivity.class));
                                    else
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }

        };
        splash.start();


    }
}
